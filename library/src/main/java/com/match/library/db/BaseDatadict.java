package com.match.library.db;

import java.io.Serializable;

/**
 * @author ryzhang
 * @date 2017/10/18
 * @time 11:50
 * Project
 */
public class BaseDatadict implements Cloneable, Serializable {
    @Override
    public BaseDatadict clone() {
        BaseDatadict datadict = null;
        try {
            datadict = (BaseDatadict) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return datadict;
    }
}
