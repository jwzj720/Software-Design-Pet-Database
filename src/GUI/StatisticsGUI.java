package GUI;

import javax.swing.*;

import DBAdapter.ClientInterface;
import DBAdapter.DBAdapter;

import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;

public class StatisticsGUI extends JFrame {
    private JLabel statTitle;
    private JLabel petsFostered;
    private JTextField numPetsFostered;
    private JLabel petsAdopted;
    private JTextField numPetsAdopted;
    private JLabel familyFoster;
    private JTextField numFamilyFoster;
    private JLabel familyAdopt;
    private JTextField numFamilyAdopt;
    private ClientInterface db;

    /**
     * Adds components to the statistics window
     * @throws Exception
     */
    public void createStatistics() throws Exception{
        db = new DBAdapter();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        GridBagConstraints gc = new GridBagConstraints();

        // add title to statistics window
        gc.anchor = GridBagConstraints.CENTER;
        gc.gridx = 0; gc.gridy=0; gc.gridwidth = 2;
        gc.insets = new Insets(10,10,20,10);
        statTitle = new JLabel("Pet Adoption Statistics");
        statTitle.setFont(new Font("The Girl Next Door", Font.PLAIN, 30));
        add(statTitle,gc);

        // add all labels
        gc.gridx = 0; gc.gridy = 1; gc.gridwidth = 1;
        gc.anchor = GridBagConstraints.WEST;
        petsFostered = new JLabel("Number of Pets Fostered:");
        petsFostered.setFont(new Font("The Girl Next Door", Font.PLAIN, 20));
        add(petsFostered,gc);
        gc.gridy = 2;
        petsAdopted = new JLabel("Number of Pets Adopted:");
        petsAdopted.setFont(new Font("The Girl Next Door", Font.PLAIN, 20));
        add(petsAdopted,gc);
        gc.gridy = 3;
        familyFoster = new JLabel("Number of Families who have Fostered:");
        familyFoster.setFont(new Font("The Girl Next Door", Font.PLAIN, 20));
        add(familyFoster,gc);
        gc.gridy = 4;
        familyAdopt = new JLabel("Number of Families who have Adopted:");
        familyAdopt.setFont(new Font("The Girl Next Door", Font.PLAIN, 20));
        add(familyAdopt,gc);

        // get all statistics // this will take in data from the databse
        String currentPetsFostered = Integer.toString(db.queryNumFoster());
        String currentPetsAdopted = Integer.toString(db.queryNumAdopt());
        String currentFamiliesFostered = Integer.toString(db.queryNumFamilyFoster());
        String currentFamiliesAdopted = Integer.toString(db.queryNumFamilyAdopt());

        // add all statistics
        gc.gridx = 1; gc.gridy = 1;
        numPetsFostered = new JTextField(currentPetsFostered);
        numPetsFostered.setFont(new Font("The Girl Next Door", Font.PLAIN, 20));
        numPetsFostered.setEditable(false);
        add(numPetsFostered,gc);
        gc.gridy = 2;
        numPetsAdopted = new JTextField(currentPetsAdopted);
        numPetsAdopted.setFont(new Font("The Girl Next Door", Font.PLAIN, 20));
        numPetsAdopted.setEditable(false);
        add(numPetsAdopted,gc);
        gc.gridy = 3;
        numFamilyFoster = new JTextField(currentFamiliesFostered);
        numFamilyFoster.setFont(new Font("The Girl Next Door", Font.PLAIN, 20));
        numFamilyFoster.setEditable(false);
        add(numFamilyFoster,gc);
        gc.gridy = 4;
        numFamilyAdopt = new JTextField(currentFamiliesAdopted);
        numFamilyAdopt.setFont(new Font("The Girl Next Door", Font.PLAIN, 20));
        numFamilyAdopt.setEditable(false);
        add(numFamilyAdopt,gc);


    }

    /**
     * Constructor method for the statistics window
     * @throws Exception
     */
    public StatisticsGUI() throws Exception{
        super();
        GridBagLayout gl = new GridBagLayout();
        setLayout(gl);
        createStatistics();
        setLocationRelativeTo(null);
        pack();
        setVisible(true);
    }

    
}
