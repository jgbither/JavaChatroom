import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;
import java.net.*;

public class Server{
    private static ServerThread[] chatConnections = new ServerThread[10];

    public static void main(String args[]) {
        final int PORT = 55555;
        boolean running = true;
        boolean serverFull = false;
        int emptySlot = 0;

        try (ServerSocket serverSocket = new ServerSocket(55555,0 )){

            System.out.println("Searching for connection...");
            while(running){
                Socket socket = serverSocket.accept();

                System.out.println("Found Connection!");

                for (int x = 0; x < 10; x++){
                    if (chatConnections[x] != null){
                        chatConnections = chatConnections[x].getServerSlots();
                    } //Might need to do an else if here
                }

                for(int x = 0; x < 10; x++){
                    if(chatConnections[x] == null){
                        System.out.println("Found an empty spot in slot: " + x);
                        emptySlot = x;
                        serverFull = false;
                        break;
                    } else if(x == 9){
                        System.out.println("TOO MANY USERS LOL");
                        serverFull = true;
                    }
                }
                if(!serverFull) {
                    chatConnections[emptySlot] = new ServerThread(socket, emptySlot);
                    chatConnections[emptySlot].start();
                }
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
