package com.jzk.xh_wms.mvp.userinfo;

import android.os.Bundle;
import android.widget.TextView;

import com.jzk.xh_wms.R;
import com.jzk.xh_wms.base.BaseActivity;
import com.jzk.xh_wms.data.UserInfoBean;

import butterknife.BindView;

/**
 * 个人信息的显示界面
 * author: timi
 * create at: 2017/8/21 9:00
 */
public class UserInfoActivity extends BaseActivity<UserInfoView,UserInfroPresenter> implements UserInfoView{
    @BindView(R.id.tv_set_userinfo_num)
    TextView tvSetUserinfoNum;
    @BindView(R.id.tv_set_userinfo_name)
    TextView tvSetUserinfoName;
    @BindView(R.id.tv_set_userinfo_sex)
    TextView tvSetUserinfoSex;
    @BindView(R.id.tv_set_userinfo_tel)
    TextView tvSetUserinfoTel;
    @BindView(R.id.tv_set_userinfo_depart)
    TextView tvSetUserinfoDepart;
    @BindView(R.id.tv_set_userinfo_from)
    TextView tvSetUserinfoFrom;

    @Override
    public int setLayoutId() {
        return R.layout.activity_user_info;
    }

    @Override
    public void initBundle(Bundle savedInstanceState) {
        setActivityTitle("用户信息");
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        //获取用户信息
        getPresenter().getUserInfoFromSp();
    }

    @Override
    public UserInfroPresenter createPresenter() {
        return new UserInfroPresenter(this);
    }

    @Override
    public UserInfoView createView() {
        return this;
    }

    @Override
    public void setSpUserInfo(UserInfoBean bean) {
        //用户工号
        tvSetUserinfoNum.setText(String.format(getString(R.string.set_userinfo_num),bean.userNum));
        //用户名字
        tvSetUserinfoName.setText(String.format(getString(R.string.set_userinfo_name),bean.userName));
        //用户性别
        tvSetUserinfoSex.setText(String.format(getString(R.string.set_userinfo_sex),bean.userSex));
        //用户部门
        tvSetUserinfoDepart.setText(String.format(getString(R.string.set_userinfo_depart),bean.userDepart));
        //用户电话
        tvSetUserinfoTel.setText(String.format(getString(R.string.set_userinfo_tel),bean.userTel));
        //用户组织
        tvSetUserinfoFrom.setText(String.format(getString(R.string.set_userinfo_from),bean.userFrom));
        //用户编号
        tvSetUserinfoNum.setText(String.format(getString(R.string.set_userinfo_num),bean.userNum));
    }
}
