package com.jzk.xh_wms.mvp.polishing;

import com.jzk.xh_wms.base.Constants;
import com.jzk.xh_wms.base.model.impl.MvpBaseModel;
import com.jzk.xh_wms.data.polishing.PolishBiographyRequestBean;
import com.jzk.xh_wms.data.polishing.PolishResultBean;
import com.jzk.xh_wms.data.station.InjectMoldBean;
import com.jzk.xh_wms.data.station.StationBean;
import com.jzk.xh_wms.data.station.StationRequest;
import com.jzk.xh_wms.http.HttpManager;
import com.jzk.xh_wms.http.subscriber.HttpSubscriber;

/**
 * Created by JuQent on 2018-07-24.
 */

public class PolishingModel extends MvpBaseModel {
    /**
     * 获取工位
     *
     * @param request
     * @param stationBeanHttpSubscriber
     */
    public void getStations(StationRequest request, HttpSubscriber<StationBean> stationBeanHttpSubscriber) {
        HttpManager.getInstance().HttpManagerRequest(stationBeanHttpSubscriber, apiService -> apiService.getStations(request));
    }


    /**
     * 获取CNC设备
     *
     * @param stationBeanHttpSubscriber
     */
    public void getPolish(HttpSubscriber<InjectMoldBean> stationBeanHttpSubscriber) {
        HttpManager.getInstance().HttpManagerRequest(stationBeanHttpSubscriber, apiService ->
                apiService.getInjectionMoldings(Constants.DeviceType.POLISH.toString()));
    }


    public void collectionPolishAsync(HttpSubscriber<PolishResultBean> polishResultBeanHttpSubscriber, PolishBiographyRequestBean polishBiographyRequestBean){
        HttpManager.getInstance().HttpManagerRequest(polishResultBeanHttpSubscriber, apiService -> apiService.collectionPolishAsync(polishBiographyRequestBean));   }
}
