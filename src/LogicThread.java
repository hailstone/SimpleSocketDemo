import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * Created by hailstone01 on 12/28/15.
 */
public class LogicThread extends Thread {
    Socket socket;
    InputStream is;
    OutputStream os;

    public LogicThread(Socket socket) {
        this.socket = socket;
        start();
    }

    @Override
    public void run() {
        byte[] b = new byte[1024];
        try {
            os = socket.getOutputStream();
            is = socket.getInputStream();
            for (int i = 0; i < 3; i++) {
                int n = is.read(b);
                if (n == -1) {
                    System.out.println("no data from client anymore, continue...");
                    continue;
                }
                byte[] response = logic(b, 0, n);
                os.write(response);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            close();
        }
    }

    private void close() {
        try {
            os.close();
            is.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private byte[] logic(byte[] b, int off, int len) {
        byte[] response = new byte[len];
        System.arraycopy(b, 0, response, 0, len);
        return response;
    }
}
