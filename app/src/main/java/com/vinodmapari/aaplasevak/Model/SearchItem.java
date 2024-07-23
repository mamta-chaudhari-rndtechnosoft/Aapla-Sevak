package com.vinodmapari.aaplasevak.Model;

import com.google.gson.annotations.SerializedName;

public class SearchItem {

    @SerializedName("id")
    private String id;

    @SerializedName("name")
    private String name;

    @SerializedName("middle_name")
    private String middleName;

    @SerializedName("surname")
    private String surname;

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

    @SerializedName("gender")
    private String gender;

    @SerializedName("epic_no")
    private String epicNo;

    @SerializedName("dob")
    private String dob;

    @SerializedName("constituency")
    private String constituency;

    @SerializedName("city_village")
    private String cityVillage;

    @SerializedName("zone")
    private String zone;

    @SerializedName("prabhag_ward")
    private String prabhagWard;

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
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

    @Override
    public String toString() {
        return "VoterDetails{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", middleName='" + middleName + '\'' +
                ", surname='" + surname + '\'' +
                ", fullName='" + fullName + '\'' +
                ", voterId='" + voterId + '\'' +
                ", boothNo='" + boothNo + '\'' +
                ", serialNo='" + serialNo + '\'' +
                ", votingCenter='" + votingCenter + '\'' +
                ", gender='" + gender + '\'' +
                ", epicNo='" + epicNo + '\'' +
                ", dob='" + dob + '\'' +
                ", constituency='" + constituency + '\'' +
                ", cityVillage='" + cityVillage + '\'' +
                ", zone='" + zone + '\'' +
                ", prabhagWard='" + prabhagWard + '\'' +
                '}';
    }

}
