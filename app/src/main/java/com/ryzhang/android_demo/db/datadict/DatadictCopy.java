package com.ryzhang.android_demo.db.datadict;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

/**
 * @author ryzhang
 * @date:2017/8/24 17:45
 */
@DatabaseTable(tableName = "tb_copy")
public class DatadictCopy implements Cloneable, Serializable {
    @DatabaseField(columnName = "Dealer_Id")
    private String Dealer_Id;//经销商代码
    @DatabaseField(columnName = "VIN", id = true)
    private String VIN;//车架号
    @DatabaseField(columnName = "FR_Id")
    private String FR_Id;//登陆的用户名
    @DatabaseField(columnName = "Date_Scanned")
    private String Date_Scanned;//盘点日期
    @DatabaseField(columnName = "Time_Scanned")
    private String Time_Scanned;//盘点时间
    @DatabaseField(columnName = "Site_No")
    private String Site_No;//库存点号
    @DatabaseField(columnName = "QC")
    private String QC;//合格证状态
    @DatabaseField(columnName = "Condition")
    private String Condition;//车况
    @DatabaseField(columnName = "S_T_O")
    private String S_T_O;//车辆状态
    @DatabaseField(columnName = "ODO")
    private String ODO;//公里数
    @DatabaseField(columnName = "Sold_Date")
    private String Sold_Date;//销售日期
    @DatabaseField(columnName = "PMT_Date")
    private String PMT_Date;
    @DatabaseField(columnName = "PIF_Date")
    private String PIF_Date;//还款日期
    @DatabaseField(columnName = "Method")
    private String Method;//盘点方式
    @DatabaseField(columnName = "Latitude")
    private String Latitude;//纬度
    @DatabaseField(columnName = "Longitude")
    private String Longitude;//经度
    @DatabaseField(columnName = "RFID_Tag")
    private String RFID_Tag;//RFID编号
    @DatabaseField(columnName = "Create_Time")
    private String Create_Time;
    @DatabaseField(columnName = "datadict_index", canBeNull = false, foreign = true, foreignAutoRefresh = true)
    private DatadictIndex datadictIndex;//索引

    public String getCreate_Time() {
        return Create_Time;
    }

    public void setCreate_Time(String create_Time) {
        Create_Time = create_Time;
    }

    public String getDealer_Id() {
        return Dealer_Id;
    }

    public void setDealer_Id(String dealer_Id) {
        Dealer_Id = dealer_Id;
    }

    public String getVIN() {
        return VIN;
    }

    public void setVIN(String VIN) {
        this.VIN = VIN;
    }

    public String getFR_Id() {
        return FR_Id;
    }

    public void setFR_Id(String FR_Id) {
        this.FR_Id = FR_Id;
    }

    public String getDate_Scanned() {
        return Date_Scanned;
    }

    public void setDate_Scanned(String date_Scanned) {
        Date_Scanned = date_Scanned;
    }

    public String getTime_Scanned() {
        return Time_Scanned;
    }

    public void setTime_Scanned(String time_Scanned) {
        Time_Scanned = time_Scanned;
    }

    public String getSite_No() {
        return Site_No;
    }

    public void setSite_No(String site_No) {
        Site_No = site_No;
    }

    public String getQC() {
        return QC;
    }

    public void setQC(String QC) {
        this.QC = QC;
    }

    public String getCondition() {
        return Condition;
    }

    public void setCondition(String condition) {
        Condition = condition;
    }

    public String getS_T_O() {
        return S_T_O;
    }

    public void setS_T_O(String s_T_O) {
        S_T_O = s_T_O;
    }

    public String getODO() {
        return ODO;
    }

    public void setODO(String ODO) {
        this.ODO = ODO;
    }

    public String getSold_Date() {
        return Sold_Date;
    }

    public void setSold_Date(String sold_Date) {
        Sold_Date = sold_Date;
    }

    public String getPMT_Date() {
        return PMT_Date;
    }

    public void setPMT_Date(String PMT_Date) {
        this.PMT_Date = PMT_Date;
    }

    public String getPIF_Date() {
        return PIF_Date;
    }

    public void setPIF_Date(String PIF_Date) {
        this.PIF_Date = PIF_Date;
    }

    public String getMethod() {
        return Method;
    }

    public void setMethod(String method) {
        Method = method;
    }

    public String getLatitude() {
        return Latitude;
    }

    public void setLatitude(String latitude) {
        Latitude = latitude;
    }

    public String getLongitude() {
        return Longitude;
    }

    public void setLongitude(String longitude) {
        Longitude = longitude;
    }

    public String getRFID_Tag() {
        return RFID_Tag;
    }

    public void setRFID_Tag(String RFID_Tag) {
        this.RFID_Tag = RFID_Tag;
    }

    @Override
    public String toString() {
        return "Datadict{" +
                "Dealer_Id='" + Dealer_Id + '\'' +
                ", VIN='" + VIN + '\'' +
                ", FR_Id='" + FR_Id + '\'' +
                ", Date_Scanned='" + Date_Scanned + '\'' +
                ", Time_Scanned='" + Time_Scanned + '\'' +
                ", Site_No='" + Site_No + '\'' +
                ", QC='" + QC + '\'' +
                ", Condition='" + Condition + '\'' +
                ", S_T_O='" + S_T_O + '\'' +
                ", ODO='" + ODO + '\'' +
                ", Sold_Date='" + Sold_Date + '\'' +
                ", PMT_Date='" + PMT_Date + '\'' +
                ", PIF_Date='" + PIF_Date + '\'' +
                ", Method='" + Method + '\'' +
                ", Latitude='" + Latitude + '\'' +
                ", Longitude='" + Longitude + '\'' +
                ", RFID_Tag='" + RFID_Tag + '\'' +
                '}';
    }

    public DatadictIndex getDatadictIndex() {
        return datadictIndex;
    }

    public void setDatadictIndex(DatadictIndex datadictIndex) {
        this.datadictIndex = datadictIndex;
    }
}
