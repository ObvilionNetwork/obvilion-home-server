package ru.obvilion.home.devices.types;

import ru.obvilion.home.devices.Device;
import ru.obvilion.home.devices.UnauthorizedDevice;
import ru.obvilion.home.packets.PacketHandler;
import ru.obvilion.home.packets.bicycle_rgb.BicycleRGBModePacket;

import java.util.HashMap;
import java.util.Map;

public class RGBStrip extends Device {
    public static HashMap<Integer, PacketHandler> packet_handlers = new HashMap<>();

    static {
        packet_handlers.put(131, new BicycleRGBModePacket());
        packet_handlers.putAll(default_packet_handlers);
    }

    public RGBStrip(UnauthorizedDevice from) {
        super(from);
    }

    @Override
    public Map<Integer, PacketHandler> getPacketHandlers() {
        return packet_handlers;
    }
}
