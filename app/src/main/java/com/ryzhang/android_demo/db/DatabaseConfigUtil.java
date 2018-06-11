package com.ryzhang.android_demo.db;

import com.j256.ormlite.android.apptools.OrmLiteConfigUtil;
import com.ryzhang.android_demo.db.datadict.Employee;
import com.ryzhang.android_demo.db.datadict.Sign;
import com.ryzhang.android_demo.db.datadict.User;
import com.ryzhang.android_demo.db.datadict.WorkType;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

/**
 * 将字典类（表）生成配置文件
 *
 * @author ryzhang
 * @date 2017/10/18
 * @time 16:56
 * Project
 */
public class DatabaseConfigUtil extends OrmLiteConfigUtil {
    private static final Class<?>[] classes = new Class[]{
            User.class, Employee.class, WorkType.class, Sign.class, Error.class
    };

    public static void main(String[] args) throws SQLException, IOException {
        System.out.println("-----bedin-----");
//             File configFile = new File(System.getProperty("user.dir") + "\\app\\src\\main\\res\\raw", "ormlite_config.txt");
        writeConfigFile(new File("ormlite_config.txt"), classes);

        System.out.println("-----end-----");
    }
}
