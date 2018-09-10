package com.jzk.xh_wms.mvp.ipqc.record;

import com.jzk.xh_wms.base.model.impl.MvpBaseModel;
import com.jzk.xh_wms.data.device.DeviceResponse;
import com.jzk.xh_wms.data.ipqc.IpqcCommonResult;
import com.jzk.xh_wms.data.ipqc.record.IpqcProcessResult;
import com.jzk.xh_wms.data.ipqc.record.IpqcRecordRequest;
import com.jzk.xh_wms.data.ipqc.record.IpqcRecordResult;
import com.jzk.xh_wms.data.station.NoneClass;
import com.jzk.xh_wms.http.HttpManager;
import com.jzk.xh_wms.http.api.ApiService;
import com.jzk.xh_wms.http.api.CommonResult;
import com.jzk.xh_wms.http.callback.ApiServiceMethodCallBack;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;


/**
  * ipqc抽检记录的model
  * @author   jzk
  * create at: 2018/8/24 9:26
  */
public class IpqcRecordModel extends MvpBaseModel {
    /**
     * 获取抽检时段
     *
     * @param observer
     */
    public void getTimePerodAsync(Observer<IpqcCommonResult> observer) {
        HttpManager.getInstance().HttpManagerRequest(observer, apiService ->
                apiService.getTimePerodAsync(new NoneClass()));
    }
    /**
     * 获取工序列表
     * @param observer
     */
    public void getProcessList( Observer<IpqcProcessResult> observer){
        HttpManager.getInstance().HttpManagerRequest(observer, apiService ->
                apiService.getProcessAsyncIpqc("IPQCProcess"));
    }
    /**
     * 获取抽检记录
     * @param observer
     */
    public void getIPQCInfoAsync(IpqcRecordRequest recordRequest, Observer<IpqcRecordResult> observer){
        HttpManager.getInstance().HttpManagerRequest(observer, apiService ->
                apiService.getIPQCInfoAsync(recordRequest));
    }
    /**
     * 获取设备列表
     *
     * @param observer
     */
    public void getEqCodeAsync(String eqType,Observer<IpqcCommonResult> observer) {
        HttpManager.getInstance().HttpManagerRequest(observer, new ApiServiceMethodCallBack<IpqcCommonResult>() {
            @Override
            public Observable<CommonResult<IpqcCommonResult>> createObservable(ApiService apiService) {
                return apiService.getEqCodeAsync(eqType);
            }
        });
    }
    /**
     * 获取设备列表
     * @param observer
     */
    public void getEquipmentTypeListasync( Observer<List<DeviceResponse>> observer){
        HttpManager.getInstance().HttpManagerRequest(observer, apiService ->
                apiService.getEquipmentTypeListasync(new NoneClass()));
    }
}
