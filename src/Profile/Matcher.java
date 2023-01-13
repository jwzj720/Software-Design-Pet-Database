package Profile;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DBAdapter.DBAdapter;

public class Matcher{

    private FamilyProfile famProfile;
    private PetProfile petProfile;
    private DBAdapter dbAdapter;
    private ArrayList<PetProfile>matchedPets = new ArrayList<>();
    private ArrayList<FamilyProfile>matchedFamilies = new ArrayList<>();

    /**
     * Gets ideal breeds from SQL query then sifts through possible potential matched features
     * Returns a list of pets that have more than 3 matches
     * @param famProfile
     * @return list of matched pet profiles
     * @throws SQLException
     */
    public ArrayList<PetProfile> familyMatcher(FamilyProfile famProfile) throws SQLException{
		try {
			dbAdapter = new DBAdapter();
		} catch (Exception e) {
			e.printStackTrace();
		}

        System.out.println(famProfile.toString());
        List<PetProfile> listOfPets = dbAdapter.queryPet(famProfile);
        
        for (PetProfile p: listOfPets){
            System.out.println(p.toString());
        }

        for (PetProfile p: listOfPets){
            int counter = 0;
            if (counter < 3){
                if (p.getAge() == famProfile.getIdealAge()){ counter++; }
                if (p.getGoodWithKids() == famProfile.getHasKids()){ counter++; }
                if (p.getEnergyLevel() == famProfile.getEnergyLevel()){ counter++; }
                if (p.getAllergies() == famProfile.getAllergies()){ counter++; }
                if (p.getGoodWithPets() == famProfile.getHasPets()){ counter++; }
                if (p.getSpecies() == famProfile.getIdealSpecies()){ counter++; }
                if (p.getPottyTrained()){counter++;} 
            }
            if (counter >= 3){
                matchedPets.add(p);
            }
        }
        return matchedPets;
    }

    /**
     * Gets ideal familes from SQL query then sifts through possible potential matched features
     * Returns a list of families that have more than 3 matches
     * @param petProfile
     * @return a list of potential matches family matches for the pet
     * @throws SQLException
     */
    public ArrayList<FamilyProfile> petMatcher(PetProfile petProfile) throws SQLException{
        try {
			dbAdapter = new DBAdapter();
		} catch (Exception e) {
			e.printStackTrace();
		}

        List<FamilyProfile> listOfFamilies = dbAdapter.queryAllFamilies();

        for (FamilyProfile f: listOfFamilies){
            int counter = 0;
           
                if (petProfile.getAge() == f.getIdealAge()){ counter++; }
                if (petProfile.getGoodWithKids() == f.getHasKids()){ counter++; }
                if (petProfile.getEnergyLevel() == f.getEnergyLevel()){ counter++; }
                if (petProfile.getAllergies() == f.getAllergies()){ counter++; }
                if (petProfile.getGoodWithPets() == f.getHasPets()){ counter++; }
                if (petProfile.getSpecies() == f.getIdealSpecies()){ counter++; }
            
            if (counter >= 3){
                matchedFamilies.add(f);
            }
        }
        return matchedFamilies;
    }
}