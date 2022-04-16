package ru.obvilion.home.socket;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class QueueManager {
    List<Worker> workers = new ArrayList<>();
    short min_workers, max_workers;

    public QueueManager(short min_workers, short max_workers) {
        this.min_workers = min_workers;
        this.max_workers = max_workers;
    }

    public void addConnection(ClientConnection connection) {
        Worker worker = workers.stream()
                .min(Comparator.comparingInt(w -> w.client_sockets.size()))
                .get(); // Always gets worker object

        worker.addConnection(connection);
    }

    public void addWorker() {

    }
}
