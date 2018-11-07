package com.android.xwpeng.tupush;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.umeng.message.PushAgent;
import com.umeng.message.UTrack;

import static anet.channel.util.Utils.context;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        PushAgent.getInstance(this.getApplicationContext()).onAppStart();
        PushAgent.getInstance(this.getApplicationContext()).addAlias("123456", "userId", new UTrack.ICallBack() {
            @Override
            public void onMessage(boolean b, String s) {
                Log.e("xwpeng16", "b: " + b + " s: " + s);
            }
        });
    }
}
