package ru.obvilion.home.packets.system;

import ru.obvilion.home.devices.Device;
import ru.obvilion.home.packets.PacketHandler;

import java.io.DataInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class MessagePacket implements PacketHandler {

    /**
     * <b>Принимает лог девайса</b>
     *
     * 0 - Кастомный лог [SIZE, char * SIZE]
     * 1 - ???
     */
    @Override
    public void handle(Device device, DataInputStream is, int length) throws IOException {
        int type = is.read();

        if (type == 0) {
            int size = is.read();
            byte[] message_bytes = new byte[size];

            is.read(message_bytes);

            String message = new String(message_bytes, StandardCharsets.US_ASCII);

            // TODO: put message to database
            System.out.println(device + ": " + message);
        }
    }

    @Override
    public int getMinLength() {
        return 1;
    }
}
