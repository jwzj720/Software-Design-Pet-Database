package GUI;

import javax.swing.*;

import DBAdapter.ClientInterface;
import DBAdapter.DBAdapter;
import Profile.FamilyProfile;


import Profile.PetProfile;

import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.ArrayList;

public class MainMenuGUI extends JFrame implements ActionListener{
    private JLabel title;
    private JLabel selectProfileType;
    private JComboBox<String> petOrFamily;
    private JButton showProfiles;
    private JButton newProfile;
    private JButton statistics;
    private ClientInterface DBAdapter;

    /** Opens the main menu window when the program is run
     * @param args
     */
    public static void main(String[] args) {
        new MainMenuGUI();
    }

    /** Adds the component to the main menu window */
    public void createMainMenu(){
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        GridBagConstraints gc = new GridBagConstraints();

        // add the title to the top of the window
        gc.anchor = GridBagConstraints.CENTER;
        gc.gridx = 0; gc.gridy=0; gc.gridwidth = 2;
        gc.insets = new Insets(10,10,20,10);
        title = new JLabel("Pet Adoption Database");
        title.setFont(new Font("The Girl Next Door", Font.PLAIN, 35));
        add(title,gc);

        // add the select profile type and drop down menu
        gc.gridx = 0; gc.gridy=1; gc.gridwidth = 1;
        gc.insets = new Insets(0,10,0,10);
        selectProfileType = new JLabel("Select Profile Type:");
        selectProfileType.setFont(new Font("The Girl Next Door", Font.PLAIN, 20));
        add(selectProfileType,gc);

        gc.fill = GridBagConstraints.HORIZONTAL;
        gc.gridx = 1; gc.gridy=1;
        String[] profileOptions = {"pet","family"};
        petOrFamily = new JComboBox<String>(profileOptions);
        petOrFamily.setFont(new Font("The Girl Next Door", Font.PLAIN, 20));
        add(petOrFamily,gc);
        
        // add show profiles button
        gc.gridx = 0; gc.gridy=2; gc.gridwidth = 2;
        showProfiles = new JButton(new AbstractAction(){
            
            @Override
            public void actionPerformed(ActionEvent e) {
                String currentSelection = (String)petOrFamily.getSelectedItem();
                if(currentSelection.equals("pet")){
                    ArrayList<PetProfile> allPetProfiles = null;
                    try {
                        allPetProfiles = (ArrayList<PetProfile>) DBAdapter.queryAllPets();
                    } catch (SQLException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                    new ListProfilesGUI(allPetProfiles);
                }else if(currentSelection.equals("family")){
                    ArrayList<FamilyProfile> allFamilyProfiles = null;
                    try {
                        allFamilyProfiles = (ArrayList<FamilyProfile>) DBAdapter.queryAllFamilies();
                    } catch (SQLException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                    new ListProfilesGUI(allFamilyProfiles);
                }
                
                // call method that opens profile list and takes in currentType as a parameter
            }
        });
        showProfiles.setText("Show Profile List");
        showProfiles.setFont(new Font("The Girl Next Door", Font.PLAIN, 20));
        showProfiles.addActionListener(this); // might be a new action listener
        add(showProfiles,gc);

        // add create new profile buttons
        gc.insets = new Insets(0,10,12,10);
        gc.gridx = 0; gc.gridy=3; gc.gridwidth = 2;
        newProfile = new JButton(new AbstractAction(){
            @Override
            public void actionPerformed(ActionEvent e) {
                String currentType = (String)petOrFamily.getSelectedItem();
                if (currentType.equals("pet")){
                    PetProfile newPetProfile = new PetProfile();
                    new PetProfileGUI(newPetProfile);
                }
                else if (currentType.equals("family")){
                    FamilyProfile newFamilyProfile = null;
                    new FamilyProfileGUI(newFamilyProfile);
                }
            }
        });
        newProfile.setText("Create a New Profile");
        newProfile.setFont(new Font("The Girl Next Door", Font.PLAIN, 20));
        newProfile.addActionListener(this); // might be a new action listener
        add(newProfile,gc);

        // add statistics button
        gc.gridx = 0; gc.gridy=4; gc.gridwidth = 2;
        statistics = new JButton(new AbstractAction(){
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new StatisticsGUI();
                } catch (Exception e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
        });
        statistics.setText("View Statistics");
        statistics.setFont(new Font("The Girl Next Door", Font.PLAIN, 20));
        statistics.addActionListener(this); // might be a new action listener
        add(statistics,gc);
    }

    /**
     * Constructor method for the main menu GUI
     */
    public MainMenuGUI(){
        super();
        try {
            this.DBAdapter = new DBAdapter();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        GridBagLayout gl = new GridBagLayout();
        setLayout(gl);
        createMainMenu();
        setLocationRelativeTo(null);
        pack();
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    // can make this either open the profile list or create a new profile
    
}
}

