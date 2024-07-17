package com.vinodmapari.aaplasevak.Model;

import com.google.gson.annotations.SerializedName;

public class AddSurveyBody {

    @SerializedName("house_no")
    private String houseNo;

    @SerializedName("series_id")
    private String seriesId;

    @SerializedName("colony_id")
    private String colonyId;

    @SerializedName("row_id")
    private String rowId;

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

    @SerializedName("caste")
    private String caste;

    @SerializedName("status_id")
    private String statusId;

    @SerializedName("voter_id")
    private String voterId;

    @SerializedName("adhar_card")
    private String adharCard;

    @SerializedName("watersupply_id")
    private String watersupplyId;

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

    @SerializedName("survey_id")
    private String surveyId;

    public AddSurveyBody(String houseNo, String seriesId, String colonyId, String rowId, String gender, String name,
                         String middleName, String surname, String mobile1, String mobile2, String dob,
                         String qualification, String caste, String statusId, String voterId, String adharCard,
                         String watersupplyId, String votingCenter, String boothNo, String votingSrNo, String apartment,
                         String flatNo, String constituency, String cityVillage, String zone, String prabhagWard,
                         String surveyId) {
        this.houseNo = houseNo;
        this.seriesId = seriesId;
        this.colonyId = colonyId;
        this.rowId = rowId;
        this.gender = gender;
        this.name = name;
        this.middleName = middleName;
        this.surname = surname;
        this.mobile1 = mobile1;
        this.mobile2 = mobile2;
        this.dob = dob;
        this.qualification = qualification;
        this.caste = caste;
        this.statusId = statusId;
        this.voterId = voterId;
        this.adharCard = adharCard;
        this.watersupplyId = watersupplyId;
        this.votingCenter = votingCenter;
        this.boothNo = boothNo;
        this.votingSrNo = votingSrNo;
        this.apartment = apartment;
        this.flatNo = flatNo;
        this.constituency = constituency;
        this.cityVillage = cityVillage;
        this.zone = zone;
        this.prabhagWard = prabhagWard;
        this.surveyId = surveyId;
    }
}
