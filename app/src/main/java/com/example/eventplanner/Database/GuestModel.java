package com.example.eventplanner.Database;

public class GuestModel {

        private int guestID;
        private String guestName,guestGender,guestAge,guestContact,guestEmail;
        private int guestCheck;
        private String guestNote;

    public GuestModel() {
    }

    public GuestModel(int guestID, String guestName, String guestGender, String guestAge, String guestContact, String guestEmail, int guestCheck, String guestNote) {
        this.guestID = guestID;
        this.guestName = guestName;
        this.guestGender = guestGender;
        this.guestAge = guestAge;
        this.guestContact = guestContact;
        this.guestEmail = guestEmail;
        this.guestCheck = guestCheck;
        this.guestNote = guestNote;
    }

    public GuestModel(String guestName, String guestGender, String guestAge, String guestContact, String guestEmail, int guestCheck, String guestNote) {
        this.guestName = guestName;
        this.guestGender = guestGender;
        this.guestAge = guestAge;
        this.guestContact = guestContact;
        this.guestEmail = guestEmail;
        this.guestCheck = guestCheck;
        this.guestNote = guestNote;
    }

    public int getGuestID() {
        return guestID;
    }

    public void setGuestID(int guestID) {
        this.guestID = guestID;
    }

    public String getGuestName() {
        return guestName;
    }

    public void setGuestName(String guestName) {
        this.guestName = guestName;
    }

    public String getGuestGender() {
        return guestGender;
    }

    public void setGuestGender(String guestGender) {
        this.guestGender = guestGender;
    }

    public String getGuestAge() {
        return guestAge;
    }

    public void setGuestAge(String guestAge) {
        this.guestAge = guestAge;
    }

    public String getGuestContact() {
        return guestContact;
    }

    public void setGuestContact(String guestContact) {
        this.guestContact = guestContact;
    }

    public String getGuestEmail() {
        return guestEmail;
    }

    public void setGuestEmail(String guestEmail) {
        this.guestEmail = guestEmail;
    }

    public int getGuestCheck() {
        return guestCheck;
    }

    public void setGuestCheck(int guestCheck) {
        this.guestCheck = guestCheck;
    }

    public String getGuestNote() {
        return guestNote;
    }

    public void setGuestNote(String guestNote) {
        this.guestNote = guestNote;
    }
}
