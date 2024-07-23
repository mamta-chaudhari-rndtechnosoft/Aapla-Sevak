package com.vinodmapari.aaplasevak.Model;

import com.google.gson.annotations.SerializedName;

public class UserDetail {
    @SerializedName("id")
    private int id;

    @SerializedName("house_no")
    private String houseNo;

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
    private int votingSrNo;

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
    private String caste;

    @SerializedName("adhar_card")
    private String adharCard;

    @SerializedName("watersupply_id")
    private int waterSupplyId;

    @SerializedName("member_id")
    private int memberId;

    @SerializedName("voter_id")
    private String voterId;

    @SerializedName("status_id")
    private String statusId;

    @SerializedName("apartment")
    private String apartment;

    @SerializedName("flat_no")
    private String flatNo;

    // Getters and Setters


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

    public int getVotingSrNo() {
        return votingSrNo;
    }

    public void setVotingSrNo(int votingSrNo) {
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

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public String getCaste() {
        return caste;
    }

    public void setCaste(String caste) {
        this.caste = caste;
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

    public String getStatusId() {
        return statusId;
    }

    public void setStatusId(String statusId) {
        this.statusId = statusId;
    }

    public String getApartment() {
        return apartment;
    }

    public void setApartment(String apartment) {
        this.apartment = apartment;
    }

    public String getFlatNo() {
        return flatNo;
    }

    public void setFlatNo(String flatNo) {
        this.flatNo = flatNo;
    }

    @Override
    public String toString() {
        return "HouseDetails{" +
                "id=" + id +
                ", houseNo='" + houseNo + '\'' +
                ", name='" + name + '\'' +
                ", middleName='" + middleName + '\'' +
                ", surname='" + surname + '\'' +
                ", votingCenter='" + votingCenter + '\'' +
                ", boothNo='" + boothNo + '\'' +
                ", votingSrNo='" + votingSrNo + '\'' +
                ", seriesId=" + seriesId +
                ", colonyId=" + colonyId +
                ", rowId=" + rowId +
                ", gender='" + gender + '\'' +
                ", mobile1='" + mobile1 + '\'' +
                ", mobile2='" + mobile2 + '\'' +
                ", dob='" + dob + '\'' +
                ", qualification='" + qualification + '\'' +
                ", caste='" + caste + '\'' +
                ", flateNo='" + flatNo + '\'' +
                ", apartmentNo='" + apartment + '\'' +
                ", adharCard='" + adharCard + '\'' +
                ", waterSupplyId=" + waterSupplyId +
                ", memberId=" + memberId +
                ", voterId='" + voterId + '\'' +
                ", statusId='" + statusId + '\'' +
                '}';
    }


}



