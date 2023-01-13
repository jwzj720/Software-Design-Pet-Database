package DBAdapter;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


import Profile.*;

public class DBAdapter implements ClientInterface {
    
    private Connection conn;

    public static void main(String[] args) {
        try {
            DBAdapter db = new DBAdapter();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /** Constructor for DBAdapter
     * @throws Exception
     */
    public DBAdapter() throws Exception {
        String db = "lucky";
        String user = "lucky";
        String pass = "qKkb5p";
        String DB_URL = "jdbc:postgresql://10.3.0.11/"+db+"?user="+user+"&password="+pass;
        conn = DriverManager.getConnection(DB_URL);
        conn.setAutoCommit(true);
       
    }
    /**
     * deleteTables deletes all tables in database
     * @throws SQLException if there is an SQL syntax or other error
     */
    public void deleteTables() throws SQLException
    {
        PreparedStatement st = conn.prepareStatement("DROP TABLE pet CASCADE");
        st.execute();
        conn.close();  
    }

    /**
     * Creates in db
     * 
     * @throws SQLException
     */
    public void createDatabase() throws SQLException {
        Statement st = conn.createStatement();

        String create_energy = "CREATE TABLE Energy (\n"
                + "energylevel VARCHAR(128) PRIMARY KEY\n"
                + ");\n"
                + "INSERT INTO Energy (energyLevel) VALUES ('high');\n"
                + "INSERT INTO Energy (energyLevel) VALUES ('medium');\n"
                + "INSERT INTO Energy (energyLevel) VALUES ('low');\n";

        String create_idealBreed = "CREATE TABLE IdealBreed (\n"
                + "idealBreed_pk    SERIAL PRIMARY KEY, \n"
                + "breedOne         VARCHAR(128), \n"
                + "breedTwo         VARCHAR(128), \n"
                + "breedThree       VARCHAR(128) \n"
                + ");\n";

        String create_family = "CREATE TABLE Family (\n"
                + "family_pk            SERIAL PRIMARY KEY,\n"
                + "familyName           VARCHAR(128),\n"
                + "idealSpecies         VARCHAR(128),\n"
                + "hasAllergies         BOOLEAN,\n"
                + "familySize           INTEGER,\n"
                + "hasPets              BOOLEAN,\n"
                + "maritalStatus        BOOLEAN,\n"
                + "willFoster           BOOLEAN,\n"
                + "idealEnergyLevel     VARCHAR(128) REFERENCES Energy(energyLevel),\n"
                + "idealBreed           INTEGER REFERENCES IdealBreed(IdealBreed_pk),\n"
                + "idealAge             INTEGER,\n"
                + "hasKids              BOOLEAN,\n"
                + "familyPhoto          VARCHAR(255)\n"
                + ");";

        String create_healthConditions = "CREATE TABLE HealthConditions (\n"
                + "healthConditions_pk    SERIAL PRIMARY KEY,\n"
                + "nameOne                VARCHAR(128),\n"
                + "nameTwo                VARCHAR(128),\n"
                + "nameThree              VARCHAR(128),\n"
                + "nameFour               VARCHAR(128),\n"
                + "nameFive               VARCHAR(128),\n"
                + "nameSix                VARCHAR(128),\n"
                + "nameSeven              VARCHAR(128),\n"
                + "nameEight              VARCHAR(128),\n"
                + "nameNine               VARCHAR(128)\n"
                + ");";

        String create_certifications = "CREATE TABLE Certifications(\n"
                + "certifications_pk    SERIAL PRIMARY KEY,\n"
                + "nameOne              VARCHAR(128),\n"
                + "nameTwo              VARCHAR(128),\n"
                + "nameThree            VARCHAR(128),\n"
                + "nameFour             VARCHAR(128),\n"
                + "nameFive             VARCHAR(128),\n"
                + "nameSix              VARCHAR(128),\n"
                + "nameSeven            VARCHAR(128),\n"
                + "nameEight            VARCHAR(128),\n"
                + "nameNine             VARCHAR(128)\n"
                + ");";

        String create_sexes = "CREATE TABLE Sexes (\n"
                + "sex char(1) PRIMARY KEY \n"
                + ");\n"
                + "INSERT INTO sexes (sex) VALUES ('M'); \n"
                + "INSERT INTO sexes (sex) VALUES ('F'); \n";

        String create_pets = "CREATE TABLE Pet (\n"
                + "pet_pk               SERIAL PRIMARY KEY,\n"
                + "name                 VARCHAR(128),\n"
                + "species              VARCHAR(128),\n"
                + "breed                VARCHAR(128),\n"
                + "sex                  CHAR(1) REFERENCES sexes(sex),\n"
                + "size                 VARCHAR(128),\n"
                + "color                VARCHAR(128),\n"
                + "energyLevel          VARCHAR(128) REFERENCES Energy(energyLevel),\n"
                + "notes                VARCHAR(255),\n"
                + "listHealthConditions INTEGER REFERENCES HealthConditions(healthConditions_pk),\n"
                + "listCertifications   INTEGER REFERENCES Certifications(certifications_pk),\n"
                + "fosteredBefore       BOOLEAN,\n"
                + "pottyTrained         BOOLEAN,\n"
                + "goodWithKids         BOOLEAN,\n"
                + "goodWithPets         BOOLEAN,\n"
                + "allergenic            BOOLEAN,\n"
                + "age                  INTEGER,\n"
                + "petPhoto             VARCHAR(255)\n"
                + ");";

                String create_placements = "CREATE TABLE Placements (\n"
                + "pet_fk                 INTEGER REFERENCES Pet(pet_pk), \n"
                + "family_fk              INTEGER REFERENCES Family(family_pk), \n"
                + "foster_start_date      DATE,\n" //format YYYY-MM-DD
                + "foster_end_date        DATE,\n"
                + "adopt_date             DATE\n"
                + ");";

        //st.execute(create_energy);
        //st.execute(create_idealBreed);
       // st.execute(create_family);
       // st.execute(create_healthConditions);
       // st.execute(create_certifications);
       // st.execute(create_sexes);
        st.execute(create_pets);
        //st.execute(create_placements);
        conn.close();

    }

    /**
     * addPet adds a pet to the Pet table
     * 
     * @param pet_profile  pet profile to add (without pet_pk)
     * @throws SQLException  if there is an SQL syntax or other error
     */
    public int addPet(PetProfile pet_profile) throws SQLException {
        PreparedStatement pst = conn.prepareStatement(
                "INSERT INTO Pet (name, species, breed, sex, size, color, energyLevel, notes, listHealthConditions, listCertifications, fosteredBefore, pottyTrained, goodWithKids, goodWithPets, allergenic, age, petPhoto) VALUES (? ,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) RETURNING pet_pk;");
        // pst.setInt(1, pet_profile.getPet_pk()); // we don't set the PK
        pst.setString(1, pet_profile.getName());
        pst.setString(2, pet_profile.getSpecies());
        pst.setString(3, pet_profile.getBreed());
        pst.setString(4, pet_profile.getSex());
        pst.setString(5, pet_profile.getSize());
        pst.setString(6, pet_profile.getColor());
        pst.setString(7, pet_profile.getEnergyLevel());
        pst.setString(8, pet_profile.getNotes());
        pst.setInt(9,  populateHealthConditions(pet_profile));
        pst.setInt(10, populateCertifications(pet_profile));
        pst.setBoolean(11, pet_profile.getFosteredBefore());
        pst.setBoolean(12, pet_profile.getPottyTrained());
        pst.setBoolean(13, pet_profile.getGoodWithKids());
        pst.setBoolean(14, pet_profile.getGoodWithPets());
        pst.setBoolean(15, pet_profile.getAllergies());
        pst.setInt(16, pet_profile.getAge());
        pst.setString(17, pet_profile.getPhoto());
        ResultSet s = pst.executeQuery();
        s.next();
        return s.getInt(1);
        
    

    }
    public int populateHealthConditions(PetProfile pet_profile) throws SQLException
    {
        // PRECONDITION - pet_profile.getCertifications() returns arraylist with max 9 strings, all unoccupied strings are NULL. 
        PreparedStatement pst = conn.prepareStatement("INSERT INTO HealthConditions(nameOne, nameTwo, nameThree, nameFour, nameFive, nameSix, nameSeven, nameEight, nameNine) VALUES (?,?,?,?,?,?,?,?,?) RETURNING healthConditions_pk");
        ArrayList<String> certifications = pet_profile.getListCertifications();
        for(int i = 1; i <= 9; i++)
        {
            pst.setString(i, certifications.get(i-1));
        }
        ResultSet rs = pst.executeQuery();
        rs.next();
        int result = rs.getInt(1);
        return result;
    }
    public int populateCertifications(PetProfile pet_profile) throws SQLException
    {
        // PRECONDITION - pet_profile.getHealthConditions() returns arraylist with max 9 strings, all unoccupied strings are NULL. 
        PreparedStatement pst = conn.prepareStatement("INSERT INTO Certifications(nameOne, nameTwo, nameThree, nameFour, nameFive, nameSix, nameSeven, nameEight, nameNine) VALUES (?,?,?,?,?,?,?,?,?) RETURNING certifications_pk");
        ArrayList<String> healthCondtions = pet_profile.getListHealthConditions();
        for(int i = 1; i <= 9; i++)
        {
            pst.setString(i, healthCondtions.get(i-1));
        }
        ResultSet rs = pst.executeQuery();
        rs.next();
        int result = rs.getInt(1);
        return result;
    }

    /**
     * addFamily adds a familyProfile to the Family table
     * 
     * @param family_profile  familyProfile to add
     * @throws SQLException  if there is an SQL syntax or other error
     */
    public int addFamily(FamilyProfile family_profile) throws SQLException {
        PreparedStatement pst = conn.prepareStatement(
                "INSERT INTO Family (familyName, idealSpecies, hasAllergies, familySize, hasPets, maritalStatus, willFoster, idealEnergyLevel, idealBreed, idealAge, hasKids, familyPhoto) VALUES (?,?,?,?,?,?,?,?,?,?,?,?) RETURNING family_pk");
        pst.setString(1, family_profile.getName());
        pst.setString(2, family_profile.getIdealSpecies());
        pst.setBoolean(3, family_profile.getAllergies());
        pst.setInt(4, family_profile.getFamilySize());
        pst.setBoolean(5, family_profile.getHasPets());
        pst.setBoolean(6, family_profile.getMaritalStatus());
        pst.setBoolean(7, family_profile.getWillFoster());
        pst.setString(8, family_profile.getEnergyLevel());// see desired breed
        int desiredBreed_pk = populateIdealBreed(family_profile);
        pst.setInt(9, desiredBreed_pk);
        pst.setInt(10, family_profile.getIdealAge());
        pst.setBoolean(11, family_profile.getHasKids());
        pst.setString(12, family_profile.getPhoto());
        ResultSet s = pst.executeQuery();
        s.next();
        return s.getInt(1);
    
        
       
    }
    /**
     * populateIdeal breed is a helper method for adding familes to populate
     * the idealBreed table.
     * 
     * @param family_profile  family profile with list of ideal breeds
     * @return the pk of the row in the ideal breed table
     * @throws SQLException if there is an SQL syntax or other error
     */
    public int populateIdealBreed(FamilyProfile family_profile) throws SQLException
    {
        PreparedStatement pst = conn.prepareStatement("INSERT INTO IdealBreed (breedOne, breedTwo, breedThree) VALUES (?,?,?) RETURNING idealBreed_pk");
        ArrayList<String> idealBreed = family_profile.getIdealBreed();
        for(int i = 1; i <= 3; i++)
        {
            pst.setString(i, idealBreed.get(i-1));
        }
        ResultSet rs = pst.executeQuery();
        rs.next();
        int result = rs.getInt(1);
        return result;
    }

    /**
     * adopt creates a new entry in placements for adoptions
     * 
     * @param family_profile  the familyProfile to match
     * @param pet_profile  the petProfile to match
     * @param adoptionDate  the date of the adoption
     * @throws SQLException  if there is an SQL syntax or other error
     */
    public void adopt(FamilyProfile family_profile, PetProfile pet_profile, Date adoptionDate) throws SQLException {
        PreparedStatement pst = conn.prepareStatement("INSERT INTO Placements(pet_fk, family_fk, foster_start_date, foster_end_date, adopt_date) VALUES (?, ?, ?, ?, ?)");
        pst.setInt(1, pet_profile.getPk());
        pst.setInt(2, family_profile.getPk());
        pst.setDate(3, null);
        pst.setDate(4, null);
        pst.setDate(5, adoptionDate);
        pst.execute();


    }

    /**
     * foster creates a new entry in placements for fosters
     * 
     * @param family_profile  the familyProfile to match
     * @param pet_profile  the petProfile to match
     * @param fosterStartDate  the start date of fostering
     * @param fosterEndDate  the end date of fostering
     * @throws SQLException
     */
    public void foster(FamilyProfile family_profile, PetProfile pet_profile, Date fosterStartDate, Date fosterEndDate) throws SQLException {
        PreparedStatement pst = conn.prepareStatement("INSERT INTO Placements(pet_fk, family_fk, foster_start_date, foster_end_date, adopt_date) VALUES (?, ?, ?, ?, ?)");
        pst.setInt(1, pet_profile.getPk());
        pst.setInt(2, family_profile.getPk());
        pst.setDate(3, fosterStartDate);
        pst.setDate(4, fosterEndDate);
        pst.setDate(5, null);
        pst.execute();
    }

    /**
     * updateFamily updates an entry in the Family table
     * 
     * @param family_profile  the familyProfile to update
     * @throws SQLException  if there is an SQL syntax or other error
     */
    public void updateFamily(FamilyProfile family_profile) throws SQLException {
        
        PreparedStatement pst = conn.prepareStatement(
            "UPDATE Family SET familyName = ?, idealSpecies = ?, hasAllergies = ?, familySize = ?, hasPets = ?, maritalStatus = ?, willFoster = ?, idealEnergyLevel = ?, idealBreed = ?, idealAge = ?, hasKids = ?, familyPhoto = ? WHERE family_pk=?");
    
        pst.setString(1, family_profile.getName());
        pst.setString(2, family_profile.getIdealSpecies());
        pst.setBoolean(3, family_profile.getAllergies());
        pst.setInt(4, family_profile.getFamilySize());
        pst.setBoolean(5, family_profile.getHasPets());
        pst.setBoolean(6, family_profile.getMaritalStatus());
        pst.setBoolean(7, family_profile.getWillFoster());
        pst.setString(8, family_profile.getEnergyLevel());// see desired breed
        int desiredBreed_pk = populateIdealBreed(family_profile);
        pst.setInt(9, desiredBreed_pk);
        pst.setInt(10, family_profile.getIdealAge());
        pst.setBoolean(11, family_profile.getHasKids());
        pst.setString(12, family_profile.getPhoto());
        pst.setInt(13, family_profile.getPk());
        pst.execute();

    }

    /**
     * updatePet updates an entry in the Pet table
     * 
     * @param pet_profile  the petProfile to update
     * @throws SQLException  if there is an SQL syntax or other error
     */
    public void updatePet(PetProfile pet_profile) throws SQLException {
        PreparedStatement pst = conn.prepareStatement(
                "UPDATE Pet SET name = ?, species = ?, breed = ?, sex = ?, size = ? , color = ?, energyLevel = ?, notes = ?, listHealthConditions = ?, listCertifications = ?, fosteredBefore = ?, pottyTrained = ?, goodWithKids = ?, goodWithPets = ?, allergenic = ?, age = ?, petPhoto = ? WHERE pet_pk = ?;");
        // pst.setInt(1, pet_profile.getPet_pk()); // we don't set the PK  
        pst.setString(1, pet_profile.getName());
        pst.setString(2, pet_profile.getSpecies());
        pst.setString(3, pet_profile.getBreed());
        pst.setString(4, pet_profile.getSex());
        pst.setString(5, pet_profile.getSize());
        pst.setString(6, pet_profile.getColor());
        pst.setString(7, pet_profile.getEnergyLevel());
        pst.setString(8, pet_profile.getNotes());
        pst.setInt(9,  populateHealthConditions(pet_profile));
        pst.setInt(10, populateCertifications(pet_profile));
        pst.setBoolean(11, pet_profile.getFosteredBefore());
        pst.setBoolean(12, pet_profile.getPottyTrained());
        pst.setBoolean(13, pet_profile.getGoodWithKids());
        pst.setBoolean(14, pet_profile.getGoodWithPets());
        pst.setBoolean(15, pet_profile.getAllergies());
        pst.setInt(16, pet_profile.getAge());
        pst.setString(17, pet_profile.getPhoto());
        pst.setInt(18, pet_profile.getPk());
        pst.execute();
    }
    /**
     * querySomeFamilies searches based on declared seatch field and value to search
     * for in Family table
     * 
     * @param searchType  the field within Family to search
     * @param value  the desired value
     * @return  a list of familyProfiles matching search
     * @throws SQLException  if there is an SQL syntax or other error
     */
    public List<FamilyProfile> querySomeFamilies(String searchType, String value) throws SQLException
    {
        ArrayList<FamilyProfile> families = new ArrayList<>();
        PreparedStatement st = conn.prepareStatement("SELECT * FROM family\n"
                       + "RIGHT JOIN IdealBreed ON idealBreed = idealBreed_pk"
                       + "WHERE ? = ?\n");
        st.setString(1, searchType);
        st.setString(2, value);
        ResultSet family_results = st.executeQuery();
        
        while(family_results.next())
        {
            ArrayList<String> breeds = new ArrayList<>(3);
            breeds.add(family_results.getString(14));
            breeds.add(family_results.getString(15));
            breeds.add(family_results.getString(16));
            FamilyProfile f = new FamilyProfile(family_results.getInt(1), family_results.getString(2), family_results.getString(3), family_results.getBoolean(4), family_results.getInt(5), family_results.getBoolean(6), family_results.getBoolean(7), family_results.getBoolean(8), family_results.getString(9), breeds, family_results.getInt(11), family_results.getBoolean(12), family_results.getString(13));
            families.add(f);
        }
        return families;
        
    }
    /**
     * queryFamiliesPK searches based on known family_pk
     * 
     * 
     * @param value the pk to search
     * @return list with 1 element holding family with PK 
     * @throws SQLException  if there is an SQL syntax or other error
     */
    public List<FamilyProfile> queryFamiliesPK(long value) throws SQLException
    {
        ArrayList<FamilyProfile> families = new ArrayList<>();
        PreparedStatement st = conn.prepareStatement("SELECT * FROM Family\n"
                       + "RIGHT JOIN IdealBreed ON idealBreed = idealBreed_pk\n"
                       + "WHERE family_pk = ? \n");
        st.setLong(1, value);
        ResultSet family_results = st.executeQuery();
        
        while(family_results.next())
        {
            ArrayList<String> breeds = new ArrayList<>(3);
            breeds.add(family_results.getString(14));
            breeds.add(family_results.getString(15));
            breeds.add(family_results.getString(16));
            FamilyProfile f = new FamilyProfile(family_results.getInt(1), family_results.getString(2), family_results.getString(3), family_results.getBoolean(4), family_results.getInt(5), family_results.getBoolean(6), family_results.getBoolean(7), family_results.getBoolean(8), family_results.getString(9), breeds, family_results.getInt(11), family_results.getBoolean(12), family_results.getString(13));
            families.add(f);
        }
        return families;
        
    }
    /**
     * queryAllFamilies searches entire table
     * 
     * @return queryALLFamilies returns a list of all families in the database to display
     * @throws SQLException  if there is an SQL syntax or other error
     */
    public List<FamilyProfile> queryAllFamilies() throws SQLException
    {
        ArrayList<FamilyProfile> families = new ArrayList<>();
        PreparedStatement st = conn.prepareStatement("SELECT * FROM family\n"
                        + "INNER JOIN IdealBreed ON idealBreed = idealBreed_pk");
        ResultSet family_results = st.executeQuery();
        
        while(family_results.next())
        {
            ArrayList<String> breeds = new ArrayList<>(3);
            breeds.add(family_results.getString(14));
            breeds.add(family_results.getString(15));
            breeds.add(family_results.getString(16));
            FamilyProfile f = new FamilyProfile(family_results.getInt(1), family_results.getString(2), family_results.getString(3), family_results.getBoolean(4), family_results.getInt(5), family_results.getBoolean(6), family_results.getBoolean(7), family_results.getBoolean(8), family_results.getString(9), breeds, family_results.getInt(11), family_results.getBoolean(12), family_results.getString(13));
            families.add(f);
        }
        return families;
        
    }

    /**
     * queryPet searches for pets with breeds that match a family's ideal breed list
     * 
     * @param f  familyProfile to search idealBreed list
     * @return  list of pets that match
     * @throws SQLException  if there is an SQL syntax or other error
     */
    public ArrayList<PetProfile> queryPet(FamilyProfile f) throws SQLException
    {
        ArrayList<PetProfile> pets = new ArrayList<>();
        PreparedStatement st = conn.prepareStatement("SELECT * FROM Pet \n"
                                                    + "RIGHT JOIN HealthConditions ON listHealthConditions = healthConditions_pk\n"
                                                    + "RIGHT JOIN Certifications ON listCertifications = certifications_pk\n"
                                                    + "WHERE breed = ? OR breed = ? OR breed = ? \n");
        st.setString(1, f.getBreedOne());
        st.setString(2, f.getBreedTwo());
        st.setString(3, f.getBreedThree());
        ResultSet pet_breed = st.executeQuery();
        while(pet_breed.next())
        {
            ArrayList<String> healthConditions = new ArrayList<>(9);
            ArrayList<String> certifications = new ArrayList<>(9);
            for(int i = 1; i <= 9; i++)
            {
                healthConditions.add(pet_breed.getString(18+i));
                certifications.add(pet_breed.getString(27+i));
            }
            PetProfile p = new PetProfile(pet_breed.getInt(1), pet_breed.getString(2), pet_breed.getString(3), pet_breed.getString(4), pet_breed.getString(5), pet_breed.getString(6), pet_breed.getString(7), pet_breed.getString(8), pet_breed.getString(9), healthConditions, certifications, pet_breed.getBoolean(12), pet_breed.getBoolean(13), pet_breed.getBoolean(14), pet_breed.getBoolean(15), pet_breed.getBoolean(16), pet_breed.getInt(17), pet_breed.getString(18));
            pets.add(p);
        }
        return pets;
        
    }
    /**
     * queryPet based on declared seatch field and value to search
     * for in Pet table
     * 
     * @param searchType  the field to search in Pet
     * @param value  the desired value
     * @return  list of pets that match
     * @throws SQLException  if there is an SQL syntax or other error
     */
    public List<PetProfile> queryPet(String searchType, String value) throws SQLException
    {
        ArrayList<PetProfile> pets = new ArrayList<>();
        PreparedStatement st = conn.prepareStatement("SELECT * FROM Pet \n"
                                                    + "RIGHT JOIN HealthConditions ON listHealthConditions = healthConditions_pk\n"
                                                    + "RIGHT JOIN Certifications ON listCertifications = certifications_pk\n"
                                                    + "WHERE ? = ? \n");
        st.setString(1, searchType);
        st.setString(2, value);
        ResultSet pet_breed = st.executeQuery();
        while(pet_breed.next())
        {
            ArrayList<String> healthConditions = new ArrayList<>(9);
            ArrayList<String> certifications = new ArrayList<>(9);
            for(int i = 1; i <= 9; i++)
            {
                healthConditions.add(pet_breed.getString(18+i));
                certifications.add(pet_breed.getString(27+i));
            }
            PetProfile p = new PetProfile(pet_breed.getInt(1), pet_breed.getString(2), pet_breed.getString(3), pet_breed.getString(4), pet_breed.getString(5), pet_breed.getString(6), pet_breed.getString(7), pet_breed.getString(8), pet_breed.getString(9), healthConditions, certifications, pet_breed.getBoolean(12), pet_breed.getBoolean(13), pet_breed.getBoolean(14), pet_breed.getBoolean(15), pet_breed.getBoolean(16), pet_breed.getInt(17), pet_breed.getString(18));
            pets.add(p);
        }
        return pets;
        
    }
    /**
     * querypetPK searches Pet for the PetProfile with matching PK
     * 
     * @param value  PK to search for
     * @return  list of size 1 of the pet with the PK
     * @throws SQLException  if there is an SQL syntax or other error
     */
    public List<PetProfile> queryPetPK(int value) throws SQLException
    {
        ArrayList<PetProfile> pets = new ArrayList<>();
        PreparedStatement st = conn.prepareStatement("SELECT * FROM Pet \n"
                                                    + "RIGHT JOIN HealthConditions ON listHealthConditions = healthConditions_pk\n"
                                                    + "RIGHT JOIN Certifications ON listCertifications = certifications_pk\n"
                                                    + "WHERE pet_pk = ? \n");
        st.setInt(1, value);
        ResultSet pet_breed = st.executeQuery();
        while(pet_breed.next())
        {
            ArrayList<String> healthConditions = new ArrayList<>(9);
            ArrayList<String> certifications = new ArrayList<>(9);
            for(int i = 1; i <= 9; i++)
            {
                healthConditions.add(pet_breed.getString(18+i));
                certifications.add(pet_breed.getString(27+i));
            }
            PetProfile p = new PetProfile(pet_breed.getInt(1), pet_breed.getString(2), pet_breed.getString(3), pet_breed.getString(4), pet_breed.getString(5), pet_breed.getString(6), pet_breed.getString(7), pet_breed.getString(8), pet_breed.getString(9), healthConditions, certifications, pet_breed.getBoolean(12), pet_breed.getBoolean(13), pet_breed.getBoolean(14), pet_breed.getBoolean(15), pet_breed.getBoolean(16), pet_breed.getInt(17), pet_breed.getString(18));
            pets.add(p);
        }
        return pets;
        
    }
    /**
     * queryAllPets gets table of pets joined with subtables
     * 
     * @return  table of pets joined with subtables
     * @throws SQLException  if there is an SQL syntax or other error
     */
    public List<PetProfile> queryAllPets() throws SQLException
    {
        ArrayList<PetProfile> pets = new ArrayList<>();
        PreparedStatement st = conn.prepareStatement("SELECT * FROM Pet \n"
                                                    + "RIGHT JOIN HealthConditions ON listHealthConditions = healthConditions_pk\n"
                                                    + "RIGHT JOIN Certifications ON listCertifications = certifications_pk\n");
        ResultSet pet_breed = st.executeQuery();
        while(pet_breed.next())
        {
            ArrayList<String> healthConditions = new ArrayList<>(9);
            ArrayList<String> certifications = new ArrayList<>(9);
            for(int i = 1; i <= 9; i++)
            {
                healthConditions.add(pet_breed.getString(18+i));
                certifications.add(pet_breed.getString(27+i));
            }
            String s = pet_breed.getString(3);
            PetProfile p = new PetProfile(pet_breed.getInt(1), pet_breed.getString(2), pet_breed.getString(3), pet_breed.getString(4), pet_breed.getString(5), pet_breed.getString(6), pet_breed.getString(7), pet_breed.getString(8), pet_breed.getString(9), healthConditions, certifications, pet_breed.getBoolean(12), pet_breed.getBoolean(13), pet_breed.getBoolean(14), pet_breed.getBoolean(15), pet_breed.getBoolean(16), pet_breed.getInt(17), pet_breed.getString(18));
            pets.add(p);
        }
        for(int i = 0; i < pets.size();i++){
            if(pets.get(i).getName()==null){
                pets.remove(i);
                i=-1;
            }
        }
        return pets;
    }
    /**
     * queryPetPlacement searches the placement table for a specific Pet
     * 
     * @param p  the PetProfile to search for
     * @return  a list of matches
     * @throws SQLException  if there is an SQL syntax or other error
     */
    public List<Placement> queryPetPlacement(PetProfile p) throws SQLException
    {
        ArrayList<Placement> matches = new ArrayList<>();
        PreparedStatement st = conn.prepareStatement("SELECT * FROM Placements \n"
                                                    +"WHERE pet_fk = ? \n");
        st.setInt(1, p.getPk());                                          
        ResultSet placement = st.executeQuery();
        while(placement.next())
        {
            List<PetProfile> pet = queryPetPK(placement.getInt(1));
            List<FamilyProfile> family = queryFamiliesPK(placement.getInt(2));
            Placement place = new Placement(pet.get(0), family.get(0), placement.getDate(3), placement.getDate(4), placement.getDate(5));
            matches.add(place);
        }
        return matches;
    }
    /**
     * queryFamilyPlacement searches the placement table for a specific Family
     * 
     * @param p  the FamilyProfile to search for
     * @return  a list of matches
     * @throws SQLException  if there is an SQL syntax or other error
     */
    public List<Placement> queryFamilyPlacement(FamilyProfile p) throws SQLException
    {
        ArrayList<Placement> matches = new ArrayList<>();
        PreparedStatement st = conn.prepareStatement("SELECT * FROM Placements \n"
                                                    +"WHERE family_fk = ? \n");
        st.setInt(1, p.getPk());                                          
        ResultSet placement = st.executeQuery();
        while(placement.next())
        {
            List<PetProfile> pet = queryPetPK(placement.getInt(1));
            List<FamilyProfile> family = queryFamiliesPK(placement.getInt(2));
            Placement place = new Placement(pet.get(0), family.get(0), placement.getDate(3), placement.getDate(4), placement.getDate(5));
            matches.add(place);
        }
        return matches;
    }

    public int queryNumFoster() throws SQLException
    {
        PreparedStatement st = conn.prepareStatement("SELECT * FROM Placements \n"
                                                    +"WHERE foster_start_date IS NOT NULL\n");
        ResultSet results = st.executeQuery();
        int count = 0;
        while(results.next())
        {
            count++;
        }
        return count;
    }
    public int queryNumAdopt() throws SQLException
    {
        PreparedStatement st = conn.prepareStatement("SELECT * FROM Placements \n"
                                                    +"WHERE adopt_date IS NOT NULL\n");
        ResultSet results = st.executeQuery();
        int count = 0;
        while(results.next())
        {
            count++;
        }
        return count;
    }
    public int queryNumFamilyFoster() throws SQLException{
        PreparedStatement st = conn.prepareStatement("SELECT * FROM Placements \n"
        +"RIGHT JOIN Family ON family_fk = family_pk\n"
        +"WHERE foster_start_date IS NOT NULL\n");
        ResultSet results = st.executeQuery();
        int count = 0;
        while(results.next()){
            count++;
        }
        return count;
    }
    
    public int queryNumFamilyAdopt() throws SQLException{
        PreparedStatement st = conn.prepareStatement("SELECT * FROM Placements \n"
                                                    +"RIGHT JOIN Family ON family_fk = family_pk\n"
                                                    +"WHERE adopt_date IS NOT NULL\n");
        ResultSet results = st.executeQuery();
        int count = 0;
        while(results.next())
        {
            count++;
        }
        return count;
    }

}
