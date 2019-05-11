package com.wd.logindemo2.presenter;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import com.wd.logindemo2.bean.Result;
import com.wd.logindemo2.core.DataCall;
import com.wd.logindemo2.model.DemoModel;
import com.wd.logindemo2.util.HttpUtil;

import org.json.JSONException;

/**
 * @author dingtao
 * @date 2019/4/25 11:42
 * qq:1940870847
 */
public class LoginPresenter extends BasePresenter{

    public LoginPresenter(DataCall dataCall) {
        super(dataCall);
    }

    @Override
    Result getModel(Object... args) throws Exception {
        return DemoModel.login((String)args[0],(String)args[1]);
    }
}
