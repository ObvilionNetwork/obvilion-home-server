package ru.obvilion.home.packets.bicycle_rgb;

import ru.obvilion.home.devices.Device;
import ru.obvilion.home.packets.PacketHandler;

import java.io.DataInputStream;
import java.io.IOException;

public class BicycleRGBModePacket implements PacketHandler {
    @Override
    public void handle(Device device, DataInputStream is, int length) throws IOException {
        int mode_byte = is.read();

        BicycleRGBMode mode = BicycleRGBMode.valueOf(mode_byte);

        // TODO: mode parsing
    }
}
