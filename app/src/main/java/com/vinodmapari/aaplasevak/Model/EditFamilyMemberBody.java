package com.vinodmapari.aaplasevak.Model;

import com.google.gson.annotations.SerializedName;

public class EditFamilyMemberBody {

    private int id;
    private String name;
    @SerializedName("middle_name")
    private String middleName;
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
    private String gender;
    private String mobile1;
    private String mobile2;
    private String qualification;
    private String caste;
    private String relation;
    private String event;
    @SerializedName("adhar_card")
    private String adharCard;
    @SerializedName("watersupply_id")
    private int watersupplyId;

    @SerializedName("voter_id")
    private String voterId;

    private int status_id;

    private String house_no;

    private String dob;
    private int member_id;

    //id, houseno,seriesId,colonyId,rowId,gender,name,middlename,surname,
    //mobile1,mobile2,qualification,caste,statusId,relation,event,voterid,
    //adhar, watersupplyid,voting center,booth no,voting sr no


    public EditFamilyMemberBody(int id, String name, String middleName, String surname, String votingCenter,
                                String boothNo, int votingSrNo, int seriesId, int colonyId, int rowId,
                                String gender, String mobile1, String mobile2, String qualification,
                                String caste, String relation, String event, String adharCard, int watersupplyId,
                                String voterId, int status_id, String house_no, String dob, int member_id) {
        this.id = id;
        this.name = name;
        this.middleName = middleName;
        this.surname = surname;
        this.votingCenter = votingCenter;
        this.boothNo = boothNo;
        this.votingSrNo = votingSrNo;
        this.seriesId = seriesId;
        this.colonyId = colonyId;
        this.rowId = rowId;
        this.gender = gender;
        this.mobile1 = mobile1;
        this.mobile2 = mobile2;
        this.qualification = qualification;
        this.caste = caste;
        this.relation = relation;
        this.event = event;
        this.adharCard = adharCard;
        this.watersupplyId = watersupplyId;
        this.voterId = voterId;
        this.status_id = status_id;
        this.house_no = house_no;
        this.dob = dob;
        this.member_id = member_id;
    }

    @Override
    public String toString() {
        return "EditFamilyMemberBody{" +
                "id='" + id + '\'' +
                ", Name='" + name + '\'' +
                ", MiddleName='" + middleName + '\'' +
                ", Surname='" + surname + '\'' +
                ", VotingCenter='" + votingCenter + '\'' +
                ", BoothNo='" + boothNo + '\'' +
                ", VotingSrNo='" + votingSrNo + '\'' +
                ", SeriesId='" + seriesId + '\'' +
                ", ColonyId='" + colonyId + '\'' +
                ", RowId='" + rowId + '\'' +
                ", Gender='" + gender + '\'' +
                ", Mobile1='" + mobile1 + '\'' +
                ", Mobile2='" + mobile2 + '\'' +
                ", Qualification='" + qualification + '\'' +
                ", Caste='" + caste + '\'' +
                ", Relation='" + relation + '\'' +
                ", Event='" + event + '\'' +
                ", AadharCard='" + adharCard + '\'' +
                ", WaterSupplyId='" + watersupplyId + '\'' +
                ", VoterId='" + voterId + '\'' +
                ", selected_status='" + status_id + '\'' +
                ", HouseNo='" + house_no + '\'' +
                '}';
    }
}


