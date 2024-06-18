package com.vinodmapari.aaplasevak.Model;

public class HouseDetail {
    private String name;
    private String middleName;
    private String surname;
    private String votingCenter;
    private String boothNo;
    private int votingSrNo;
    private String voterId;

    public HouseDetail(String name, String middleName, String surname, String votingCenter, String boothNo, int votingSrNo, String voterId) {
        this.name = name;
        this.middleName = middleName;
        this.surname = surname;
        this.votingCenter = votingCenter;
        this.boothNo = boothNo;
        this.votingSrNo = votingSrNo;
        this.voterId = voterId;
    }

    // Getters
    public String getName() {
        return name;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getSurname() {
        return surname;
    }

    public String getVotingCenter() {
        return votingCenter;
    }

    public String getBoothNo() {
        return boothNo;
    }

    public int getVotingSrNo() {
        return votingSrNo;
    }

    public String getVoterId() {
        return voterId;
    }
}

