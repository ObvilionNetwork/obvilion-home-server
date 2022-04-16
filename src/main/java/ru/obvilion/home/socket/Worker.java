package ru.obvilion.home.socket;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Worker extends Thread {
    public List<ClientConnection> client_sockets = new ArrayList<>();
    public int delta;

    @Override
    public void run() {
        while (true) {
            long before = System.nanoTime();
            for (ClientConnection client : client_sockets) {
                try {
                    if (client.get().getInputStream().available() > 0) {
                        client.onData(client.get().getInputStream());
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            delta = (int) (System.nanoTime() - before);

            // Мимнимум 1 наносекунда, чтобы поспать и не грузить процессор
            if (delta > 999) {
                delta = 999;
            }

            try {
                Thread.sleep(0, 1000 - delta);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
