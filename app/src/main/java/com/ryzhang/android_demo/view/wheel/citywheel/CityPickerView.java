package com.ryzhang.android_demo.view.wheel.citywheel;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.match.library.utils.common.WindowUtils;
import com.ryzhang.android_demo.R;
import com.ryzhang.android_demo.bean.address.AddressBean;
import com.ryzhang.android_demo.bean.address.CityChildsBean;
import com.ryzhang.android_demo.bean.address.CountyChildsBean;
import com.ryzhang.android_demo.view.wheel.CityConfig;
import com.ryzhang.android_demo.view.wheel.OnWheelChangedListener;
import com.ryzhang.android_demo.view.wheel.WheelView;
import com.ryzhang.android_demo.view.wheel.adapters.ArrayWheelAdapter;
import com.ryzhang.android_demo.view.wheel.interfaces.CanShow;
import com.ryzhang.android_demo.view.wheel.interfaces.OnCityItemClickListener;


/**
 * 省市区三级选择
 * 作者：liji on 2015/12/17 10:40
 * 邮箱：lijiwork@sina.com
 */
public class CityPickerView implements CanShow, OnWheelChangedListener {

    private String TAG = "citypicker_log";

    private PopupWindow popwindow;

    private View popview;

    private WheelView mViewAddress;

    private WheelView mViewCity;

    private WheelView mViewCountyChilds;

    private LinearLayout mRelativeTitleBg;

    private TextView mTvOK;

    private TextView mTvTitle;

    private TextView mTvCancel;

    private OnCityItemClickListener mBaseListener;

    private CityParseHelper parseHelper;

    private CityConfig config;

    public void setOnCityItemClickListener(OnCityItemClickListener listener) {
        mBaseListener = listener;
    }

    public CityPickerView(final CityConfig config) {
        this.config = config;
        parseHelper = new CityParseHelper(config);
        LayoutInflater layoutInflater = LayoutInflater.from(config.getContext());
        popview = layoutInflater.inflate(R.layout.app_popup_picker, null);

        mViewAddress = popview.findViewById(R.id.id_province);
        mViewCity = popview.findViewById(R.id.id_city);
        mViewCountyChilds = popview.findViewById(R.id.id_district);
        mRelativeTitleBg = popview.findViewById(R.id.ll_title);
        mTvOK = popview.findViewById(R.id.tv_picker_confirm);
        mTvTitle = popview.findViewById(R.id.tv_picker_title);
        mTvCancel = popview.findViewById(R.id.tv_picker_cancel);

        popwindow = new PopupWindow(popview, LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        popwindow.setAnimationStyle(R.style.pop_bottom);
        popwindow.setBackgroundDrawable(new ColorDrawable());
        popwindow.setTouchable(true);
        popwindow.setOutsideTouchable(false);
        popwindow.setFocusable(true);

        popwindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                if (config.isShowBackground()) {
                    WindowUtils.setBackgroundAlpha(config.getContext(), 1.0f);
                }
            }
        });
        /**
         * 设置标题背景颜色
         */
        if (!TextUtils.isEmpty(config.getTitleBackgroundColorStr())) {
            mRelativeTitleBg.setBackgroundColor(Color.parseColor(config.getTitleBackgroundColorStr()));
        }

        //标题
        if (!TextUtils.isEmpty(config.getTitle())) {
            mTvTitle.setText(config.getTitle());
        }

        //标题文字颜色
        if (!TextUtils.isEmpty(config.getTitleTextColorStr())) {
            mTvTitle.setTextColor(Color.parseColor(config.getTitleTextColorStr()));
        }

        //设置确认按钮文字颜色
        if (!TextUtils.isEmpty(config.getConfirmTextColorStr())) {
            mTvOK.setTextColor(Color.parseColor(config.getConfirmTextColorStr()));
        }

        //设置取消按钮文字颜色
        if (!TextUtils.isEmpty(config.getCancelTextColorStr())) {
            mTvCancel.setTextColor(Color.parseColor(config.getCancelTextColorStr()));
        }

        //只显示省市两级联动
        if (config.getWheelType() == CityConfig.WheelType.PRO) {
            mViewCity.setVisibility(View.GONE);
            mViewCountyChilds.setVisibility(View.GONE);
        } else if (config.getWheelType() == CityConfig.WheelType.PRO_CITY) {
            mViewCountyChilds.setVisibility(View.GONE);
        } else {
            mViewAddress.setVisibility(View.VISIBLE);
            mViewCity.setVisibility(View.VISIBLE);
            mViewCountyChilds.setVisibility(View.VISIBLE);
        }

        //解析初始数据
        if (parseHelper == null || parseHelper.getAddressBeanArrayList().isEmpty()) {
            parseHelper.initData(config.getContext());
        }

        // 添加change事件
        mViewAddress.addChangingListener(this);
        // 添加change事件
        mViewCity.addChangingListener(this);
        // 添加change事件
        mViewCountyChilds.addChangingListener(this);
        // 添加onclick事件
        mTvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBaseListener.onCancel();
                hide();
            }
        });

        //完成选择
        mTvOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (parseHelper != null) {
                    if (config.getWheelType() == CityConfig.WheelType.PRO) {
                        mBaseListener.onSelected(parseHelper.getAddressBean());
                    } else if (config.getWheelType() == CityConfig.WheelType.PRO_CITY) {
                        mBaseListener.onSelected(parseHelper.getAddressBean(), parseHelper.getCityChildsBean());
                    } else {
                        mBaseListener.onSelected(parseHelper.getAddressBean(), parseHelper.getCityChildsBean(), parseHelper.getCountyChildsBean());
                    }

                }
                hide();
            }
        });

    }

    /**
     * 加载数据
     */
    private void setUpData() {

        if (parseHelper == null || config == null) {
            return;
        }

        int AddressDefault = -1;
        if (!TextUtils.isEmpty(config.getDefaultProvinceName()) && parseHelper.getProvinceBeenArray().length > 0) {
            for (int i = 0; i < parseHelper.getProvinceBeenArray().length; i++) {
                if (parseHelper.getProvinceBeenArray()[i].getName().contains(config.getDefaultProvinceName())) {
                    AddressDefault = i;
                    break;
                }
            }
        }
        String[] ProvinceBeenArray = new String[parseHelper.getProvinceBeenArray().length];
        ArrayWheelAdapter arrayWheelAdapter = new ArrayWheelAdapter<AddressBean>(config.getContext(),
                parseHelper.getProvinceBeenArray());
        mViewAddress.setViewAdapter(arrayWheelAdapter);
        //获取所设置的省的位置，直接定位到该位置
        if (-1 != AddressDefault) {
            mViewAddress.setCurrentItem(AddressDefault);
        }
        // 设置可见条目数量
        mViewAddress.setVisibleItems(config.getVisibleItems());
        mViewCity.setVisibleItems(config.getVisibleItems());
        mViewCountyChilds.setVisibleItems(config.getVisibleItems());
        mViewAddress.setCyclic(config.isProvinceCyclic());
        mViewCity.setCyclic(config.isCityCyclic());
        mViewCountyChilds.setCyclic(config.isDistrictCyclic());
        arrayWheelAdapter.setPadding(config.getPadding());
        arrayWheelAdapter.setTextColor(Color.parseColor(config.getTextColor()));
        arrayWheelAdapter.setTextSize(config.getTextSize());

        updateCities();

        updateAreas();
    }

    /**
     * 根据当前的省，更新市WheelView的信息
     */
    private void updateCities() {

        if (parseHelper == null || config == null) {
            return;
        }

        //省份滚轮滑动的当前位置
        int pCurrent = mViewAddress.getCurrentItem();

        //省份选中的名称
        AddressBean mAddressBean = parseHelper.getProvinceBeenArray()[pCurrent];
        parseHelper.setAddressBean(mAddressBean);

        if (parseHelper.getPro_CityMap() == null) {
            return;
        }

        CityChildsBean[] cities = parseHelper.getPro_CityMap().get(mAddressBean.getName());
        if (cities == null) {
            return;
        }

        //设置最初的默认城市
        int cityDefault = -1;
        if (!TextUtils.isEmpty(config.getDefaultCityName()) && cities.length > 0) {
            for (int i = 0; i < cities.length; i++) {
                if (config.getDefaultCityName().contains(cities[i].getName())) {
                    cityDefault = i;
                    break;
                }
            }
        }

        ArrayWheelAdapter cityWheel = new ArrayWheelAdapter<CityChildsBean>(config.getContext(), cities);
        // 设置可见条目数量
        cityWheel.setTextColor(Color.parseColor(config.getTextColor()));
        cityWheel.setTextSize(config.getTextSize());
        mViewCity.setViewAdapter(cityWheel);
        if (-1 != cityDefault) {
            mViewCity.setCurrentItem(cityDefault);
        } else {
            mViewCity.setCurrentItem(0);
        }

        cityWheel.setPadding(config.getPadding());

        updateAreas();
    }

    /**
     * 根据当前的市，更新区WheelView的信息
     */
    private void updateAreas() {

        int pCurrent = mViewCity.getCurrentItem();
        if (parseHelper.getPro_CityMap() == null || parseHelper.getCity_DisMap() == null) {
            return;
        }

        if (config.getWheelType() == CityConfig.WheelType.PRO_CITY
                || config.getWheelType() == CityConfig.WheelType.PRO_CITY_DIS) {

            CityChildsBean mCityChildsBean = parseHelper.getPro_CityMap().get(parseHelper.getAddressBean().getName())[pCurrent];
            parseHelper.setCityChildsBean(mCityChildsBean);

            if (config.getWheelType() == CityConfig.WheelType.PRO_CITY_DIS) {

                CountyChildsBean[] areas = parseHelper.getCity_DisMap()
                        .get(parseHelper.getAddressBean().getName() + mCityChildsBean.getName());

                if (areas == null) {
                    return;
                }

                int CountyChildsDefault = -1;
                if (!TextUtils.isEmpty(config.getDefaultDistrict()) && areas.length > 0) {
                    for (int i = 0; i < areas.length; i++) {
                        if (config.getDefaultDistrict().contains(areas[i].getName())) {
                            CountyChildsDefault = i;
                            break;
                        }
                    }
                }

                ArrayWheelAdapter CountyChildsWheel = new ArrayWheelAdapter<CountyChildsBean>(config.getContext(), areas);
                // 设置可见条目数量
                CountyChildsWheel.setTextColor(Color.parseColor(config.getTextColor()));
                CountyChildsWheel.setTextSize(config.getTextSize());
                mViewCountyChilds.setViewAdapter(CountyChildsWheel);

                CountyChildsBean mCountyChildsBean = null;
                if (parseHelper.getDisMap() == null) {
                    return;
                }

                if (-1 != CountyChildsDefault) {
                    mViewCountyChilds.setCurrentItem(CountyChildsDefault);
                    //获取第一个区名称
                    mCountyChildsBean = parseHelper.getDisMap().get(parseHelper.getAddressBean().getName()
                            + mCityChildsBean.getName() + config.getDefaultDistrict());
                } else {
                    mViewCountyChilds.setCurrentItem(0);
                    if (areas.length > 0) {
                        mCountyChildsBean = areas[0];
                    }
                }

                parseHelper.setCountyChildsBean(mCountyChildsBean);
                CountyChildsWheel.setPadding(config.getPadding());

            }
        }

    }

    @Override
    public void setType(int type) {

    }

    @Override
    public void show() {
        if (!isShow()) {
            setUpData();
            popwindow.showAtLocation(popview, Gravity.BOTTOM, 0, 0);
        }
    }

    @Override
    public void hide() {
        if (isShow()) {
            popwindow.dismiss();
        }
    }

    @Override
    public boolean isShow() {
        if (config.isShowBackground()) {
            WindowUtils.setBackgroundAlpha(config.getContext(), 0.5f);
        }
        return popwindow.isShowing();
    }

    @Override
    public void onChanged(WheelView wheel, int oldValue, int newValue) {
        if (wheel == mViewAddress) {
            updateCities();
        } else if (wheel == mViewCity) {
            updateAreas();
        } else if (wheel == mViewCountyChilds) {
            if (parseHelper != null && parseHelper.getCity_DisMap() != null) {

                CountyChildsBean mCountyChildsBean = parseHelper.getCity_DisMap()
                        .get(parseHelper.getAddressBean().getName() + parseHelper.getCityChildsBean().getName())[newValue];

                parseHelper.setCountyChildsBean(mCountyChildsBean);
            }
        }
    }

}
