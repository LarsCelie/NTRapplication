package nl.hu.team.ntrapplication.objects;

/**
 * Created by Milamber on 24-3-2015.
 */
public class Attachment {
    private int ID;
    private String TYPE;
    private String LOCATION;

    public Attachment() {

    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getTYPE() {
        return TYPE;
    }

    public void setTYPE(String TYPE) {
        this.TYPE = TYPE;
    }

    public String getLOCATION() {
        return LOCATION;
    }

    public void setLOCATION(String LOCATION) {
        this.LOCATION = LOCATION;
    }
}
