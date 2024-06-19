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
    private String dob;
    private String qualification;
    private String caste;
    private String relation;
    private String event;
    private String adharCard;
    private int watersupplyId;
    private int memberId;
    private String voterId;

    public EditFamilyMemberBody(int id, String name, String middleName, String surname, String votingCenter, String boothNo, int votingSrNo, String seriesId,
                                int colonyId, int rowId, String gender, String mobile1,
                                String mobile2, String dob, String qualification, String caste, String relation,
                                String event, String adharCard, int watersupplyId, int memberId, String voterId) {
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
}
