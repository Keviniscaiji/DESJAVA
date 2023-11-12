package Application;


import UIs.VerticalFlowLayout;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import javax.swing.*;


public class Sender {
    static Receiver rc;
    static Key_generator_UI kgu;
    static JFileChooser chooser;
    static String fileName = null;
    static String newFileName = null;
    static String filePath = null;
    Sender() {
//    public static void main(String[] args) {

        rc = new Receiver();
        kgu = new Key_generator_UI();
        JFrame frame = new JFrame("Send Message");
        frame.setSize(350, 450);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        JPanel panel = new JPanel();


        JTabbedPane jTabbedPane = new JTabbedPane(JTabbedPane.TOP);

        JLabel[] jLabels = new JLabel[2];

        jLabels[0] = new JLabel();
        placeComponents(jLabels[0]);
        jTabbedPane.add("Send message", jLabels[0]);

        jLabels[1] = new JLabel();
        placeComponents2(jLabels[1]);
        jTabbedPane.add("Send File", jLabels[1]);

        frame.add(jTabbedPane);
//            placeComponents(panel);
        frame.setVisible(true);
    }

    private static void placeComponents(JLabel panel) {

        panel.setLayout(new VerticalFlowLayout());
        JLabel messageLabel = new JLabel("Message:");
        panel.add(messageLabel);
        TextArea messageText = new TextArea(5, 40);
        panel.add(messageText);

        JLabel keyLabel = new JLabel("Key:");
        panel.add(keyLabel);
        TextArea keyText = new TextArea(1, 40);
        keyText.setEditable(false);
        panel.add(keyText);

        JLabel dataLabel = new JLabel("Data:");
        panel.add(dataLabel);
        TextArea dataText = new TextArea(5, 40);
        dataText.setEditable(false);
        panel.add(dataText);

        JButton loginButton = new JButton("Send Message");
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Key_generator.editEncryptedData(messageText.getText());
                    Key_generator.saveDesKey();
                    kgu.keyText.setText(Key_generator.getKey());
                    keyText.setText(Key_generator.getKey());
                    Key_generator.encrypt("Sender/plainText.txt", "Third_party/encrypt.txt");
                    dataText.setText(Key_generator.getEncryptData());
                    Key_generator.decrypt("Third_party/encrypt.txt", "Receiver/decrypt.txt");
                    rc.getDecryptedData(rc.dataText);
                    rc.keyText.setText(Key_generator.getKey());
                    rc.messageText.setText(Key_generator.getEncryptData());
                } catch (IOException ex) {
                    ex.printStackTrace();
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        });
        panel.add(loginButton);
    }

    private static void placeComponents2(JLabel panel) {
        chooser = new JFileChooser("/Users/gongkaiwen/Desktop/大学/大四上/安全/assignment/DES/Sender"); //初始化文件选择器

        panel.setLayout(new VerticalFlowLayout());
        JLabel messageLabel = new JLabel("FileInfo:");
        panel.add(messageLabel);
        TextArea messageText = new TextArea(15, 40);
        messageText.setEditable(false);
        panel.add(messageText);
        JButton actionButton = new JButton("Select File");
        actionButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                int state; //文件选择器返回状态
                chooser.removeChoosableFileFilter(chooser.getAcceptAllFileFilter()); //移去所有文件过滤器
                state = chooser.showOpenDialog(null); //显示打开文件对话框
                File file = chooser.getSelectedFile(); //得到选择的文件

                filePath = file.getPath();
                messageText.setText("Path: "+filePath);
                fileName = file.getName();
                newFileName ="(Encrypted)"+fileName;
                messageText.append("\nFIle name: "+file.getName());

            }
        });
        panel.add(actionButton);
        JButton actionButton2 = new JButton("Send File");
        actionButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (messageText.getText().length()==0){
                    JOptionPane.showMessageDialog(null, "Please select a file first"); //显示提示信息
                }else{
                    String key;
                    key = Key_generator.getKey();
                    kgu.keyText.setText(key);
                    Locker locker = new Locker(key);
                    try {
                        locker.encrypt(filePath,"Receiver/"+newFileName);
                        locker.decrypt("Receiver/"+newFileName,"Receiver/"+fileName);
                        messageText.append("\nSend File Succeed");

                        rc.messageText.setText("Receive new File");
                        rc.keyText.setText(key);
                        rc.dataText.setText("Decrypted file generated");
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
        panel.add(actionButton2);


    }
}


