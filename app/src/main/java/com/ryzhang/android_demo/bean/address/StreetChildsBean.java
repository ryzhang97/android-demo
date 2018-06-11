package com.ryzhang.android_demo.bean.address;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author ryzhang
 * @date 2017/11/16
 * @time 16:36
 * Project 街道
 */
public class StreetChildsBean implements Parcelable {
    private String code;
    private String name;

    protected StreetChildsBean(Parcel in) {
        code = in.readString();
        name = in.readString();
    }

    public static final Creator<StreetChildsBean> CREATOR = new Creator<StreetChildsBean>() {
        @Override
        public StreetChildsBean createFromParcel(Parcel in) {
            return new StreetChildsBean(in);
        }

        @Override
        public StreetChildsBean[] newArray(int size) {
            return new StreetChildsBean[size];
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(code);
        parcel.writeString(name);
    }
    public String toString() {
        return this.name;
    }
}
