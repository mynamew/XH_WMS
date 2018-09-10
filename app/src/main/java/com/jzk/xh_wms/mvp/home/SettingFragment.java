package com.jzk.xh_wms.mvp.home;

import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jzk.xh_wms.MainActivity;
import com.jzk.xh_wms.R;
import com.jzk.xh_wms.base.BaseFragment;
import com.jzk.xh_wms.base.Constants;
import com.jzk.xh_wms.data.LoginBean;
import com.jzk.xh_wms.data.VersionBean;
import com.jzk.xh_wms.http.message.BaseMessage;
import com.jzk.xh_wms.http.message.event.HomeEvent;
import com.jzk.xh_wms.mvp.about.AboutActivity;
import com.jzk.xh_wms.mvp.device.SelectDeviceTypeActivity;
import com.jzk.xh_wms.mvp.deviceinfo.DeviceInfoActivity;
import com.jzk.xh_wms.mvp.login.LoginActivity;
import com.jzk.xh_wms.mvp.org_change.OrganizationSwitchActivity;
import com.jzk.xh_wms.mvp.process.ProcessSelectActivity;
import com.jzk.xh_wms.mvp.update_password.UpdatePasswordActivity;
import com.jzk.xh_wms.mvp.userinfo.UserInfoActivity;
import com.jzk.xh_wms.utils.PackageUtils;
import com.jzk.xh_wms.utils.SDCardUtils;
import com.jzk.xh_wms.utils.SpUtils;
import com.jzk.xh_wms.utils.ToastUtils;
import com.jzk.xh_wms.view.MyDialog;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 个人设置的碎片
 * author: timi
 * create at: 2017-08-17 11:34
 */
public class SettingFragment extends BaseFragment<SetFragmentView, SetFragmentPresenter> implements SetFragmentView {
    @BindView(R.id.iv_set_need_update)
    ImageView ivSetNewVersion;
    @BindView(R.id.tv_set_userinfo)
    TextView tvSetUserinfo;
    @BindView(R.id.tv_set_username)
    TextView tvSetUserName;
    @BindView(R.id.tv_set_deviceinfo)
    TextView tvSetDeviceinfo;
    @BindView(R.id.tv_set_update_psw)
    TextView tvSetUpdatePsw;
    @BindView(R.id.tv_set_server)
    TextView tvSetServer;
    @BindView(R.id.tv_set_about)
    TextView tvSetAbout;
    @BindView(R.id.tv_set_title)
    TextView tvSetTitle;
    @BindView(R.id.tv_set_update_version)
    TextView tvSetUpdateVersion;
    @BindView(R.id.tv_set_update_team)
    TextView tvSetUpdateTeam;
    @BindView(R.id.tv_set_exit)
    TextView btnSetExit;
    @BindView(R.id.rl_set_update_version)
    RelativeLayout rlSetUpdateVersion;
    private LoginBean bean = null;

    /**
     * 点击事件
     *
     * @param view
     */
    @OnClick({R.id.tv_set_update_team,R.id.tv_set_device, R.id.tv_set_process, R.id.tv_set_exit, R.id.tv_set_server, R.id.btn_set_exit, R.id.rl_set_update_version, R.id.tv_set_userinfo, R.id.tv_set_deviceinfo, R.id.tv_set_update_psw, R.id.tv_set_about})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            /**
             * 跳转到用户信息
             */
            case R.id.tv_set_userinfo:
                startActivity(new Intent(getActivity(), UserInfoActivity.class));
                break;
            /**
             * 跳转到设备信息
             */
            case R.id.tv_set_deviceinfo:
                startActivity(new Intent(getActivity(), DeviceInfoActivity.class));
                break;
            /**
             * 跳转到设置密码
             */
            case R.id.tv_set_update_psw:
                startActivity(new Intent(getActivity(), UpdatePasswordActivity.class));
                break;
            /**
             * 跳转到关于
             */
            case R.id.tv_set_about:
                startActivity(new Intent(getActivity(), AboutActivity.class));
                break;
            /**
             * 退出登录
             */
            case R.id.btn_set_exit:
                shwoLogoutDialog();
                break;
            /**
             * 退出登录
             */
            case R.id.tv_set_exit:
                //退出登录 跳转到登录界面
                shwoLogoutDialog();
                break;
            /**
             * 选择工序
             */
            case R.id.tv_set_process:
                startActivity(new Intent(getActivity(), ProcessSelectActivity.class));
                break;
            /**
             * 选择设备
             */
            case R.id.tv_set_device:
                startActivity(new Intent(getActivity(), SelectDeviceTypeActivity.class));
                break;
            /**
             * 服务配置
             */
            case R.id.tv_set_server:
                shwoServerSetDialog();
                break;
            /**
             * 组织切换
             */
            case R.id.tv_set_update_team:
                startActivity(new Intent(getActivity(), OrganizationSwitchActivity.class));
                break;
            /**
             * 版本更新
             */
            case R.id.rl_set_update_version:
                /**
                 * 显示进度条
                 */
                showProgressDialog();
                /**
                 * 获取版本
                 */
                getPresenter().getVersion();
                break;
            default:
        }
    }


    /**
     * 显示退出登录的Dialog
     */
    private MyDialog mLogoutDialog = null;

    /**
     * 显示退出登录的提示框
     */
    public void shwoLogoutDialog() {
        if (null == mLogoutDialog) {
            mLogoutDialog = new MyDialog(getActivity(), R.layout.dialog_logout)
                    .setButtonListener(R.id.tv_logout_cancel, getString(R.string.cancel), new MyDialog.DialogClickListener() {
                        @Override
                        public void dialogClick(MyDialog dialog) {
                            dialog.dismiss();
                        }
                    })
                    .setButtonListener(R.id.tv_logout_confirm, getString(R.string.confirm), new MyDialog.DialogClickListener() {
                        @Override
                        public void dialogClick(MyDialog dialog) {
                            dialog.dismiss();
                            startActivity(new Intent(getActivity(), LoginActivity.class));
                            getActivity().onBackPressed();
                        }
                    })
                    .setAnimation(R.style.popWindow_animation_push)
                    .setImageViewListener(R.id.iv_close, dialog -> dialog.dismiss());
        }
        mLogoutDialog.show();
    }

    /**
     * 显示服务配置的Dialog
     */
    private MyDialog mServerSetDialog = null;

    /**
     * 显示服务配置的提示框
     */
    public void shwoServerSetDialog() {
        if (null == mServerSetDialog) {
            mServerSetDialog = new MyDialog(getActivity(), R.layout.dialog_logout)
                    .setTextViewContent(R.id.tv_title, getString(R.string.server_set))
                    .setTextViewContent(R.id.tv_content, getString(R.string.reset_server_will_logout))
                    .setButtonListener(R.id.tv_logout_cancel, getString(R.string.cancel), new MyDialog.DialogClickListener() {
                        @Override
                        public void dialogClick(MyDialog dialog) {
                            dialog.dismiss();
                        }
                    })
                    .setImageViewListener(R.id.iv_close, new MyDialog.DialogClickListener() {
                        @Override
                        public void dialogClick(MyDialog dialog) {
                            dialog.dismiss();
                        }
                    })
                    .setButtonListener(R.id.tv_logout_confirm, getString(R.string.confirm), new MyDialog.DialogClickListener() {
                        @Override
                        public void dialogClick(MyDialog dialog) {
                            // TODO: 2017/8/25 做登录的数据的清除工作
                            dialog.dismiss();
                            //显示服务配置弹出框
                            ((MainActivity) getActivity()).jumpToLoginActivity(true);
                        }
                    }).setAnimation(R.style.popWindow_animation_push);
        }
        mServerSetDialog.show();
    }

    @Override
    public int setLayoutId() {
        return R.layout.fragment_setting;
    }

    @Override
    public void initData() {
        if (null != bean) {
            tvSetUserName.setText(bean.getFullName());
        }
        BaseMessage.register(this);

        boolean isHaveDownloadNew = SpUtils.getInstance().getBoolean(Constants.IS_HAVE_DOWNLOAD_NEW);
        ivSetNewVersion.setVisibility(!isHaveDownloadNew ? View.VISIBLE : View.INVISIBLE);
    }

    @Override
    public void initBundle() {
        bean = new Gson().fromJson(SpUtils.getInstance().getLoginBeanStr(), LoginBean.class);
    }

    @Override
    public SetFragmentPresenter createPresenter() {
        return new SetFragmentPresenter(getActivity());
    }

    @Override
    public SetFragmentView createView() {
        return this;
    }

    /**
     * 接受语言改变的事件 更改文字
     *
     * @param event
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void languageUpdate(HomeEvent event) {
        /**
         * 更改本地多语言 设置文本
         */
        /**
         * 设置用户名
         */
        tvSetUserName.setText(new Gson().fromJson(SpUtils.getInstance().getLoginBeanStr(), LoginBean.class).getFullName());
        /**
         * 本地多语言设置
         */
        tvSetAbout.setText(R.string.about);
        tvSetDeviceinfo.setText(R.string.deviceinfo);
        tvSetUpdatePsw.setText(R.string.update_password);
        tvSetServer.setText(R.string.server_set);
        tvSetUserinfo.setText(R.string.set_userinfo);

        tvSetTitle.setText(R.string.home_mine);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        BaseMessage.unregister(this);
    }

    MyDialog myDialogProgress;

    @Override
    public void getVersion(VersionBean versionBean) {
        try {
            //当前应用的版本号
            String versionName = PackageUtils.getVersionName(getActivity());
            //新版本号：需要自己合成
            final String newVersion = versionBean.getVersion() / 100 + "." + versionBean.getVersion() % 100 / 10 + "." + versionBean.getVersion()%100 % 10;
            String updateUrl = SpUtils.getInstance().getBaseUrl() + versionBean.getPath();
            //应用版本号和服务端的版本号不一致 则需要更新否则无操作直接提示已经是最新版本
            //是否需要版本更新
            if (!versionName.equals(newVersion)) {
                MyDialog myDialog = new MyDialog(getActivity(), R.layout.dialog_update_version)
                        .setButtonListener(R.id.versionchecklib_version_dialog_commit, null, new MyDialog.DialogClickListener() {
                            @Override
                            public void dialogClick(MyDialog dialog) {
                                dialog.dismiss();
                                myDialogProgress = new MyDialog(getActivity(), R.layout.dialog_progress)
                                        .setTextViewContent(R.id.tv_content, "正在下载...")
                                        .setCantCancelByBackPress()
                                        .setCancelByOutside(false);
                                myDialogProgress.show();
                                getPresenter().downLoad(updateUrl, versionBean, newVersion);
                            }
                        });
                /**
                 * 是否是强制更新
                 */
                if (versionBean.getUpdateMode() == 2) {
                    myDialog.setCantCancelByBackPress();
                }
                myDialog.setTextViewContent(R.id.tv_title, newVersion + "版本更新")
                        .setTextViewContent(R.id.tv_msg, versionBean.getRemark());
                myDialog.show();
            } else {
                ToastUtils.showShort(R.string.is_new_version);
            }
            //设置版本更新的标识
            SpUtils.getInstance().putBoolean(Constants.IS_HAVE_DOWNLOAD_NEW, versionName.equals(newVersion));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void downLoadApk(File o, VersionBean versionBean, String newVersion) {
        myDialogProgress.dismiss();
        Intent intent = new Intent();
        // 执行动作
        intent.setAction(Intent.ACTION_VIEW);
        File file = new File(SDCardUtils.getAPKPath(getActivity()) + "/" + Constants.APK_NAME);
        // 执行的数据类型
        intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
        getActivity().startActivity(intent);
    }
}
