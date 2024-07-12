package com.vinodmapari.aaplasevak.Model;

public class SendSmsBody {

    int series_id;
    int row_id;
    int colony_id;
    int watersupply_id;
    int constituency;
    int city_village;
    int zone;
    int prabhag_ward;
    String template_desc;


    public SendSmsBody() {
    }

    public SendSmsBody(int series_id, int row_id, int colony_id, int watersupply_id, int constituency, int city_village, int zone, int prabhag_ward, String template_desc) {
        this.series_id = series_id;
        this.row_id = row_id;
        this.colony_id = colony_id;
        this.watersupply_id = watersupply_id;
        this.constituency = constituency;
        this.city_village = city_village;
        this.zone = zone;
        this.prabhag_ward = prabhag_ward;
        this.template_desc = template_desc;
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
                ", template_desc=" + template_desc + '\'' + '}';
    }
}
