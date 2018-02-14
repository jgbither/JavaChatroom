
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClientGui extends JFrame{
    private JTextField  textField;
    private JTextArea   textArea;
    private String      message;
    private Client      client;
    private JTextField  ipAddressField;
    private JButton     acceptButton;
    private String      ipAddress;
    public ClientGui(Client client){
        initGui();
        this.client = client;
    }

    private void initGui(){
        JPanel introPanel = new JPanel();
        JPanel panel = new JPanel();
        ipAddress = "";

        acceptButton = new JButton("Accept");
        ipAddressField = new JTextField();
        ipAddressField.setColumns(12);
        introPanel.add(ipAddressField);
        introPanel.add(acceptButton);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        setTitle("Chatroom");
        setSize(600, 400);
        setResizable(false);
        add(introPanel);
        setVisible(true);

        textField = new JTextField();
        textField.setColumns(50);

        textField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                message = textField.getText();
                client.sendMessage(message);
                textField.setText("");
            }
        });

        textArea = new JTextArea();
        textArea.setLineWrap(true);
        textArea.setVisible(true);

        JScrollPane scroll = new JScrollPane (textArea);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        panel.add(textField);

        acceptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ipAddress = ipAddressField.getText();
                System.out.println(ipAddress);
                getContentPane().removeAll();
                System.out.println(ipAddress);
                add(scroll, BorderLayout.CENTER);
                add(panel, BorderLayout.SOUTH);
                setVisible(true);
            }
        });
    }

    public void printMessage(String message){
        textArea.append(message + "\n");
    }

    public String getIpAddress (){
        while(ipAddress.equals("")){
            System.out.print(ipAddress);
        }
        System.out.println("OSKOFDKf");
        return ipAddress;
    }
}
//172.20.4.181