package com.vinodmapari.aaplasevak.Model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class WhatsAppNumberResponseData {

    @SerializedName("WHATSAPP_NO")
    private ArrayList<WhatsAppNumList> whatsAppNumList;

    public WhatsAppNumberResponseData(ArrayList<WhatsAppNumList> whatsAppNumList) {
        this.whatsAppNumList = whatsAppNumList;
    }

    public ArrayList<WhatsAppNumList> getWhatsAppNumList() {
        return whatsAppNumList;
    }

    public void setWhatsAppNumList(ArrayList<WhatsAppNumList> whatsAppNumList) {
        this.whatsAppNumList = whatsAppNumList;
    }
}

