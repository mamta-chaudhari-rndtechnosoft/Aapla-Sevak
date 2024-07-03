package com.vinodmapari.aaplasevak.Model;

public class EditMemberRespponseData {
    String status;
    String message;

    public EditMemberRespponseData() {
    }

    public EditMemberRespponseData(String status, String message) {
        this.status = status;
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
