package GUI;

import javax.swing.*;

import DBAdapter.ClientInterface;
import DBAdapter.DBAdapter;
import Profile.FamilyProfile;
import Profile.Matcher;
import Profile.PetProfile;
import Profile.Profile;

import java.awt.*;
import java.awt.event.*;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

public class FamilyProfileGUI extends JFrame implements ActionListener{
    private boolean editClicked = false;
    private JTextField[] textFields; 
    private FamilyProfile newFamilyProfile;
    private ImageIcon image;
    private JLabel nameLabel, familySizeLabel, marStatusLabel, kidsLabel, allergiesLabel,
        existingPetsLabel, willFosterLabel, energyLabel, breedLabel, ageLabel, photoLabel;
    private JTextField familyName, familySize, marStatus, kids, allergies, existingPets, willFoster, energyPreference,
        breedPreference, agePreference;
    private JButton edit, match, foster, adopt, photoChooser;
    private JFileChooser selectImage;
    private ClientInterface adapter;
    private String photoPath;


    /** Adds the components to the family profile window
     * @param thisFamily
     */
    public void createFamilyProfile(FamilyProfile familyProfile){
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        //String photoPath = "GUI_Images/blankprofile.png";
        if (familyProfile != null){
            photoPath = familyProfile.getPhoto();
        }
        //create DBAdapter
        try {
            adapter = new DBAdapter();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        GridBagConstraints gc = new GridBagConstraints();
        gc.insets = new Insets(1,10,1,10);

        // add the edit button
        gc.anchor = GridBagConstraints.EAST;
        gc.gridx = 3; gc.gridy = 0;
        edit = new JButton("Edit");
        edit.setFont(new Font("The Girl Next Door", Font.PLAIN, 15));
        edit.addActionListener(this);
        add(edit,gc);

        // add image icon of family
        gc.gridx = 0; gc.gridy=2; gc.gridheight = 3;
        Insets padding = new Insets(0,0,10,0);
        addImageToFrame(gc, padding,0,0,2, photoPath);
        
        
        // add name Label and all family characteristic labels
        createLabel(gc,nameLabel,0,1,20,"Name:");
        createLabel(gc,familySizeLabel,0,2,20,"Family Size:");
        createLabel(gc,marStatusLabel,0,3,20,"Marrital Status:");
        createLabel(gc,kidsLabel,0,4,20,"Kids Status:");
        createLabel(gc,allergiesLabel,0,5,20,"Allergies:");
        createLabel(gc,existingPetsLabel,0,6,20,"Existing Pets:");
        createLabel(gc,willFosterLabel,0,7,20,"Willing to Foster:");
        createLabel(gc,energyLabel,0,8,20,"Energy Level Preference:");
        createLabel(gc,breedLabel,0,9,20,"Breed Preferences:");
        createLabel(gc,ageLabel,0,10,20,"Ideal Pet Age:");
        createLabel(gc, photoLabel, 0, 11, 20, "Choose a photo:");

        // get all information from the database and make it a string
        String familyNameText = "";
        String familySizeText = "";
        String marStatusText = "";
        String kidsText = "";
        String allergiesText = "";
        String petsText = "";
        String fosterText = "";
        String energyText = "";
        String breedText = "";
        String ageText = "";
        if (familyProfile != null){
            familyNameText = familyProfile.getName();
            familySizeText = Integer.toString(familyProfile.getFamilySize());
            marStatusText = "Not Married";
            if (familyProfile.getMaritalStatus()){ marStatusText = "Married"; }
            kidsText = "Has No Kids";
            if (familyProfile.getHasKids()){ kidsText = "Has Kids"; }
            allergiesText = "No Allergies";
            if (familyProfile.getAllergies()){ allergiesText = "Has Allergies"; }
            petsText = "No Pets";
            if (familyProfile.getHasPets()){ petsText = "Has Pets"; }
            fosterText = "Not Willing to Foster";
            if (familyProfile.getWillFoster()){ fosterText = "Willing to Foster"; }
            energyText = familyProfile.getEnergyLevel();
            breedText = familyProfile.getBreedOne()+", "+familyProfile.getBreedTwo()+", "+familyProfile.getBreedThree();
            ageText = Integer.toString(familyProfile.getIdealAge());
        }

        // add all textfields of family characteristics
        familyName = createTextField(gc,familyName,1,1,35,3,familyNameText);
        familySize = createTextField(gc,familySize,1,2,20,1,familySizeText);
        marStatus = createTextField(gc,marStatus,1,3,20,1,marStatusText);
        kids = createTextField(gc,kids,1,4,20,1,kidsText);
        allergies = createTextField(gc,allergies,1,5,20,3,allergiesText);
        existingPets = createTextField(gc,existingPets,1,6,20,3,petsText);
        willFoster = createTextField(gc,willFoster,1,7,20,3,fosterText);
        energyPreference = createTextField(gc,energyPreference,1,8,20,3,energyText);
        breedPreference = createTextField(gc,breedPreference,1,9,20,3,breedText);
        agePreference = createTextField(gc,agePreference,1,10,20,3,ageText);
        textFields = new JTextField[]{familyName,familySize,marStatus,kids,allergies,
            existingPets,willFoster,energyPreference,breedPreference,agePreference};

        // add JField button
        gc.gridheight = 1;
        gc.insets = new Insets(2,50,2,50);
        gc.fill = GridBagConstraints.HORIZONTAL;
        gc.gridx = 1; gc.gridy = 11; gc.gridwidth = 2;
        photoChooser = new JButton("Files");
        photoChooser.setFont(new Font("The Girl Next Door", Font.PLAIN, 25));
        photoChooser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    PhotoChooserGUI photoChooser = new PhotoChooserGUI();
                    photoPath = photoChooser.getChosenPhoto();
                    System.out.println(photoPath);
                    Insets padding = new Insets(0,0,10,0);
                    addImageToFrame(gc, padding,0,0,2, photoPath);
                } catch (Exception e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                
            }  
        });
        add(photoChooser, gc);

        if (familyProfile!=null){
            //URL resource = this.getClass().getResource("GUI_Images/dog.jpg"); 
            image = new ImageIcon(photoPath);
            image = resizeImageIcon(image, 200, 150);
            JLabel label = new JLabel(image);
            add(label,gc);
        }

        // add match button
        gc.gridheight = 1;
        gc.insets = new Insets(2,100,2,100);
        gc.fill = GridBagConstraints.HORIZONTAL;
        gc.gridx = 0; gc.gridy = 12; gc.gridwidth = 4;
        match = new JButton("Match Family to Pets");
        match.setFont(new Font("The Girl Next Door", Font.PLAIN, 25));
        match.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Matcher match = new Matcher();
                try {
                    ArrayList<PetProfile> matches = (ArrayList) match.familyMatcher(familyProfile);
                    new ListProfilesGUI(matches,familyProfile);
                } catch (Exception e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                
            }
            
        }); // this should open list of matching profiles window
        add(match,gc);

        // add foster button
        gc.gridy = 13;
        foster = new JButton("Foster a Pet");
        foster.setFont(new Font("The Girl Next Door", Font.PLAIN, 25));
        foster.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new ListProfilesGUI((ArrayList<PetProfile>) adapter.queryAllPets(),familyProfile);
                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
              }
            
        }); // this should open list of all pets
        add(foster,gc);
        
        // add adopt button
        gc.gridy = 14;
        adopt = new JButton("Adopt a Pet");
        adopt.setFont(new Font("The Girl Next Door", Font.PLAIN, 25));
        adopt.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new ListProfilesGUI((ArrayList<PetProfile>) adapter.queryAllPets(),familyProfile);
                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                
            }
            
        }); // this should open list of all pets
        add(adopt,gc);

    }

    /** Constructor for the family profile GUI, sets the window to visible
     * @param thisFamily
     */
    public FamilyProfileGUI(FamilyProfile familyProfile){
        super();
        GridBagLayout gl = new GridBagLayout();
        setLayout(gl);
        createFamilyProfile(familyProfile);
        setLocationRelativeTo(null);
        pack();
        setVisible(true);
    }
  
    @Override
    public void actionPerformed(ActionEvent e) {
        editClicked = !editClicked;
        // Becomes editable
        if(editClicked == true){
            edit.setText("Save");
            for(JTextField textField: textFields){
                textField.setEditable(true);
            }
        }else{
            edit.setText("Edit");
            for(JTextField textField: textFields){
                textField.setEditable(false);
            }
            ArrayList<String> idealBreed = new ArrayList<String>();
            newFamilyProfile = new FamilyProfile("", "idealSpecies", false, 0, false, false, false, "idealEnergyLevel", idealBreed, 0, false, "blankprofile.png");
            try {
                readInTextToProfile(textFields,newFamilyProfile,photoPath);
            } catch (SQLException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        }  
    }

    /** Reads in user input to create a new family profile
     * @param textFields list of all the textfields in the profile
     * @param familyProfile the new family profile that is being created
     * @return
     * @throws SQLException
     */
    public FamilyProfile readInTextToProfile(JTextField[] textFields,FamilyProfile familyProfile, String photoPath) throws SQLException{
        familyProfile.setName(textFields[0].getText());
        familyProfile.setFamilySize(Integer.valueOf(textFields[1].getText()));
        familyProfile.setMaritalStatus(Boolean.parseBoolean(textFields[2].getText()));
        familyProfile.setHasKids(Boolean.parseBoolean(textFields[3].getText()));
        familyProfile.setAllergies(Boolean.parseBoolean(textFields[4].getText()));
        familyProfile.setHasPets(Boolean.parseBoolean(textFields[5].getText()));
        familyProfile.setWillFoster(Boolean.parseBoolean(textFields[6].getText()));
        familyProfile.setEnergyLevel(textFields[7].getText());
        ArrayList<String> breedPreferences = new ArrayList<String> 
            (Arrays.asList(textFields[8].getText().split(",")));
        familyProfile.setIdealBreed(breedPreferences);
        familyProfile.setIdealAge(Integer.valueOf(textFields[9].getText()));
        
        if (familyProfile.getPhoto()==null){
            familyProfile.setPhoto(photoPath);

        }
         
        if(familyProfile.getPk() == 0)
        {
            familyProfile.setPk(adapter.addFamily(familyProfile));
        }
        else{
            adapter.updateFamily(familyProfile);
        }
        
        return familyProfile;
        
    }

    /* Adds an ImageIcon to the frame
    * @param padding GridBag paddings to be used
    * @param gridX Grid X position
    * @param gridY Grid Y positions
    * @param gridwidth width of Image on the grid
    * @param pathToImage path from current directory to the image
    */
   public void addImageToFrame(GridBagConstraints gc, Insets padding, int gridX, int gridY, int gridwidth, String photoPath){
       gc.anchor = GridBagConstraints.CENTER;
       gc.gridx = gridX; 
       gc.gridy = gridY; 
       gc.gridwidth = gridwidth;
       gc.insets = padding;
       ImageIcon image = new ImageIcon(photoPath);
       image = resizeImageIcon(image, 100, 100);
       JLabel label = new JLabel(image);
       add(label,gc);
   }

     /**
     * Creates all the labels with the categories for family profile
     * @param gc GridBagConstraints
     * @param labelName the name of the label being added
     * @param x horizontal grid coordinate
     * @param y vertical grid coordinate
     * @param text text on the label
     */
    private void createLabel(GridBagConstraints gc, JLabel labelName, int x, int y, int fontSize, String text){
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(7,5,7,1);
        gc.gridx = x; gc.gridy = y; gc.gridwidth = 1; gc.gridheight = 1;
        labelName = new JLabel(text);
        labelName.setFont(new Font("The Girl Next Door", Font.PLAIN, fontSize));
        add(labelName,gc);
    }

    /** Creates all JTextFields on the Family Profile GUI
     * @param gc GridBagConstraints
     * @param fieldName name of the Text Field
     * @param x horizontal coordinate
     * @param y vertical coordinate
     * @param fontSize size of text
     * @param gridw number of columns
     * @param text the text written into the text field with the family information
     */
    private JTextField createTextField(GridBagConstraints gc, JTextField fieldName, int x, int y, int fontSize, int gridw, String text){
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(7,5,7,1);
        gc.gridx = x; gc.gridy = y; gc.gridwidth = gridw; gc.gridheight = 1;
        fieldName = new JTextField(text);
        fieldName.setColumns(10);
        fieldName.setFont(new Font("The Girl Next Door", Font.PLAIN, fontSize));
        fieldName.setEditable(false);
        add(fieldName,gc);
        return fieldName;
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

