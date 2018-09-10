package com.jzk.xh_wms.mvp.deviceinfo;

import android.os.Bundle;
import android.text.Selection;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.TextView;

import com.jzk.xh_wms.R;
import com.jzk.xh_wms.base.BaseActivity;
import com.jzk.xh_wms.utils.NetWorkUtils;
import com.jzk.xh_wms.utils.PackageUtils;
import com.jzk.xh_wms.utils.SpUtils;
import com.jzk.xh_wms.utils.ToastUtils;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 设备信息
 * author: timi
 * create at: 2017/8/24 16:38
 */
public class DeviceInfoActivity extends BaseActivity<DeviceInfoView, DeviceInfoPresenter> implements DeviceInfoView {
    @BindView(R.id.et_set_deviceinfo_num)
    EditText etSetDeviceinfoNum;
    @BindView(R.id.tv_set_deviceinfo_mac)
    TextView tvSetDeviceinfoMac;
    @BindView(R.id.tv_set_deviceinfo_ip)
    TextView tvSetDeviceInfoIp;

    @Override
    public int setLayoutId() {
        return R.layout.activity_device_info;
    }

    @Override
    public void initBundle(Bundle savedInstanceState) {
        setActivityTitle(getString(R.string.deviceinfo));
        //设置mac地址
        String strMac = PackageUtils.getMacAddress();
        tvSetDeviceinfoMac.setText(String.format(getString(R.string.set_deviceinfo_mac), TextUtils.isEmpty(strMac) ? "未获取到设备MAC地址" : strMac));
        //设置ip地址
        String strIp = NetWorkUtils.getIP(this.getApplicationContext());
        tvSetDeviceInfoIp.setText(String.format(getString(R.string.set_deviceinfo_ip), TextUtils.isEmpty(strIp) ? "" : strIp));

    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        Map<String, Object> params = new HashMap<>();
        params.put("UserId", SpUtils.getInstance().getUserId());
        params.put("OrgId", SpUtils.getInstance().getOrgId());
        params.put("MAC", PackageUtils.getMac());
        getPresenter().getPDACode(params);
    }

    @Override
    public DeviceInfoPresenter createPresenter() {
        return new DeviceInfoPresenter(this);
    }

    @Override
    public DeviceInfoView createView() {
        return this;
    }

    @OnClick(R.id.btn_deviceinfo_confirm)
    public void onViewClicked() {
        String setDeviceinfoNum = etSetDeviceinfoNum.getText().toString().trim();
        //判断设备编号是否为空
        if (TextUtils.isEmpty(setDeviceinfoNum)) {
            ToastUtils.showShort(DeviceInfoActivity.this, getString(R.string.please_input_device_num));
            return;
        }
        Map<String, Object> params = new HashMap<>();
        params.put("UserId", SpUtils.getInstance().getUserId());
        params.put("OrgId", SpUtils.getInstance().getOrgId());
        params.put("MAC", PackageUtils.getMac());
        params.put("PDANo", setDeviceinfoNum);
        getPresenter().setPDACode(params);
    }


    @Override
    public void setPDACode() {
        ToastUtils.showShort(R.string.set_pda_code_success);
        onBackPressed();
    }

    @Override
    public void getPDACode(String o) {
        etSetDeviceinfoNum.setText(o);
        Selection.selectAll(etSetDeviceinfoNum.getText());
    }
}
