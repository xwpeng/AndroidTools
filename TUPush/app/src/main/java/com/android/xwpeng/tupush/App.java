package com.android.xwpeng.tupush;

import android.app.Application;
import android.app.Notification;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.umeng.commonsdk.UMConfigure;
import com.umeng.message.IUmengRegisterCallback;
import com.umeng.message.PushAgent;
import com.umeng.message.UmengMessageHandler;
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
//        Context context, String appkey, String channel, int deviceType, String pushSecret
        UMConfigure.init(this, APP_KEY, null, 1, MESSAGE_SECRET);
        //应用在前台时否显示通知， 此方法请在mPushAgent.register方法之前调用。
//        mPushAgent.setNotificaitonOnForeground(false);
        uPushRegister();
        uPushClickHandler();
        uMessageHandler();
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
        //只有自定义处理类型的才可以
        UmengNotificationClickHandler notificationClickHandler = new UmengNotificationClickHandler() {
            @Override
            public void dealWithCustomAction(Context context, UMessage msg) {
//                Toast.makeText(context, msg.extra.get("url"), Toast.LENGTH_LONG).show();
                Log.e("xwpeng16", msg.extra.get("url"));
            }
        };
        mPushAgent.setNotificationClickHandler(notificationClickHandler);
    }

    private void uMessageHandler(){
        UmengMessageHandler messageHandler = new UmengMessageHandler() {
            @Override
            public void dealWithNotificationMessage(Context context, UMessage msg) {
                Toast.makeText(context, msg.custom, Toast.LENGTH_LONG).show();
                //调用super则会走通知展示流程，不调用super则不展示通知
                super.dealWithNotificationMessage(context, msg);
            }

            @Override
            public Notification getNotification(Context context, UMessage uMessage) {
                //可以自定义通知栏
                return super.getNotification(context, uMessage);
            }

            //消息类推送在这里处理
            @Override
            public void dealWithCustomMessage(Context context, UMessage uMessage) {
                super.dealWithCustomMessage(context, uMessage);
            }
        };
        mPushAgent.setMessageHandler(messageHandler);
    }
}
