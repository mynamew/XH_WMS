package com.jzk.xh_wms.mvp.org_change;


import com.jzk.xh_wms.base.view.iml.MvpBaseView;
import com.jzk.xh_wms.data.LoginBean;

/**
 * $dsc
 * author: timi
 * create at: 2018-04-16 09:29
 */

public interface OrganizationSwitchView extends MvpBaseView {
    void changeOrg(LoginBean o);
}
