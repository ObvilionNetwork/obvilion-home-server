package ru.obvilion.home.devices;

import ru.obvilion.home.packets.AuthorizationPacket;
import ru.obvilion.home.packets.PacketHandler;
import ru.obvilion.home.packets.VersionPacket;
import ru.obvilion.home.socket.ClientConnection;

import java.util.HashMap;
import java.util.Map;

public class UnauthorizedDevice extends Device {
    public static HashMap<Integer, PacketHandler> packet_handlers = new HashMap<>();

    static {
        packet_handlers.put(128, new AuthorizationPacket());
        packet_handlers.put(129, new VersionPacket());
    }

    public String device_name;

    public short[] private_keys = { 1, 2, 3, 4 };
    public short[] public_keys = { 1, 2, 3, 4 };

    public UnauthorizedDevice(ClientConnection connection) {
        super(connection);
    }

    @Override
    public Map<Integer, PacketHandler> getPacketHandlers() {
        return packet_handlers;
    }
}
