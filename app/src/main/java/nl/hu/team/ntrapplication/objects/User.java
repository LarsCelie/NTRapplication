package nl.hu.team.ntrapplication.objects;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

/**
 * Created by JiryDam on 22-05-15.
 */
public class User implements Parcelable{

    // Variabelen
    private int id;
    private String firstname;
    private String lastname;
    private String email;
    private String username;
    private String password;

    public User() {

    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // Parcelable methods
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(firstname);
        dest.writeString(lastname);
        dest.writeString(email);
        dest.writeString(username);
        dest.writeString(password);
    }
    public static final Creator<User> CREATOR
            = new Parcelable.Creator<User>() {
        public User createFromParcel(Parcel in) {
            return new User(in);
        }
        public User[] newArray(int size) {
            return new User[size];
        }
    };
    private User(Parcel in) {
        id = in.readInt();
        firstname = in.readString();
        lastname = in.readString();
        email = in.readString();
        username = in.readString();
        password = in.readString();
    }
}
