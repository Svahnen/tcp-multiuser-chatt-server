public class Main {
    static int port;

    public static void main(String[] args) {
        try {
            port = Integer.parseInt(args[0]);
        } catch (Exception e) {
            System.out.println("Usage: java Main <port>");
            System.exit(1);
        }
        System.out.println("Server started on port " + port);
        ServerListener serverListener = new ServerListener(port);
    }
}