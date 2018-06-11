package com.ryzhang.android_demo.bean.address;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ryzhang
 * @date 2017/11/16
 * @time 16:35
 * Project 市
 */
public class CityChildsBean implements Parcelable {

    private String code;
    private String name;
    private List<CountyChildsBean> childs;

    protected CityChildsBean(Parcel in) {
        code = in.readString();
        name = in.readString();
        this.childs = new ArrayList();
        in.readList(this.childs, CountyChildsBean.class.getClassLoader());
    }

    public static final Creator<CityChildsBean> CREATOR = new Creator<CityChildsBean>() {
        @Override
        public CityChildsBean createFromParcel(Parcel in) {
            return new CityChildsBean(in);
        }

        @Override
        public CityChildsBean[] newArray(int size) {
            return new CityChildsBean[size];
        }
    };

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<CountyChildsBean> getChilds() {
        return childs;
    }

    public void setChilds(List<CountyChildsBean> childs) {
        this.childs = childs;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(code);
        parcel.writeString(name);
        parcel.writeList(childs);
    }
    public String toString() {
        return this.name;
    }
}
