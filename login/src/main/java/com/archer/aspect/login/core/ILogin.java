package com.archer.aspect.login.core;

import android.content.Context;

/**
 * 登录接口回调
 * Create by linjiaqiang 5/5/21
 */
public interface ILogin {

    /**
     * Desc: 登录事件接收
     * <p>
     * Author: linjiaqiang
     * Date: 5/5/21
     */
    void login(Context applicationContext, int userDefine);

    /**
     * Desc: 判断是否登录
     * <p>
     * Author: linjiaqiang
     * Date: 5/5/21
     */
    boolean isLogin(Context applicationContext);

    /**
     * Desc: 清除登录状态
     * <p>
     * Author: linjiaqiang
     * Date: 5/5/21
     */
    void clearLoginStatus(Context applicationContext);

}