package com.example.ru.smartsoft.csv.reader.model;

public class FormCount {

    public FormCount() {
    }

    public FormCount(String formid, Integer count) {
        this.formid = formid;
        this.count = count;
    }

    private String formid;
    private Integer count;

    public String getFormid() {
        return formid;
    }

    public void setFormid(String formid) {
        this.formid = formid;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}