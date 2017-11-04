package com.ryzhang.android_demo.db.datadict;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;


/**
 * @author ryzhang
 * @date 2017/10/26
 * @time 9:54
 * Project 签到表
 */
@DatabaseTable(tableName = "tb_sign")
public class Sign {
    @DatabaseField(columnName = "sign_id", generatedId = true)
    private int id;//id
    @DatabaseField(columnName = "sign_type", defaultValue = "其他")
    private String type;//工作类型
    @DatabaseField(columnName = "sign_time", canBeNull = false)
    private float time;//工时
    //    @DatabaseField(columnName = "sign_datetime", canBeNull = false, dataType = DataType.DATE_TIME)
//    private String dateTime;//日期
    @DatabaseField(columnName = "sign_datetime", canBeNull = false, dataType = DataType.DATE_STRING)
    private Date dateTime;//日期
    @DatabaseField(columnName = "sing_employee", canBeNull = false, foreign = true, foreignAutoRefresh = true)
    private Employee employee;//员工

    protected Sign() {
    }

//    public Sign(Employee employee, String dataTime, float time) {
//        setEmployee(employee);
//        setDateTime(dataTime);
//        setTime(time);
//    }

    //    public Sign(Employee employee, String dataTime, float time, String type) {
//        this(employee, dataTime, time);
//        setType(type);
//    }

    public Sign(Employee employee, Date dataTime, float time) {
        setEmployee(employee);
        setDateTime(dataTime);
        setTime(time);
    }

    public Sign(Employee employee, Date dataTime, float time, String type) {
        this(employee, dataTime, time);
        setType(type);
    }

    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public float getTime() {
        return time;
    }

    public void setTime(float time) {
        this.time = time;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }
//    public String getDateTime() {
//        return dateTime;
//    }
//
//    public void setDateTime(String dateTime) {
//        this.dateTime = dateTime;
//    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
