package ru.obvilion.home;

import ru.obvilion.home.socket.ClientConnection;
import ru.obvilion.home.socket.QueueManager;
import ru.obvilion.home.socket.Worker;

import java.io.IOException;
import java.net.ServerSocket;

public class LaunchWrapper {
    public static void main(String[] args) {
        try {
            ServerSocket server = new ServerSocket(1010);

            QueueManager queue = new QueueManager(2, 8);

            while (true) {
                queue.addConnection(new ClientConnection(server.accept()));
                System.out.println("Client connected");
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("ok");
    }
}
