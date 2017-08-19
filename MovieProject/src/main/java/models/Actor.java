package models;

/**
 *
 * @author mqul
 * 
 * actor model mapping to actor tbl
 */
//@Entity
public class Actor{

    //@Column(name = "imdb_id")
    private String actorID;

    //@Column(name = "actor_firstNames")
    private String firstNames;

    //@Column(name = "actor_lastName")
    private String lastName;

    public Actor()
    {
        //
    }

    public Actor(String actorID, String firstNames, String lastName)
    {
        this.actorID = actorID;
        this.firstNames = firstNames;
        this.lastName = lastName;
    }

    public String getActorID() {
        return actorID;
    }

    public void setActorID(String actorID) {
        this.actorID = actorID;
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
