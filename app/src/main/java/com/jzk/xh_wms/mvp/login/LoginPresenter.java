package com.jzk.xh_wms.mvp.login;

import android.content.Context;

import com.jzk.xh_wms.base.presenter.impl.MvpBasePresenter;
import com.jzk.xh_wms.data.LoginBean;
import com.jzk.xh_wms.data.LoginRequest;
import com.jzk.xh_wms.http.callback.OnResultCallBack;
import com.jzk.xh_wms.http.subscriber.HttpSubscriber;


/**
 * s
 * Created by jzk on 2017/8/14.
 */

public class LoginPresenter extends MvpBasePresenter<LoginView> {
    private LoginModel mLoginModel;
    private HttpSubscriber<LoginBean> mLoginBeanHttpSubscriber;

    public LoginPresenter(Context context) {
        super(context);
        this.mLoginModel = new LoginModel();
    }

    /**
     * 网络请求 获取登录结果 
     *
     * @param bean
     */
    public void getLoginResult(LoginRequest bean) {
        /**
         * 显示进度条
         */
          getView().showProgressDialog();
            /**
         * 初始化观察者
         */
        if (null == mLoginBeanHttpSubscriber) {
            mLoginBeanHttpSubscriber = new HttpSubscriber<>(new OnResultCallBack<LoginBean>() {
                @Override
                public void onSuccess(LoginBean loginBean) {
                    getView().getLoginResult(loginBean);
                }

                @Override
                public void onError(String errorMsg) {
//                    ToastUtils.showShort(errorMsg);
                }
            });
        }
        /**
         * 调用请求的方法
         */
        mLoginModel.login(bean,mLoginBeanHttpSubscriber);
    }

    @Override
    public void dettachView() {
        super.dettachView();
        //反注册
        if (null != mLoginBeanHttpSubscriber) {
            mLoginBeanHttpSubscriber.unSubscribe();
            mLoginBeanHttpSubscriber = null;
        }
    }
}
