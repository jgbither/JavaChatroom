import javax.swing.*;
import java.awt.*;

public class ClientGui extends JFrame{
    public ClientGui(){
        initGui();
    }

    private void initGui(){
        setTitle("Chatroom");
        setSize(600,400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JTextField textField = new JTextField();
        JTextArea  textArea  = new JTextArea();

        textArea.setColumns(60);
        textArea.setRows(30);

        //label.add(textField);
        //label.add(textArea);
        //this.add(label);

        add(textArea, BorderLayout.NORTH);
        add(textField, BorderLayout.SOUTH);

        this.setVisible(true);
    }
}
