package com.vinodmapari.aaplasevak.Model;

public class Relations {
    String id, relation_name;

    public Relations(String id, String relation_name) {
        this.id = id;
        this.relation_name = relation_name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRelations_name() {
        return relation_name;
    }

    public void setRelations_name(String relation_name) {
        this.relation_name = relation_name;
    }
}
