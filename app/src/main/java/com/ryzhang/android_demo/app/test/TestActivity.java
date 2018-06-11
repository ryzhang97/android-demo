package com.ryzhang.android_demo.app.test;

import android.view.View;
import android.widget.Button;

import com.match.library.utils.Logcat;
import com.match.library.utils.common.CalendarUtil;
import com.match.library.utils.dependent.view.annotation.ContentView;
import com.match.library.utils.dependent.view.annotation.ViewInject;
import com.ryzhang.android_demo.R;
import com.ryzhang.android_demo.app.BaseActivity;
import com.ryzhang.android_demo.db.dao.BaseDao;
import com.ryzhang.android_demo.db.datadict.Datadict;
import com.ryzhang.android_demo.db.datadict.DatadictIndex;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@ContentView(R.layout.activity_main)
public class TestActivity extends BaseActivity {
    @ViewInject(R.id.insert)
    private Button insert;
    @ViewInject(R.id.delete)
    private Button delete;
    @ViewInject(R.id.update)
    private Button update;
    @ViewInject(R.id.select)
    private Button select;

    private BaseDao datadictDao = null;
    private BaseDao datadictIndexDao = null;

    private static List<Datadict> list = new ArrayList<>();

    static {
        Datadict datadict1 = new Datadict();
        datadict1.setDealer_Id("1745");
        datadict1.setVIN("LSGAR55L1FH000338");
        Datadict datadict2 = new Datadict();
        datadict2.setDealer_Id("1745");
        datadict2.setVIN("LSGAR5AL5FH004687");
        Datadict datadict3 = new Datadict();
        datadict3.setDealer_Id("1745");
        datadict3.setVIN("LSGGH55L6FS029799");
        Datadict datadict4 = new Datadict();
        datadict4.setDealer_Id("1745");
        datadict4.setVIN("LSGAR5AL7FH002763");
        Datadict datadict5 = new Datadict();
        datadict5.setDealer_Id("2805");
        datadict5.setVIN("LSGAR55L0FH003232");
        datadict5.setFR_Id("2017-12-19");
        datadict5.setDate_Scanned("08:04:00");
        datadict5.setTime_Scanned("23");
        datadict5.setQC("G");
        datadict5.setPIF_Date("M");
        Datadict datadict6 = new Datadict();
        datadict6.setDealer_Id("2805");
        datadict6.setVIN("LSGAR5AL9FH007284");
        Datadict datadict7 = new Datadict();
        datadict7.setDealer_Id("2805");
        datadict7.setVIN("LWGAR5AL9FH007283");
        list.add(datadict1);
        list.add(datadict2);
        list.add(datadict3);
        list.add(datadict4);
        list.add(datadict5);
        list.add(datadict6);
        list.add(datadict7);
    }

    @Override
    protected void initListener() {
        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatadictIndex datadictIndex = new DatadictIndex("ryzhang", CalendarUtil.YEAR_TO_DAY);
                int result = datadictIndexDao.creat(datadictIndex);
                Logcat.e("成功" + result);
                for (int i = 0; i < list.size(); i++) {
                    list.get(i).setDatadictIndex(datadictIndex);
                }
                result = datadictDao.creat(list);
                Logcat.e("成功" + result);
            }
        });
        delete.setOnClickListener(view -> {
            datadictDao.deleteAll();
            datadictIndexDao.deleteAll();
        });
        update.setOnClickListener(view -> {
        });
        select.setOnClickListener(view -> {
            HashMap<String, String> hashMap = new HashMap<>();
            hashMap.put("Dealer_Id", "2805,1745");
            List<Datadict> datadictList = datadictDao.query(hashMap);
            Logcat.e("成功" + datadictList.size());

        });
    }

    @Override
    protected void loadData() {
        datadictDao = new BaseDao(context, Datadict.class);
        datadictIndexDao = new BaseDao(context, DatadictIndex.class);
    }

//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        OpenHelperManager.releaseHelper();
//    }
}
