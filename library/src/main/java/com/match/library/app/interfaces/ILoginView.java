package com.match.library.app.interfaces;

import android.view.View;

/**
 * @author ryzhang
 * @date 2018/1/5
 * @time 15:29
 * Project
 */
public interface ILoginView {
    String getUsername();

    String getPassword();

    void initView();

    void setViewClickListener(View.OnClickListener onclikLister);

    void onUsernameEmpty();   // 用户名为空时的显示操作

    void onPasswordEmpty();   // 密码为空时的显示操作

    void onLoginSuccess();   // 登陆成功时的显示操作

    void onLoginFail();   // 密码失败时的显示操作
}
