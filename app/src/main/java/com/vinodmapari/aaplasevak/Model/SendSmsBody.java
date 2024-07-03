package com.vinodmapari.aaplasevak.Model;

public class SendSmsBody {

    int series_id;
    int row_id;
    int colony_id;
    int watersupply_id;
    String template_desc;


    public SendSmsBody() {
    }

    public SendSmsBody(int series_id, int row_id, int colony_id,
                       int watersupply_id, String template_desc) {
        this.series_id = series_id;
        this.row_id = row_id;
        this.colony_id = colony_id;
        this.watersupply_id = watersupply_id;
        this.template_desc = template_desc;
    }
}
