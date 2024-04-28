import java.io.IOException;

public class AppStarter {
    public static void main(String[] args) {
        // 启动Server
        try {
            Server server = new Server();
            Thread serverThread = new Thread(server);
            serverThread.start();
            System.out.println("Server started.");
        } catch (IOException e) {
            System.err.println("Error starting server: " + e.getMessage());
            e.printStackTrace();
        }

        // 启动Client GUI
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    new ClientGUI();
                    System.out.println("Client GUI started.");
                } catch (Exception e) {
                    System.err.println("Error starting client GUI: " + e.getMessage());
                    e.printStackTrace();
                }
            }
        });
    }
}

