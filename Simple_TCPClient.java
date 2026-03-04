import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Simple_TCPClient {

    public static void main(String[] args) {

        String serverIP = "192.168.1.10";
        int port = 5000;

        String clientID = "PC1";
        String targetID = "ESP2";

        try (Socket socket = new Socket(serverIP, port);
             Scanner scanner = new Scanner(System.in)) {

            BufferedReader input =
                    new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter output =
                    new PrintWriter(socket.getOutputStream(), true);

            output.println(clientID); 

            new Thread(() -> {
                try {
                    String msg;
                    while ((msg = input.readLine()) != null) {
                        System.out.println("Received: " + msg);
                    }
                } catch (IOException ignored) {}
            }).start();

            while (true) {
                String message = scanner.nextLine();
                output.println("TO:" + targetID + "|" + message);
            }

        } catch (IOException e) {
            System.out.println("Connection Error");
        }
    }
}