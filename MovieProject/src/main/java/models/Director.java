package models;

/**
 *
 * @author mqul
 *
 * director model mapping to director tbl
 */
//@Entity
public class Director{

    //@Column(name = "imdb_id")
    private String directorID;

    //@Column(name = "director_firstNames")
    private String firstNames;

    //@Column(name = "director_lastName")
    private String lastName;

    public Director()
    {
        //
    }

    public Director(String directorID, String firstNames, String lastName)
    {
        this.directorID = directorID;
        this.firstNames = firstNames;
        this.lastName = lastName;
    }

    public String getDirectorID() {
        return directorID;
    }

    public void setDirectorID(String directorID) {
        this.directorID = directorID;
    }

    public String getFirstNames() {
        return firstNames;
    }

    public void setFirstNames(String firstNames) {
        this.firstNames = firstNames;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
