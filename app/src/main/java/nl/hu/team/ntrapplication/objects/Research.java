package nl.hu.team.ntrapplication.objects;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Milamber on 24-3-2015.
 */
public class Research implements Parcelable{
    private int ID;
    private String NAME;
    private Date BEGIN_DATE;
    private Date END_DATE;
    private String STATUS;
    private ArrayList<Survey> surveys = new ArrayList<>();

    public Research(){

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

    public Date getBEGIN_DATE() {
        return BEGIN_DATE;
    }

    public void setBEGIN_DATE(Date BEGIN_DATE) {
        this.BEGIN_DATE = BEGIN_DATE;
    }

    public Date getEND_DATE() {
        return END_DATE;
    }

    public void setEND_DATE(Date END_DATE) {
        this.END_DATE = END_DATE;
    }

    public String getSTATUS() {
        return STATUS;
    }

    public void setSTATUS(String STATUS) {
        this.STATUS = STATUS;
    }

    public ArrayList<Survey> getSurveys() {
        return surveys;
    }

    public void setSurveys(ArrayList<Survey> surveys) {
        this.surveys = surveys;
    }

    public void addSurvey(Survey survey) {
        surveys.add(survey);
    }

    public void removeSurvey(Survey survey) {
        surveys.remove(survey);
    }

    public String toString() {
        return NAME + " heeft " + surveys.size() + " vragenlijsten" ;
    }

    @Override
    public int describeContents() {
        return 0;
    }
    @Override
    public void writeToParcel(Parcel pc, int flags) {
        pc.writeInt(ID);
        pc.writeString(NAME);
        pc.writeSerializable(BEGIN_DATE);
        pc.writeSerializable(END_DATE);
        pc.writeString(STATUS);
        pc.writeTypedList(surveys);
    }
    public static final Creator<Research> CREATOR
            = new Parcelable.Creator<Research>() {
        public Research createFromParcel(Parcel in) {
            return new Research(in);
        }
        public Research[] newArray(int size) {
            return new Research[size];
        }
    };
    private Research(Parcel in) {
        ID = in.readInt();
        NAME = in.readString();
        BEGIN_DATE = (Date) in.readSerializable();
        END_DATE = (Date) in.readSerializable();
        STATUS = in.readString();
        in.readTypedList(surveys, Survey.CREATOR);
    }
}
