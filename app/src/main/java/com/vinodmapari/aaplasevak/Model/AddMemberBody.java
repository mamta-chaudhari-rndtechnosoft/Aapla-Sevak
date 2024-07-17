package com.vinodmapari.aaplasevak.Model;

public class AddMemberBody {
    String survey_id, gender, name, middle_name, surname, mobile1, mobile2, dob,
            qualification, status_id, relation_id, voter_id,
            adhar_card, voting_center, house_no, row_id, series_id,
            colony_id, watersupply_id, caste,
            constituency, city_village, zone, prabhag_ward,
            apartment, flat_no;


    public AddMemberBody(String survey_id, String gender, String name, String middle_name, String surname,
                         String mobile1, String mobile2, String dob, String qualification, String status_id,
                         String relation_id, String voter_id, String adhar_card,
                         String voting_center, String house_no, String row_id, String series_id,
                         String colony_id, String watersupply_id, String caste,
                         String constituency, String city_village, String zone, String prabhag_ward,
                         String apartment, String flat_no) {
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
        this.voter_id = voter_id;
        this.adhar_card = adhar_card;
        this.voting_center = voting_center;
        this.house_no = house_no;
        this.row_id = row_id;
        this.series_id = series_id;
        this.colony_id = colony_id;
        this.watersupply_id = watersupply_id;
        this.caste = caste;
        this.constituency = constituency;
        this.city_village = city_village;
        this.zone = zone;
        this.prabhag_ward = prabhag_ward;
        this.apartment = apartment;
        this.flat_no = flat_no;
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



    public String getWatersupply_id() {
        return watersupply_id;
    }

    public void setWatersupply_id(String watersupply_id) {
        this.watersupply_id = watersupply_id;
    }

    public String getCaste() {
        return caste;
    }

    public void setCaste(String caste) {
        this.caste = caste;
    }

    public String getVoting_center() {
        return voting_center;
    }

    public void setVoting_center(String voting_center) {
        this.voting_center = voting_center;
    }

    public String getHouse_no() {
        return house_no;
    }

    public void setHouse_no(String house_no) {
        this.house_no = house_no;
    }

    public String getRow_id() {
        return row_id;
    }

    public void setRow_id(String row_id) {
        this.row_id = row_id;
    }

    public String getSeries_id() {
        return series_id;
    }

    public void setSeries_id(String series_id) {
        this.series_id = series_id;
    }

    public String getColony_id() {
        return colony_id;
    }

    public void setColony_id(String colony_id) {
        this.colony_id = colony_id;
    }

    public String getSurvey_id() {
        return survey_id;
    }

    public void setSurvey_id(String survey_id) {
        this.survey_id = survey_id;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMiddle_name() {
        return middle_name;
    }

    public void setMiddle_name(String middle_name) {
        this.middle_name = middle_name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
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

    public String getStatus_id() {
        return status_id;
    }

    public void setStatus_id(String status_id) {
        this.status_id = status_id;
    }

    public String getRelation_id() {
        return relation_id;
    }

    public void setRelation_id(String relation_id) {
        this.relation_id = relation_id;
    }

    public String getVoter_id() {
        return voter_id;
    }

    public void setVoter_id(String voter_id) {
        this.voter_id = voter_id;
    }

    public String getAdhar_card() {
        return adhar_card;
    }

    public void setAdhar_card(String adhar_card) {
        this.adhar_card = adhar_card;
    }

    public String getConstituency() {
        return constituency;
    }

    public void setConstituency(String constituency) {
        this.constituency = constituency;
    }

    public String getCity_village() {
        return city_village;
    }

    public void setCity_village(String city_village) {
        this.city_village = city_village;
    }

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }

    public String getPrabhag_ward() {
        return prabhag_ward;
    }

    public void setPrabhag_ward(String prabhag_ward) {
        this.prabhag_ward = prabhag_ward;
    }

    public String getApartment() {
        return apartment;
    }

    public void setApartment(String apartment) {
        this.apartment = apartment;
    }

    public String getFlat_no() {
        return flat_no;
    }

    public void setFlat_no(String flat_no) {
        this.flat_no = flat_no;
    }
}
