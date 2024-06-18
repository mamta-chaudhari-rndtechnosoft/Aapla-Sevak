package com.vinodmapari.aaplasevak.Model;

public class MainMemberDetail
{
    String id,house_no,series_id,row_id,colony_id,surname,caste,watersupply_id;

    public MainMemberDetail(String id, String house_no, String series_id, String row_id, String colony_id, String surname, String caste, String watersupply_id) {
        this.id = id;
        this.house_no = house_no;
        this.series_id = series_id;
        this.row_id = row_id;
        this.colony_id = colony_id;
        this.surname = surname;
        this.caste = caste;
        this.watersupply_id = watersupply_id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHouse_no() {
        return house_no;
    }

    public void setHouse_no(String house_no) {
        this.house_no = house_no;
    }

    public String getSeries_id() {
        return series_id;
    }

    public void setSeries_id(String series_id) {
        this.series_id = series_id;
    }

    public String getRow_id() {
        return row_id;
    }

    public void setRow_id(String row_id) {
        this.row_id = row_id;
    }

    public String getColony_id() {
        return colony_id;
    }

    public void setColony_id(String colony_id) {
        this.colony_id = colony_id;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getCaste() {
        return caste;
    }

    public void setCaste(String caste) {
        this.caste = caste;
    }

    public String getWatersupply_id() {
        return watersupply_id;
    }

    public void setWatersupply_id(String watersupply_id) {
        this.watersupply_id = watersupply_id;
    }
}
