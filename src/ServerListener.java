import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerListener {

    public ServerListener(int port) {
        while (true) {
            try (ServerSocket serverSocket = new ServerSocket(port)) {
                final Socket socketToClient = serverSocket.accept();
                MultiUserServer clientHandler = new MultiUserServer(socketToClient);
                clientHandler.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
