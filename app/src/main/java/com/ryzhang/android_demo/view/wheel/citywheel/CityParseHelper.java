package com.ryzhang.android_demo.view.wheel.citywheel;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.match.library.utils.common.AssetsUtils;
import com.ryzhang.android_demo.bean.address.AddressBean;
import com.ryzhang.android_demo.bean.address.CityChildsBean;
import com.ryzhang.android_demo.bean.address.CountyChildsBean;
import com.ryzhang.android_demo.view.wheel.CityConfig;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 省市区解析辅助类
 * 作者：liji on 2017/11/4 10:09
 * 邮箱：lijiwork@sina.com
 * QQ ：275137657
 */

public class CityParseHelper {

    /**
     * 省份数据
     */
    private ArrayList<AddressBean> mAddressBeanArrayList = new ArrayList<>();

    /**
     * 城市数据
     */
    private ArrayList<ArrayList<CityChildsBean>> mCityChildsBeanArrayList;

    /**
     * 地区数据
     */
    private ArrayList<ArrayList<ArrayList<CountyChildsBean>>> mCountyChildsBeanArrayList;

    private AddressBean[] mProvinceBeenArray;

    private AddressBean mAddressBean;

    private CityChildsBean mCityChildsBean;

    private CountyChildsBean mCountyChildsBean;

    private CityConfig config;

    /**
     * key - 省 value - 市
     */
    private Map<String, CityChildsBean[]> mPro_CityMap = new HashMap<String, CityChildsBean[]>();

    /**
     * key - 市 values - 区
     */
    private Map<String, CountyChildsBean[]> mCity_DisMap = new HashMap<String, CountyChildsBean[]>();

    /**
     * key - 区 values - 邮编
     */
    private Map<String, CountyChildsBean> mDisMap = new HashMap<String, CountyChildsBean>();

    /**
     * 默认加载的城市数据type，只包含基本的省市区名称，不包含code、经纬度、拼音等数据
     */
//    private String cityJsonDataType = "simple_cities_pro_city.json";
    private String cityJsonDataType = "address.json";

    public ArrayList<AddressBean> getAddressBeanArrayList() {
        return mAddressBeanArrayList;
    }

    public void setAddressBeanArrayList(ArrayList<AddressBean> AddressBeanArrayList) {
        mAddressBeanArrayList = AddressBeanArrayList;
    }

    public ArrayList<ArrayList<CityChildsBean>> getCityChildsBeanArrayList() {
        return mCityChildsBeanArrayList;
    }

    public void setCityChildsBeanArrayList(ArrayList<ArrayList<CityChildsBean>> CityChildsBeanArrayList) {
        mCityChildsBeanArrayList = CityChildsBeanArrayList;
    }

    public ArrayList<ArrayList<ArrayList<CountyChildsBean>>> getCountyChildsBeanArrayList() {
        return mCountyChildsBeanArrayList;
    }

    public void setCountyChildsBeanArrayList(ArrayList<ArrayList<ArrayList<CountyChildsBean>>> CountyChildsBeanArrayList) {
        mCountyChildsBeanArrayList = CountyChildsBeanArrayList;
    }

    public AddressBean[] getProvinceBeenArray() {
        return mProvinceBeenArray;
    }

    public void setProvinceBeenArray(AddressBean[] provinceBeenArray) {
        mProvinceBeenArray = provinceBeenArray;
    }

    public AddressBean getAddressBean() {
        return mAddressBean;
    }

    public void setAddressBean(AddressBean AddressBean) {
        mAddressBean = AddressBean;
    }

    public CityChildsBean getCityChildsBean() {
        return mCityChildsBean;
    }

    public void setCityChildsBean(CityChildsBean CityChildsBean) {
        mCityChildsBean = CityChildsBean;
    }

    public CountyChildsBean getCountyChildsBean() {
        return mCountyChildsBean;
    }

    public void setCountyChildsBean(CountyChildsBean CountyChildsBean) {
        mCountyChildsBean = CountyChildsBean;
    }

    public Map<String, CityChildsBean[]> getPro_CityMap() {
        return mPro_CityMap;
    }

    public void setPro_CityMap(Map<String, CityChildsBean[]> pro_CityMap) {
        mPro_CityMap = pro_CityMap;
    }

    public Map<String, CountyChildsBean[]> getCity_DisMap() {
        return mCity_DisMap;
    }

    public void setCity_DisMap(Map<String, CountyChildsBean[]> city_DisMap) {
        mCity_DisMap = city_DisMap;
    }

    public Map<String, CountyChildsBean> getDisMap() {
        return mDisMap;
    }

    public void setDisMap(Map<String, CountyChildsBean> disMap) {
        mDisMap = disMap;
    }

    public CityParseHelper(CityConfig config) {
        this.config = config;
    }

    /**
     * 初始化数据，解析json数据
     */
    public void initData(Context context) {

//        //如果只显示省份的话
//        if (config.getWheelType() == CityConfig.WheelType.PRO) {
//            if (config.getCityInfoType() == CityConfig.CityInfoType.DETAIL) {
//                cityJsonDataType = "simple_cities_pro_city_dis.json";
//            }
//            else {
//                cityJsonDataType = "simple_cities_pro.json";
//            }
//        }
//        else {
//            if (config.getCityInfoType() == CityConfig.CityInfoType.DETAIL) {
//                cityJsonDataType = "simple_cities_pro_city_dis.json";
//            }
//            else {
//                cityJsonDataType = "simple_cities_pro_city.json";
//            }
//
//        }
//        cityJsonDataType="address.json";
        String cityJson = AssetsUtils.getStringContent(context, cityJsonDataType);
        Type type = new TypeToken<ArrayList<AddressBean>>() {
        }.getType();

        mAddressBeanArrayList = new Gson().fromJson(cityJson, type);

        if (mAddressBeanArrayList == null || mAddressBeanArrayList.isEmpty()) {
            return;
        }

        mCityChildsBeanArrayList = new ArrayList<>(mAddressBeanArrayList.size());
        mCountyChildsBeanArrayList = new ArrayList<>(mAddressBeanArrayList.size());

        //*/ 初始化默认选中的省、市、区，默认选中第一个省份的第一个市区中的第一个区县
        if (mAddressBeanArrayList != null && !mAddressBeanArrayList.isEmpty()) {
            mAddressBean = mAddressBeanArrayList.get(0);
            List<CityChildsBean> cityList = mAddressBean.getChilds();
            if (cityList != null && !cityList.isEmpty() && cityList.size() > 0) {
                mCityChildsBean = cityList.get(0);
                List<CountyChildsBean> districtList = mCityChildsBean.getChilds();
                if (districtList != null && !districtList.isEmpty() && districtList.size() > 0) {
                    mCountyChildsBean = districtList.get(0);
                }
            }
        }

        //省份数据
        mProvinceBeenArray = new AddressBean[mAddressBeanArrayList.size()];

        for (int p = 0; p < mAddressBeanArrayList.size(); p++) {

            //遍历每个省份
            AddressBean itemProvince = mAddressBeanArrayList.get(p);

            //当现实二级或者三级联动时，才会解析该数据
            if (config.getWheelType() == CityConfig.WheelType.PRO_CITY || config.getWheelType() == CityConfig.WheelType.PRO_CITY_DIS) {

                //每个省份对应下面的市
                ArrayList<CityChildsBean> cityList = (ArrayList<CityChildsBean>) itemProvince.getChilds();

                //当前省份下面的所有城市
                CityChildsBean[] cityNames = new CityChildsBean[cityList.size()];

                //遍历当前省份下面城市的所有数据
                for (int j = 0; j < cityList.size(); j++) {
                    cityNames[j] = cityList.get(j);

                    //当前省份下面每个城市下面再次对应的区或者县
                    List<CountyChildsBean> districtList = cityList.get(j).getChilds();
                    if (districtList == null) {
                        break;
                    }
                    CountyChildsBean[] distrinctArray = new CountyChildsBean[districtList.size()];

                    for (int k = 0; k < districtList.size(); k++) {

                        // 遍历市下面所有区/县的数据
                        CountyChildsBean districtModel = districtList.get(k);

                        //存放 省市区-区 数据
                        mDisMap.put(itemProvince.getName() + cityNames[j].getName() + districtList.get(k).getName(),
                                districtModel);

                        distrinctArray[k] = districtModel;

                    }
                    // 市-区/县的数据，保存到mDistrictDatasMap
                    mCity_DisMap.put(itemProvince.getName() + cityNames[j].getName(), distrinctArray);

                }

                // 省-市的数据，保存到mCitisDatasMap
                mPro_CityMap.put(itemProvince.getName(), cityNames);

                mCityChildsBeanArrayList.add(cityList);

                //只有显示三级联动，才会执行
                ArrayList<ArrayList<CountyChildsBean>> array2DistrictLists = new ArrayList<>(cityList.size());

                for (int c = 0; c < cityList.size(); c++) {
                    CityChildsBean CityChildsBean = cityList.get(c);
                    array2DistrictLists.add((ArrayList<CountyChildsBean>) CityChildsBean.getChilds());
                }
                mCountyChildsBeanArrayList.add(array2DistrictLists);

            }
            //赋值所有省份的名称
            mProvinceBeenArray[p] = itemProvince;

        }

    }

}
