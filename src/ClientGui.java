
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;

public class ClientGui extends JFrame{
    private JTextField  textField;
    private JTextArea   textArea;
    private String      message;
    private Client      client;
    private JTextField  ipAddressField;
    private JButton     acceptButton;
    private String      ipAddress;
    private int         port;
    private String      name;
    private JTextField  portField;
    private JScrollPane scroll;
    private JLabel      ipAddressLabel;
    private JLabel      portLabel;
    private JLabel      nameLabel;
    private JTextField  nameField;
    private boolean     canRetrieveInput = false;
    ClientGui(Client client){
        initGui();
        this.client = client;
    }

    private void initGui(){
        JPanel introPanel = new JPanel();
        GridLayout layout = new GridLayout(4,2);
        layout.setHgap(100);
        layout.setVgap(100);
        introPanel.setLayout(layout);
        JPanel panel = new JPanel();
        ipAddress = "";

        portLabel = new JLabel("Port:");
        portLabel.setHorizontalAlignment(JLabel.CENTER);
        portLabel.setVerticalAlignment(JLabel.CENTER);
        portField = new JTextField();
        portField.setHorizontalAlignment(JTextField.CENTER);
        portField.setColumns(12);

        ipAddressLabel = new JLabel("IP Address:");
        ipAddressLabel.setHorizontalAlignment(JLabel.CENTER);
        ipAddressLabel.setVerticalAlignment(JLabel.CENTER);
        ipAddressField = new JTextField();
        ipAddressField.setHorizontalAlignment(JTextField.CENTER);
        ipAddressField.setColumns(12);

        nameField = new JTextField();
        nameLabel = new JLabel("Name: ");
        nameLabel.setHorizontalAlignment(JLabel.CENTER);
        nameLabel.setVerticalAlignment(JLabel.CENTER);
        nameField.setHorizontalAlignment(JTextField.CENTER);
        nameField.setColumns(12);

        acceptButton = new JButton("Accept");
        acceptButton.setVerticalAlignment(JButton.CENTER);
        acceptButton.setHorizontalAlignment(JButton.CENTER);

        introPanel.add(ipAddressLabel);
        introPanel.add(ipAddressField);
        introPanel.add(portLabel);
        introPanel.add(portField);
        introPanel.add(nameLabel);
        introPanel.add(nameField);
        introPanel.add(acceptButton);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        try{
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e){
            System.out.println(e.getMessage());
        }

        setTitle("Chatroom");
        setSize(600, 400);
        setResizable(false);
        add(introPanel);
        setVisible(true);

        textField = new JTextField();
        textField.setColumns(53);

        textField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                message = textField.getText();
                client.sendMessage(message);
                textField.setText("");
                scroll.getVerticalScrollBar().setValue(scroll.getVerticalScrollBar().getMaximum());
            }
        });

        Font font = new Font("Helvetica", Font.PLAIN, 12);
        textArea = new JTextArea();
        textArea.setFont(font);
        textArea.setLineWrap(true);
        textArea.setEditable(false);
        textArea.setVisible(true);
        textField.setFont(font);

        panel.add(textField);

        scroll = new JScrollPane (textArea);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        acceptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ipAddress = ipAddressField.getText();
                port = Integer.parseInt(portField.getText());
                name = nameField.getText();
                canRetrieveInput = true;
                getContentPane().removeAll();
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
        return ipAddress;
    }

    public boolean CanRetrieveInput() {
        return canRetrieveInput;
    }

    public int getPort() {
        return port;
    }

    @Override
    public String getName() {
        return name;
    }
}
//172.20.6.38