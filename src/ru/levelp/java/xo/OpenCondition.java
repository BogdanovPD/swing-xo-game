package ru.levelp.java.xo;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class OpenCondition implements ActionListener, Serializable {

    GUI gui;

    public OpenCondition(GUI gui) {
        this.gui = gui;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        deserialize();
    }
    
    public void deserialize(){
        new SaveCurrentCondition(gui.frame).serialize();

        JFrame frame = new JFrame("");
        frame.setBounds(100, 100, 200, 100);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JFileChooser openFile = new JFileChooser();
        int openOption = openFile.showOpenDialog(frame);
        try {
            FileInputStream fis = new FileInputStream(openFile.getSelectedFile().getPath());
            ObjectInputStream is = new ObjectInputStream(fis);

            Object o = is.readObject();

            gui.frame.frameStyle = ((MyFrame)o).frameStyle.equals("blue") ? "red" : "blue";
            new SwitchGUI_Listener(gui).refresh();

            is.close();
            fis.close();
        }
        catch (IOException e){
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
