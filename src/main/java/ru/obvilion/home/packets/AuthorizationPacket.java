package ru.obvilion.home.packets;

import ru.obvilion.home.devices.Device;
import ru.obvilion.home.devices.UnauthorizedDevice;

import java.io.DataInputStream;

public class AuthorizationPacket implements PacketHandler {
    @Override
    public void handle(Device device, DataInputStream is, int length) {
        if (!(device instanceof UnauthorizedDevice)) {
            return;
        }

        // TODO: decrypt data
    }
}
