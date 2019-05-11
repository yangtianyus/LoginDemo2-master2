package com.wd.logindemo2.model;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.wd.logindemo2.bean.Goods;
import com.wd.logindemo2.bean.Result;
import com.wd.logindemo2.bean.Shop;
import com.wd.logindemo2.bean.User;
import com.wd.logindemo2.util.HttpUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.List;


public class DemoModel {

    public static Result login(final String mobile, final String password) throws JSONException {
        String result = HttpUtil.getInstance().postByOKHttp(
//                "http://mobile.bwstudent.com/small/user/v1/login",
                "http://172.17.8.100/small/user/v1/login",
                new String[]{"phone", "pwd"},
                new String[]{mobile, password});
        if (result != null) {
//            JSONObject jsonObject = new JSONObject(result);
//            data.setStatus(jsonObject.getString("status"));
//            data.setMessage(jsonObject.getString("message"));
//            jsonObject.getJSONObject("result");
//            jsonObject.getJSONArray("result")
            Type type = new TypeToken<Result<User>>() {
            }.getType();
            Gson gson = new Gson();
            Result<User> data = gson.fromJson(result, type);
            return data;
        }
        return new Result();
    }

    public static Result register(final String mobile, final String password) throws JSONException {
        String result = HttpUtil.getInstance().postByOKHttp(
                "http://172.17.8.100/small/user/v1/register",
                new String[]{"phone", "pwd"},
                new String[]{mobile, password});
        if (result != null) {
            Type type = new TypeToken<Result<User>>() {
            }.getType();
            Gson gson = new Gson();
            Result<User> data = gson.fromJson(result, type);
            return data;
        }
        return new Result();
    }

    public static Result<List<Goods>> getSearchList(String keyword, String page, String count) {

        String result = HttpUtil.getInstance().getByOKHttp(
                "http://172.17.8.100/small/commodity/v1/findCommodityByKeyword",
                new String[]{"keyword", "page", "count"},
                new String[]{keyword, page, count});
        Type type = new TypeToken<Result<List<Goods>>>() {
        }.getType();
        Gson gson = new Gson();
        Result<List<Goods>> data = gson.fromJson(result, type);
        return data;
    }

    public static Result getShopList() {
        String result = HttpUtil.getInstance().getByOKHttp(
                "http://172.17.8.100/small/commodity/v1/findFirstCategory");
        Gson gson = new Gson();

        Type type = new TypeToken<Result<List<Shop>>>() {
        }.getType();

        Result data = gson.fromJson(result, type);
        return data;
    }

    public static Result getGoodsList(String shopId) {
        String result = HttpUtil.getInstance().getByOKHttp(
                "http://172.17.8.100/small/commodity/v1/findSecondCategory",
                new String[]{"firstCategoryId"},
                new String[]{shopId});
        Gson gson = new Gson();

        Type type = new TypeToken<Result<List<Goods>>>() {
        }.getType();

        Result data = gson.fromJson(result, type);
        return data;
    }

}
