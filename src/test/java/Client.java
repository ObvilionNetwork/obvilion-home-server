import java.io.IOException;
import java.net.Socket;

public class Client {
    public static void main(String[] args) throws IOException, InterruptedException {
        Socket s = new Socket("localhost", 1010);

        Thread.sleep(1000);
        byte[] b = new byte[65686];

        s.getOutputStream().write(b);

        while (true) {
            Thread.sleep(1);
        }
    }
}
