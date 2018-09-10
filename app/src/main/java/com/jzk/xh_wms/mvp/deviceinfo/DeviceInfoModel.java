package com.jzk.xh_wms.mvp.deviceinfo;


import com.jzk.xh_wms.base.model.impl.MvpBaseModel;
import com.jzk.xh_wms.http.HttpManager;

import java.util.Map;

import io.reactivex.Observer;

/**
 * author: timi
 * create at: 2017-08-24 16:35
 */
public class DeviceInfoModel extends MvpBaseModel {
    /**
     * 设置PDA编号
     * author: timi
     * create at: 2017/8/15 14:26
     */
    public void setPDACode(final Map<String, Object> params, final Observer<Object> observer) {
        HttpManager.getInstance().HttpManagerRequest(observer, apiService -> apiService.setPDACode(params));
    }
    /**
     * 获取PDA编号
     * author: timi
     * create at: 2017/8/15 14:26
     */
    public void getPDACode(final Map<String, Object> params, final Observer<String> observer) {
        HttpManager.getInstance().HttpManagerRequest(observer, apiService -> apiService.getPDACode(params));
    }
}
