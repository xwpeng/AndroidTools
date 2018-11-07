package com.android.xwpeng.tgson;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.alibaba.fastjson.JSON;
import com.android.xwpeng.tgson.bean.FastJsonTest;
import com.android.xwpeng.tgson.bean.User;
import com.google.gson.Gson;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = MainActivity.class.getSimpleName();
    private LinearLayout mRootLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRootLayout = findViewById(R.id.root_layout);
//        addTestBtn("GsonTest.baseDecode", v -> GsonTest.baseDecode());
//        addTestBtn("FastJsonTest.baseDecode", v -> FastJsonTest.baseDecode());
//        addTestBtn("GsonTest.baseEncode", v -> GsonTest.baseEncode());
//        addTestBtn("FastJsonTest.baseEncode", v -> FastJsonTest.baseEncode());
//        addTestBtn("GsonTest.beanEncode", v -> GsonTest.beanEncode());
        addTestBtn("FastJsonTest.beanEncode", v -> FastJsonTest.beanEncode());
//        addTestBtn("GsonTest.beanDecode", v -> GsonTest.beanDecode());
//        addTestBtn("FastJsonTest.beanDecode", v -> FastJsonTest.beanDecode());
//        addTestBtn("FastJsonTest.listEncode", v -> FastJsonTest.listEncode());
//        addTestBtn("FastJsonTest.listDecode", v -> FastJsonTest.listDecode());
//        addTestBtn("FastJsonTest.jsonFile", v -> FastJsonTest.jsonFile(this.getApplicationContext()));
//        addTestBtn("GsonTest.listDecode", v -> GsonTest.listDecode());
//        addTestBtn("GsonTest.listEncode", v -> GsonTest.listEncode());
//        addTestBtn("GsonTest.readerDecode", v -> GsonTest.readerDecode());
    }

    private void addTestBtn(String title, View.OnClickListener listener) {
        if (TextUtils.isEmpty(title)) return;
        Button button = new Button(this);
        button.setText(title);
        if (listener != null) button.setOnClickListener(listener);
        mRootLayout.addView(button);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        if (mRootLayout.getChildCount() > 1) params.setMargins(0, 60, 0, 0);
        button.setLayoutParams(params);
    }
}


