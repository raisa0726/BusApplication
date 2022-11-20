package com.example.busapplication.data;

import android.content.res.AssetManager;
import android.os.Bundle;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class Introduce {
    int introduceId;
    String name;
    int img;
    String url;
    String detail;

    // get
    public int getIntroduceId() {
        return introduceId;
    }

    public String getName() {
        return name;
    }

    public int getImg() {
        return img;
    }

    public String getUrl() {
        return url;
    }

    public String getDetail() {
        return detail;
    }

    // set

    public void setIntroduceId(int introduceId) {
        this.introduceId = introduceId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public ArrayList<String> setIntroduce() {
        ArrayList<String> array = new ArrayList<>();
        array.add(String.valueOf(introduceId));
        array.add(name);
        array.add(String.valueOf(img));
        array.add(url);
        array.add(detail);
        return array;
    }
}
