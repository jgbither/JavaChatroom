import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;
import java.net.*;
public class Client implements Runnable{
    private int port;
    private Scanner scan;
    private String name;
    private ClientGui gui;
    private String ipAddress;
    private Socket socket;
    private PrintWriter pw;
    private BufferedReader br;
    private static Client client;

    private void connect(int port) {
        try {
            gui = new ClientGui(this);
            boolean flag = gui.CanRetrieveInput();
            while(!flag) {
                flag = gui.CanRetrieveInput();
                Thread.sleep(500);
            }

            System.out.println("DONE");
            name = gui.getName();
            ipAddress = gui.getIpAddress();
            port = gui.getPort();

            System.out.println("Client IP is " + ipAddress);

            socket = new Socket(ipAddress, port);
            scan = new Scanner(System.in);

            pw = new PrintWriter(socket.getOutputStream(), true);
            br = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            System.out.println("Sending name: " + name);
            pw.println(name);

            System.out.println("Print whatever you want");

            Thread chatThread = new Thread(client);
            chatThread.start();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void run(){
        try {
            while (true) {
                String tempHolder = br.readLine();
                System.out.println(tempHolder);
                gui.printMessage(tempHolder);
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void sendMessage(String message){
        pw.println(message);
    }

    public static void main(String args[]) {
        client = new Client();
        client.connect(55555);
    }
}
