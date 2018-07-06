package com.android.xwpeng.trxtoolzing;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * Created by xwpeng on 2018/7/6.
 */

public class ActivityCreateCode extends AppCompatActivity {
    private EditText mCodeEditText;
    private ImageView mCodeIv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_code);
        initView();
    }

    private void initView() {
        mCodeEditText = findViewById(R.id.create_edittext);
        mCodeIv = findViewById(R.id.code_iv);
        findViewById(R.id.create_btn).setOnClickListener(v -> {
            createCode();
        });
    }

    private void createCode() {
        String str = mCodeEditText.getText().toString();
        if (TextUtils.isEmpty(str)) {
            Toast.makeText(this, "二维码文字内容不能为空！", Toast.LENGTH_SHORT).show();
            return;
        }
        //二维码生成方式一  推荐此方法
//            RxQRCode.builder(str).
//                    backColor(getResources().getColor(R.color.white)).
//                    codeColor(getResources().getColor(R.color.black)).
//                    codeSide(600).
//                    into(mCodeIv);

        //二维码生成方式二 默认宽和高都为800 背景为白色 二维码为黑色
//        RxQRCode.createQRCode(str, mCodeIv);
        //二维码生成方式三，带log
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.aaa);
        mCodeIv.setImageBitmap(RxQRCode.createImage(str, 800, 800, bitmap));
        Toast.makeText(this, "二维码已生成！", Toast.LENGTH_SHORT).show();
    }
}
