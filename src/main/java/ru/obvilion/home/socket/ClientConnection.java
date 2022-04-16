package ru.obvilion.home.socket;

import ru.obvilion.home.devices.Device;
import ru.obvilion.home.devices.UnauthorizedDevice;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class ClientConnection {
    public static final int[] delimiting_bytes = { 222, 111, 222 };

    private Worker worker;
    private Socket client_socket;
    private Device device;
    private int wait_data = 0;

    public ClientConnection(Socket socket) {
        client_socket = socket;
        device = new UnauthorizedDevice(this);
    }


    public Worker getWorker() {
        return worker;
    }

    public void setWorker(Worker worker) {
        this.worker = worker;
    }


    /**
     * @return Client socket connection
     */
    public Socket get() {
        return client_socket;
    }


    public void onData(InputStream is) throws IOException {
        byte mode = 0;

        // Проверяем, дошли ли до конца данные пакета
        if (wait_data > 0 && is.available() >= wait_data) {
            device.onData(new DataInputStream(is), wait_data);
            return;
        }

        // Проверяем пакет по схеме:
        // [222, 111, 222, SIZE; SIZE, PACKET_TYPE; PACKET_TYPE, ...]
        //                             ^ Размер пакета начинает считываться отсюда
        if (is.available() < 6) {
            return;
        }

        // Проверка разграничительных первичных трёх байтов
        for (int i = 0; i < is.available(); i++) {

            // Проверка первого байта (222)
            if (mode == 0) {
                if (is.read() == delimiting_bytes[0]) {
                    mode = 1;
                }

                continue;
            }

            // Проверка второго байта (111)
            if (mode == 1) {
                if (is.read() == delimiting_bytes[1]) {
                    mode = 2;
                } else {
                    mode = 0;
                }

                continue;
            }

            // Проверка третьего байта (222)
            if (is.read() == delimiting_bytes[2]) {
                // Читает uint16_t - размер пакета
                int size = (is.read() << 8) + is.read();

                // Ещё не все данные дошли, ставим в очередь
                if (is.available() < size) {
                    wait_data = size;
                    return;
                }

                device.onData(new DataInputStream(is), wait_data);
            } else {
                mode = 0;
            }
        }
    }
}
