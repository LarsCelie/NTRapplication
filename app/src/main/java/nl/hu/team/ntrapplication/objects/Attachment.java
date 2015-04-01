package nl.hu.team.ntrapplication.objects;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Milamber on 24-3-2015.
 */
public class Attachment implements Parcelable{
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(ID);
        dest.writeString(TYPE);
        dest.writeString(LOCATION);
    }
    public static final Creator<Attachment> CREATOR
            = new Parcelable.Creator<Attachment>() {
        public Attachment createFromParcel(Parcel in) {
            return new Attachment(in);
        }
        public Attachment[] newArray(int size) {
            return new Attachment[size];
        }
    };
    private Attachment(Parcel in) {
        ID = in.readInt();
        TYPE = in.readString();
        LOCATION = in.readString();
    }
}
