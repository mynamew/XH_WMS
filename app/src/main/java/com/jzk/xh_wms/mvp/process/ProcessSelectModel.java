package com.jzk.xh_wms.mvp.process;


import com.jzk.xh_wms.base.model.impl.MvpBaseModel;
import com.jzk.xh_wms.data.process.ProcessSelectBean;
import com.jzk.xh_wms.data.station.NoneClass;
import com.jzk.xh_wms.http.HttpManager;

import java.util.List;

import io.reactivex.Observer;

/**
 * $dsc
 * author: timi
 * create at: 2018-07-19 14:37
 */
public class ProcessSelectModel extends MvpBaseModel {
    /**
     * 获取工序列表
     * @param observer
     */
    public void getProcessList( Observer<List<ProcessSelectBean>> observer){
        HttpManager.getInstance().HttpManagerRequest(observer, apiService ->
                apiService.getProcessList(new NoneClass()));
    }
}
