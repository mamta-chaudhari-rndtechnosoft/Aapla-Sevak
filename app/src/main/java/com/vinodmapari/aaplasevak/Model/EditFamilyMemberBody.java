package com.vinodmapari.aaplasevak.Model;

public class EditFamilyMemberBody {

    private int id;
    private String name;
    private String middleName;
    private String surname;
    private String votingCenter;
    private String boothNo;
    private int votingSrNo;
    private String seriesId;
    private int colonyId;
    private int rowId;
    private String gender;
    private String mobile1;
    private String mobile2;
    private String qualification;
    private String caste;
    private String relation;
    private String event;
    private String adharCard;
    private int watersupplyId;
    private String voterId;

    //id, houseno,seriesId,colonyId,rowId,gender,name,middlename,surname,
    //mobile1,mobile2,qualification,caste,statusId,relation,event,voterid,
    //adhar, watersupplyid,voting center,booth no,voting sr no


    public EditFamilyMemberBody(int id, String name, String middleName, String surname, String votingCenter,
                                String boothNo, int votingSrNo, String seriesId, int colonyId, int rowId,
                                String gender, String mobile1, String mobile2, String qualification, String caste,
                                String relation, String event, String adharCard, int watersupplyId, String voterId
    ) {
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
    }
}
