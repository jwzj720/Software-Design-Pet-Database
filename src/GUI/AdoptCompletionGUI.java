package GUI;

import java.awt.*;
import java.awt.event.*;
import java.sql.Date;
import java.sql.SQLException;

import javax.swing.*;

import DBAdapter.ClientInterface;
import DBAdapter.DBAdapter;
import Profile.*;

public class AdoptCompletionGUI extends JFrame implements ActionListener{
    private GridBagConstraints gridConstraints = new GridBagConstraints();
    private Font CURRENTFONT;
    private JTextField startDateTextField;
    private JTextField endDateTextField;
    private FamilyProfile familyProfile;
    private PetProfile petProfile;
    private ClientInterface dbAdapter;
    private String startDateString;
    private String endDateString;
    private Date startDate;
    private Date endDate;
    /**
     * Creates the Adoption completion GUI. This GUI allows users to complete adoptions or fosters.
     */
    public void createAdoptCompletionGUI(){
        Insets padding = new Insets(0,0,0,0);
        addJLabelToFrame(padding, 0, 0, 1, "Start Date", 15);
        startDateTextField = addJTextFieldToFrame(padding, 1, 0, 1, "year/month/day");
        addJLabelToFrame(padding, 2, 0, 1, "End Date", 15);
        endDateTextField = addJTextFieldToFrame(padding, 3, 0, 1, "year/month/day");
        JButton adoptButton = addJButtonToFrame(padding, 0, 1, 4, "Adopt or Foster", null, 20);
        adoptButton.addActionListener(this);
    }

    public AdoptCompletionGUI(FamilyProfile familyProfile, PetProfile petProfile){
        super();
        try {
            this.dbAdapter = new DBAdapter();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        this.familyProfile = familyProfile;
        this.petProfile = petProfile;
        GridBagLayout gl = new GridBagLayout();
        setLayout(gl);
        createAdoptCompletionGUI();
        setLocationRelativeTo(null);
        pack();
        setVisible(true);
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
        textField.setText(text);
        textField.setEditable(true);
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
     * Converts a String representation of a date to an SQL date object
     * @param dateString String representation of date
     * @return SQL Date object
     */
    public Date convertStringToDate(String dateString){
        String[] dateArray = dateString.split("/");
        Date newDate = null;
        try{
            newDate = new Date(Integer.valueOf(dateArray[0]),
                            Integer.valueOf(dateArray[1]),Integer.valueOf(dateArray[2]));
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("ERROR: incorrectly formatted date entry");
        }
        return newDate;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        // Foster
        startDateString = startDateTextField.getText();
        endDateString = endDateTextField.getText();
        if(!startDateString.isEmpty() && !endDateString.isEmpty()){
            Date startDate = convertStringToDate(startDateString);
            Date endDate = convertStringToDate(endDateString);
            try {
                dbAdapter.foster(familyProfile, petProfile, startDate, endDate);
            } catch (SQLException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        }else if(!startDateString.isEmpty()){ // Adopt
            Date startDate = convertStringToDate(startDateString);
            try {
                dbAdapter.adopt(familyProfile, petProfile, startDate);
            } catch (SQLException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        }
    }
}
