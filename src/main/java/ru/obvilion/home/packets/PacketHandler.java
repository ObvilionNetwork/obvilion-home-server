package ru.obvilion.home.packets;

import ru.obvilion.home.devices.Device;

import java.io.DataInputStream;
import java.io.IOException;

public interface PacketHandler {
    void handle(Device device, DataInputStream is, int length) throws IOException;

    default int getMinLength() {
        return 0;
    }
}
