package GUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.*;

import Profile.FamilyProfile;
import Profile.PetProfile;
import Profile.Profile;

public class ListProfilesGUI extends JFrame {
    private JScrollPane scrollPane;
    private JPanel listPane;
    private ArrayList<Profile> profiles;
    private Profile profileToBeMatched = null;
    public void createNewListProfilesGUI(){      
        for(Profile profile: profiles){
            addJButtonToFrame( profile, 15);
        }
        
    }
    /** General method for adding buttons
     * @param profile the profile to be added
     * @param fontSize the size of the font
     * @return
     */
    public JButton addJButtonToFrame(Profile profile, int fontSize){
        // add the edit button
        AbstractAction action = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(profileToBeMatched == null){
                    if(profile instanceof PetProfile){
                        new PetProfileGUI((PetProfile) profile);
                    }else if(profile instanceof FamilyProfile){
                        new FamilyProfileGUI((FamilyProfile) profile);
                    } 
                }else {
                    if(profile instanceof PetProfile){
                        new AdoptCompletionGUI((FamilyProfile) profileToBeMatched, (PetProfile) profile);
                    }else if(profile instanceof FamilyProfile){
                        new AdoptCompletionGUI((FamilyProfile) profile, (PetProfile) profileToBeMatched);
                    } 
                }
                
            }
        };
        JButton button = new JButton();
        button.setAction(action);
        button.setText(profile.getName());
        Image img = null;
        String photoPath = profile.getPhoto();
        //String photoPath = "src/GUI/GUI_Images/dog.jpg";
        try {
            img = ImageIO.read(new File(photoPath));
        } catch (Exception e1) {
            // TODO Auto-generated catch block
            //e1.printStackTrace();
        }
        if(img!=null){
            ImageIcon image = new ImageIcon(img);
            image = resizeImageIcon(image, 30, 30);
            button.setIcon(image);
        }
        button.setFont(new Font("The Girl Next Door", Font.PLAIN, fontSize));
        listPane.add(button);
        return button;
    }
    /** Constructor for making list of profiles GUI
     * @param profiles list of profiles
     */
    public ListProfilesGUI(ArrayList<?> profiles){
        this.profiles = (ArrayList<Profile>) profiles;
        this.listPane = new JPanel();
        this.scrollPane =  new JScrollPane(this.listPane);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);  
        listPane.setLayout(new BoxLayout(listPane, BoxLayout.PAGE_AXIS));
        add(this.scrollPane);
        createNewListProfilesGUI();
        setSize(200, 100);
        setLocationRelativeTo(null);
        pack();
        setVisible(true);
    }
    /** Constructor for opening list of profiles for matching
     * @param profiles list of matching profiles
     * @param profileToBeMatched profile that is being matched
     */
    public ListProfilesGUI(ArrayList<?> profiles, Profile profileToBeMatched){
        this.profileToBeMatched = profileToBeMatched;
        this.profiles = (ArrayList<Profile>) profiles;
        this.listPane = new JPanel();
        this.scrollPane =  new JScrollPane(this.listPane);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);  
        listPane.setLayout(new BoxLayout(listPane, BoxLayout.PAGE_AXIS));
        add(this.scrollPane);
        createNewListProfilesGUI();
        setSize(200, 100);
        setLocationRelativeTo(null);
        pack();
        setVisible(true);
    }

    /**
     * Resizes an image icon to a given width and height
     * @param image ImageIcon to resize
     * @param width in pixels to resize it too
     * @param height in pixels to resize it too
     * @return the ImageIcon that was taken in as a parameter
     */
    public ImageIcon resizeImageIcon(ImageIcon image, int width, int height){
        Image imageIconToImage = image.getImage(); // transform it to an image 
        // Scale the image down
        Image scaledImage = imageIconToImage.getScaledInstance(width, height,  java.awt.Image.SCALE_SMOOTH);  
        image = new ImageIcon(scaledImage);  // transform it back to an ImageIcon
        return image;
    }
}
