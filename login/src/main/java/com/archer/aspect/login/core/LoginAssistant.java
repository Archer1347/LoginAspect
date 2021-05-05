package com.archer.aspect.login.core;

import android.content.Context;

/**
 * Create by linjiaqiang 5/5/21
 */
public class LoginAssistant {

    private LoginAssistant() {
    }

    private static LoginAssistant instance;

    public static LoginAssistant getInstance() {
        if (instance == null) {
            synchronized (LoginAssistant.class) {
                if (instance == null) {
                    instance = new LoginAssistant();
                }
            }
        }
        return instance;
    }

    private ILogin iLogin;

    public ILogin getLogin() {
        return iLogin;
    }

    public void setLogin(ILogin iLogin) {
        this.iLogin = iLogin;
    }

    private Context applicationContext;

    public Context getApplicationContext() {
        return applicationContext;
    }

    public void setApplicationContext(Context applicationContext) {
        this.applicationContext = applicationContext;
    }

    /**
     * Desc: 判断Token是否过期
     * <p>
     * Author: linjiaqiang
     * Date: 5/5/21
     */
    public void serverTokenInvalidation(int userDefine) {
        if (iLogin == null) {
            return;
        }
        iLogin.clearLoginStatus(applicationContext);
        iLogin.login(applicationContext, userDefine);
    }
}