package com.jzk.xh_wms.mvp.cnc;


import com.jzk.xh_wms.base.view.iml.MvpBaseView;
import com.jzk.xh_wms.data.cnc.CncBean;
import com.jzk.xh_wms.data.inject.EquipmentByTypeList;
import com.jzk.xh_wms.data.station.StationBean;

public interface CNC1View extends MvpBaseView {
    /**
     * 获取工位代码
     * @param o
     */
    void getStations(StationBean o);

    /**
     * 获取cnc夹具
     * @param o
     */
    void getCNCTongs(EquipmentByTypeList o);

    /**
     * cnc 提交
      * @param o
     */
    void cncCommit(CncBean o);

    /**
     * 设置产品序列号选中
     */
    void setProductCodeSelect();
}
