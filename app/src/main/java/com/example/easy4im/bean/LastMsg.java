package com.example.easy4im.bean;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * @Author yang.zhao
 * Date: 2021/1/26
 * Description:
 **/

@DatabaseTable(tableName = "im_friend")
public class LastMsg {
    @DatabaseField(index = true, columnName = "friendshipid", dataType = DataType.LONG)
    private long friendShipId;
}
