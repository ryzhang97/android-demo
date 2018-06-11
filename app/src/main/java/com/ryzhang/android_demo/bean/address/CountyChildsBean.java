package com.ryzhang.android_demo.bean.address;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ryzhang
 * @date 2017/11/16
 * @time 16:36
 * Project 区县
 */
public class CountyChildsBean implements Parcelable {
    private String code;
    private String name;
    private List<StreetChildsBean> childs;

    protected CountyChildsBean(Parcel in) {
        code = in.readString();
        name = in.readString();
        this.childs = new ArrayList();
        in.readList(this.childs, StreetChildsBean.class.getClassLoader());
    }

    public static final Creator<CountyChildsBean> CREATOR = new Creator<CountyChildsBean>() {
        @Override
        public CountyChildsBean createFromParcel(Parcel in) {
            return new CountyChildsBean(in);
        }

        @Override
        public CountyChildsBean[] newArray(int size) {
            return new CountyChildsBean[size];
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

    public List<StreetChildsBean> getChilds() {
        return childs;
    }

    public void setChilds(List<StreetChildsBean> childs) {
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
