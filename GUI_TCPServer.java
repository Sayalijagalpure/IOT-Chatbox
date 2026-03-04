import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.*;
import java.util.*;

public class GUI_TCPServer extends JFrame {

    private JTextArea chatArea;
    private ServerSocket serverSocket;

    private final Map<String, PrintWriter> clients = new HashMap<>();

    public GUI_TCPServer() {

        setTitle("2-Way IoT Chat Server");
        setSize(450, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        chatArea = new JTextArea();
        chatArea.setEditable(false);
        add(new JScrollPane(chatArea));

        setVisible(true);

        startServer();
    }

    private void startServer() {

        new Thread(() -> {
            try {
                serverSocket = new ServerSocket(5000);
                append("Server Started...\n");

                while (true) {
                    Socket socket = serverSocket.accept();
                    new Thread(() -> handleClient(socket)).start();
                }

            } catch (IOException e) {
                append("Server Error\n");
            }
        }).start();
    }

    private void handleClient(Socket socket) {

        try {
            BufferedReader input =
                    new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter output =
                    new PrintWriter(socket.getOutputStream(), true);

            String clientID = input.readLine();
            clients.put(clientID, output);

            append(clientID + " Connected\n");

            String message;

            while ((message = input.readLine()) != null) {

                append("Received: " + message + "\n");

                if (message.startsWith("TO:")) {

                    String[] parts = message.split("\\|");

                    String target = parts[0].replace("TO:", "");
                    String actualMessage = parts[1];

                    PrintWriter targetClient = clients.get(target);

                    if (targetClient != null) {
                        targetClient.println(actualMessage);
                    }
                }
            }

        } catch (IOException e) {
            append("Client Disconnected\n");
        }
    }

    private void append(String text) {
        SwingUtilities.invokeLater(() -> chatArea.append(text));
    }

    public static void main(String[] args) {
        new GUI_TCPServer();
    }
}