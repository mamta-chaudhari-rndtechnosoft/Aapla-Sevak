package com.vinodmapari.aaplasevak.Model;

public class Status {
    String id, status_name;

    public Status(String id, String status_name) {
        this.id = id;
        this.status_name = status_name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatus_name() {
        return status_name;
    }

    public void setStatus_name(String status_name) {
        this.status_name = status_name;
    }
}
