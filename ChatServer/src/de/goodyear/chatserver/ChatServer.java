package de.goodyear.chatserver;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class ChatServer {

    private final List<ClientHandler> clients = new Vector<>();

    private synchronized void broadcast(String message) {
        clients.forEach(c->c.send(message));
    }



    private void go() {

        try (ServerSocket serverSocket = new ServerSocket(8080)) {

            while(!serverSocket.isClosed()) {
               new ClientHandler(serverSocket.accept()).start();
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            broadcast("Server will be stopped.");

        }
    }

    private class ClientHandler extends Thread {

        private String nickname = "Nobody";
        private final Socket socket;
        private final BufferedReader in;
        private final PrintWriter out;

        public ClientHandler(final Socket socket) {
            try {
                this.socket = socket;
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                out = new PrintWriter(socket.getOutputStream());
                clients.add(this);
            } catch (IOException e) {
                e.printStackTrace();
                throw new HeadlessException("Upps");
            }
        }

        @Override
        public void run() {
            String message;
            try(socket) {
                send("Hier ist der Chatserver 1.0.0\n\rWelcome\n\r\n\r");
                send("Bitte geben Sie einen Nickname ein:");
                nickname = in.readLine();
                broadcast(nickname + " appears out of nowhere");
                while((message=in.readLine())!= null) {
                    if(".bye".equalsIgnoreCase(message)) break;
                    broadcast(nickname + ": " + message);
                }
                send("Bye Bye");

            } catch (Exception e) {
                // Ok
            } finally {
                clients.remove(this);
                broadcast(nickname + " disappeared in a puff of smoke....");

            }

        }

        private void send(String message) {
            out.println(message);
            out.flush();
        }




    }

    public static void main(String[] args) {
        new ChatServer().go();
    }
}