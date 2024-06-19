package com.vinodmapari.aaplasevak.Model;

public class DeleteMemberResponseData {


    String success;
    String message;

    public DeleteMemberResponseData() {
    }

    public DeleteMemberResponseData(String success, String message) {
        this.success = success;
        this.message = message;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }
}
