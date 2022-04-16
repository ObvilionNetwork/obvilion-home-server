package ru.obvilion.home.devices;

import ru.obvilion.home.packets.PacketHandler;
import ru.obvilion.home.socket.ClientConnection;

import java.util.HashMap;
import java.util.Map;

public class UnauthorizedDevice extends Device {
    public static HashMap<Integer, PacketHandler> packet_handlers = new HashMap<>();

    public UnauthorizedDevice(ClientConnection connection) {
        super(connection);
    }

    @Override
    public Map<Integer, PacketHandler> getPacketHandlers() {
        return packet_handlers;
    }
}