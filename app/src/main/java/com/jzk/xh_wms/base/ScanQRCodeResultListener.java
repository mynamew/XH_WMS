package com.jzk.xh_wms.base;
/** 
  * 扫描的返回接口
  * @author   jzk
  * create at: 2018/8/10 11:20
  */  
public interface ScanQRCodeResultListener {
    void scanSuccess(int requestCode, String result);

}
