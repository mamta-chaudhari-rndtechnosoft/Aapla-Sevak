package com.vinodmapari.aaplasevak.Model;

import com.google.gson.annotations.SerializedName;

public class FamilyDetailsItem {
    @SerializedName("id")
    private int id;

    @SerializedName("house_no")
    private String houseNo;

    @SerializedName("s_no")
    private int serialNo;

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

    @SerializedName("voting_sr_no")
    private Integer votingSrNo;

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
    private int qualification;

    @SerializedName("caste")
    private int caste;

    @SerializedName("relation")
    private String relation;

    @SerializedName("event")
    private String event;

    @SerializedName("adhar_card")
    private String adharCard;

    @SerializedName("watersupply_id")
    private int watersupplyId;

    @SerializedName("member_id")
    private int memberId;

    @SerializedName("voter_id")
    private String voterId;

    @SerializedName("constituency")
    private int constituency;

    @SerializedName("city_village")
    private int cityVillage;

    @SerializedName("zone")
    private int zone;

    @SerializedName("prabhag_ward")
    private int prabhagWard;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHouseNo() {
        return houseNo;
    }

    public void setHouseNo(String houseNo) {
        this.houseNo = houseNo;
    }

    public int getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(int serialNo) {
        this.serialNo = serialNo;
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

    public Integer getVotingSrNo() {
        return votingSrNo;
    }

    public void setVotingSrNo(Integer votingSrNo) {
        this.votingSrNo = votingSrNo;
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

    public int getQualification() {
        return qualification;
    }

    public void setQualification(int qualification) {
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

    public int getWatersupplyId() {
        return watersupplyId;
    }

    public void setWatersupplyId(int watersupplyId) {
        this.watersupplyId = watersupplyId;
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

    public int getConstituency() {
        return constituency;
    }

    public void setConstituency(int constituency) {
        this.constituency = constituency;
    }

    public int getCityVillage() {
        return cityVillage;
    }

    public void setCityVillage(int cityVillage) {
        this.cityVillage = cityVillage;
    }

    public int getZone() {
        return zone;
    }

    public void setZone(int zone) {
        this.zone = zone;
    }

    public int getPrabhagWard() {
        return prabhagWard;
    }

    public void setPrabhagWard(int prabhagWard) {
        this.prabhagWard = prabhagWard;
    }


    @Override
    public String toString() {
        return "PrintHouseDetail{" +
                "id=" + id +
                ", houseNo='" + houseNo + '\'' +
                ", serialNo=" + serialNo +
                ", name='" + name + '\'' +
                ", middleName='" + middleName + '\'' +
                ", surname='" + surname + '\'' +
                ", votingCenter='" + votingCenter + '\'' +
                ", boothNo='" + boothNo + '\'' +
                ", votingSrNo=" + votingSrNo +
                ", seriesId=" + seriesId +
                ", colonyId=" + colonyId +
                ", rowId=" + rowId +
                ", gender='" + gender + '\'' +
                ", mobile1='" + mobile1 + '\'' +
                ", mobile2='" + mobile2 + '\'' +
                ", dob='" + dob + '\'' +
                ", qualification=" + qualification +
                ", caste=" + caste +
                ", relation='" + relation + '\'' +
                ", event='" + event + '\'' +
                ", adharCard='" + adharCard + '\'' +
                ", watersupplyId=" + watersupplyId +
                ", memberId=" + memberId +
                ", voterId='" + voterId + '\'' +
                ", constituency=" + constituency +
                ", cityVillage=" + cityVillage +
                ", zone=" + zone +
                ", prabhagWard=" + prabhagWard +
                '}';
    }


}
