package com.wd.logindemo2.presenter;

import android.os.Handler;
import android.os.Message;

import com.wd.logindemo2.bean.Result;
import com.wd.logindemo2.core.DataCall;
import com.wd.logindemo2.model.DemoModel;

import org.json.JSONException;

/**
 * @author dingtao
 * @date 2019/4/25 11:42
 * qq:1940870847
 */
public abstract class BasePresenter {

    private DataCall dataCall;

    private boolean isRunning;

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            Result result = (Result)msg.obj;
            if (result.getStatus().equals("0000")) {
                dataCall.success(result.getResult());
            }else{
                dataCall.fail(result);
            }
        }
    };

    public BasePresenter(DataCall dataCall){
        this.dataCall = dataCall;
    }

    public void requestData(Object...args){
        if (isRunning){
            return;
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                isRunning = true;
                Message message = handler.obtainMessage();
                Result result;
                try {
                    result = getModel(args);
                    if (result ==null){
                        result = new Result();
                    }
                } catch (Exception e) {
                    result = new Result();
                    result.setMessage(e.getMessage());
                }
                message.obj = result;
                handler.sendMessage(message);
                isRunning = false;
            }
        }).start();
    }

    abstract Result getModel(Object...args) throws Exception;

    public void unBind(){
        dataCall = null;
    }
}
