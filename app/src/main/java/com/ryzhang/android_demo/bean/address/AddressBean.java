package com.ryzhang.android_demo.bean.address;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ryzhang
 * @date 2017/11/16
 * @time 16:29
 * Project 省
 */
public class AddressBean implements Parcelable {
    private String code;
    private String name;
    private List<CityChildsBean> childs;

    protected AddressBean(Parcel in) {
        code = in.readString();
        name = in.readString();
        this.childs = new ArrayList();
        in.readList(this.childs, CityChildsBean.class.getClassLoader());
    }

    public static final Creator<AddressBean> CREATOR = new Creator<AddressBean>() {
        @Override
        public AddressBean createFromParcel(Parcel in) {
            return new AddressBean(in);
        }

        @Override
        public AddressBean[] newArray(int size) {
            return new AddressBean[size];
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

    public List<CityChildsBean> getChilds() {
        return childs;
    }

    public void setChilds(List<CityChildsBean> childs) {
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
