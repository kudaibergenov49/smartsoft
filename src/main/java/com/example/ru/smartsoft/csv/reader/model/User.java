package com.example.ru.smartsoft.csv.reader.model;

import javax.persistence.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Table(name = "usr")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ssoid;//Уникальный идентификатор пользователей
    private Long ts;//Время
    private String grp;//Группа события
    private String type;//Тип события
    private String subtype;//Подтип события
    private String url;//Адрес с которого пришло событие
    private String orgid;//Организация предоставляющая услугу
    private String formid;//Идентификатор формы
    private String code;
    private String ltpa;
    private String sudirresponse;
    private Date ymdh;

    public User() {

    }

    public User(String ssoid, Long ts, String grp, String type, String subtype, String url, String orgid, String formid,
                String code, String ltpa, String sudirresponse, Date ymdh) {
        this.ssoid = ssoid;
        this.ts = ts;
        this.grp = grp;
        this.type = type;
        this.subtype = subtype;
        this.url = url;
        this.orgid = orgid;
        this.formid = formid;
        this.code = code;
        this.ltpa = ltpa;
        this.sudirresponse = sudirresponse;
        this.ymdh = ymdh;
    }

    public String getSsoid() {
        return ssoid;
    }

    public void setSsoid(String ssoid) {
        this.ssoid = ssoid;
    }

    public Long getTs() {
        return ts;
    }

    public void setTs(Long ts) {
        this.ts = ts;
    }

    public String getGrp() {
        return grp;
    }

    public void setGrp(String grp) {
        this.grp = grp;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSubtype() {
        return subtype;
    }

    public void setSubtype(String subtype) {
        this.subtype = subtype;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getOrgid() {
        return orgid;
    }

    public void setOrgid(String orgid) {
        this.orgid = orgid;
    }

    public String getFormid() {
        return formid;
    }

    public void setFormid(String formid) {
        this.formid = formid;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLtpa() {
        return ltpa;
    }

    public void setLtpa(String ltpa) {
        this.ltpa = ltpa;
    }

    public String getSudirresponse() {
        return sudirresponse;
    }

    public void setSudirresponse(String sudirresponse) {
        this.sudirresponse = sudirresponse;
    }

    public Date getYmdh() {
        return ymdh;
    }

    public void setYmdh(String ymdh) throws ParseException {
        this.ymdh = new SimpleDateFormat("yyyy-MM-dd-HH").parse(ymdh);
    }

    @Override
    public String toString() {
        return "ssoid: " + this.ssoid +
                ", ts: " + this.ts.toString() +
                ", grp: " + this.grp +
                ", type: " + this.type +
                ", subtype: " + this.subtype +
                ", url: " + this.url +
                ", orgid: " + this.orgid +
                ", formid: " + this.formid +
                ", code: " + this.code +
                ", ltpa: " + this.ltpa +
                ", sudirresponse: " + this.sudirresponse +
                ", ymdh: " + this.ymdh.toString();
    }
}