package com.jzk.xh_wms.mvp.supply;


import com.jzk.xh_wms.base.model.impl.MvpBaseModel;
import com.jzk.xh_wms.data.inject.EquipmentByTypeList;
import com.jzk.xh_wms.data.station.AddMaterialBean;
import com.jzk.xh_wms.data.station.AddMaterialRequest;
import com.jzk.xh_wms.data.station.NoneClass;
import com.jzk.xh_wms.data.station.StationBean;
import com.jzk.xh_wms.data.station.StationRequest;
import com.jzk.xh_wms.data.station.SupplyMaterialBean;
import com.jzk.xh_wms.data.station.ValIsInjectSameBatchRequest;
import com.jzk.xh_wms.data.station.WorkerOrderBean;
import com.jzk.xh_wms.http.HttpManager;
import com.jzk.xh_wms.http.subscriber.HttpSubscriber;

/**
 * $dsc
 * author: timi
 * create at: 2018-07-19 17:05
 */
public class StationSelectModel extends MvpBaseModel {
    /**
     * 获取工位
     *
     * @param request
     * @param stationBeanHttpSubscriber
     */
    public void getStations(StationRequest request, HttpSubscriber<StationBean> stationBeanHttpSubscriber) {
        HttpManager.getInstance().HttpManagerRequest(stationBeanHttpSubscriber,
                apiService -> apiService.getStations(request));
    }

    /**
     * 获取注塑机
     *
     * @param stationBeanHttpSubscriber
     */
    public void getEquipmentByTypeList(HttpSubscriber<EquipmentByTypeList> stationBeanHttpSubscriber) {
        HttpManager.getInstance().HttpManagerRequest(stationBeanHttpSubscriber, apiService -> apiService.getEquipmentByTypeList("MOLDING"));
    }

    /**
     * 获取供料机
     *
     * @param stationBeanHttpSubscriber
     */
    public void getSuppliyEqps(HttpSubscriber<SupplyMaterialBean> stationBeanHttpSubscriber) {
        HttpManager.getInstance().HttpManagerRequest(stationBeanHttpSubscriber, apiService -> apiService.getSuppliyEqps("FEED"));
    }

    /**
     * 获取工单
     *
     * @param stationBeanHttpSubscriber
     */
    public void getMoCode(HttpSubscriber<WorkerOrderBean> stationBeanHttpSubscriber) {
        HttpManager.getInstance().HttpManagerRequest(stationBeanHttpSubscriber, apiService -> apiService.getMoCode(new NoneClass()));
    }

    /**
     * 校验
     *
     * @param request
     * @param stationBeanHttpSubscriber
     */
    public void valIsInjectSameBatch(ValIsInjectSameBatchRequest request, HttpSubscriber<Object> stationBeanHttpSubscriber) {
        HttpManager.getInstance().HttpManagerRequest(stationBeanHttpSubscriber, apiService -> apiService.valIsInjectSameBatch(request));
    }

    /**
     * 上料单号提交
     *
     * @param request
     * @param stationBeanHttpSubscriber
     */
    public void createOrUpdateOnWipMaterial(AddMaterialRequest request, HttpSubscriber<AddMaterialBean> stationBeanHttpSubscriber) {
        HttpManager.getInstance().HttpManagerRequest(stationBeanHttpSubscriber, apiService -> apiService.createOrUpdateOnWipMaterial(request));
    }
}
