package com.jzk.xh_wms.mvp.login;


import com.jzk.xh_wms.base.view.iml.MvpBaseView;
import com.jzk.xh_wms.data.LoginBean;

/**
 * 登录
 */

public interface LoginView extends MvpBaseView {
    void getLoginResult(LoginBean bean);
}
