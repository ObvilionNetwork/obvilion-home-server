package ru.obvilion.home;

import ru.obvilion.home.socket.ClientConnection;
import ru.obvilion.home.socket.Worker;

import java.io.IOException;
import java.net.ServerSocket;

public class LaunchWrapper {
    public static void main(String[] args) {
        try {
            ServerSocket server = new ServerSocket(1010);

            Worker worker = new Worker();
            worker.start();

            ClientConnection c = new ClientConnection(server.accept());
            worker.client_sockets.add(c);

            System.out.println("connected");
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("ok");
    }
}
