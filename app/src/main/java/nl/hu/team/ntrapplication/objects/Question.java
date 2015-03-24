package nl.hu.team.ntrapplication.objects;

/**
 * Created by Milamber on 24-3-2015.
 */
public class Question {

    // Variabels
    private int id;
    private String description;
    private int sequence;
    private String type;

    // Constructor
    public Question() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getSequence() {
        return sequence;
    }

    public void setSequence(int sequence) {
        this.sequence = sequence;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
