package com.vinodmapari.aaplasevak.Model;

import com.google.gson.annotations.SerializedName;

public class VoterCountItem {

    @SerializedName("error")
    private String error;

    @SerializedName("voter_count")
    private String voterCount;

    public VoterCountItem(String error, String voterCount) {
        this.error = error;
        this.voterCount = voterCount;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getVoterCount() {
        return voterCount;
    }

    public void setVoterCount(String voterCount) {
        this.voterCount = voterCount;
    }
}
