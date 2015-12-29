import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by hailstone01 on 12/28/15.
 */
public class MulThreadSocketServer {
    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        Socket socket = null;
        int port = 10000;
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("server is started");
            while (true) {
                socket = serverSocket.accept();
                new LogicThread(socket);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
