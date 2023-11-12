package Application;


import UIs.VerticalFlowLayout;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;



public class Receiver {
    TextArea messageText;
    TextArea dataText;
    TextArea keyText;
    Receiver(){
        messageText = new TextArea( 5, 40);
        keyText = new TextArea( 1, 40);
        dataText = new TextArea( 5, 40);
        JFrame frame = new JFrame("Receive Message");
        frame.setSize(350, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        frame.add(panel);

        placeComponents(panel,messageText,keyText,dataText);
        frame.setVisible(true);
    }

    private static void placeComponents(JPanel panel,TextArea messageText,TextArea keyText,TextArea dataText) {

        panel.setLayout(new VerticalFlowLayout());
        JLabel messageLabel = new JLabel("Data:");

        panel.add(messageLabel);

        messageText.setEditable(false);
        panel.add(messageText);

        JLabel keyLabel = new JLabel("Key:");
        panel.add(keyLabel);

        keyText.setEditable(false);
        panel.add(keyText);

        JLabel dataLabel = new JLabel("Plain text:");
        panel.add(dataLabel);

        dataText.setEditable(false);
        panel.add(dataText);

    }
    public void getDecryptedData(TextArea messageText) throws IOException {
        String fileName = "Receiver/decrypt.txt";
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        String result =  br.readLine();
        messageText.setText(result);
    }
}


