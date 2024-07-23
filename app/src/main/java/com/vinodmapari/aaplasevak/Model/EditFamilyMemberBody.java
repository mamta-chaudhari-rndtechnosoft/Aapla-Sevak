package com.vinodmapari.aaplasevak.Model;

import com.google.gson.annotations.SerializedName;

public class EditFamilyMemberBody {

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

    @SerializedName("constituency")
    private String constituency;

    @SerializedName("city_village")
    private String cityVillage;

    @SerializedName("zone")
    private String zone;

    @SerializedName("prabhag_ward")
    private String prabhagWard;

    //id, houseno,seriesId,colonyId,rowId,gender,name,middlename,surname,
    //mobile1,mobile2,qualification,caste,statusId,relation,event,voterid,
    //adhar, watersupplyid,voting center,booth no,voting sr no


    public EditFamilyMemberBody(int id, String houseNo, String name, String middleName, String surname,
                                String votingCenter, String boothNo, int votingSrNo, int seriesId,
                                int colonyId, int rowId, String gender, String mobile1, String mobile2,
                                String dob, String qualification, String caste, String adharCard,
                                int waterSupplyId, int memberId, String voterId, String statusId,
                                String apartment, String flatNo, String constituency, String cityVillage,
                                String zone, String prabhagWard) {
        this.id = id;
        this.houseNo = houseNo;
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
        this.dob = dob;
        this.qualification = qualification;
        this.caste = caste;
        this.adharCard = adharCard;
        this.waterSupplyId = waterSupplyId;
        this.memberId = memberId;
        this.voterId = voterId;
        this.statusId = statusId;
        this.apartment = apartment;
        this.flatNo = flatNo;
        this.constituency = constituency;
        this.cityVillage = cityVillage;
        this.zone = zone;
        this.prabhagWard = prabhagWard;
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
                ", votingSrNo=" + votingSrNo +
                ", seriesId=" + seriesId +
                ", colonyId=" + colonyId +
                ", rowId=" + rowId +
                ", gender='" + gender + '\'' +
                ", mobile1='" + mobile1 + '\'' +
                ", mobile2='" + mobile2 + '\'' +
                ", dob='" + dob + '\'' +
                ", qualification='" + qualification + '\'' +
                ", caste='" + caste + '\'' +
                ", adharCard='" + adharCard + '\'' +
                ", waterSupplyId=" + waterSupplyId +
                ", memberId=" + memberId +
                ", voterId='" + voterId + '\'' +
                ", statusId='" + statusId + '\'' +
                ", apartment='" + apartment + '\'' +
                ", flatNo='" + flatNo + '\'' +
                ", constituency='" + constituency + '\'' +
                ", cityVillage='" + cityVillage + '\'' +
                ", zone='" + zone + '\'' +
                ", prabhagWard='" + prabhagWard + '\'' +
                '}';
    }
}


