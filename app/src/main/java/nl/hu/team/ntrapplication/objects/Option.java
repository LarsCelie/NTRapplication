package nl.hu.team.ntrapplication.objects;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Milamber on 24-3-2015.
 */
public class Option implements Parcelable{
    private int ID;
    private String CONTENT;
    private String VALUE;
    private boolean selected = false;

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(ID);
        dest.writeString(CONTENT);
        dest.writeString(VALUE);
    }
    public static final Creator<Option> CREATOR
            = new Parcelable.Creator<Option>() {
        public Option createFromParcel(Parcel in) {
            return new Option(in);
        }
        public Option[] newArray(int size) {
            return new Option[size];
        }
    };
    private Option(Parcel in) {
        ID = in.readInt();
        CONTENT = in.readString();
        VALUE = in.readString();
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
