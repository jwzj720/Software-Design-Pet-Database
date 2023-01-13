package DBAdapter;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import Profile.*;

public interface ClientInterface {
    
  

    /**
     * addPet adds a pet to the Pet table
     * 
     * @param pet_profile  pet profile to add (without pet_pk)
     * @return 
     * @throws SQLException  if there is an SQL syntax or other error
     */
    public int addPet(PetProfile pet_profile) throws SQLException;
    public int populateHealthConditions(PetProfile pet_profile) throws SQLException;
    public int populateCertifications(PetProfile pet_profile) throws SQLException;

    /**
     * addFamily adds a familyProfile to the Family table
     * 
     * @param family_profile  familyProfile to add
     * @throws SQLException  if there is an SQL syntax or other error
     */
    public int addFamily(FamilyProfile family_profile) throws SQLException;
    /**
     * populateIdeal breed is a helper method for adding familes to populate
     * the idealBreed table.
     * 
     * @param family_profile  family profile with list of ideal breeds
     * @return the pk of the row in the ideal breed table
     * @throws SQLException if there is an SQL syntax or other error
     */
    public int populateIdealBreed(FamilyProfile family_profile) throws SQLException;

    /**
     * adopt creates a new entry in placements for adoptions
     * 
     * @param family_profile  the familyProfile to match
     * @param pet_profile  the petProfile to match
     * @param adoptionDate  the date of the adoption
     * @throws SQLException  if there is an SQL syntax or other error
     */
    public void adopt(FamilyProfile family_profile, PetProfile pet_profile, Date adoptionDate) throws SQLException;

    /**
     * foster creates a new entry in placements for fosters
     * 
     * @param family_profile  the familyProfile to match
     * @param pet_profile  the petProfile to match
     * @param fosterStartDate  the start date of fostering
     * @param fosterEndDate  the end date of fostering
     * @throws SQLException
     */
    public void foster(FamilyProfile family_profile, PetProfile pet_profile, Date fosterStartDate, Date fosterEndDate) throws SQLException;

    /**
     * updateFamily updates an entry in the Family table
     * 
     * @param family_profile  the familyProfile to update
     * @throws SQLException  if there is an SQL syntax or other error
     */
    public void updateFamily(FamilyProfile family_profile) throws SQLException;

    /**
     * updatePet updates an entry in the Pet table
     * 
     * @param pet_profile  the petProfile to update
     * @throws SQLException  if there is an SQL syntax or other error
     */
    public void updatePet(PetProfile pet_profile) throws SQLException;
    /**
     * querySomeFamilies searches based on declared seatch field and value to search
     * for in Family table
     * 
     * @param searchType  the field within Family to search
     * @param value  the desired value
     * @return  a list of familyProfiles matching search
     * @throws SQLException  if there is an SQL syntax or other error
     */
    public List<FamilyProfile> querySomeFamilies(String searchType, String value) throws SQLException;
    /**
     * queryFamiliesPK searches based on known family_pk
     * 
     * 
     * @param value the pk to search
     * @return list with 1 element holding family with PK 
     * @throws SQLException  if there is an SQL syntax or other error
     */
    public List<FamilyProfile> queryFamiliesPK(long value) throws SQLException;
    /**
     * queryAllFamilies searches entire table
     * 
     * @return queryALLFamilies returns a list of all families in the database to display
     * @throws SQLException  if there is an SQL syntax or other error
     */
    public List<FamilyProfile> queryAllFamilies() throws SQLException;

    /**
     * queryPet searches for pets with breeds that match a family's ideal breed list
     * 
     * @param f  familyProfile to search idealBreed list
     * @return  list of pets that match
     * @throws SQLException  if there is an SQL syntax or other error
     */
    public List<PetProfile> queryPet(FamilyProfile f) throws SQLException;
    /**
     * queryPet based on declared seatch field and value to search
     * for in Pet table
     * 
     * @param searchType  the field to search in Pet
     * @param value  the desired value
     * @return  list of pets that match
     * @throws SQLException  if there is an SQL syntax or other error
     */
    public List<PetProfile> queryPet(String searchType, String value) throws SQLException;
    /**
     * querypetPK searches Pet for the PetProfile with matching PK
     * 
     * @param value  PK to search for
     * @return  list of size 1 of the pet with the PK
     * @throws SQLException  if there is an SQL syntax or other error
     */
    public List<PetProfile> queryPetPK(int value) throws SQLException;
    /**
     * queryAllPets gets table of pets joined with subtables
     * 
     * @return  table of pets joined with subtables
     * @throws SQLException  if there is an SQL syntax or other error
     */
    public List<PetProfile> queryAllPets() throws SQLException;
    /**
     * queryPetPlacement searches the placement table for a specific Pet
     * 
     * @param p  the PetProfile to search for
     * @return  a list of matches
     * @throws SQLException  if there is an SQL syntax or other error
     */
    public List<Placement> queryPetPlacement(PetProfile p) throws SQLException;
    /**
     * queryFamilyPlacement searches the placement table for a specific Family
     * 
     * @param p  the FamilyProfile to search for
     * @return  a list of matches
     * @throws SQLException  if there is an SQL syntax or other error
     */
    public List<Placement> queryFamilyPlacement(FamilyProfile p) throws SQLException;
    /**
     * queryNumFoster returns the number of fosters
     * @return
     * @throws SQLException
     */
    public int queryNumFoster() throws SQLException;

    /**
     * queryNumAdopt returns the number of adoptions
     * @return
     * @throws SQLException
     */
    public int queryNumAdopt() throws SQLException;

    /**
     * queryNumFamilyFoster gives the number of familes that have fostered
     * @return
     * @throws SQLException
     */
    public int queryNumFamilyFoster() throws SQLException;

    /**
     * queryNumFamilyAdopt gives the number of families that have adopted
     * @return
     * @throws SQLException
     */
    public int queryNumFamilyAdopt() throws SQLException;
    
}
