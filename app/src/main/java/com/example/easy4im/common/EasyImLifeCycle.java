package com.example.easy4im.common;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

/**
 * @Author yang.zhao
 * Date: 2021/1/29
 * Description: 管理全局应用activity生命周期
 **/
public class EasyImLifeCycle implements Application.ActivityLifecycleCallbacks {
    private static int resumed;
    private static int paused;
    private static int started;
    private static int stopped;
    @Override
    public void onActivityCreated(Activity activity, Bundle bundle) {

    }

    @Override
    public void onActivityStarted(Activity activity) {
        ++started;
    }

    @Override
    public void onActivityResumed(Activity activity) {
        ++resumed;
    }

    @Override
    public void onActivityPaused(Activity activity) {
        ++paused;
        android.util.Log.w("test", "application is in foreground: " + (resumed > paused));
    }

    @Override
    public void onActivityStopped(Activity activity) {
        ++stopped;
        android.util.Log.w("test", "application is visible: " + (started > stopped));
    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {

    }

    public static boolean isApplicationVisible() {
        return started > stopped;
    }

    public static boolean isApplicationInForeground() {
        // 当所有 Activity 的状态中处于 resumed 的大于 paused 状态的，即可认为有Activity处于前台状态中
        return resumed > paused;
    }
}
