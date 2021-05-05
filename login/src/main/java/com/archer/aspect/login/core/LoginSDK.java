package com.archer.aspect.login.core;

import android.content.Context;

/**
 * 登录SDK
 * Create by linjiaqiang 5/5/21
 */
public class LoginSDK {

    private static LoginSDK instance;

    private LoginSDK() {
    }

    public static LoginSDK getInstance() {
        if (instance == null) {
            synchronized (LoginSDK.class) {
                if (instance == null) {
                    instance = new LoginSDK();
                }
            }
        }
        return instance;
    }

    public void init(Context context, ILogin iLogin) {
        LoginAssistant.getInstance().setApplicationContext(context);
        LoginAssistant.getInstance().setLogin(iLogin);
    }

    /**
     * Desc: 单点登录，验证token失效的统一接入入口
     * <p>
     * Author: linjiaqiang
     * Date: 5/5/21
     */
    public void serverTokenInvalidation(int userDefine) {
        LoginAssistant.getInstance().serverTokenInvalidation(userDefine);
    }
}