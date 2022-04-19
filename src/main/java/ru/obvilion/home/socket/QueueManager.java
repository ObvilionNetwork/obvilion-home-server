package ru.obvilion.home.socket;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class QueueManager {
    List<Worker> workers = new ArrayList<>();
    int min_workers, max_workers;

    public QueueManager(int min_workers, int max_workers) {
        this.min_workers = min_workers;
        this.max_workers = max_workers;

        for (int i = 0; i < min_workers; i++) {
            workers.add(new Worker());
        }
    }

    public void addConnection(ClientConnection connection) {
        Worker worker = workers.stream()
                .min(Comparator.comparingInt(w -> w.client_sockets.size()))
                .get(); // Always gets worker object

        worker.addConnection(connection);
    }

    public void addWorker() {
        int connections = 0;
        for (Worker worker : workers) {
            connections += worker.client_sockets.size();
        }

        int delta = connections / workers.size() - connections / (workers.size() + 1);

        Worker new_worker = new Worker();

        for (int i = 0; i < delta; i++) {
            for (Worker worker : workers) {
                if (worker.client_sockets.size() == 0) {
                    continue;
                }

                new_worker.addConnection(worker.client_sockets.get(0));
            }
        }

        workers.add(new_worker);
    }
}
