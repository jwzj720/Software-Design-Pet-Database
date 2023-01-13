package Profile;

import java.util.ArrayList;

public class FamilyProfile implements Profile {

    private String name, idealEnergyLevel, photo, idealSpecies;
    private boolean allergies, hasPets, maritalStatus, willFoster, hasKids;
    private int familySize, idealAge, family_pk;
    private ArrayList<String> idealBreed;
   
    /**
     * This class instantiates the family profile
     * @param name of the family
     * @param species that is preferred for pet
     * @param energyLevel that is preferred for pet
     * @param photo of the family
     * @param allergies that the family may have
     * @param hasPets whether if the family has any pets
     * @param maritalStatus 
     * @param willFoster status of adoption/fostering
     * @param idealBreed that is preferred for the pet
     * @param familySize size of family
     * @param idealAge ideal age for pet
     * @param hasKids number of kids of had any 
     */
    public FamilyProfile(String familyName, String idealSpecies, boolean hasAllergies , int familySize, boolean hasPets, boolean maritalStatus, boolean willFoster, String idealEnergyLevel, ArrayList<String> idealBreed, int idealAge, boolean hasKids, String familyPhoto){
        this.name = familyName;
        this.idealSpecies = idealSpecies;
        this.allergies = hasAllergies;
        this.familySize = familySize;
        this.hasPets = hasPets;
        this.maritalStatus = maritalStatus;
        this.willFoster = willFoster;
        this.idealEnergyLevel = idealEnergyLevel;
        this.idealBreed = idealBreed;
        this.idealAge = idealAge;
        this.hasKids = hasKids;
        this.photo = familyPhoto;
        this.family_pk = 0;

    }
    public FamilyProfile(int pk, String familyName, String idealSpecies, boolean hasAllergies , int familySize, boolean hasPets, boolean maritalStatus, boolean willFoster, String idealEnergyLevel, ArrayList<String> idealBreed, int idealAge, boolean hasKids, String familyPhoto){
     this.family_pk = pk;
     this.name = familyName;
     this.idealSpecies = idealSpecies;
     this.allergies = hasAllergies;
     this.familySize = familySize;
     this.hasPets = hasPets;
     this.maritalStatus = maritalStatus;
     this.willFoster = willFoster;
     this.idealEnergyLevel = idealEnergyLevel;
     this.idealBreed = idealBreed;
     this.idealAge = idealAge;
     this.hasKids = hasKids;
     this.photo = familyPhoto;

 }
     public FamilyProfile(){
          this.family_pk = 0;
     }
/* 
     public FamilyProfile(String idealSpecies, int family_pk, String name, String species, String energyLevel, String photo, boolean allergies, boolean hasPets, boolean maritalStatus, boolean willFoster, ArrayList<String> idealBreed, int familySize, int idealAge, boolean hasKids){
        this.idealSpecies = idealSpecies;
        this.family_pk = family_pk;
        this.name = name;
        this.species = species;
        this.energyLevel = energyLevel;
        this.photo = photo;
        this.allergies = allergies;
        this.hasPets = hasPets;
        this.maritalStatus = maritalStatus;
        this.willFoster = willFoster;
        this.idealBreed = idealBreed;
        this.familySize = familySize;
        this.idealAge = idealAge;
        this.hasKids = hasKids;
    }
*/
     public String getIdealSpecies(){
          return this.idealSpecies;
     }

     public void setIdealSpecies(String idealSpecies){
          this.idealSpecies = idealSpecies;
     }

	/**
     * Gets name of family
     * @return name
     */
    public String getName() {
		return this.name;
	}

	/**
     * Sets name of family
     */
    public void setName(String name) {
		this.name = name;
	}

	/**
     * Gets preferred energy level
     * @return energyLevel
     */
    public String getEnergyLevel() {
		return this.idealEnergyLevel;
	}

	/**
     * Sets preferred energy level
     */
    public void setEnergyLevel(String idealEnergyLevel) {
		this.idealEnergyLevel = idealEnergyLevel;
	}

	/**
     * Gets pathway of the family photo
     * @return photo path
     */
    public String getPhoto() {
		return this.photo;
	}

    /**
     * Sets pathway of the family photo
     */
	public void setPhoto(String photo) {
		this.photo = photo;
	}

	/**
     * Gets whether if family is allergic
     * @return boolean
     */
    public boolean getAllergies() {
		return this.allergies;
	}

    /**
     * Sets whether if family is allergic
     */
	public void setAllergies(boolean allergies) {
		this.allergies = allergies;
	}

	/**
     * Gets whether if family has other pets
     * @return boolean 
     */
    public boolean getHasPets() {
		return this.hasPets;
	}

	/**
     * Sets whether if family is allergic
     */
    public void setHasPets(boolean hasPets) {
		this.hasPets = hasPets;
	}

	/**
     * Gets marital status of family
     * @return marital status
     */
    public boolean getMaritalStatus() {
		return this.maritalStatus;
	}

	/**
     * Sets marital status of family
     */
    public void setMaritalStatus(boolean maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	/**
     * Gets fostering status for family
     * @return fostering status
     */
    public boolean getWillFoster() {
		return this.willFoster;
	}

    /**
     * Sets fostering status for family
     */
	public void setWillFoster(boolean willFoster) {
		this.willFoster = willFoster;
	}

	/**
     * Gets list of preferred breeds
     * @return list of preffered breed
     */
    public ArrayList<String> getIdealBreed(){
		return this.idealBreed;
	}

	/**
     * Sets list of preferred breeds
     */
    public void setIdealBreed(ArrayList<String> idealBreed) {
		this.idealBreed = idealBreed;
	}

    /**
     * Gets first preferred breed
     * @return breed 
     */
    public String getBreedOne(){
        return this.idealBreed.get(0);
    }

    /**
     * Gets second preferred breed
     * @return breed 
     */
    public String getBreedTwo(){
        return this.idealBreed.get(1);
    }

    /**
     * Gets third preferred breed
     * @return breed 
     */
    public String getBreedThree(){
        return this.idealBreed.get(2);
    }

    /** returns family size
 * @return
 */
     public int getFamilySize(){
          return this.familySize;
    } 

    /** sets family size
 * @param familySize
 */
     public void setFamilySize(int familySize){
          this.familySize = familySize;
    }
    
     /** gets ideal age of pet
      * @return
      */
     public int getIdealAge(){
          return this.idealAge;
    } 

    /** sets the ideal age of the pet
 * @param idealAge
 */
     public void setIdealAge(int idealAge){
          this.idealAge = idealAge;
    } 

    /** returns whether the family has kids
 * @return
 */
     public boolean getHasKids(){
          return this.hasKids;
    }

     /** sets whetehr the family has kids
      * @param hasKids
      */
     public void setHasKids(boolean hasKids){
          this.hasKids = hasKids;
    }

    /** gets the type of the family
 * @return
 */
     public String getType(){
     return this.getClass().getSimpleName();
     }

     /**
      * gets the pk identification of the family
      */
    public int getPk(){
          return this.family_pk;
    }
    /**
     * sets the pk of the family
     * @param pk
     */
    public void setPk(int pk)
    {
     this.family_pk = pk;
    }
    /**
     * @Override
     */
    public String toString()
    {
     
          String s = family_pk + " , " + name + " , " + idealSpecies + " , " + allergies + " , " + familySize  + " , " + hasPets
          + " , " + maritalStatus + " , " + willFoster + " , " + idealEnergyLevel + " , " + idealBreed.toString()
          + " , " + idealAge + " , " + hasKids + " , " + photo + "\n";
          return s;
    }
}
