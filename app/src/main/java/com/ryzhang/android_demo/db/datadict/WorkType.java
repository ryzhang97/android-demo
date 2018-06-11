package com.ryzhang.android_demo.db.datadict;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import com.match.library.db.BaseDatadict;

/**
 * @author ryzhang
 * @date 2017/10/26
 * @time 9:41
 * Project 工种表
 */
@DatabaseTable(tableName = "tb_work_type")
public class WorkType extends BaseDatadict {
    @DatabaseField(columnName = "work_type_id", generatedId = true)
    private int id;  //工种id
    @DatabaseField(columnName = "work_type_name", canBeNull = false)
    private String name; //工种名称

    protected WorkType() {
    }

    public WorkType(String name) {
        setName(name);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
