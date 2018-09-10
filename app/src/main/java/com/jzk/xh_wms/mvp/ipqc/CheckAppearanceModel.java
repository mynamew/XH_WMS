package com.jzk.xh_wms.mvp.ipqc;


import com.jzk.xh_wms.base.model.impl.MvpBaseModel;
import com.jzk.xh_wms.data.ipqc.CalculateCheckCountRequest;
import com.jzk.xh_wms.data.ipqc.CheckRecardInfoRequest;
import com.jzk.xh_wms.data.ipqc.IpqcCommonResult;
import com.jzk.xh_wms.data.station.NoneClass;
import com.jzk.xh_wms.http.HttpManager;
import com.jzk.xh_wms.http.api.ApiService;
import com.jzk.xh_wms.http.api.CommonResult;
import com.jzk.xh_wms.http.callback.ApiServiceMethodCallBack;
import com.jzk.xh_wms.utils.SpUtils;

import io.reactivex.Observable;
import io.reactivex.Observer;

/**
 * 抽检外观的Model
 *
 * @author jzk
 * create at: 2018/8/3 16:24
 */
public class CheckAppearanceModel extends MvpBaseModel {
    /**
     * 获取批号信息
     *
     * @param lotNo
     * @param observer
     */
    public void getLotInfoAsync(String lotNo, Observer<IpqcCommonResult> observer) {
        HttpManager.getInstance().HttpManagerRequest(observer, new ApiServiceMethodCallBack<IpqcCommonResult>() {
            @Override
            public Observable<CommonResult<IpqcCommonResult>> createObservable(ApiService apiService) {
                return apiService.getLotInfoAsync(lotNo);
            }
        });
    }

    /**
     * 计算抽检总数
     *
     * @param request
     * @param observer
     */
    public void calculateCheckCountAsync(CalculateCheckCountRequest request, Observer<IpqcCommonResult> observer) {
        HttpManager.getInstance().HttpManagerRequest(observer, new ApiServiceMethodCallBack<IpqcCommonResult>() {
            @Override
            public Observable<CommonResult<IpqcCommonResult>> createObservable(ApiService apiService) {
                return apiService.calculateCheckCountAsync(request);
            }
        });
    }

    /**
     * 生成批号
     *
     * @param observer
     */
    public void createNewLotNoAsync(Observer<IpqcCommonResult> observer) {
        HttpManager.getInstance().HttpManagerRequest(observer, new ApiServiceMethodCallBack<IpqcCommonResult>() {
            @Override
            public Observable<CommonResult<IpqcCommonResult>> createObservable(ApiService apiService) {
                return apiService.createNewLotNoAsync("IPQC");
            }
        });
    }

    /**
     * 获取质检名称
     *
     * @param observer
     */
    public void getIQPCNameAsync(Observer<IpqcCommonResult> observer) {
        HttpManager.getInstance().HttpManagerRequest(observer, new ApiServiceMethodCallBack<IpqcCommonResult>() {
            @Override
            public Observable<CommonResult<IpqcCommonResult>> createObservable(ApiService apiService) {
                return apiService.getIQPCNameAsync(new NoneClass());
            }
        });
    }

    /**
     * 获取抽检时段
     *
     * @param observer
     */
    public void getTimePerodAsync(Observer<IpqcCommonResult> observer) {
        HttpManager.getInstance().HttpManagerRequest(observer, new ApiServiceMethodCallBack<IpqcCommonResult>() {
            @Override
            public Observable<CommonResult<IpqcCommonResult>> createObservable(ApiService apiService) {
                return apiService.getTimePerodAsync(new NoneClass());
            }
        });
    }

    /**
     * 获取抽检工序
     *
     * @param observer
     */
    public void getProcessAsync(Observer<IpqcCommonResult> observer) {
        HttpManager.getInstance().HttpManagerRequest(observer, new ApiServiceMethodCallBack<IpqcCommonResult>() {
            @Override
            public Observable<CommonResult<IpqcCommonResult>> createObservable(ApiService apiService) {
                return apiService.getProcessAsync("IPQCProcess");
            }
        });
    }
  /**
     * 获取设备列表
     *
     * @param observer
     */
    public void getEqCodeAsync(Observer<IpqcCommonResult> observer) {
        HttpManager.getInstance().HttpManagerRequest(observer, new ApiServiceMethodCallBack<IpqcCommonResult>() {
            @Override
            public Observable<CommonResult<IpqcCommonResult>> createObservable(ApiService apiService) {
                return apiService.getEqCodeAsync(SpUtils.getInstance().getDeivceSelectCode());
            }
        });
    }

    /**
     * 抽检校验
     *
     * @param request
     * @param observer
     */
    public void checkRCardInfoAsync(CheckRecardInfoRequest request, Observer<IpqcCommonResult> observer) {
        HttpManager.getInstance().HttpManagerRequest(observer, new ApiServiceMethodCallBack<IpqcCommonResult>() {
            @Override
            public Observable<CommonResult<IpqcCommonResult>> createObservable(ApiService apiService) {
                return apiService.checkRCardInfoAsync(request);
            }
        });
    }

    /**
     * 批通过
     *
     * @param lotNo
     * @param observer
     */
    public void ipacLotPassAsync(String lotNo,String eqCode, Observer<IpqcCommonResult> observer) {
        HttpManager.getInstance().HttpManagerRequest(observer, new ApiServiceMethodCallBack<IpqcCommonResult>() {
            @Override
            public Observable<CommonResult<IpqcCommonResult>> createObservable(ApiService apiService) {
                return apiService.ipacLotPassAsync(lotNo,eqCode);
            }
        });
    }

    /**
     * 批退
     *
     * @param lotNo
     * @param observer
     */
    public void ipqcLotRejectAsync(String lotNo,String eqCode, Observer<IpqcCommonResult> observer) {
        HttpManager.getInstance().HttpManagerRequest(observer, new ApiServiceMethodCallBack<IpqcCommonResult>() {
            @Override
            public Observable<CommonResult<IpqcCommonResult>> createObservable(ApiService apiService) {
                return apiService.ipqcLotRejectAsync(lotNo, eqCode);
            }
        });
    }
}
