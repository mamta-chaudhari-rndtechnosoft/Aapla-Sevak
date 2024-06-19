package com.vinodmapari.aaplasevak.Model;

import com.google.gson.annotations.SerializedName;

public class HouseDetail {
    private int id;
    private String name;
    private String middle_Name;
    private String surname;
    private String voting_Center;
    private String boothNo;
    private int votingSrNo;
    private String seriesId;
    private int colonyId;
    private int rowId;
    private String gender;
    private String mobile1;
    private String mobile2;
    private String dob;
    private String qualification;
    private String caste;
    private String relation;
    private String event;
    @SerializedName("adhar_card")
    private String adharCard;
    private int watersupplyId;
    private int memberId;
    @SerializedName("voter_id")
    private String voterId;

    public HouseDetail(String name, String middle_Name, String surname, String voting_Center, String boothNo, int votingSrNo, String voterId) {
        this.name = name;
        this.middle_Name = middle_Name;
        this.surname = surname;
        this.voting_Center = voting_Center;
        this.boothNo = boothNo;
        this.votingSrNo = votingSrNo;
        this.voterId = voterId;
    }


    public HouseDetail(int id, String name, String middle_Name, String surname, String voting_Center, String boothNo,
                       int votingSrNo, String seriesId, int colonyId, int rowId, String gender, String mobile1, String mobile2,
                       String dob, String qualification, String caste, String relation, String event, String adharCard,
                       int watersupplyId, int memberId, String voterId) {
        this.id = id;
        this.name = name;
        this.middle_Name = middle_Name;
        this.surname = surname;
        this.voting_Center = voting_Center;
        this.boothNo = boothNo;
        this.votingSrNo = votingSrNo;
        this.seriesId = seriesId;
        this.colonyId = colonyId;
        this.rowId = rowId;
        this.gender = gender;
        this.mobile1 = mobile1;
        this.mobile2 = mobile2;
        this.dob = dob;
        this.qualification = qualification;
        this.caste = caste;
        this.relation = relation;
        this.event = event;
        this.adharCard = adharCard;
        this.watersupplyId = watersupplyId;
        this.memberId = memberId;
        this.voterId = voterId;
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
        return middle_Name;
    }

    public void setMiddleName(String middle_Name) {
        this.middle_Name = middle_Name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getVotingCenter() {
        return voting_Center;
    }

    public void setVotingCenter(String voting_Center) {
        this.voting_Center = voting_Center;
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

    public String getSeriesId() {
        return seriesId;
    }

    public void setSeriesId(String seriesId) {
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
}

