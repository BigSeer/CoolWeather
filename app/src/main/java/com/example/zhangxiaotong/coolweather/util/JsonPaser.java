package com.example.zhangxiaotong.coolweather.util;

import android.text.TextUtils;
import com.example.zhangxiaotong.coolweather.db.City;
import com.example.zhangxiaotong.coolweather.db.County;
import com.example.zhangxiaotong.coolweather.db.Province;
import org.json.JSONArray;
import org.json.JSONObject;

public class JsonPaser {

    public static boolean handleProvinceResponse(String responsn) {
        if (TextUtils.isEmpty(responsn)) {
            return false;
        }

        try {
            JSONArray jsonArray = new JSONArray(responsn);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.optJSONObject(i);
                if (jsonObject != null) {
                    Province province = new Province();
                    province.setProvinceName(jsonObject.optString("name"));
                    province.setProvinceCode(jsonObject.optInt("id"));
                    province.save();
                }
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean handleCountyResponse(String responsn, int cityId) {
        if (TextUtils.isEmpty(responsn)) {
            return false;
        }

        try {
            JSONArray jsonArray = new JSONArray(responsn);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.optJSONObject(i);
                if (jsonObject != null) {
                    County county = new County();
                    county.setCountyName(jsonObject.optString("name"));
                    county.setCityId(cityId);
                    county.setWeatherId(jsonObject.optString("weather_id"));
                    county.save();
                }
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean handleCityResponse(String responsn, int provinceId) {
        if (TextUtils.isEmpty(responsn)) {
            return false;
        }

        try {
            JSONArray jsonArray = new JSONArray(responsn);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.optJSONObject(i);
                if (jsonObject != null) {
                    City city = new City();
                    city.setCityName(jsonObject.optString("name"));
                    city.setCityCode(jsonObject.optInt("id"));
                    city.setProvinceId(provinceId);
                    city.save();
                }
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
