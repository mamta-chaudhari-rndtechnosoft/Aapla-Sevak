package com.vinodmapari.aaplasevak.Model;

public class SearchList {
    String id, house_no, series_name, colony_name, row_name, gender, name, middle_name, surname,
            mobile1, mobile2, dob, qualification, caste, status_name, voter_id, adhar_card,
            slot_name, voting_center, member_id,  booth_no, voting_sr_no;


    public SearchList(String id, String house_no, String series_name, String colony_name, String row_name,
                      String gender, String name, String middle_name, String surname, String mobile1,
                      String mobile2, String dob, String qualification, String caste, String status_name,
                      String voter_id, String adhar_card, String slot_name, String voting_center,
                      String member_id,  String booth_no, String voting_sr_no) {
        this.id = id;
        this.house_no = house_no;
        this.series_name = series_name;
        this.colony_name = colony_name;
        this.row_name = row_name;
        this.gender = gender;
        this.name = name;
        this.middle_name = middle_name;
        this.surname = surname;
        this.mobile1 = mobile1;
        this.mobile2 = mobile2;
        this.dob = dob;
        this.qualification = qualification;
        this.caste = caste;
        this.status_name = status_name;
        this.voter_id = voter_id;
        this.adhar_card = adhar_card;
        this.slot_name = slot_name;
        this.voting_center = voting_center;
        this.member_id = member_id;
        this.booth_no = booth_no;
        this.voting_sr_no = voting_sr_no;
    }

    public String getMember_id() {
        return member_id;
    }

    public void setMember_id(String member_id) {
        this.member_id = member_id;
    }

    public String getHouse_n0() {
        return house_no;
    }

    public void setHouse_n0(String house_no) {
        this.house_no = house_no;
    }

    public String getSeries_id() {
        return series_name;
    }

    public void setSeries_id(String series_name) {
        this.series_name = series_name;
    }

    public String getColony_id() {
        return colony_name;
    }

    public void setColony_id(String colony_name) {
        this.colony_name = colony_name;
    }

    public String getRow_id() {
        return row_name;
    }

    public void setRow_id(String row_name) {
        this.row_name = row_name;
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

    public String getStatus_id() {
        return status_name;
    }

    public void setStatus_id(String status_name) {
        this.status_name = status_name;
    }


    public String getWatersupply_id() {
        return slot_name;
    }

    public void setWatersupply_id(String slot_name) {
        this.slot_name = slot_name;
    }

    public String getVoting_center() {
        return voting_center;
    }

    public void setVoting_center(String voting_center) {
        this.voting_center = voting_center;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getBooth_no() {
        return booth_no;
    }

    public void setBooth_no(String booth_no) {
        this.booth_no = booth_no;
    }

    public String getVoting_sr_no() {
        return voting_sr_no;
    }

    public void setVoting_sr_no(String voting_sr_no) {
        this.voting_sr_no = voting_sr_no;
    }
}
