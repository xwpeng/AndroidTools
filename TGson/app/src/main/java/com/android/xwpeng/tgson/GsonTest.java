package com.android.xwpeng.tgson;

import android.util.JsonWriter;
import android.util.Log;

import com.android.xwpeng.tgson.bean.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import org.json.JSONException;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by xwpeng on 2018/7/11.
 */

public class GsonTest {
    public static final String TAG = GsonTest.class.getSimpleName();
    private static Gson gson = new Gson();

    public static void baseDecode() {
        int a = gson.fromJson("1", int.class);
        Log.e(TAG, a + "");
        double b = gson.fromJson("\"99.99\"", double.class);
        Log.e(TAG, b + "");
        boolean c = gson.fromJson("true", boolean.class);
        Log.e(TAG, c + "");
        String d = gson.fromJson("gald", String.class);
        Log.e(TAG, d);
    }

    public static void baseEncode() {
        String a = gson.toJson(1);
        Log.e(TAG, a);
        String b = gson.toJson(99.99);
        Log.e(TAG, b);
        String c = gson.toJson(true);
        Log.e(TAG, c);
        String s = "gald";
        String d = gson.toJson(s);
        Log.e(TAG, d + " len= " + d.length());
    }

    public static void beanEncode() {
        String jsonStr = gson.toJson(new User());
        Log.e(TAG, jsonStr);
        jsonStr = gson.toJson(new User("xwpeng", 25, "xwpeng@github.com"));
        Log.e(TAG, jsonStr);
    }

    public static void beanDecode() {
        String jsonStr = "{\"age\":25,\"emailAddress\":\"xwpeng@github.com\",\"name\":\"xwpeng\"}";
        Log.e(TAG, gson.fromJson(jsonStr, User.class).toString());
        jsonStr = "{\"age\":25,\"name\":\"xwpeng\"}";
        Log.e(TAG, gson.fromJson(jsonStr, User.class).toString());
    }

    public static void listEncode() {
        List<User> users = new ArrayList<>();
        users.add(new User("xwpeng1", 11, "11e"));
        users.add(new User("xwpeng2", 22, "22e"));
        String jsonStr = gson.toJson(users);
        Log.e(TAG, jsonStr);
        org.json.JSONArray jsonArray;
        try {
            jsonArray = new org.json.JSONArray(jsonStr);
            Log.e(TAG, "jsonArray: " + jsonArray.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static void listDecode() {
        String jsonStr = "[{\"age\":11,\"emailAddress\":\"11e\",\"name\":\"xwpeng1\"},{\"age\":22,\"emailAddress\":\"22e\",\"name\":\"xwpeng2\"}]";
        List<User> users = gson.fromJson(jsonStr, new TypeToken<ArrayList<User>>(){}.getType());
        Log.e(TAG, users.toString());
        User[] users1 = gson.fromJson(jsonStr, User[].class);
        Log.e(TAG, Arrays.toString(users1));
    }

    public static void readerDecode() {
        String jsonStr = "[{\"age\":11,\"emailAddress\":\"11e\",\"name\":\"xwpeng1\"},{\"age\":22,\"emailAddress\":\"22e\",\"name\":\"xwpeng2\"}]";
       List<User> users = new ArrayList<>();
       User user;
        JsonReader jsonReader = new JsonReader(new StringReader(jsonStr));
        try {
            jsonReader.beginArray();
            while (jsonReader.hasNext()) {
                user = new User();
               jsonReader.beginObject();
               while (jsonReader.hasNext()) {
                    String name = jsonReader.nextName();
                   if ("name".equals(name)) user.name = jsonReader.nextString();
                   else jsonReader.skipValue();
               }
               users.add(user);
               jsonReader.endObject();
            }
            jsonReader.endArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.e(TAG, users.toString());
    }

    public static void gsonBuilder() {
        Gson gson = new GsonBuilder()
                .serializeNulls()//null值输出
                .create();
    }


}
