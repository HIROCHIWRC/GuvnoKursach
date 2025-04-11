package com.network;

import com.controllers.*;
import com.enums.Operation;
import com.exceptions.ResponseException;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientThread {
    private final Socket clientSocket;

    private final UserController userController;
    private final DTPController dtpController;

    public ClientThread(Socket socket) {
        this.clientSocket = socket;
        userController = new UserController();
        dtpController = new DTPController();
    }

    public void run() {
        try (
                ObjectInputStream input = new ObjectInputStream(clientSocket.getInputStream());
                ObjectOutputStream output = new ObjectOutputStream(clientSocket.getOutputStream())
        ) {
            boolean keepRunning = true;

            while (keepRunning) {
                try {
                    Request request = (Request) input.readObject();

                    if (request != null) {
                        Response response = processRequest(request);
                        if (request.getOperation() == Operation.DISCONNECT) {
                            keepRunning = false;
                        }

                        output.writeObject(response);
                        output.flush();
                    } else {
                        Response errorResponse = new Response(false, "Received invalid object", null);
                        output.writeObject(errorResponse);
                        output.flush();
                    }
                } catch (IOException e) {
                    System.err.println("Connection error: " + e.getMessage());
                    keepRunning = false;
                } catch (ClassNotFoundException e) {
                    System.err.println("Class not found: " + e.getMessage());
                    keepRunning = false;
                } catch (Exception e) {
                    e.printStackTrace();
                    keepRunning = false;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            closeConnection();
        }
    }

    private Response processRequest(Request request) {
        try {
            return switch (request.getOperation()) {
                // Book CRUD
                case CREATE_DTP -> dtpController.createDTP(request);
                case READ_DTP_DATA -> dtpController.getDTPByTitle(request);
                case UPDATE_DTP -> dtpController.updateDTP(request);
                case DELETE_DTP -> dtpController.deleteDTP(request);

                // User CRUD
                case READ_USER -> userController.readEntity(request);
                case DELETE_USER -> userController.deleteUser(request);
                case UPDATE_USER -> userController.updateEntity(request);

                // SYSTEM
                case LOGIN -> userController.login(request);
                case REGISTER -> userController.register(request);
                case DISCONNECT -> new Response(true, "Disconnected successfully", null);
                default -> new Response(false, "Received unknown operation", null);
            };
        } catch (ResponseException e) {
            return new Response(false, e.getMessage(), null);
        } catch (Exception e) {
            e.printStackTrace();

            return new Response(false, "Something went wrong on the server side!", null);
        }
    }

    private void closeConnection() {
        try {
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
