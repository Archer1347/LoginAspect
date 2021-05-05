package com.archer.login;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;

import com.archer.aspect.login.annotation.LoginFilter;
import com.archer.aspect.login.core.ILogin;
import com.archer.aspect.login.core.LoginSDK;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initLoginSdk(getApplication());
        checkLogin();
    }

    @LoginFilter
    public void checkLogin() {
        Log.e("linjiaqiang", "login true");
    }

    private void initLoginSdk(Application application) {
        LoginSDK.getInstance().init(application, new ILogin() {
            @Override
            public void login(Context applicationContext, int loginStatusCode) {
                // 跳转登录页面
            }

            @Override
            public boolean isLogin(Context applicationContext) {
                //返回登录状态(true 已登录：false 未登录)
                return true;
            }

            @Override
            public void clearLoginStatus(Context applicationContext) {
                //清除登录状态
            }
        });

    }
}