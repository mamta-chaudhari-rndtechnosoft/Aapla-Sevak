package com.vinodmapari.aaplasevak.Model;

public class GetContact {
    String id, name,mobile1,middle_name,surname;
    private boolean isMessageSent = false;

    public GetContact(String id, String name, String mobile1, String middle_name, String surname) {
        this.id = id;
        this.name = name;
        this.mobile1 = mobile1;
        this.middle_name = middle_name;
        this.surname = surname;
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

    public String getMobile1() {
        return mobile1;
    }

    public void setMobile2(String mobile1) {
        this.mobile1 = mobile1;
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


    public boolean isMessageSent() {
        return isMessageSent;
    }

    public void setMessageSent(boolean messageSent) {
        isMessageSent = messageSent;
    }
}
