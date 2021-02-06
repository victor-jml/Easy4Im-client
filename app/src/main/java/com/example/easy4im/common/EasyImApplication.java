package com.example.easy4im.common;

import android.app.Application;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.util.Log;

import androidx.core.app.NotificationManagerCompat;

import com.alibaba.fastjson.JSON;
import com.example.easy4im.R;
import com.example.easy4im.bean.res.UserResVo;
import com.example.easy4im.constant.Constant;
import com.example.easy4im.db.DatabaseHelper;
import com.example.easy4im.ui.main.MainActivity;

/**
 * @Author yang.zhao
 * Date: 2021/1/26
 * Description: 全局共享
 **/
public class EasyImApplication extends Application {

    private static final int NOTIFICATION_ID = 1001;

    private static EasyImApplication Instance;

    public static EasyImApplication getInstance(){
        return Instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        DatabaseHelper.initOrmLite(this);
        Instance = this;
        registerActivityLifecycleCallbacks(new EasyImLifeCycle());
    }

    /**
     * 保存用户登录信息到Shared中，每一次启动应用后可以实现自动登录
     * @param key
     * @param value
     */
    public void cache(String key,String value){
        String valueEncrypt = AESUtil.encrypt(value);
        SharedPreferences sharedPreferences = getSharedPreferences("data",
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, valueEncrypt);
        editor.commit();
    }

    /**
     * 获取Shared中保存的用户登录信息
     * @param key
     * @return
     */
    public String getCache(String key){
        SharedPreferences sharedPreferences = getSharedPreferences("data",
                Context.MODE_PRIVATE);
        String valueEncrypt = sharedPreferences.getString(key, "");
        return AESUtil.decrypt(valueEncrypt);
    }

    public void cleanCache() {
//        //删除聊天记录
//        new LastMsgDao().deleteAll(getUser().getUserId());
//        new ChatMsgDao().deleteAll(getUser().getUserId());
    }


    public UserResVo getUser() {
        return JSON.parseObject(getCache(Constant.UserInfo), UserResVo.class);
    }


    public void notificationNewMsg() {
        if (EasyImLifeCycle.isApplicationInForeground()) {
            Log.i("应用在前台", "应用在前台");
            return;
        }
        if (!NotificationManagerCompat.from(getBaseContext()).areNotificationsEnabled()) {
            Log.i("通知权限没打开", "通知权限没打开");
            return;
        }
        NotificationManager manager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        Intent resultIntent = new Intent(getApplicationContext(),
                MainActivity.class);
        resultIntent.putExtra("initStatus", 0);
        PendingIntent resultPI = PendingIntent.getActivity(getBaseContext(),
                1, resultIntent, PendingIntent.FLAG_CANCEL_CURRENT);
        Notification.Builder builder =
                new Notification.Builder(getBaseContext()).setContentTitle(
                        "新消息").setContentText("您有新的消息。").setWhen(System.currentTimeMillis()).setSmallIcon(R.mipmap.ic_launcher).setAutoCancel(true).setLargeIcon(BitmapFactory.decodeResource(getBaseContext().getResources(), R.mipmap.ic_launcher));
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("001",
                    "my_channel", NotificationManager.IMPORTANCE_DEFAULT);
            channel.enableLights(true); //是否在桌面icon右上角展示小红点
            channel.setLightColor(Color.RED); //小红点颜色
            channel.setShowBadge(true); //是否在久按桌面图标时显示此渠道的通知
            manager.createNotificationChannel(channel);
            builder.setChannelId("001");
        }
        builder.setContentIntent(resultPI);
        Notification n = builder.build();
        manager.notify(NOTIFICATION_ID, n);
    }
}
