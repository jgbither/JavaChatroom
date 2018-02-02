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
        String text;
        boolean running = true;
        int chatUserCount = 0;

        try (ServerSocket serverSocket = new ServerSocket(55555,0 ,InetAddress.getByName("localhost"))){

            System.out.println("Searching for connection...");
            while(running){
                if(chatConnections[0] != null){
                    chatUserCount = chatConnections[0].getTotalUsers();
                }

                //The loop pauses at the serverSocket.accept, meaning that it will not get the correct chatUserCount from above
                chatConnections[chatUserCount] = new ServerThread(serverSocket.accept(), names[chatUserCount], chatUserCount);
                chatConnections[chatUserCount].start();
                //chatConnections[chatUserCount].setTotalUsers(++chatUserCount);
                int temp = chatUserCount;
                chatConnections[temp].setTotalUsers(++chatUserCount);
                if(chatUserCount == 10){
                    System.out.println("Max count of 10 users");
                }
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
