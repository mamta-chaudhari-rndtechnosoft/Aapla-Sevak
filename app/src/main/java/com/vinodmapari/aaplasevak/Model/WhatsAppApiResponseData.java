package com.vinodmapari.aaplasevak.Model;

public class WhatsAppApiResponseData {

    String status;
    String message;

    public WhatsAppApiResponseData() {
    }

    public WhatsAppApiResponseData(String status, String message) {
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
