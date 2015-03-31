package nl.hu.team.ntrapplication.objects;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by Milamber on 24-3-2015.
 */
public class Question implements Parcelable {

    // Variabels
    private int id;
    private String description;
    private int sequence;
    private String type;
    private ArrayList<Option> options = new ArrayList<>();
    private ArrayList<Attachment> attachments = new ArrayList<>();

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

    public ArrayList<Option> getOptions() {
        return options;
    }

    public void setOptions(ArrayList<Option> options) {
        this.options = options;
    }

    public void addOption(Option option) {
        options.add(option);
    }

    public void removeOption(Option option) {
        options.remove(option);
    }

    public ArrayList<Attachment> getAttachments() {
        return attachments;
    }

    public void setAttachments(ArrayList<Attachment> attachments) {
        this.attachments = attachments;
    }

    public void addAttachment(Attachment attachment){
        attachments.add(attachment);
    }

    public void removeAttachment(Attachment attachment) {
        attachments.remove(attachment);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(description);
        dest.writeInt(sequence);
        dest.writeString(type);
//        dest.writeTypedList(options);
//        dest.writeTypedList(attachments);
    }
    public static final Creator<Question> CREATOR
            = new Parcelable.Creator<Question>() {
        public Question createFromParcel(Parcel in) {
            return new Question(in);
        }
        public Question[] newArray(int size) {
            return new Question[size];
        }
    };
    private Question(Parcel in) {
        id = in.readInt();
        description = in.readString();
        sequence = in.readInt();
        type = in.readString();
//        in.readTypedList(options, Option.CREATOR);
//        in.readTypedList(attachments, Attachment.CREATOR);
    }
}
