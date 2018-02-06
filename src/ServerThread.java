import java.net.*;
import java.io.*;

public class ServerThread extends Thread{
    private Socket socket;
    private int chatNumber;
    private String name;
    private PrintWriter pw;
    private BufferedReader br;
    private static ServerThread[] chatConnections = new ServerThread[10];

    public ServerThread(Socket socket, String name, int number){
        super("localhost");
        this.socket = socket;
        this.name = name;
        chatNumber = number;
        chatConnections[number] = this;
    }

    public void run(){
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
            chatConnections[chatNumber] = null;

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    private void distributeMessage(String message, String nameOfSender){
        for(int x = 0; x < 10; x++){
            if(chatConnections[x] != null) {
                System.out.println("Sending message to " + chatConnections[x].name);
                chatConnections[x].getPw().println(nameOfSender + ": " + message);
            }
        }
    }
    private void distributeMessage(String message){
        for(int x = 0; x < 10; x++){
            if (chatConnections[x] != null) {
                chatConnections[x].getPw().println(message);
            }
        }
    }

    private PrintWriter getPw(){
        return this.pw;
    }

    public ServerThread[] getServerSlots(){
        return chatConnections;
    }
}
