package GUI;

import javax.swing.JFrame;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;	
import javax.swing.JFileChooser;
import java.io.File;  


public class PhotoChooserGUI extends JFrame{
    JLabel emptyLabel;
    JFrame frame;
    JFileChooser fileChooser; 
    String chosenPhoto;

    public PhotoChooserGUI(){
        fileChooser = new JFileChooser();
        int returnVal = fileChooser.showOpenDialog(null);
        if(returnVal == JFileChooser.APPROVE_OPTION) {
            this.setChosenPhoto(fileChooser.getSelectedFile().getAbsolutePath());    
         }
    }
    
    public String getChosenPhoto(){
        return this.chosenPhoto;
    }
    
    public void setChosenPhoto(String chosenPhoto){
        this.chosenPhoto = chosenPhoto;
    }
}
