package com.vinodmapari.aaplasevak;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

public class VoterSearchItem implements Parcelable {

    private boolean isSelected;

    @SerializedName("id")
    private String id;
    private String name;
    private String surname;
    @SerializedName("middle_name")
    private String middleName;

    @SerializedName("fullname")
    private String fullName;

    @SerializedName("voter_id")
    private String voterId;
    @SerializedName("booth_no")
    private String boothNo;
    @SerializedName("s_no")
    private String serialNo;
    @SerializedName("voting_center")
    private String votingCenter;
    private String gender;
    @SerializedName("epic_no")
    private String epicNo;
    private String dob;
    private String constituency;
    @SerializedName("city_village")
    private String cityVillage;
    private String zone;
    @SerializedName("prabhag_ward")
    private String prabhagWard;


    // Constructor getters and setters
    public VoterSearchItem() {
    }

  /*  public VoterSearchItem(String name, String surname, String middleName, String voterId, String boothNo,
                           String serialNo, String votingCenter, String gender, String epicNo,
                           String dob, String constituency, String cityVillage,
                           String zone, String prabhagWard) {
        this.name = name;
        this.surname = surname;
        this.middleName = middleName;
        this.voterId = voterId;
        this.boothNo = boothNo;
        this.serialNo = serialNo;
        this.votingCenter = votingCenter;
        this.gender = gender;
        this.epicNo = epicNo;
        this.dob = dob;
        this.constituency = constituency;
        this.cityVillage = cityVillage;
        this.zone = zone;
        this.prabhagWard = prabhagWard;
    }*/

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public VoterSearchItem(String id, String name, String surname, String middleName,
                           String fullName, String voterId, String boothNo, String serialNo,
                           String votingCenter, String gender, String epicNo,
                           String dob, String constituency, String cityVillage,
                           String zone, String prabhagWard) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.middleName = middleName;
        this.fullName = fullName;
        this.voterId = voterId;
        this.boothNo = boothNo;
        this.serialNo = serialNo;
        this.votingCenter = votingCenter;
        this.gender = gender;
        this.epicNo = epicNo;
        this.dob = dob;
        this.constituency = constituency;
        this.cityVillage = cityVillage;
        this.zone = zone;
        this.prabhagWard = prabhagWard;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getVoterId() {
        return voterId;
    }

    public void setVoterId(String voterId) {
        this.voterId = voterId;
    }

    public String getBoothNo() {
        return boothNo;
    }

    public void setBoothNo(String boothNo) {
        this.boothNo = boothNo;
    }

    public String getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }

    public String getVotingCenter() {
        return votingCenter;
    }

    public void setVotingCenter(String votingCenter) {
        this.votingCenter = votingCenter;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEpicNo() {
        return epicNo;
    }

    public void setEpicNo(String epicNo) {
        this.epicNo = epicNo;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getConstituency() {
        return constituency;
    }

    public void setConstituency(String constituency) {
        this.constituency = constituency;
    }

    public String getCityVillage() {
        return cityVillage;
    }

    public void setCityVillage(String cityVillage) {
        this.cityVillage = cityVillage;
    }

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }

    public String getPrabhagWard() {
        return prabhagWard;
    }

    public void setPrabhagWard(String prabhagWard) {
        this.prabhagWard = prabhagWard;
    }

    // Getter for isSelected
    public boolean isSelected() {
        return isSelected;
    }

    // Setter for isSelected
    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    // Parcelable implementation
    protected VoterSearchItem(Parcel in) {
        isSelected = in.readByte() != 0;
        name = in.readString();
        surname = in.readString();
        middleName = in.readString();
        voterId = in.readString();
        boothNo = in.readString();
        serialNo = in.readString();
        votingCenter = in.readString();
        gender = in.readString();
        epicNo = in.readString();
        dob = in.readString();
        constituency = in.readString();
        cityVillage = in.readString();
        zone = in.readString();
        prabhagWard = in.readString();
    }

    public static final Creator<VoterSearchItem> CREATOR = new Creator<VoterSearchItem>() {
        @Override
        public VoterSearchItem createFromParcel(Parcel in) {
            return new VoterSearchItem(in);

        }

        @Override
        public VoterSearchItem[] newArray(int size) {
            return new VoterSearchItem[size];

        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeByte((byte) (isSelected ? 1 : 0));
        dest.writeString(name);
        dest.writeString(surname);
        dest.writeString(middleName);
        dest.writeString(voterId);
        dest.writeString(boothNo);
        dest.writeString(serialNo);
        dest.writeString(votingCenter);
        dest.writeString(gender);
        dest.writeString(epicNo);
        dest.writeString(dob);
        dest.writeString(constituency);
        dest.writeString(cityVillage);
        dest.writeString(zone);
        dest.writeString(prabhagWard);
    }
}
