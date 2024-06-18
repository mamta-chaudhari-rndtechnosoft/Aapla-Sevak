package com.vinodmapari.aaplasevak.Model;

public class Template
{
    String id,template;

    public Template(String id, String template) {
        this.id = id;
        this.template = template;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template)
    {
        this.template = template;
    }
}
