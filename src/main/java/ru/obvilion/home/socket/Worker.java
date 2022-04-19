package ru.obvilion.home.socket;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Worker extends Thread {
    public final List<ClientConnection> client_sockets = new ArrayList<>();
    public int delta;

    public Worker() {
        start();
    }

    public void addConnection(ClientConnection con) {
        con.setWorker(this);
        client_sockets.add(con);
    }

    @Override
    public void run() {
        while (true) {
            long before = System.nanoTime();

            for (ClientConnection client : client_sockets) {
                if (client.get().isClosed()) {
                    client_sockets.remove(client);
                    continue;
                }

                try {
                    if (client.get().getInputStream().available() > 0) {
                        client.onData(client.get().getInputStream());
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            // Вычисляем время последней проверки
            delta = (int) (System.nanoTime() - before);

            try {
                // 1 наносекунда сна для предотвращения большой загрузки ЦП
                Thread.sleep(0, (delta > 999) ? (1) : (1000 - delta));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
