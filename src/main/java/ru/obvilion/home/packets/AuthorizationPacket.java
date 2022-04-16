package ru.obvilion.home.packets;

import ru.obvilion.home.devices.Device;
import ru.obvilion.home.devices.DeviceVersions;
import ru.obvilion.home.devices.UnauthorizedDevice;

import java.io.DataInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class AuthorizationPacket implements PacketHandler {
    @Override
    public void handle(Device device, DataInputStream is, int length) throws IOException {
        if (!(device instanceof UnauthorizedDevice)) {
            return;
        }

        byte auth_method = DeviceVersions.getAuthMethod((UnauthorizedDevice) device);

        if (auth_method == 0) {
            byte[] token_bytes = new byte[40];
            is.read(token_bytes);

            device.token = new String(token_bytes, StandardCharsets.US_ASCII);
        } else {
            System.err.println("Error during authorization " + device);
        }
    }
}
