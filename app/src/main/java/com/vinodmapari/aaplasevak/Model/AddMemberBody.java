package com.vinodmapari.aaplasevak.Model;

import com.google.gson.annotations.SerializedName;

public class AddMemberBody {
    @SerializedName("survey_id")
    private String surveyId;

    @SerializedName("gender")
    private String gender;

    @SerializedName("name")
    private String name;

    @SerializedName("middle_name")
    private String middleName;

    @SerializedName("surname")
    private String surname;

    @SerializedName("mobile1")
    private String mobile1;

    @SerializedName("mobile2")
    private String mobile2;

    @SerializedName("dob")
    private String dob;

    @SerializedName("qualification")
    private String qualification;

    @SerializedName("status_id")
    private String statusId;

    @SerializedName("voter_id")
    private String voterId;

    @SerializedName("adhar_card")
    private String adharCard;

    @SerializedName("voting_center")
    private String votingCenter;

    @SerializedName("booth_no")
    private String boothNo;

    @SerializedName("voting_sr_no")
    private String votingSrNo;

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

    public AddMemberBody(String surveyId, String gender, String name, String middleName, String surname,
                         String mobile1, String mobile2, String dob, String qualification, String statusId,
                         String voterId, String adharCard, String votingCenter, String boothNo,
                         String votingSrNo, String apartment, String flatNo, String constituency,
                         String cityVillage, String zone, String prabhagWard) {
        this.surveyId = surveyId;
        this.gender = gender;
        this.name = name;
        this.middleName = middleName;
        this.surname = surname;
        this.mobile1 = mobile1;
        this.mobile2 = mobile2;
        this.dob = dob;
        this.qualification = qualification;
        this.statusId = statusId;
        this.voterId = voterId;
        this.adharCard = adharCard;
        this.votingCenter = votingCenter;
        this.boothNo = boothNo;
        this.votingSrNo = votingSrNo;
        this.apartment = apartment;
        this.flatNo = flatNo;
        this.constituency = constituency;
        this.cityVillage = cityVillage;
        this.zone = zone;
        this.prabhagWard = prabhagWard;
    }


    /*public AddMemberBody(String survey_id, String gender, String name, String middle_name, String surname,
                         String mobile1, String mobile2, String dob, String qualification, String status_id,
                         String relation_id, String event, String voter_id, String adhar_card,
                         String voting_center, String house_no, String row_id, String series_id,
                         String colony_id, String watersupply_id, String caste, String member_id) {
        this.survey_id = survey_id;
        this.gender = gender;
        this.name = name;
        this.middle_name = middle_name;
        this.surname = surname;
        this.mobile1 = mobile1;
        this.mobile2 = mobile2;
        this.dob = dob;
        this.qualification = qualification;
        this.status_id = status_id;
        this.relation_id = relation_id;
        this.event = event;
        this.voter_id = voter_id;
        this.adhar_card = adhar_card;
        this.voting_center = voting_center;
        this.house_no = house_no;
        this.row_id = row_id;
        this.series_id = series_id;
        this.colony_id = colony_id;
        this.watersupply_id = watersupply_id;
        this.caste = caste;
        this.member_id=member_id;
    }*/


}
