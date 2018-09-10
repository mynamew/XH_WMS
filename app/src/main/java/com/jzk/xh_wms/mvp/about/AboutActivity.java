package com.jzk.xh_wms.mvp.about;

import android.os.Bundle;
import android.widget.TextView;

import com.jzk.xh_wms.R;
import com.jzk.xh_wms.base.BaseNoMvpActivity;
import com.jzk.xh_wms.utils.PackageUtils;

import butterknife.BindView;


public class AboutActivity extends BaseNoMvpActivity {
    @BindView(R.id.tv_current_version)
    TextView tvCurrentVersion;

    @Override
    public int setLayoutId() {
        return R.layout.activity_about;
    }

    @Override
    public void initBundle(Bundle savedInstanceState) {
        setActivityTitle("关于");
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        tvCurrentVersion.setText(PackageUtils.getAppVersionName(this));
    }

}
