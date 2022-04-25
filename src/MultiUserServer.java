import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class MultiUserServer extends Thread {

    private Socket clientSocket;
    String inputLine;
    public static List<PrintWriter> writers = new ArrayList<>();

    public MultiUserServer(Socket clientSocket) throws IOException {
        this.clientSocket = clientSocket;
    }

    public void run() {

        try (
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(clientSocket.getInputStream()));) {
            writers.add(out);
            for (PrintWriter printWriter : writers) {
                printWriter.println(clientSocket.getInetAddress() + " connected");
            }
            System.out.println(clientSocket.getInetAddress() + " connected");
            while ((inputLine = in.readLine()) != null) {
                System.out.println(clientSocket.getInetAddress() + " says: " + inputLine);
                for (PrintWriter printWriter : writers) {
                    printWriter.println(inputLine);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
