package com.match.library.app.interfaces;

/**
 * @author ryzhang
 * @date 2018/1/5
 * @time 15:27
 * Project
 */
public interface ILoginModel {
    void login(String username, String password, LoginCallBack callBack);
}
