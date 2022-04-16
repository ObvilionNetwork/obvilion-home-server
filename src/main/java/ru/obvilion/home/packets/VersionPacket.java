package ru.obvilion.home.packets;

import ru.obvilion.home.devices.Device;
import ru.obvilion.home.devices.UnauthorizedDevice;

import java.io.DataInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class VersionPacket implements PacketHandler {

    @Override
    public void handle(Device device, DataInputStream is, int length) throws IOException {
        if (!(device instanceof UnauthorizedDevice)) {
            return;
        }

        int size = is.read();
        byte[] device_bytes = new byte[size];

        is.read(device_bytes);

        ((UnauthorizedDevice) device).device_name = new String(device_bytes, StandardCharsets.US_ASCII);
        device.version = is.readUnsignedShort();
    }

    @Override
    public int getMinLength() {
        return 4;
    }
}
