package com.android.xwpeng.tupush;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.umeng.commonsdk.UMConfigure;
import com.umeng.message.IUmengRegisterCallback;
import com.umeng.message.PushAgent;
import com.umeng.message.UmengNotificationClickHandler;
import com.umeng.message.entity.UMessage;

/**
 * Created by xwpeng on 2018/7/2.
 */

public class App extends Application {
    private static final String APP_KEY = "5b39cf25b27b0a65910000c5";
    private static final String MESSAGE_SECRET = "99f4ab1e8496f5514d300b7d89d2b8c4";
    private PushAgent mPushAgent;

    @Override
    public void onCreate() {
        super.onCreate();
        mPushAgent = PushAgent.getInstance(this);
        UMConfigure.init(this, APP_KEY, null, 1, MESSAGE_SECRET);
        uPushRegister();
        uPushClickHandler();
    }

    private void uPushRegister() {
        mPushAgent.register(new IUmengRegisterCallback() {
            @Override
            public void onSuccess(String deviceToken) {
                //注册成功会返回device token
                Log.e("xwpeng", deviceToken);
            }

            @Override
            public void onFailure(String s, String s1) {

            }
        });
    }

    private void uPushClickHandler(){
        UmengNotificationClickHandler notificationClickHandler = new UmengNotificationClickHandler() {
            @Override
            public void dealWithCustomAction(Context context, UMessage msg) {
                Toast.makeText(context, msg.custom, Toast.LENGTH_LONG).show();
            }
        };
        mPushAgent.setNotificationClickHandler(notificationClickHandler);
    }
}
