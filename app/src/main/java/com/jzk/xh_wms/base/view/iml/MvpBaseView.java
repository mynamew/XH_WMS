package com.jzk.xh_wms.base.view.iml;


import com.jzk.xh_wms.base.view.MvpView;

/**
 * mvp 的base  实现类
 */

public interface MvpBaseView extends MvpView {
    void showProgressDialog();


    void dismisProgressDialog();
}
