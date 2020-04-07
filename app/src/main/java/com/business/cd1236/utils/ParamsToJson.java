package com.business.cd1236.utils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ParamsToJson {

    public static String PTJO(Object[] names, Object... params) {
        //本界面专用 -- 方便参数传递
        JSONObject jsonObject = new JSONObject();
        try {
            if (names.length != params.length) {
                throw new JSONException("数据传输异常");
            }
            for (int i = 0; i < names.length; i++) {
                jsonObject.put(String.valueOf(names[i]), params[i]);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject.toString();
    }
    public static void PTJA(){

    }

    /**
     * params to []
     * @param names
     * @return
     */
    public static Object[] PTO(String... names) {
        ArrayList<String> name = new ArrayList<>();
        for (int i = 0; i < names.length; i++) {
            name.add(names[i]);
        }
        Object[] objects = name.toArray();
        return objects;
    }
}
