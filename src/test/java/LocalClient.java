import java.io.IOException;
import java.net.Socket;

public class LocalClient {
    public static Socket s;

    public static void main(String[] args) throws IOException, InterruptedException {
        s = new Socket("192.168.100.22", 9922);

        s.getOutputStream().write(new byte[] {(byte) 222, (byte) 111, (byte) 222, (byte) 4, (byte) 4 });

        System.err.println("writed");

//        s.getOutputStream().write(new byte[] {(byte) 222, (byte) 111, (byte) 222, (byte) 4, (byte) 4 });
//
//        System.err.println("writed");

//        // First packet bytes
//        write(222, 111, 222);
//
//        // Packet type
//        write(4);
//
//        write(2);

        while (true) {
            Thread.sleep(1);
        }
    }

    public static void write(int ...bytes) throws IOException {
        for (int b : bytes) {
            s.getOutputStream().write(b);

            System.err.println("writed");
        }
    }
}
