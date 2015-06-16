package nl.hu.team.ntrapplication.objects;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Milamber on 24-3-2015.
 */
public class Research implements Parcelable{
    private int id;
    private String name;
    private Date beginDate;
    private Date endDate;
    private String status;
    private ArrayList<Survey> surveys = new ArrayList<>();

    public Research(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
        return name;
    }
    //De volgende regel hoort in de toString
    //+ " heeft " + surveys.size() + " vragenlijst(en)"

    @Override
    public int describeContents() {
        return 0;
    }
    @Override
    public void writeToParcel(Parcel pc, int flags) {
        pc.writeInt(id);
        pc.writeString(name);
        pc.writeSerializable(beginDate);
        pc.writeSerializable(endDate);
        pc.writeString(status);
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
        id = in.readInt();
        name = in.readString();
        beginDate = (Date) in.readSerializable();
        endDate = (Date) in.readSerializable();
        status = in.readString();
        in.readTypedList(surveys, Survey.CREATOR);
    }
}
