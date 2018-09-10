package com.jzk.xh_wms.mvp.org_change;

import android.content.Context;

import com.jzk.xh_wms.base.presenter.impl.MvpBasePresenter;
import com.jzk.xh_wms.data.LoginBean;
import com.jzk.xh_wms.data.set.ChangeOrgRequest;
import com.jzk.xh_wms.http.callback.OnResultCallBack;
import com.jzk.xh_wms.http.subscriber.HttpSubscriber;


/**
 * $dsc
 * author: timi
 * create at: 2018-04-16 09:30
 */

public class OrganizationSwitchPresenter extends MvpBasePresenter<OrganizationSwitchView> {
    private OrganizationSwitchModel model;
    private HttpSubscriber<LoginBean> subscriber;

    public OrganizationSwitchPresenter(Context context) {
        super(context);
        model = new OrganizationSwitchModel();
    }

    public void changeOrgainzation(ChangeOrgRequest request) {
        if (null == subscriber) {
            subscriber = new HttpSubscriber<LoginBean>(new OnResultCallBack<LoginBean>() {
                @Override
                public void onSuccess(LoginBean o) {
                    getView().changeOrg(o);
                }

                @Override
                public void onError(String errorMsg) {

                }
            });
        }
        model.changeOrgainzation(request, subscriber);
    }

    @Override
    public void dettachView() {
        super.dettachView();
        if (null != subscriber) {
            subscriber.unSubscribe();
            subscriber = null;
        }
    }
}
