package com.jzk.xh_wms.mvp.org_change;


import com.jzk.xh_wms.base.model.impl.MvpBaseModel;
import com.jzk.xh_wms.data.LoginBean;
import com.jzk.xh_wms.data.set.ChangeOrgRequest;
import com.jzk.xh_wms.http.HttpManager;

import io.reactivex.Observer;

/**
 * $dsc
 * author: timi
 * create at: 2018-04-16 09:30
 */

public class OrganizationSwitchModel extends MvpBaseModel {
    /**
     * 切换组织
     * @param request
     * @param observer
     */
    public void changeOrgainzation(final ChangeOrgRequest request, Observer<LoginBean> observer) {

        HttpManager.getInstance().HttpManagerRequest(observer, apiService -> apiService.changeOrgainzation(request));
    }
}
