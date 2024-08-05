package com.vinodmapari.aaplasevak.Model;

import com.google.gson.annotations.SerializedName;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class WhatsAppApiBody {

    private  RequestBody series_id;
    private RequestBody colony_id;
    private RequestBody row_id;
    private RequestBody watersupply_id;
    private RequestBody constituency;
    private RequestBody city_village;
    private RequestBody zone;
    private RequestBody prabhag_ward;
    private RequestBody template_desc;
    private MultipartBody.Part file;

    public WhatsAppApiBody() {
    }


    public WhatsAppApiBody(RequestBody series_id, RequestBody colony_id, RequestBody row_id, RequestBody watersupply_id, RequestBody constituency, RequestBody city_village, RequestBody zone, RequestBody prabhag_ward, RequestBody template_desc) {
        this.series_id = series_id;
        this.colony_id = colony_id;
        this.row_id = row_id;
        this.watersupply_id = watersupply_id;
        this.constituency = constituency;
        this.city_village = city_village;
        this.zone = zone;
        this.prabhag_ward = prabhag_ward;
        this.template_desc = template_desc;
    }

    public WhatsAppApiBody(RequestBody series_id, RequestBody colony_id, RequestBody row_id, RequestBody watersupply_id, RequestBody constituency, RequestBody city_village, RequestBody zone, RequestBody prabhag_ward, RequestBody template_desc, MultipartBody.Part file) {
        this.series_id = series_id;
        this.colony_id = colony_id;
        this.row_id = row_id;
        this.watersupply_id = watersupply_id;
        this.constituency = constituency;
        this.city_village = city_village;
        this.zone = zone;
        this.prabhag_ward = prabhag_ward;
        this.template_desc = template_desc;
        this.file = file;
    }

    public RequestBody getSeries_id() {
        return series_id;
    }

    public void setSeries_id(RequestBody series_id) {
        this.series_id = series_id;
    }

    public RequestBody getColony_id() {
        return colony_id;
    }

    public void setColony_id(RequestBody colony_id) {
        this.colony_id = colony_id;
    }

    public RequestBody getRow_id() {
        return row_id;
    }

    public void setRow_id(RequestBody row_id) {
        this.row_id = row_id;
    }

    public RequestBody getWatersupply_id() {
        return watersupply_id;
    }

    public void setWatersupply_id(RequestBody watersupply_id) {
        this.watersupply_id = watersupply_id;
    }

    public RequestBody getConstituency() {
        return constituency;
    }

    public void setConstituency(RequestBody constituency) {
        this.constituency = constituency;
    }

    public RequestBody getCity_village() {
        return city_village;
    }

    public void setCity_village(RequestBody city_village) {
        this.city_village = city_village;
    }

    public RequestBody getZone() {
        return zone;
    }

    public void setZone(RequestBody zone) {
        this.zone = zone;
    }

    public RequestBody getPrabhag_ward() {
        return prabhag_ward;
    }

    public void setPrabhag_ward(RequestBody prabhag_ward) {
        this.prabhag_ward = prabhag_ward;
    }

    public RequestBody getTemplate_desc() {
        return template_desc;
    }

    public void setTemplate_desc(RequestBody template_desc) {
        this.template_desc = template_desc;
    }

    public MultipartBody.Part getFile() {
        return file;
    }

    public void setFile(MultipartBody.Part file) {
        this.file = file;
    }

    @Override
    public String toString() {
        return "SendSmsBody{" +
                "series_id='" + series_id + '\'' +
                ", row_id='" + row_id + '\'' +
                ", colony_id='" + colony_id + '\'' +
                ", watersupply_id='" + watersupply_id + '\'' +
                ", constituency='" + constituency + '\'' +
                ", zone='" + zone + '\'' +
                ", city_village='" + city_village + '\'' +
                ", prabhag_ward='" + prabhag_ward + '\'' +
                ", template_desc=" + template_desc + '\'' +
                ", file=" + file +
                '}';
    }

}
