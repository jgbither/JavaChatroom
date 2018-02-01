import java.net.*;
import java.io.*;

public class ServerThread extends Thread{
    private Socket socket;
    private int chatNumber;
    private String name;
    String[] names = {"Bill", "James", "John", "Wes", "Josh", "Andrew", "Aaron", "Adave", "Joey", "Caleb"};
    private PrintWriter pw;
    private BufferedReader br;
    private static int totalUsers;
    private static ServerThread[] chatConnections = new ServerThread[10];

    public ServerThread(Socket socket, String name, int number){
        super("localhost");
        this.socket = socket;
        this.name = name;
        chatNumber = number;
        chatConnections[number] = this;
        totalUsers = number;
        System.out.println(totalUsers);
        //Have to make it so serverThread will send the clients message to all users!!!
    }

    public void run(){
        System.out.println("Found!");
        System.out.println(name + " has connected!");
        try{
            pw = new PrintWriter(socket.getOutputStream(), true);
            br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String input;
            distributeMessage(name + " has connected!");
            while ((input = br.readLine()) != null){
                System.out.println(name + " is sending a message");
                String nameOfSender = name;
                distributeMessage(input, nameOfSender);
            }
        }catch (SocketException e){
            System.out.println("User " + name + " disconnected!");
            distributeMessage("User " + name + " disconnected!");
        }catch (Exception e){
            System.out.println("LOL IT's UCKED MATE");
        }
    }

    public void distributeMessage(String message, String nameOfSender){
        //pw.println(nameOfSender + ": " + message);
        for(int x = 0; x <= totalUsers; x++){
            System.out.println("Sending message to " + chatConnections[x].name);
            chatConnections[x].getPw().println(nameOfSender + ": " + message);
        }
    }
    public void distributeMessage(String message){
        for(int x = 0; x <= totalUsers; x++){
            chatConnections[x].getPw().println(message);
        }
    }

    public PrintWriter getPw(){
        return this.pw;
    }

}
