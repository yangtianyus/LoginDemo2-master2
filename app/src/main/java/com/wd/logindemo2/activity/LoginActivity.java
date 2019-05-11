package com.wd.logindemo2.activity;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.tencent.connect.UserInfo;
import com.tencent.connect.auth.QQToken;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;
import com.umeng.analytics.MobclickAgent;
import com.wd.logindemo2.R;
import com.wd.logindemo2.bean.Result;
import com.wd.logindemo2.bean.User;
import com.wd.logindemo2.core.DataCall;
import com.wd.logindemo2.presenter.LoginPresenter;
import com.wd.logindemo2.util.HttpUtil;
import com.wd.logindemo2.util.StringUtil;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText mMobile,mPassword;

    private LoginPresenter loginPresenter;

    private Tencent mTencent;
    private LogInListener mListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mMobile = findViewById(R.id.login_mobile);
        mPassword = findViewById(R.id.login_pas);

        findViewById(R.id.login_btn).setOnClickListener(this);
        findViewById(R.id.login_reg).setOnClickListener(this);
        findViewById(R.id.login_qq_btn).setOnClickListener(this);

        loginPresenter = new LoginPresenter(new LoginCall());

        mTencent = Tencent.createInstance("1108864590", this.getApplicationContext());
    }

    @Override
    public void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.login_btn) {//点击登录按钮
            final String mobile = mMobile.getText().toString();
            final String password = mPassword.getText().toString();

            if (!StringUtil.isPhone(mobile)){
                Toast.makeText(this,"请输入正确手机号",Toast.LENGTH_LONG).show();
                return;
            }
//            if (password.length()<6){
//                Toast.makeText(this,"密码最少6位",Toast.LENGTH_LONG).show();
//                return;
//            }

            //登录请求
            loginPresenter.requestData(mobile,password);

        }else if (v.getId()==R.id.login_reg){//点击注册按钮
            Intent intent = new Intent(this,RegActivity.class);
            startActivity(intent);
        }else if(v.getId()==R.id.login_qq_btn){
            if (!mTencent.isSessionValid()) {
                mListener = new LogInListener();
                mTencent.login(this, "all", mListener);
            }
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Tencent.onActivityResultData(requestCode, resultCode, data, mListener);
    }

    class LoginCall implements DataCall<User>{
        @Override
        public void success(User result) {
            Toast.makeText(getBaseContext(),result.getUserId()+"  "+result.getSessionId(),Toast.LENGTH_LONG).show();
            Intent intent = new Intent(LoginActivity.this,MainActivity.class);
            startActivity(intent);
            finish();
        }

        @Override
        public void fail(Result result) {
            Toast.makeText(getBaseContext(),"失败："+result.getMessage(),Toast.LENGTH_LONG).show();
        }
    }

    class LogInListener implements IUiListener {
        //... (1)授权成功
        @Override
        public void onComplete(Object o) {
            JSONObject jsonObject = (JSONObject) o;
            //①方案：如果我们的应用只需要QQ的openId和token,那么可以直接进行登录
//            new LoginCall().requestData(openId,token);

            //②方案：如果我们的应用需要QQ的头像和昵称的话，那么必须请求QQ获取用户信息之后，在进行登录。
            // 1. 设置openid和token，进一步获取用户信息
            initOpenidAndToken(jsonObject);
            // 2. 获取QQ用户的各信息
            getUserInfo();
        }
        //... (2)授权失败
        @Override
        public void onError(UiError uiError) {
            Toast.makeText(getBaseContext(),"授权失败:错误信息:"+uiError.toString(),Toast.LENGTH_LONG).show();
        }
        //... (3)授权取消
        @Override
        public void onCancel() {
            Toast.makeText(getBaseContext(),"授权取消:",Toast.LENGTH_LONG).show();

        }
    }

    private void initOpenidAndToken(JSONObject jsonObject) {
        try {
            String openid = jsonObject.getString("openid");//获取OpenId
            String token = jsonObject.getString("access_token");
            String expires = jsonObject.getString("expires_in");
            mTencent.setAccessToken(token, expires);
            mTencent.setOpenId(openid);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void getUserInfo() {
        QQToken mQQToken = mTencent.getQQToken();
        UserInfo userInfo = new UserInfo(this, mQQToken);
        userInfo.getUserInfo(new IUiListener() {
                                 @Override
                                 public void onComplete(final Object o) {
                                     try {
                                         JSONObject jsonObject=new JSONObject(o.toString());
                                         String mNickname=jsonObject.getString("nickname");//获取用户QQ名称
                                         String mGender=jsonObject.getString("gender");//获取用户性别
                                         String mUserHeader=jsonObject.getString("figureurl_qq_2");//获取用户头像
                                         //把我们的用户昵称和头像等信息提交到我们服务器进行登录。
                                         Log.i("dt","用户信息："+jsonObject.toString());
                                         Toast.makeText(getBaseContext(),jsonObject.toString(),Toast.LENGTH_LONG).show();
                                     } catch (JSONException e) {
                                         e.printStackTrace();
                                     }
                                 }
                                 @Override
                                 public void onError(UiError uiError) {
                                     Toast.makeText(getBaseContext(),"获取用户QQ信息错误",Toast.LENGTH_LONG).show();
                                 }
                                 @Override
                                 public void onCancel() {
                                     Toast.makeText(getBaseContext(),"取消获取用户QQ信息",Toast.LENGTH_LONG).show();
                                 }
                             }
        );
    }

}
