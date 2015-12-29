import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * Created by hailstone01 on 12/28/15.
 */
public class Client {
    public static void main(String[] args) {
        Socket socket = null;
        InputStream is = null;
        OutputStream os = null;
        String serverIP = "127.0.0.1";
        int port = 10000;
        String[] data = {"First", "Second", "Third"};

        try {
            socket = new Socket(serverIP, port);
            os = socket.getOutputStream();
            is = socket.getInputStream();
            byte[] b = new byte[1024];
            for (int i = 0; i < data.length; i++) {
                os.write(data[i].getBytes());
                int n = is.read(b);
                System.out.println("the feedback from server: " + new String(b, 0, n));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
                os.close();
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
