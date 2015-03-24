package nl.hu.team.ntrapplication.objects;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Milamber on 24-3-2015.
 */
public class Research {
    private int ID;
    private String NAME;
    private Date BEGINDATE;
    private Date ENDDATE;
    private String STATUS;

    public Research() {

    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getNAME() {
        return NAME;
    }

    public void setNAME(String NAME) {
        this.NAME = NAME;
    }

    public Date getBEGINDATE() {
        return BEGINDATE;
    }

    public void setBEGINDATE(Date BEGINDATE) {
        this.BEGINDATE = BEGINDATE;
    }

    public Date getENDDATE() {
        return ENDDATE;
    }

    public void setENDDATE(Date ENDDATE) {
        this.ENDDATE = ENDDATE;
    }

    public String getSTATUS() {
        return STATUS;
    }

    public void setSTATUS(String STATUS) {
        this.STATUS = STATUS;
    }


}
