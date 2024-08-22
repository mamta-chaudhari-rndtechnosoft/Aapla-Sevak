package com.vinodmapari.aaplasevak.Model;

import com.google.gson.annotations.SerializedName;

public class SurveyDetailItem {

    @SerializedName("surveyor_id")
    private int id;

    @SerializedName("name")
    private String name;

    @SerializedName("middle_name")
    private String middleName;

    @SerializedName("surname")
    private String surname;

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

    @SerializedName("zone")
    private String zoneName;

    @SerializedName("city_village")
    private String cityVillageName;

    @SerializedName("constituency")
    private String constituencyName;

    @SerializedName("prabhag_ward")
    private String prabhagWardName;


    public SurveyDetailItem() {
    }


    public SurveyDetailItem(int id, String name, String middleName, String surname,
                            String boothNo, String serialNo, String votingCenter,
                            String gender, String epicNo, String zoneName,
                            String cityVillageName, String constituencyName,
                            String prabhagWardName) {
        this.id = id;
        this.name = name;
        this.middleName = middleName;
        this.surname = surname;
        this.boothNo = boothNo;
        this.serialNo = serialNo;
        this.votingCenter = votingCenter;
        this.gender = gender;
        this.epicNo = epicNo;
        this.zoneName = zoneName;
        this.cityVillageName = cityVillageName;
        this.constituencyName = constituencyName;
        this.prabhagWardName = prabhagWardName;
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


    public String getZoneName() {
        return zoneName;
    }

    public void setZoneName(String zoneName) {
        this.zoneName = zoneName;
    }

    public String getCityVillageName() {
        return cityVillageName;
    }

    public void setCityVillageName(String cityVillageName) {
        this.cityVillageName = cityVillageName;
    }

    public String getConstituencyName() {
        return constituencyName;
    }

    public void setConstituencyName(String constituencyName) {
        this.constituencyName = constituencyName;
    }

    public String getPrabhagWardName() {
        return prabhagWardName;
    }

    public void setPrabhagWardName(String prabhagWardName) {
        this.prabhagWardName = prabhagWardName;
    }

    @Override
    public String toString() {
        return "SurveyDetailItem{" +
                ", ID='" + id + '\'' +
                ", constituencyName='" + constituencyName + '\'' +
                ", cityVillageName='" + cityVillageName + '\'' +
                ", zoneName='" + zoneName + '\'' +
                ", prabhagWardName='" + prabhagWardName + '\'' +
                "gender='" + gender + '\'' +
                ", name='" + name + '\'' +
                ", middleName='" + middleName + '\'' +
                ", surname='" + surname + '\'' +
                ", epicNo='" + epicNo + '\'' +
                ", boothNo='" + boothNo + '\'' +
                ", serialNo='" + serialNo + '\'' +
                ", votingCenter='" + votingCenter + '\'' +
                '}';
    }
}
