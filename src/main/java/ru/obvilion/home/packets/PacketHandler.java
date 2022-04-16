package ru.obvilion.home.packets;

import java.io.DataInputStream;

public interface PacketHandler {
    void handle(DataInputStream is, int length);

    default int getMinLength() {
        return 0;
    }
}
