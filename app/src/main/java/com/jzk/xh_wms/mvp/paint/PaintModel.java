package com.jzk.xh_wms.mvp.paint;


import com.jzk.xh_wms.base.model.impl.MvpBaseModel;
import com.jzk.xh_wms.data.paint.PaintRequest;
import com.jzk.xh_wms.data.paint.PaintResult;
import com.jzk.xh_wms.data.station.InjectMoldBean;
import com.jzk.xh_wms.data.station.NoneClass;
import com.jzk.xh_wms.data.station.StationBean;
import com.jzk.xh_wms.data.station.StationRequest;
import com.jzk.xh_wms.data.station.WorkerOrderBean;
import com.jzk.xh_wms.http.HttpManager;
import com.jzk.xh_wms.http.subscriber.HttpSubscriber;

/**
  * 喷漆的model
  * @author   jzk
  * create at: 2018/8/23 14:24
  */  
public class PaintModel extends MvpBaseModel {
    /**
     * 获取工位
     *
     * @param request
     * @param stationBeanHttpSubscriber
     */
    public void getStations(StationRequest request, HttpSubscriber<StationBean> stationBeanHttpSubscriber) {
        HttpManager.getInstance().HttpManagerRequest(stationBeanHttpSubscriber,
                apiService -> apiService.getStationsPaint(request));
    }

    /**
     * 获取喷漆设备
     *
     * @param stationBeanHttpSubscriber
     */
    public void getEquipmentByTypeList(HttpSubscriber<InjectMoldBean> stationBeanHttpSubscriber) {
        HttpManager.getInstance().HttpManagerRequest(stationBeanHttpSubscriber, apiService ->
                apiService.getEquipmentByTypeListPaint("COATING"));
    }
    /**
     * 获取工单
     *
     * @param stationBeanHttpSubscriber
     */
    public void getMoCode(HttpSubscriber<WorkerOrderBean> stationBeanHttpSubscriber) {
        HttpManager.getInstance().HttpManagerRequest(stationBeanHttpSubscriber, apiService ->
                apiService.getMoCodePaint(new NoneClass()));
    }
    /**
     * 喷漆条码扫描
     *
     * @param stationBeanHttpSubscriber
     */
    public void createOrUpdateOnWipPaint(PaintRequest request, HttpSubscriber<PaintResult> stationBeanHttpSubscriber) {
        HttpManager.getInstance().HttpManagerRequest(stationBeanHttpSubscriber, apiService ->
                apiService.createOrUpdateOnWipPaint(request));
    }
}
