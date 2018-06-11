package com.ryzhang.android_demo.db.datadict;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

/**
 * @author ryzhang
 * @date 2017/10/24
 * @time 15:21
 * Project 用户表
 */
@DatabaseTable(tableName = "tb_index")
public class DatadictIndex implements Cloneable, Serializable {
    @DatabaseField(columnName = "index_id", canBeNull = false, id = true)
    private long id;//索引id(主键)
    @DatabaseField(columnName = "FR_Id", canBeNull = false)
    private String FR_Id;//登陆的用户名
    @DatabaseField(columnName = "index_datetime", canBeNull = false)
    private String dateTime;//日期
    @ForeignCollectionField
    private ForeignCollection<Datadict> datadicts;

    protected DatadictIndex() {
    }

    public DatadictIndex(String FR_Id,String dataTime) {
        this.FR_Id = FR_Id;
        this.dateTime=dataTime;
        id = System.currentTimeMillis();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFR_Id() {
        return FR_Id;
    }

    public void setFR_Id(String FR_Id) {
        this.FR_Id = FR_Id;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public ForeignCollection<Datadict> getDatadicts() {
        return datadicts;
    }

    public void setDatadicts(ForeignCollection<Datadict> datadicts) {
        this.datadicts = datadicts;
    }
}
