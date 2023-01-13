package Profile;

import java.util.ArrayList;

public class PetProfile implements Profile {
    
    private String species, name, breed, size, color, energyLevel, notes, petPhoto, sex;
    private ArrayList<String> listHealthConditions;
    private ArrayList<String> listCertifications;
    private boolean fosteredBefore, pottyTrained, goodWithKids, goodWithPets, allergenic;
    private int age, pet_pk;
	public PetProfile(){
		this.pet_pk = 0;
		
	}
    /** Pet Profile Constructor
     * @param name
     * @param species
     * @param breed
     * @param sex
     * @param size
     * @param color
     * @param energyLevel
     * @param notes
     * @param listHealthConditions
     * @param listCertifications
     * @param fosteredBefore
     * @param pottyTrained
     * @param goodWithKids
     * @param goodWithPets
     * @param allergenic
     * @param age
     * @param petPhoto
     */
    public PetProfile(String name, String species, String breed, String sex, String size, String color, String energyLevel, String notes, ArrayList<String> listHealthConditions, ArrayList<String> listCertifications, boolean fosteredBefore, boolean pottyTrained, boolean goodWithKids, boolean goodWithPets, boolean allergenic, int age, String petPhoto){
        this.species = species;
        this.name = name;
        this.breed = breed;
        this.size = size;
        this.color = color;
        this.energyLevel = energyLevel;
        this.notes = notes;
        this.petPhoto = petPhoto;
        this.sex = sex;
        this.fosteredBefore = fosteredBefore;
        this.listHealthConditions = listHealthConditions;
        this.listCertifications = listCertifications;
        this.pottyTrained = pottyTrained;
        this.goodWithKids = goodWithKids;
        this.goodWithPets = goodWithPets;
        this.age = age;
		this.allergenic = allergenic;
    }

	public PetProfile(int pk, String name, String species, String breed, String sex, String size, String color, String energyLevel, String notes, ArrayList<String> listHealthConditions, ArrayList<String> listCertifications, boolean fosteredBefore, boolean pottyTrained, boolean goodWithKids, boolean goodWithPets, boolean allergenic, int age, String petPhoto){
        this.pet_pk = pk;
		this.species = species;
        this.name = name;
        this.breed = breed;
        this.size = size;
        this.color = color;
        this.energyLevel = energyLevel;
        this.notes = notes;
        this.petPhoto = petPhoto;
        this.sex = sex;
        this.fosteredBefore = fosteredBefore;
        this.listHealthConditions = listHealthConditions;
        this.listCertifications = listCertifications;
        this.pottyTrained = pottyTrained;
        this.goodWithKids = goodWithKids;
        this.goodWithPets = goodWithPets;
        this.age = age;
		this.allergenic = allergenic;
    }

	/** Returns whether it is allergenic or not
	 * @return
	 */
	public boolean getAllergies(){
		return this.allergenic;
	}

	/** sets whether it is allergenic or not
	 * @param allergenic
	 */
	public void setAllergies(boolean allergenic){
		this.allergenic = allergenic;
	}

    /** gets the species type
     * @return
     */
    public String getSpecies(){
        return this.species;
    }

    /** sets the species type
     * @param species
     */
    public void setSpecies(String species){
        this.species = species;
    }

	/* returns the name of the pet
	 */
	public String getName(){
		return this.name;
	}

	/** sets the name of the pet
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/** returns the breed of the pet
	 * @return breed of the pet
	 */
	public String getBreed(){
		return this.breed;
	}

	/** sets the breed of the pet
	 * @param breed
	 */
	public void setBreed(String breed) {
		this.breed = breed;
	}

	/** returns the size of the pet
	 * @return
	 */
	public String getSize() {
		return this.size;
	}

	/** sets the size of the pet
	 * @param size
	 */
	public void setSize(String size) {
		this.size = size;
	}

	/** gets the color of the pet
	 * @return
	 */
	public String getColor() {
		return this.color;
	}

	/** sets the color of the pet
	 * @param color
	 */
	public void setColor(String color) {
		this.color = color;
	}

	/** returns the energy level of the pet
	 * @return
	 */
	public String getEnergyLevel() {
		return this.energyLevel;
	}

	/** set energy level of pet
	 * @param energyLevel
	 */
	public void setEnergyLevel(String energyLevel) {
		this.energyLevel = energyLevel;
	}

	/** get notes about the pet
	 * @return
	 */
	public String getNotes() {
		return this.notes;
	}

	/** set notes about the pet
	 * @param notes
	 */
	public void setNotes(String notes) {
		this.notes = notes;
	}

	/* get the photo path of the pet
	 */
	public String getPhoto() {
		return this.petPhoto;
	}

	/** set the photo path of the pet
	 * @param photo
	 */
	public void setPhoto(String photo) {
		this.petPhoto = photo;
	}

	/** get the sex of the pet
	 * @return
	 */
	public String getSex() {
		return this.sex;
	}

	/** set the sex of the pet
	 * @param sex
	 */
	public void setSex(String sex) {
		this.sex = sex;
	}

	/** get the health conditions of the pet
	 * @return
	 */
	public ArrayList<String> getListHealthConditions() {
		return this.listHealthConditions;
	}

	/** set the health conditions of the pet
	 * @param listHealthConditions
	 */
	public void setListHealthConditions(ArrayList<String> listHealthConditions) {
		this.listHealthConditions = listHealthConditions;
	}

	/** get certifications of the pet
	 * @return
	 */
	public ArrayList<String> getListCertifications() {
		return this.listCertifications;
	}

	/** set certifications of the pet
	 * @param listCertifications
	 */
	public void setListCertifications(ArrayList<String> listCertifications) {
		this.listCertifications = listCertifications;
	}

	/** returns whether it is potty trained
	 * @return
	 */
	public boolean getPottyTrained() {
		return this.pottyTrained;
	}

	/** sets whether it is potty trained
	 * @param pottyTrained
	 */
	public void setPottyTrained(boolean pottyTrained) {
		this.pottyTrained = pottyTrained;
	}

	/** returns whether it is good with kids
	 * @return
	 */
	public boolean getGoodWithKids() {
		return this.goodWithKids;
	}

	/** sets whether it is good with kids
	 * @param goodWithKids
	 */
	public void setGoodWithKids(boolean goodWithKids) {
		this.goodWithKids = goodWithKids;
	}

	/** gets whetehr it is good with kids
	 * @return
	 */
	public boolean getGoodWithPets() {
		return this.goodWithPets;
	}

	/** sets whether it is good with other pets
	 * @param getGoodWithPets
	 */
	public void setGoodWithPets(boolean getGoodWithPets) {
		this.goodWithPets = getGoodWithPets;
	}

    /** gets whetehr it has been fostered before
     * @return
     */
    public boolean getFosteredBefore(){
        return this.fosteredBefore;
    }

    /** sets whether it has been fostered before
     * @param fosteredBefore
     */
    public void setFosteredBefore(boolean fosteredBefore){
        this.fosteredBefore = fosteredBefore;
    }

	/** get the age of the pet
	 * @return
	 */
	public int getAge() {
		return this.age;
	}

	/** sets the age of the pet
	 * @param age
	 */
	public void setAge(int age) {
		this.age = age;
	}

    /* get the pk identification of the pet
     * 
     */
    public int getPk(){
        return this.pet_pk;
    }

	/** get the type of the pet
	 * @return
	 */
	public String getType(){
		return this.getClass().getSimpleName();
	}
	
	/** set the pk identification of the pet
	 * @param pk
	 */
	public void setPk(int pk)
	{
		this.pet_pk = pk;
	}
	/* sets to string
	 * 
	 */
	public String toString()
	{
		/*
		 *  this.species = species;
        this.name = name;
        this.breed = breed;
        this.size = size;
        this.color = color;
        this.energyLevel = energyLevel;
        this.notes = notes;
        this.petPhoto = petPhoto;
        this.sex = sex;
        this.fosteredBefore = fosteredBefore;
        this.listHealthConditions = listHealthConditions;
        this.listCertifications = listCertifications;
        this.pottyTrained = pottyTrained;
        this.goodWithKids = goodWithKids;
        this.goodWithPets = goodWithPets;
        this.age = age;
		 */
		String s =  species + " , " + breed + " , " + size + " , " + color + " , " + energyLevel + " , " + notes + " , " + petPhoto + " , " + sex 
		+ " , " + fosteredBefore + " , " + listHealthConditions.toString() + " , " + listCertifications.toString() + " , " + pottyTrained 
		+ " , " + goodWithKids + " , " + goodWithPets + " , " + age;

		return s;
	}

}
