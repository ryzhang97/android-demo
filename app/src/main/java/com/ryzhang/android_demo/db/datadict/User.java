package com.ryzhang.android_demo.db.datadict;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;
import com.match.library.db.BaseDatadict;

/**
 * @author ryzhang
 * @date 2017/10/24
 * @time 15:21
 * Project 用户表
 */
@DatabaseTable(tableName = "tb_user")
public class User extends BaseDatadict {
    @DatabaseField(columnName = "user_name", canBeNull = false)
    private String name;    //姓名
    @DatabaseField(columnName = "user_phone", canBeNull = false, id = true)
    private String phone;//手机号码(主键)
    @DatabaseField(columnName = "user_password", defaultValue = "000000")
    private String password;//密码
    @DatabaseField(columnName = "user_level", defaultValue = "1", dataType = DataType.INTEGER)
    private int level;//级别
    @ForeignCollectionField
    private ForeignCollection<Employee> employees;

    protected User() {
    }

    public User(String phone, String name) {
        setPhone(phone);
        setName(name);
    }

    public User(String phone, String name, int level) {
        this(phone, name);
        setLevel(level);
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }


    public ForeignCollection<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(ForeignCollection<Employee> employees) {
        this.employees = employees;
    }
}
