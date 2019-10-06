package com.code.response;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;

import java.util.*;

public class Test1 {

    public static void main(String[] args) {
        String jsonArrStr = "[{'name':'huangbiao','age':15,'id':24,'score':55.75},{'name':'liumei','age':14,'id':24,'score':84.75},{'name':'liumei','age':14,'id':18,'score':78.75}]";
        JSONArray jsonArray = jsonArraySort(jsonArrStr, "score");
        System.out.println(jsonArray);
        List<Integer> list = new ArrayList<Integer>();
        for (int i = 0; i < jsonArray.size(); i++) {
            list.add(jsonArray.getJSONObject(i).getInteger("id"));
        }
        System.out.println(list);
        System.out.println(list.get(2));

    }

    public static JSONArray jsonArraySort(String jsonArrStr,String key) {
        JSONArray jsonArr = JSON.parseArray(jsonArrStr);
        JSONArray sortedJsonArray = new JSONArray();
        List<JSONObject> jsonValues = new ArrayList<JSONObject>();
        for (int i = 0; i < jsonArr.size(); i++) {
            jsonValues.add(jsonArr.getJSONObject(i));
        }
        Collections.sort(jsonValues, new Comparator<JSONObject>() {
            @Override
            public int compare(JSONObject a, JSONObject b) {
                String valA = new String();
                String valB = new String();
                try {
                    // 这里是a、b需要处理的业务，需要根据你的规则进行修改。
                    String aStr = a.getString(key);
                    valA = aStr.replaceAll("-", "");
                    String bStr = b.getString(key);
                    valB = bStr.replaceAll("-", "");
                } catch (JSONException e) {
                    // do something
                }
                return -valA.compareTo(valB);
                // if you want to change the sort order, simply use the following:
                // return -valA.compareTo(valB);
            }
        });
        for (int i = 0; i < jsonArr.size(); i++) {
            sortedJsonArray.add(jsonValues.get(i));
        }
        return sortedJsonArray;
    }
}
