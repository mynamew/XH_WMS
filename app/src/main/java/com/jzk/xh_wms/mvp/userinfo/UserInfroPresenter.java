package com.jzk.xh_wms.mvp.userinfo;

import android.content.Context;

import com.jzk.xh_wms.base.presenter.impl.MvpBasePresenter;


/**
 * author: timi
 * create at: 2017-08-21 10:05
 */
public class UserInfroPresenter extends MvpBasePresenter<UserInfoView> {
    private UserInfoModel model=null;
    public UserInfroPresenter(Context context) {
        super(context);
        model=new UserInfoModel();
    }

    /**
     * 获取从sp 来得用户信息，并且进行设置
     */
    public void getUserInfoFromSp(){
        getView().setSpUserInfo(model.getSpUserInfo());
    }
}
