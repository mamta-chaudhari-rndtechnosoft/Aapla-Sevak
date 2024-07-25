package com.vinodmapari.aaplasevak.Model;

import com.google.gson.annotations.SerializedName;

public class HouseDetail {
    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;

    @SerializedName("middle_name")
    private String middleName;

    @SerializedName("surname")
    private String surname;

    @SerializedName("voting_center")
    private String votingCenter;

    @SerializedName("booth_no")
    private String boothNo;

    @SerializedName("s_no")
    private String sNo;

    @SerializedName("series_id")
    private int seriesId;

    @SerializedName("colony_id")
    private int colonyId;

    @SerializedName("row_id")
    private int rowId;

    @SerializedName("gender")
    private String gender;

    @SerializedName("mobile1")
    private String mobile1;

    @SerializedName("mobile2")
    private String mobile2;

    @SerializedName("dob")
    private String dob;

    @SerializedName("qualification")
    private String qualification;

    @SerializedName("caste")
    private int caste;

    @SerializedName("relation")
    private String relation;

    @SerializedName("event")
    private String event;

    @SerializedName("adhar_card")
    private String adharCard;

    @SerializedName("watersupply_id")
    private int waterSupplyId;

    @SerializedName("member_id")
    private int memberId;

    @SerializedName("voter_id")
    private String voterId;

    @SerializedName("constituency")
    private int constituencyId;

    @SerializedName("city_village")
    private int cityVillageId;

    @SerializedName("zone")
    private int zoneId;

    @SerializedName("prabhag_ward")
    private int wardId;

    /*public HouseDetail(String name, String middle_Name, String surname, String voting_Center, String boothNo, int votingSrNo, String voterId, String seriesId) {
        this.name = name;
        this.middle_Name = middle_Name;
        this.surname = surname;
        this.voting_Center = voting_Center;
        this.boothNo = boothNo;
        this.votingSrNo = votingSrNo;
        this.voterId = voterId;
        this.seriesId = seriesId;
    }*/

    public HouseDetail(String name, String middleName, String surname, String votingCenter, String boothNo, String sNo, String voterId,int seriesId) {
        this.name = name;
        this.middleName = middleName;
        this.surname = surname;
        this.votingCenter = votingCenter;
        this.boothNo = boothNo;
        this.sNo = sNo;
        this.voterId = voterId;
        this.seriesId = seriesId;
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

    public String getVotingCenter() {
        return votingCenter;
    }

    public void setVotingCenter(String votingCenter) {
        this.votingCenter = votingCenter;
    }

    public String getBoothNo() {
        return boothNo;
    }

    public void setBoothNo(String boothNo) {
        this.boothNo = boothNo;
    }

    public String getSNo() {
        return sNo;
    }

    public void setSNo(String votingSrNo) {
        this.sNo = votingSrNo;
    }

    public int getSeriesId() {
        return seriesId;
    }

    public void setSeriesId(int seriesId) {
        this.seriesId = seriesId;
    }

    public int getColonyId() {
        return colonyId;
    }

    public void setColonyId(int colonyId) {
        this.colonyId = colonyId;
    }

    public int getRowId() {
        return rowId;
    }

    public void setRowId(int rowId) {
        this.rowId = rowId;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getMobile1() {
        return mobile1;
    }

    public void setMobile1(String mobile1) {
        this.mobile1 = mobile1;
    }

    public String getMobile2() {
        return mobile2;
    }

    public void setMobile2(String mobile2) {
        this.mobile2 = mobile2;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public int getCaste() {
        return caste;
    }

    public void setCaste(int caste) {
        this.caste = caste;
    }

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getAdharCard() {
        return adharCard;
    }

    public void setAdharCard(String adharCard) {
        this.adharCard = adharCard;
    }

    public int getWaterSupplyId() {
        return waterSupplyId;
    }

    public void setWaterSupplyId(int waterSupplyId) {
        this.waterSupplyId = waterSupplyId;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public String getVoterId() {
        return voterId;
    }

    public void setVoterId(String voterId) {
        this.voterId = voterId;
    }

    public int getConstituencyId() {
        return constituencyId;
    }

    public void setConstituencyId(int constituencyId) {
        this.constituencyId = constituencyId;
    }

    public int getCityVillageId() {
        return cityVillageId;
    }

    public void setCityVillageId(int cityVillageId) {
        this.cityVillageId = cityVillageId;
    }

    public int getZoneId() {
        return zoneId;
    }

    public void setZoneId(int zoneId) {
        this.zoneId = zoneId;
    }

    public int getWardId() {
        return wardId;
    }

    public void setWardId(int wardId) {
        this.wardId = wardId;
    }
}

