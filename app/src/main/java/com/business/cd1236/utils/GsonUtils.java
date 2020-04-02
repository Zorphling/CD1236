package com.business.cd1236.utils;

import android.content.Context;
import android.content.res.AssetManager;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 */
public class GsonUtils {

    public static <T> T parseJsonWithGson(String jsonData, Class<T> type) {
        Gson gson = new Gson();
        return gson.fromJson(jsonData, type);
    }

    public static <T> ArrayList<T> parseJsonArrayWithGson(String jsonData, Class<T> type) {
        Gson gson = new Gson();
        ArrayList<JsonObject> jsonObjects = gson.fromJson(jsonData, new TypeToken<ArrayList<JsonObject>>() {
        }.getType());


        ArrayList<T> result = new ArrayList<>();
        for (JsonObject jsonObject : jsonObjects) {
            T t = gson.fromJson(jsonObject, type);
            result.add(t);
        }
        return result;
    }

    //获取assess中的文件
    public static String getJson(Context context, String fileName) {
        //将json数据变成字符串
        StringBuilder stringBuilder = new StringBuilder();
        try {
            //获取assets资源管理器
            AssetManager assetManager = context.getAssets();
            //通过管理器打开文件并读取
            BufferedReader bf = new BufferedReader(new InputStreamReader(
                    assetManager.open(fileName)));
            String line;
            while ((line = bf.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }

    public static String parseStringWithGson(String jsonString, String key) {
        try {
            return new JSONObject(jsonString).optString(key);
        } catch (JSONException e) {
            e.printStackTrace();
            return "";
        }
    }
    public static long parseLongWithGson(String jsonString, String key) {
        try {
            return new JSONObject(jsonString).optLong(key);
        } catch (JSONException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static int parseIntWithGson(String jsonString, String key) {
        try {
            return new JSONObject(jsonString).optInt(key);
        } catch (JSONException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static JSONObject parseJsonObject(String jsonString, String key) {
        try {
            return new JSONObject(jsonString).optJSONObject(key);
        } catch (JSONException e) {
            e.printStackTrace();
            return new JSONObject();
        }
    }

    /**
     * 将json 数组转换为Map 对象
     *
     * @param jsonString
     * @return
     */
    public static Map<String, Object> getMap(String jsonString) {
        JSONObject jsonObject;
        try {
            jsonObject = new JSONObject(jsonString);
            @SuppressWarnings("unchecked")
            Iterator<String> keyIter = jsonObject.keys();
            String key;
            Object value;
            Map<String, Object> valueMap = new HashMap<String, Object>();
            while (keyIter.hasNext()) {
                key = (String) keyIter.next();
                value = jsonObject.get(key);
                valueMap.put(key, value);
            }
            return valueMap;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 把json 转换为ArrayList 形式
     *
     * @return
     */
    public static List<Map<String, Object>> getList(String jsonString) {
        List<Map<String, Object>> list = null;
        try {
            JSONArray jsonArray = new JSONArray(jsonString);
            JSONObject jsonObject;
            list = new ArrayList<Map<String, Object>>();
            for (int i = 0; i < jsonArray.length(); i++) {
                jsonObject = jsonArray.getJSONObject(i);
                list.add(getMap(jsonObject.toString()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

}
