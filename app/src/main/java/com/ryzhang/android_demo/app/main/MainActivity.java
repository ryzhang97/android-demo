package com.ryzhang.android_demo.app.main;

import android.widget.Button;
import android.widget.Toast;

import com.j256.ormlite.dao.ForeignCollection;
import com.match.library.utils.Logcat;
import com.match.library.utils.common.CalendarUtil;
import com.match.library.utils.dependent.view.annotation.ContentView;
import com.match.library.utils.dependent.view.annotation.ViewInject;
import com.ryzhang.android_demo.R;
import com.ryzhang.android_demo.app.BaseActivity;
import com.ryzhang.android_demo.db.dao.BaseDao;
import com.ryzhang.android_demo.db.datadict.Employee;
import com.ryzhang.android_demo.db.datadict.Sign;
import com.ryzhang.android_demo.db.datadict.User;

import me.leolin.shortcutbadger.ShortcutBadger;

@ContentView(R.layout.activity_main)
public class MainActivity extends BaseActivity {
//    @ViewInject(R.id.insert)
    private Button insert;
    @ViewInject(R.id.delete)
    private Button delete;
    @ViewInject(R.id.update)
    private Button update;
    @ViewInject(R.id.select)
    private Button select;

    private BaseDao userDao = null;
    private BaseDao employeeDao = null;
    private BaseDao signDao = null;

    @Override
    protected void initListener() {
        insert.setOnClickListener(view -> {
            String name = CalendarUtil.formatDate(CalendarUtil.getCurrentDate(), CalendarUtil.YEAR_TO_MINUTE);
            String phone = System.currentTimeMillis() + "";
            User user = new User(name, phone);
            int result = 0;
            result = userDao.creat(user);
            Logcat.d("创建成功：" + result);
//                Employee employee = new Employee(name, name, 1);
//                employee.setUser(user);
//                employeeDao.creat(employee);
//                Sign sign = new Sign(employee, CalendarUtil.getCurrentDate(), 1);
//                signDao.creat(sign);
        });
        delete.setOnClickListener(view -> {
            int result = userDao.delete(userDao.queryForFirst());
            Logcat.d(getClass(), "result:" + result);
        });
        update.setOnClickListener(view -> {
            int badgeCount = 1;
            ShortcutBadger.applyCount(getApplicationContext(), badgeCount); //for 1.1.4+
            boolean success = ShortcutBadger.applyCount(context, badgeCount);

            Toast.makeText(getApplicationContext(), "Set count=" + badgeCount + ", success=" + success, Toast.LENGTH_SHORT).show();

        });
        select.setOnClickListener(view -> {
            User user = (User) userDao.queryForFirst();
            ForeignCollection<Employee> employees = user.getEmployees();
            for (Employee employee : employees) {
                Logcat.d(getClass(), employee.getName());
                for (Sign sign : employee.getSigns()) {
                    Logcat.d(getClass(), CalendarUtil.formatDate(sign.getDateTime(), CalendarUtil.YEAR_TO_SECOND));
                }
            }

        });
    }

    @Override
    protected void loadData() {
        userDao = new BaseDao(context, User.class);
        employeeDao = new BaseDao(context, Employee.class);
        signDao = new BaseDao(context, Sign.class);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
