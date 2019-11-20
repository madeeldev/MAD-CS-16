package adeelsafdar.secondassignment;

public class Contact {
    // Store the id of the  movie poster
    private int mImageDrawable;
    // Store the name of the movie
    private String mName;
    // Store the release date of the movie
    private String mContactNumber;
    // Store the email
    private String mEmail;
    // Constructor that is used to create an instance of the Contact object
    public Contact(int mImageDrawable, String mName, String mContactNumber, String mEmail) {
        this.mImageDrawable = mImageDrawable;
        this.mName = mName;
        this.mContactNumber = mContactNumber;
        this.mEmail = mEmail;
    }

    public int getmImageDrawable() {
        return mImageDrawable;
    }

    public void setmImageDrawable(int mImageDrawable) {
        this.mImageDrawable = mImageDrawable;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmContactNumber() {
        return mContactNumber;
    }

    public void setmContactNumber(String mContactNumber) {
        this.mContactNumber = mContactNumber;
    }
    public String getmEmail() {
        return mEmail;
    }

    public void setmEmail(String mEmail) {
        this.mEmail = mEmail;
    }
}
