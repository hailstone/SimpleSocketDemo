import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by hailstone01 on 12/28/15.
 */
public class SingleThreadServer {
    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        Socket socket = null;
        OutputStream os = null;
        InputStream is = null;
        int port = 10000;

        try {
            serverSocket = new ServerSocket(port);
            System.out.println("server is started");
            socket = serverSocket.accept();
            is = socket.getInputStream();
            os = socket.getOutputStream();
            byte[] b = new byte[1024];
            for (int i = 0; i < 3; i++) {
                int n = is.read(b);
                System.out.println("the content from server is: " + new String(b, 0, n));
                os.write(b, 0, n);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                os.close();
                is.close();
                socket.close();
                serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
