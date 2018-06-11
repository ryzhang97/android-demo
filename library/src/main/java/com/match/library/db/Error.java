package com.match.library.db;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * @author ryzhang
 * @date 2018/1/11
 * @time 17:02
 * Project 错误信息表
 * 、、、、、、
 */
@DatabaseTable(tableName = "tb_error")
public class Error extends BaseDatadict {
    @DatabaseField(columnName = "e_date_time")
    private String dateTime;//日期
    @DatabaseField(columnName = "e_id")
    private String userid;//用户id
    @DatabaseField(columnName = "e_serial")
    private String serial;//手机序号
    @DatabaseField(columnName = "e_modle")
    private String modle;// 手机版本
    @DatabaseField(columnName = "e_version")
    private String version;// 软件版本
    @DatabaseField(columnName = "e_cause")
    private String cause;//错误原因
    @DatabaseField(columnName = "e_look", defaultValue = "false")
    private Boolean look;//是否查看

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public Boolean getLook() {
        return look;
    }

    public void setLook(Boolean look) {
        this.look = look;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public String getModle() {
        return modle;
    }

    public void setModle(String modle) {
        this.modle = modle;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }
}
