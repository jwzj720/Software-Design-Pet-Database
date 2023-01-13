package DBAdapter;
import java.sql.Date;

import Profile.*;

public class Placement {
    private PetProfile pet;
    private FamilyProfile family;
    private Date adoption;
    private Date fosterStart;
    private Date fosterEnd;

    
    /**
     * Instantiates pet/family profiles, foster start/end dates
     * @param p
     * @param f
     * @param fosterStart
     * @param fosterEnd
     * @param adopt
     */
    public Placement(PetProfile p, FamilyProfile f, Date fosterStart, Date fosterEnd, Date adopt)
    {
        this.pet = p;
        this.family = f;
        this.adoption = adopt;
        this.fosterStart = fosterStart;
        this.fosterEnd = fosterEnd;
    }
   
    /**
     * Gets the pet profile
     * @return pet profile
     */
    public PetProfile getPet() {
        return pet;
    }

    /**
     * Sets the pet profile
     * @param pet
     */
    public void setPet(PetProfile pet) {
        this.pet = pet;
    }

    /**
     * Gets the family profile
     * @return family profile
     */
    public FamilyProfile getFamily() {
        return family;
    }

    /**
     * Sets family profile
     * @param family
     */
    public void setFamily(FamilyProfile family) {
        this.family = family;
    }

    /**
     * Gets adoption date
     * @return adoption date
     */
    public Date getAdoption() {
        return adoption;
    }

    /**
     * Adds adoption date
     * @param adoption
     */
    public void setAdoption(Date adoption) {
        this.adoption = adoption;
    }

    /**
     * Gets foster start date
     * @return foster start date
     */
    public Date getFosterStart() {
        return fosterStart;
    }

    /**
     * Sets foster start daye
     * @param fosterStart
     */
    public void setFosterStart(Date fosterStart) {
        this.fosterStart = fosterStart;
    }

    /**
     * Gets foster end date
     * @return foster end date
     */
    public Date getFosterEnd() {
        return fosterEnd;
    }

    /**
     * Sets foster end date
     * @param fosterEnd
     */
    public void setFosterEnd(Date fosterEnd) {
        this.fosterEnd = fosterEnd;
    }

    /* 
     * To string method for fosters/adoptions
     * @see java.lang.Object#toString()
     * @Override
     */
    public String toString()
    {
        String s = "";
        if(fosterStart == null)
        {
         s = pet.toString() + "\n \n" +  family.toString() + "\n \n"
        + " , " + adoption.toString() + "\n";
        }
        else{
            s = pet.toString() + "\n \n" +  family.toString() + "\n \n" + fosterStart.toString() + " , " + fosterEnd.toString()
            + "\n";
        }
        return s;
    }
}
