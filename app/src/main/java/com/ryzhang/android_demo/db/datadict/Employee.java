package com.ryzhang.android_demo.db.datadict;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * @author ryzhang
 * @date 2017/10/25
 * @time 15:35
 * Project 员工表
 */
@DatabaseTable(tableName = "tb_employee")
public class Employee extends BaseDatadict {
    @DatabaseField(columnName = "employee_name", canBeNull = false)
    private String name;//姓名
    @DatabaseField(columnName = "employee_type", defaultValue = "A")
    private String type;//工种
    @DatabaseField(columnName = "employee_phone", canBeNull = false, id = true)
    private String phone;//手机号码
    @DatabaseField(columnName = "employee_salary", canBeNull = false)
    private float salary;//日工资
    @DatabaseField(columnName = "employee_boss", canBeNull = false, foreign = true, foreignAutoRefresh = true)
    private User user;//老板
    @ForeignCollectionField
    private ForeignCollection<Sign> signs;//签到

    protected Employee() {
    }

    public Employee(String name, String phone, float salary) {
        setPhone(phone);
        setName(name);
        setSalary(salary);
    }

    public Employee(String name, String phone, float salary, User user) {
        this(name, phone, salary);
        setUser(user);
    }

    public Employee(String name, String phone, String type) {
        setPhone(phone);
        setName(name);
        setType(type);
    }

    public Employee(String name, String phone, String type, User user) {
        this(name, phone, type);
        setUser(user);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public float getSalary() {
        return salary;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }

    public ForeignCollection<Sign> getSigns() {
        return signs;
    }

    public void setSigns(ForeignCollection<Sign> signs) {
        this.signs = signs;
    }
}
