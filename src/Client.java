
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;
import java.net.*;
public class Client implements Runnable{
    final int PORT = 55555;
    private Scanner scan;
    private String name;
    private ClientGui gui;
    private Socket socket;
    private PrintWriter pw;
    private BufferedReader br;
    private static Client client;

    private void connect(int port) {
        try {
            socket = new Socket( InetAddress.getByName("localhost"),port);
            scan = new Scanner(System.in);

            gui = new ClientGui();

            pw = new PrintWriter(socket.getOutputStream(), true);
            br = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            System.out.println("Print whatever you want");

            Thread chatThread = new Thread(client);
            chatThread.start();

            while(true){
                pw.println(scan.nextLine());
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void run(){
        try {
            while (true) {
                System.out.println(br.readLine());
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public static void main(String args[]) {
        client = new Client();
        client.connect(55555);
    }
}
