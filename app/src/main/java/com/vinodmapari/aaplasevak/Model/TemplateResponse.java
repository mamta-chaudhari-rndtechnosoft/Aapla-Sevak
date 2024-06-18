package com.vinodmapari.aaplasevak.Model;

import java.util.List;

public class TemplateResponse {
    private List<TemplateDesc> TEMPLATE_DESC;

    public List<TemplateDesc> getTEMPLATE_DESC() {
        return TEMPLATE_DESC;
    }

    public void setTEMPLATE_DESC(List<TemplateDesc> TEMPLATE_DESC) {
        this.TEMPLATE_DESC = TEMPLATE_DESC;
    }

    public static class TemplateDesc {
        private String error;
        private String id;
        private String template;
        private String template_desc;

        public String getError() {
            return error;
        }

        public void setError(String error) {
            this.error = error;
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

        public void setTemplate(String template) {
            this.template = template;
        }

        public String getTemplateDesc() {
            return template_desc;
        }

        public void setTemplateDesc(String template_desc) {
            this.template_desc = template_desc;
        }
    }
}
