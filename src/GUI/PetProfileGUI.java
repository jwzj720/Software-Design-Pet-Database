package GUI;
import javax.swing.*;

import DBAdapter.ClientInterface;
import DBAdapter.DBAdapter;
import Profile.FamilyProfile;
import Profile.Matcher;
import Profile.PetProfile;

import java.awt.*;
import java.awt.event.*;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

public class PetProfileGUI extends JFrame implements ActionListener{
    private GridBagConstraints gridConstraints = new GridBagConstraints();
    private Font CURRENTFONT;
    private JButton editButton;
    private boolean editClicked = false;
    private JTextField[] textFields; 
    private PetProfile petProfile;
    private ClientInterface adapter;
    private String photoPath;
    /** Adds the component to the main menu window */
    public void createPetProfileWindow(){
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        photoPath = petProfile.getPhoto();
        Insets padding = new Insets(0,0,10,0);
        addImageToFrame(padding,0,0,2, photoPath);
        try {
            adapter = new DBAdapter();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        //Insets padding = new Insets(0,0,10,0);
        int gridX = 1;
        int labelFontSize = 15;
        addJLabelToFrame(padding,gridX,0,2,"Name:",labelFontSize);
        addJLabelToFrame(padding,gridX,1,2,"Age:",labelFontSize);
        addJLabelToFrame(padding,gridX,2,2,"Breed:",labelFontSize);
        addJLabelToFrame(padding,gridX,3,2,"Sex:",labelFontSize);
        addJLabelToFrame(padding,gridX,4,2,"Color:",labelFontSize);
        addJLabelToFrame(padding,gridX,5,2,"Size:",labelFontSize);
        padding = new Insets(0,0,10,80); // increasing padding for longer words
        addJLabelToFrame(padding,gridX,6,2,"Energy Level:",labelFontSize);
        addJLabelToFrame(padding,gridX,7,2,"Certifications:",labelFontSize);
        addJLabelToFrame(padding,gridX,8,2,"Has Been Fostered:",labelFontSize);
        addJLabelToFrame(padding,gridX,9,2,"Health Conditions:",labelFontSize);
        addJLabelToFrame(padding,gridX,10,2,"Good with Kids:",labelFontSize);
        addJLabelToFrame(padding,gridX,11,2,"Potty Trained:",labelFontSize);
        addJLabelToFrame(padding,gridX,12,2,"Allergenic",labelFontSize);
        addJLabelToFrame(padding,gridX,13,2,"Good with Pets:",labelFontSize);
        addJLabelToFrame(padding,gridX,14,2,"Notes:",labelFontSize);
        addJLabelToFrame(padding,gridX,15,2,"Species:",labelFontSize);
        addJLabelToFrame(padding,gridX,16,2,"Choose a photo:",labelFontSize);
        
        padding = new Insets(0,0,10,0);
        //addImageToFrame(padding,0,0,2, photoPath);
        
        gridX = 3;
        padding = new Insets(0,-80,10,80);
        JTextField textField1 = addJTextFieldToFrame(padding, gridX, 0, 2, "");
        JTextField textField2 = addJTextFieldToFrame(padding, gridX, 1, 2, "");
        JTextField textField3 = addJTextFieldToFrame(padding, gridX, 2, 2, "");
        JTextField textField4 = addJTextFieldToFrame(padding, gridX, 3, 2, "");
        JTextField textField5 = addJTextFieldToFrame(padding, gridX, 4, 2, "");
        JTextField textField6 = addJTextFieldToFrame(padding, gridX, 5, 2, "");
        JTextField textField7 = addJTextFieldToFrame(padding, gridX, 6, 2, "");
        JTextField textField8 = addJTextFieldToFrame(padding, gridX, 7, 2, "");
        JTextField textField9 = addJTextFieldToFrame(padding, gridX, 8, 2, "");
        JTextField textField10 = addJTextFieldToFrame(padding, gridX, 9, 2, "");
        JTextField textField11 = addJTextFieldToFrame(padding, gridX, 10, 2, "");
        JTextField textField12 = addJTextFieldToFrame(padding, gridX, 11, 2, "");
        JTextField textField13 = addJTextFieldToFrame(padding, gridX, 12, 2, "");
        JTextField textField14 = addJTextFieldToFrame(padding, gridX, 13, 2, "");
        JTextField textField15 = addJTextFieldToFrame(padding, gridX, 14, 2, "");
        JTextField textField16 = addJTextFieldToFrame(padding, gridX, 15, 2, "");

        textFields = new JTextField[]{textField1,textField2,textField3,textField4,textField5,textField6,textField7,
            textField8,textField9,textField10,textField11,textField12,textField13,textField14,textField15,textField16};
        // If a PetProfile has been given, use it to populate textFields 
        setTextFieldFromProfile(textFields,petProfile);

        AbstractAction getPhotoAction = new AbstractAction("photo") {
            @Override
            public void actionPerformed(ActionEvent e){
                PhotoChooserGUI photoChooser = new PhotoChooserGUI();
                photoPath = photoChooser.getChosenPhoto();
                System.out.println(photoPath);
                Insets padding = new Insets(0,0,10,0);
                addImageToFrame(padding,0,0,2, photoPath);
            }
        };

        AbstractAction matchAction = new AbstractAction("match") {
            @Override
            public void actionPerformed(ActionEvent e) {
                Matcher match = new Matcher();
                try {
                    ArrayList<FamilyProfile> matches = (ArrayList) match.petMatcher(petProfile);
                    new ListProfilesGUI(matches, petProfile);
                    Insets padding = new Insets(0,0,10,0);
                    addImageToFrame(padding,0,0,2, photoPath);
                } catch (Exception e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
               
            }
        };
        AbstractAction fosterAction = new AbstractAction("foster") {
            @Override
            public void actionPerformed( ActionEvent e ) {
                try {
                    //new ListProfilesGUI(, "match", 0);
                    new ListProfilesGUI((ArrayList<FamilyProfile>) adapter.queryAllFamilies(), petProfile);
                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }  
            }
        };
        AbstractAction adoptAction = new AbstractAction("adopt") {
            @Override
            public void actionPerformed( ActionEvent e ) {
                try {
                    new ListProfilesGUI((ArrayList<FamilyProfile>) adapter.queryAllFamilies(), petProfile);
                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }  
            }
        };

        addJButtonToFrame(padding, 0, 19, 6, "Match Pet to Family", matchAction, 20);
        addJButtonToFrame(padding, 0, 18, 6, "Get adopted or fostered", adoptAction, 20);
        //addJButtonToFrame(padding, 0, 21, 6, "Foster a Pet", fosterAction, 20);
        addJButtonToFrame(padding, 2, 16, 6, "File", getPhotoAction, 20);

        padding = new Insets(0,200,10,0);
        editButton = addJButtonToFrame(padding, 5, 0, 2, "Edit", null, 15);
        editButton.addActionListener(this);

    }
    /**
     * Adds a JLabel to the Frame
     * @param padding 
     * @param gridX X position on Gridbag
     * @param gridY Y position on GridBag
     * @param gridwidth width of cell on Gridbag
     * @param labelText text to be shown on JLabel
     * @param fontSize size of font used
     */
    public void addJLabelToFrame(Insets padding, int gridX, int gridY, int gridwidth, String labelText,int fontSize ){
        CURRENTFONT = new Font("The Girl Next Door", Font.PLAIN, fontSize);
        gridConstraints.anchor = GridBagConstraints.CENTER;
        gridConstraints.gridx = gridX; 
        gridConstraints.gridy=gridY; 
        gridConstraints.gridwidth=gridwidth;
        gridConstraints.insets = padding;
        JLabel label = new JLabel(labelText);
        label.setFont(CURRENTFONT);
        add(label,gridConstraints);
    }
    /**
     * Adds an ImageIcon to the frame
     * @param padding GridBag paddings to be used
     * @param gridX Grid X position
     * @param gridY Grid Y positions
     * @param gridwidth width of Image on the grid
     * @param pathToImage path from current directory to the image
     */
    public void addImageToFrame(Insets padding, int gridX, int gridY, int gridwidth, String photoPath){
        gridConstraints.anchor = GridBagConstraints.CENTER;
        gridConstraints.gridx = gridX; 
        gridConstraints.gridy = gridY; 
        gridConstraints.gridwidth = gridwidth;
        gridConstraints.insets = padding;
        ImageIcon image = new ImageIcon(photoPath);
        image = resizeImageIcon(image, 100, 100);
        JLabel label = new JLabel(image);
        add(label,gridConstraints);
    }
    /**
     * Adds a JTextField to the frame with given parameters
     * @param padding Grid padding to be used
     * @param gridX Grid X position
     * @param gridY Grid Y position
     * @param gridwidth width of grid cell
     * @param text text of JTextField
     * @return JTextField object created 
     */
    public JTextField addJTextFieldToFrame(Insets padding, int gridX, int gridY, int gridwidth, String text){
        gridConstraints.anchor = GridBagConstraints.CENTER;
        gridConstraints.gridx = gridX; 
        gridConstraints.gridy = gridY; 
        gridConstraints.gridwidth = gridwidth;
        gridConstraints.insets = padding;
        JTextField textField = new JTextField(20);
        textField.setEditable(false);
        add(textField,gridConstraints);
        return textField;
    }
    /**
     * Adds a JButton to the frame with given parameters
     * @param padding Grid padding to be used
     * @param gridX Grid Y position
     * @param gridY Grid Y position
     * @param gridwidth width of grid cell
     * @param text text of JButton
     * @param action action perfomed on JButton click
     * @param fontSize font size for text on button
     * @return JButton object created 
     */
    public JButton addJButtonToFrame(Insets padding, int gridX, int gridY, int gridwidth, 
                                String text, AbstractAction action,int fontSize){
        // add the edit button
        gridConstraints.gridx = gridX; gridConstraints.gridy = gridY;
        gridConstraints.gridwidth = gridwidth;
        JButton button = new JButton(action);
        button.setText(text);
        button.setFont(new Font("The Girl Next Door", Font.PLAIN, fontSize));
        add(button,gridConstraints);
        return button;
    }
    /**
     * Sets all of the GUI's texts fields from the petProfile
     * @param textFields GUI's JTextFields
     * @param petProfile PetProfile being viewed
     */
    public void setTextFieldFromProfile(JTextField[] textFields, PetProfile petProfile){
        if(petProfile == null || petProfile.getName()==null){
            return;
        }else{
            textFields[0].setText(petProfile.getName());
            textFields[1].setText(String.valueOf(petProfile.getAge()));
            textFields[2].setText(petProfile.getBreed());
            textFields[3].setText(petProfile.getSex());
            textFields[4].setText(petProfile.getColor());
            textFields[5].setText(petProfile.getSize());
            textFields[6].setText(petProfile.getEnergyLevel());
            String certifications =  String.join(" ", petProfile.getListCertifications());
            textFields[7].setText(certifications);
            textFields[8].setText(String.valueOf(petProfile.getFosteredBefore()));
            String healthConditions =  String.join(" ", petProfile.getListHealthConditions());
            textFields[9].setText(healthConditions);
            textFields[10].setText(String.valueOf(petProfile.getGoodWithKids()));
            textFields[11].setText(String.valueOf(petProfile.getPottyTrained()));
            textFields[12].setText(String.valueOf(petProfile.getAllergies()));
            textFields[13].setText(String.valueOf(petProfile.getGoodWithPets()));
            textFields[14].setText(petProfile.getNotes());    
            textFields[15].setText(petProfile.getSpecies());       
        }
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

    /** pet profile constructor
     * @param petProfile
     */
    public PetProfileGUI(PetProfile petProfile){
        super();
        this.petProfile = petProfile;
        GridBagLayout layout = new GridBagLayout();
        setLayout(layout);
        createPetProfileWindow();
        setLocationRelativeTo(null);
        pack();
        setSize(900,900);
        setVisible(true);
    }
    /**
     * Reads in the users submitted responses and converts them to a petProfile or alters an already existing profile
     * @param textFields Array of text fields encapsulating all the user responses
     * @param petProfile profile created or edited from textFields
     * @return edited petProfile
     */
    public PetProfile readInTextToProfile(JTextField[] textFields,PetProfile petProfile, String photoPath){
        petProfile.setName(textFields[0].getText());
        petProfile.setAge(Integer.valueOf(textFields[1].getText()));
        petProfile.setBreed(textFields[2].getText());
        petProfile.setSex(textFields[3].getText());
        petProfile.setColor(textFields[4].getText());
        petProfile.setSize(textFields[5].getText());
        petProfile.setEnergyLevel(textFields[6].getText());
        ArrayList<String> certifications = new ArrayList<String> 
                                        (Arrays.asList(textFields[7].getText().split(" ")));
        while(certifications.size() < 9)
        {
            certifications.add("none");
        }
        petProfile.setListCertifications(certifications);
        
        petProfile.setFosteredBefore(Boolean.parseBoolean(textFields[8].getText()));
        ArrayList<String> healthConditions = new ArrayList<String> 
                                        (Arrays.asList(textFields[9].getText().split(" ")));
        while(healthConditions.size() < 9)
        {
            healthConditions.add("none");
        }         
        petProfile.setListHealthConditions(healthConditions);                       
        petProfile.setGoodWithKids(Boolean.parseBoolean(textFields[10].getText()));
        petProfile.setPottyTrained(Boolean.parseBoolean(textFields[11].getText()));
        petProfile.setAllergies(Boolean.parseBoolean(textFields[12].getText()));
        petProfile.setGoodWithPets(Boolean.parseBoolean(textFields[13].getText()));
        petProfile.setNotes(textFields[14].getText());
        petProfile.setSpecies(textFields[15].getText());
        petProfile.setPhoto(photoPath);
        try{
        if(petProfile.getPk() == 0)
        {
            petProfile.setPk(adapter.addPet(petProfile));   
        }
        else{
            adapter.updatePet(petProfile);
        }
        }catch(SQLException e)
        {
            e.printStackTrace();
        }
        
        return petProfile;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
            editClicked = !editClicked;
            if(editClicked == true){
                editButton.setText("Save");
                for(JTextField textField: textFields){
                    textField.setEditable(true);
                }
            }else{
                editButton.setText("Edit");
                for(JTextField textField: textFields){
                    textField.setEditable(false);
                }
                readInTextToProfile(textFields,petProfile,photoPath);
                System.out.println(petProfile.getAge());
            }
    }
    
}
