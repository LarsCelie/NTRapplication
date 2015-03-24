package nl.hu.team.ntrapplication.objects;

/**
 * Created by Milamber on 24-3-2015.
 */
public class Option {
    private int ID;
    private String CONTENT;
    private String VALUE;

    public Option() {

    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getCONTENT() {
        return CONTENT;
    }

    public void setCONTENT(String CONTENT) {
        this.CONTENT = CONTENT;
    }

    public String getVALUE() {
        return VALUE;
    }

    public void setVALUE(String VALUE) {
        this.VALUE = VALUE;
    }
}
