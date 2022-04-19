import java.io.IOException;
import java.net.Socket;

public class Client {
    public static Socket s;

    public static void main(String[] args) throws IOException, InterruptedException {
        s = new Socket("localhost", 1010);

        // First packet bytes
        write(222, 111, 222);

        // Packet size
        write(0, 7);
        // Packet type
        write(0, 129);

        // Device name length
        write(4);
        // Device name
        write('t', 'e', 's', 't');
        // Device version
        write(0, 1);

        while (true) {
            Thread.sleep(1);
        }
    }

    public static void write(int ...bytes) throws IOException {
        for (int b : bytes) {
            s.getOutputStream().write(b);
        }
    }
}
