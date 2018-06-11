package com.ryzhang.android_demo.app.main.fragment;

import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.widget.TextView;

import com.match.library.utils.dependent.view.annotation.ContentView;
import com.match.library.utils.dependent.view.annotation.ViewInject;
import com.ryzhang.android_demo.R;
import com.ryzhang.android_demo.bean.address.AddressBean;
import com.ryzhang.android_demo.view.wheel.CityConfig;
import com.ryzhang.android_demo.view.wheel.citywheel.CityPickerView;
import com.ryzhang.android_demo.view.wheel.interfaces.OnCityItemClickListener;

@ContentView(R.layout.fragment_draw1)
public class Draw1Fragment extends BaseFragment {
    @ViewInject(R.id.status_bar)
    private TextView status_bar;//状态栏
    @ViewInject(R.id.app_draw_title)
    private ConstraintLayout app_draw_title;//标题栏
    @ViewInject(R.id.vp_app_draw_content)
    private ConstraintLayout vp_app_draw_content;//标题栏

    public Draw1Fragment() {
    }

    @Override
    protected void onCreateView() {
        initStatusBar(status_bar, R.color.white);
        app_draw_title.setBackgroundResource(R.color.white);
    }

    @Override
    protected void onListener() {
        vp_app_draw_content.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CityConfig cityConfig = new CityConfig.Builder(getContext()).build();
                CityPickerView cityPicker = new CityPickerView(cityConfig);
                cityPicker.show();
                cityPicker.setOnCityItemClickListener(new OnCityItemClickListener() {
                    @Override
                    public void onSelected(AddressBean province) {
                        super.onSelected(province);
                    }
                });
            }
        });
    }

}
