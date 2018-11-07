package com.android.xwpeng.tgson.bean;

import android.content.Context;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONReader;
import com.alibaba.fastjson.JSONWriter;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.parser.deserializer.ExtraProcessor;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by xwpeng on 2018/7/11.
 */

public class FastJsonTest {
    public static final String TAG = FastJsonTest.class.getSimpleName();

    public static void baseDecode() {
        int a = JSON.parseObject("1", int.class);
        Log.e(TAG, a + "");
        double b = JSON.parseObject("\"99.9\"", double.class);
        Log.e(TAG, b + "");
        boolean c = JSON.parseObject("true", boolean.class);
        Log.e(TAG, c + "");
        //直接报错，不支持这样写 JSON.parseObject("glad", String.class);
        String d = JSON.parseObject("\"glad\"", String.class);
        Log.e(TAG, d);
    }

    public static void baseEncode() {
        String a = JSON.toJSONString(1);
        Log.e(TAG, a);
        String b = JSON.toJSONString(99.99);
        Log.e(TAG, b);
        String c = JSON.toJSONString(true);
        Log.e(TAG, c);
        String s = "gald";
        String d = JSON.toJSONString(s);
        Log.e(TAG, d + " len= " + d.length());
    }

    public static void beanEncode() {
        String jsonStr = JSON.toJSONString(new User(), SerializerFeature.WriteNullListAsEmpty,
                SerializerFeature.SkipTransientField,
                SerializerFeature.DisableCircularReferenceDetect);
        Log.e(TAG, jsonStr);
        jsonStr = JSON.toJSONString(new User("xwpeng", 25, "xwpeng@github.com"));
        Log.e(TAG, jsonStr);
    }

    public static void beanDecode() {
        String jsonStr = "{\"age\":25,\"emailAddress\":\"xwpeng@github.com\",\"name\":\"xwpeng\"}";
        Log.e(TAG, JSON.parseObject(jsonStr, User.class).toString());
        jsonStr = "{\"age\":25,\"name\":\"xwpeng\"}";
        Log.e(TAG, JSON.parseObject(jsonStr, User.class).toString());
    }

    public static void listEncode() {
        List<User> users = new ArrayList<>();
        users.add(new User("xwpeng1", 11, "11e"));
        users.add(new User("xwpeng2", 22, "22e"));
        String jsonStr = JSON.toJSONString(users);
        Log.e(TAG, jsonStr);
        JSONArray jsonArray = (JSONArray) JSON.toJSON(users);
        Log.e(TAG, jsonArray.toString());
    }

    public static void listDecode() {
        String jsonStr = "[{\"age\":11,\"emailAddress\":\"11e\",\"name\":\"xwpeng1\"},{\"age\":22,\"emailAddress\":\"22e\",\"name\":\"xwpeng2\"}]";
        List<User> users = JSON.parseArray(jsonStr, User.class);
        Log.e(TAG, users.toString());
        users = JSON.parseObject(jsonStr, new TypeReference<List<User>>() {});
        Log.e(TAG, users.toString());
    }

    public static void jsonFile(Context context){
        try {
            File file = new File(context.getFilesDir(), "aa.json");
            file.createNewFile();
            OutputStream outputStream = new FileOutputStream(file);
            JSONWriter writer = new JSONWriter(new OutputStreamWriter(outputStream));
            writer.startArray();
            for (int i = 0; i < 10; ++i) {
                writer.writeValue(new User("id" + i, 11, "email" + i));
            }
            writer.endArray();
            writer.close();
            JSONReader reader = new JSONReader(new FileReader(file));
            reader.startArray();
            while (reader.hasNext()) {
//                String key = reader.readString();
                User user = reader.readObject(User.class);
               Log.e(TAG, user.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
