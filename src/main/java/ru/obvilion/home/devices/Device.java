package ru.obvilion.home.devices;

import ru.obvilion.home.packets.PacketHandler;
import ru.obvilion.home.packets.system.MessagePacket;
import ru.obvilion.home.socket.ClientConnection;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public abstract class Device {
    public static HashMap<Integer, PacketHandler> default_packet_handlers = new HashMap<>();

    static {
        default_packet_handlers.put(130, new MessagePacket());
    }

    public ClientConnection client_connection;
    public String token;
    public int version = -1;

    public Device(ClientConnection connection) {
        client_connection = connection;
    }

    public Device(UnauthorizedDevice device) {
        client_connection = device.client_connection;
        version = device.version;
    }

    public abstract Map<Integer, PacketHandler> getPacketHandlers();


    public void onData(DataInputStream is, int length) throws IOException {
        int type = is.readUnsignedShort();

        PacketHandler handler = getPacketHandlers().get(type);
        if (handler == null) {
            // TODO: error handling
            System.err.println("Handler for " + type + " not found for " + this);
            return;
        }

        if (handler.getMinLength() > length) {
            // TODO: error handling
            System.err.println("Handler " + handler.getClass().getSimpleName() + " min length more than packet size, " +
                    "device: " + this);
            return;
        }

        handler.handle(this, is, length - 2);
    }

    @Override
    public String toString() {
        return "Device[" +
            "ip=" + client_connection.get().getInetAddress() +
            "version=" + version +
        ']';
    }
}
