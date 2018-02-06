import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;
import java.net.*;

public class Server{
    private static ServerThread[] chatConnections = new ServerThread[10];
    private static String[] names = {"Bill", "James", "John", "Wes", "Josh", "Andrew", "Aaron", "Adave", "Joey", "Caleb"};

    public static void main(String args[]) {
        final int PORT = 55555;
        boolean running = true;
        int emptySlot = 0;

        try (ServerSocket serverSocket = new ServerSocket(55555,0 ,InetAddress.getByName("localhost"))){

            System.out.println("Searching for connection...");
            while(running){
                Socket socket = serverSocket.accept();

                System.out.println("Found Connection!");

                for (int x = 0; x < 10; x++){
                    if (chatConnections[x] != null){
                        chatConnections = chatConnections[x].getServerSlots();
                    } //Might need to do an else if here
                }

               /* if(chatConnections ==  null) {
                    System.out.println("Starting new serverThread");
                    chatConnections = new ServerThread[10];
                }else{
                    System.out.println("Retrieving serverThread");
                    chatConnections = chatConnections[0].getServerSlots();
                }*/

                for(int x = 0; x < 10; x++){
                    if(chatConnections[x] == null){
                        System.out.println("Found an empty spot in slot: " + x);
                        emptySlot = x;
                        break;
                    } else if(x == 9){
                        System.out.println("TOO MANY USERS LOL");
                    }
                }
                chatConnections[emptySlot] = new ServerThread(socket, names[emptySlot], emptySlot);
                chatConnections[emptySlot].start();
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
