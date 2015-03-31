package nl.hu.team.ntrapplication.objects;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Milamber on 24-3-2015.
 */
public class Survey implements Parcelable{

    //Variables
    private int id;
    private String name;
    private Date beginDate;
    private Date endDate;
    private String status;
    private ArrayList<Question> questions = new ArrayList<>();

    // Constructor
    public Survey() {

    }
    // Methods

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

    public ArrayList<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(ArrayList<Question> questions) {
        this.questions = questions;
    }

    public void addQuestion(Question question) {
        questions.add(question);
    }

    public void removeQuestion(Question question) {
        questions.remove(question);
    }

    public String toString() {
        return name;
    }

    // Parcelable methods
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeTypedList(questions);
    }
    public static final Creator<Survey> CREATOR
            = new Parcelable.Creator<Survey>() {
        public Survey createFromParcel(Parcel in) {
            return new Survey(in);
        }
        public Survey[] newArray(int size) {
            return new Survey[size];
        }
    };
    private Survey(Parcel in) {
        id = in.readInt();
        name = in.readString();
        in.readTypedList(questions, Question.CREATOR);
    }
}
