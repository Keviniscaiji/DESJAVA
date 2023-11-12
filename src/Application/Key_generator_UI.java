package Application;


import UIs.VerticalFlowLayout;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class Key_generator_UI {
    TextArea messageText;
    TextArea dataText;
    TextArea keyText;
    Key_generator_UI(){
        messageText = new TextArea( 5, 40);
        keyText = new TextArea( 4, 40);
        dataText = new TextArea( 5, 40);
        JFrame frame = new JFrame("Key Generator");
        frame.setSize(350, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        frame.add(panel);

        placeComponents(panel,messageText,keyText,dataText);
        frame.setVisible(true);
    }

    private static void placeComponents(JPanel panel,TextArea messageText,TextArea keyText,TextArea dataText) {

        panel.setLayout(new VerticalFlowLayout());


        JLabel keyLabel = new JLabel("Key:");
        panel.add(keyLabel);

        keyText.setEditable(false);
        panel.add(keyText);



    }

}


