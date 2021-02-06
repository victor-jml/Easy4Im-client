package com.example.easy4im.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.easy4im.bean.ChatMsg;
import com.example.easy4im.bean.Friend;
import com.example.easy4im.bean.LastMsg;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author yang.zhao
 * Date: 2021/1/26
 * Description:
 **/
public class DatabaseHelper extends OrmLiteSqliteOpenHelper {
    // 数据库名称
    public static final String DATABASE_NAME = "im.db";

    // 本类的单例实例
    private static DatabaseHelper instance;
    /**
     * 版本号
     */
    private static final int DBVERSION = 15;

    private static Context mContext;

    /**
     * 初始化
     *
     * @param context
     */
    public static void initOrmLite(Context context) {
        mContext = context;
        getInstance();
    }

    // 存储APP中所有的DAO对象的Map集合
    private Map<String, Dao> daos = new HashMap<>();

    // 获取本类单例对象的方法
    public static synchronized DatabaseHelper getInstance() {
        if (instance == null) {
            synchronized (DatabaseHelper.class) {
                if (instance == null) {
                    instance = new DatabaseHelper(mContext);
                }
            }
        }
        return instance;
    }

    // 私有的构造方法
    private DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DBVERSION);
    }

    // 根据传入的DAO的路径获取到这个DAO的单例对象（要么从daos这个Map中获取，要么新创建一个并存入daos）
    public synchronized Dao getDao(Class clazz) throws SQLException {
        Dao dao = null;
        String className = clazz.getSimpleName();
        if (daos.containsKey(className)) {
            dao = daos.get(className);
        }
        if (dao == null) {
            dao = super.getDao(clazz);
            daos.put(className, dao);
        }
        return dao;
    }

    @Override // 创建数据库时调用的方法
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            //好友列表数据库
            TableUtils.createTable(connectionSource, Friend.class);
            //聊天记录
            TableUtils.createTable(connectionSource, ChatMsg.class);
            //消息列表
            TableUtils.createTable(connectionSource, LastMsg.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override // 数据库版本更新时调用的方法
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            TableUtils.dropTable(connectionSource, Friend.class, true);
            TableUtils.dropTable(connectionSource, ChatMsg.class, true);
            TableUtils.dropTable(connectionSource, LastMsg.class, true);
            onCreate(database, connectionSource);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 释放资源
    @Override
    public void close() {
        super.close();
        for (String key : daos.keySet()) {
            Dao dao = daos.get(key);
            dao = null;
        }
    }
}