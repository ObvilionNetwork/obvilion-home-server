import sun.audio.AudioDevice;

import javax.sound.sampled.*;
import java.io.*;
import java.net.URL;
import java.net.URLEncoder;

public class PlaySound {
    public static void main(String[] args) {

        try {
            PipedInputStream in = new PipedInputStream();
            PipedOutputStream out = new PipedOutputStream(in);

            AudioDevice device = AudioDevice.device;

            new Thread(() -> {
                try (BufferedInputStream inn = new BufferedInputStream(new URL("http://localhost:8080/?text="
                        + URLEncoder.encode("Скороговор скороговорил, выскороговаривал, что все скороговорки перевыскороговорит, но, заскороговорившись, выскороговаривал, что всех скороговорок не перескороговоришь, не перевыскороговоришь.", "UTF-8")).openStream())) {
                    byte[] dataBuffer = new byte[1024];
                    int bytesRead;
                    while ((bytesRead = inn.read(dataBuffer, 0, 1024)) != -1) {
                        out.write(dataBuffer, 0, bytesRead);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();

//            new Thread(() -> {
//                File f = new File("./f.wav");
//                try {
//                    FileInputStream fos = new FileInputStream(f);
//
//                    while (true) {
//                        try {
//                            out.write(fos.read());
//                            out.flush();
//                        } catch (IOException e) {
//                            device.close();
//                        }
//                    }
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }).start();

            device.openChannel(in);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    private static AudioFormat getAudioFormat(){
        float sampleRate = 8000.0F;
        int sampleSizeInBits = 16;
        int channels = 1;
        boolean signed = true;
        boolean bigEndian = false;

        return new AudioFormat(
                sampleRate,
                sampleSizeInBits,
                channels,
                signed,
                bigEndian
        );
    }
}
