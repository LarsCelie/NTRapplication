package nl.hu.team.ntrapplication.objects;

/**
 * Created by Milamber on 24-3-2015.
 */
public class Option {
    private int ID;
    private String DESCRIPTION;
    private int SEQUENCE;
    private String TYPE;

    private Option() {

    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getDESCRIPTION() {
        return DESCRIPTION;
    }

    public void setDESCRIPTION(String DESCRIPTION) {
        this.DESCRIPTION = DESCRIPTION;
    }

    public int getSEQUENCE() {
        return SEQUENCE;
    }

    public void setSEQUENCE(int SEQUENCE) {
        this.SEQUENCE = SEQUENCE;
    }

    public String getTYPE() {
        return TYPE;
    }

    public void setTYPE(String TYPE) {
        this.TYPE = TYPE;
    }
}
