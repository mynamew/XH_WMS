package com.jzk.xh_wms.mvp.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.AppCompatCheckBox;
import android.text.Editable;
import android.text.Selection;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.jzk.xh_wms.MainActivity;
import com.jzk.xh_wms.R;
import com.jzk.xh_wms.base.BaseActivity;
import com.jzk.xh_wms.base.Constants;
import com.jzk.xh_wms.data.LoginBean;
import com.jzk.xh_wms.data.LoginRequest;
import com.jzk.xh_wms.http.message.BaseMessage;
import com.jzk.xh_wms.http.message.event.HomeEvent;
import com.jzk.xh_wms.utils.LanguageUtils;
import com.jzk.xh_wms.utils.LogUitls;
import com.jzk.xh_wms.utils.PackageUtils;
import com.jzk.xh_wms.utils.SpUtils;
import com.jzk.xh_wms.utils.ToastUtils;
import com.jzk.xh_wms.view.MyDialog;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 登录界面
 * author: timi
 * create at: 2017/8/16 8:57
 */
public class LoginActivity extends BaseActivity<LoginView, LoginPresenter> implements LoginView {
    @BindView(R.id.iv_login_eye)
    ImageView ivLoginEye;
    @BindView(R.id.et_login_username)
    EditText etLoginUsername;
    @BindView(R.id.et_login_password)
    EditText etLoginPassword;
    @BindView(R.id.cb_login_rempsw)
    AppCompatCheckBox cbLoginRempsw;
    @BindView(R.id.btn_login)
    TextView btnLogin;
    @BindView(R.id.btn_set)
    TextView btnSet;
    @BindView(R.id.iv_login_clear_username)
    ImageView ivLoginClearUsername;
    @BindView(R.id.tv_app_name)
    TextView tvAppName;
    @BindView(R.id.tv_head_title)
    TextView tvHeadTitle;

    //flag
    private boolean isCanSeePsw = false;

    /**
     * unAuthorizedRequest 是否是token失效，跳转来的登录
     */
    private boolean unAuthorizedRequest;

    @Override
    public int setLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void initBundle(Bundle savedInstanceState) {
        /**
         * 获取用户名
         */
        String userName = SpUtils.getInstance().getString(Constants.USER_NAME);
        if (!TextUtils.isEmpty(userName)) {
            etLoginUsername.setText(userName);
            ivLoginClearUsername.setVisibility(View.VISIBLE);
        }
        /**
         * 获取 存储的密码
         */
        String password = SpUtils.getInstance().getPassword();
        if (!TextUtils.isEmpty(password)) {
            etLoginPassword.setText(password);
        }
        /**
         * 设置是否记录密码
         */
        boolean isRememberPsw = SpUtils.getInstance().getBoolean(Constants.REMENBER_PSW);
        cbLoginRempsw.setChecked(isRememberPsw);
        unAuthorizedRequest = getIntent().getBooleanExtra("unAuthorizedRequest", false);
    }

    @Override
    public void initView() {

        etLoginUsername.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String username = s.toString();
                if (!TextUtils.isEmpty(username)) {
                    ivLoginClearUsername.setVisibility(View.VISIBLE);
                } else {
                    ivLoginClearUsername.setVisibility(View.GONE);
                }
            }
        });
    }

    @Override
    public void initData() {
    }

    @Override
    public LoginPresenter createPresenter() {
        return new LoginPresenter(this);
    }

    @Override
    public LoginView createView() {
        return this;
    }

    @Override
    protected void onResume() {
        super.onResume();
        //如果  url或语言设置为空 则弹出填写框
        if (TextUtils.isEmpty(SpUtils.getInstance().getBaseUrl())
                || TextUtils.isEmpty(SpUtils.getInstance().getLocaleLanguage())) {
            showServerSetDialogShow();
        } else {
            //如果界面传过来的参数需要弹出服务配置的弹出框
            boolean isNeedShowServerSet = getIntent().getBooleanExtra(Constants.IS_NEED_SHOW_SHOW_SERVER_SET, false);
            if (isNeedShowServerSet) {
                showServerSetDialogShow();
            }
        }
    }

    /**
     * 登录的请求
     * author: timi
     * create at: 2017/8/15 18:24
     */
    @OnClick(R.id.btn_login)
    public void submit() {
        String baseUrl = SpUtils.getInstance().getBaseUrl();
        if (TextUtils.isEmpty(baseUrl)) {
            ToastUtils.showShort(this, getString(R.string.tip_set_servise_address));
            return;
        }
        String username = etLoginUsername.getText().toString().trim();
        if (TextUtils.isEmpty(username)) {
            ToastUtils.showShort(this, getString(R.string.tip_username));
            return;
        }
        String password = etLoginPassword.getText().toString().trim();
        if (TextUtils.isEmpty(password)) {
            ToastUtils.showShort(this, getString(R.string.tip_password));
            return;
        }
//        //如果记录密码 存储用户名和密码
        LogUitls.d("是否存入密码-->" + cbLoginRempsw.isChecked());
        if (cbLoginRempsw.isChecked()) {
            //存储用户名和密码
            SpUtils.getInstance()
                    .putUserName(username)
                    .putPassword(password)
                    .putBoolean(Constants.REMENBER_PSW, true);
        } else {
            //清空用户名和密码
            SpUtils.getInstance()
                    .putUserName("")
                    .putPassword("")
                    .putBoolean(Constants.REMENBER_PSW, false);
        }
        //获取租户地址
        String tenancyName = SpUtils.getInstance().gettenancyName();
        String mac = PackageUtils.getMac();
//        //登录请求
        LoginRequest request = new LoginRequest();
        request.setDeviceType(32);
        request.setMac(mac);
        request.setPassword(password);
        request.setTenancyName(tenancyName);
        request.setUsernameOrEmailAddress(username);
        //打印
        LogUitls.e(request.toString());
        getPresenter().getLoginResult(request);
    }

    /**
     * 是否显示密码
     */
    @OnClick(R.id.iv_login_eye)
    public void showPassword() {
        isCanSeePsw = !isCanSeePsw;
        //selector 切换
        ivLoginEye.setSelected(isCanSeePsw);
        //设置是否显示密码
        if (!isCanSeePsw) {
            /**
             *  将文本框的内容以密文形式显示
             */
            etLoginPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
        } else {
            /**
             * 以明文显示
             */
            etLoginPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
        }
        Selection.setSelection(etLoginPassword.getText(), etLoginPassword.getText().length());
    }

    private LoginBean bean = null;

    @Override
    public void getLoginResult(LoginBean bean) {
        //存储 登录的返回
        this.bean = bean;
        Toast.makeText(this, R.string.login_success, Toast.LENGTH_SHORT).show();
        //登录成功  存储 id
        SpUtils.getInstance().putUserid(bean.getUserId());
        SpUtils.getInstance().putOrgId(bean.getOrgUnits().get(0).getId());
        SpUtils.getInstance().putAuthorization(bean.getToken());
        SpUtils.getInstance().putUserName(etLoginUsername.getText().toString().trim());
        SpUtils.getInstance().putNickName(bean.getFullName());
        /**
         * 存储用户的所有信息 以字符串的形式
         */
        SpUtils.getInstance().putLoginBeanStr(new Gson().toJson(bean));
        LogUitls.e("用户信息---->", new Gson().toJson(bean));
        jumpToMainActivity();
    }

    /**
     * 跳转到主页的方法
     */
    private void jumpToMainActivity() {
        if (!unAuthorizedRequest) {
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
        }
        finish();

    }

    private MyDialog myDialog = null;

    @OnClick(R.id.btn_set)
    public void onViewClicked() {
        showServerSetDialogShow();
    }

    /**
     * 清楚用户名
     */
    @OnClick(R.id.iv_login_clear_username)
    public void clearUsername() {
        etLoginUsername.setText("");
        ivLoginClearUsername.setVisibility(View.GONE);
    }

    /**
     * 选择不同的语言 显示不同的语言文字以及Dialog的消失
     *
     * @param index
     */
    private void setCurrentActivityLanguage(int index) {
        final TextView tvLoginLanguage = myDialog.getTextView(R.id.tv_login_language);
        final TextView tvLoginServerSet = myDialog.getTextView(R.id.login_server_set);
        final TextView tvLoginZuhuTip = myDialog.getTextView(R.id.login_zuhu_tip);
        final TextView tvLoginUrlTip = myDialog.getTextView(R.id.login_url_tip);
        final TextView tvLoginLanguageTip = myDialog.getTextView(R.id.login_language_tip);
        final TextView btnLoginComfirmSet = (TextView) myDialog.getView(R.id.bt_login__confirm);
        final TextView btnLoginComfirmCancel = (TextView) myDialog.getView(R.id.btn_cancel);
        EditText etLoginUrl =  myDialog.findViewById(R.id.et_login_server);
        EditText etLoginZuhu =  myDialog.findViewById(R.id.et_login_zuhu);
        RadioButton rdSimple =  myDialog.findViewById(R.id.rd_simple);
        RadioButton rdTradtional =  myDialog.findViewById(R.id.rd_tradtional);
        RadioButton rdEnglish =  myDialog.findViewById(R.id.rd_english);
        switch (index) {
            /**
             * 简体
             */
            case 0:
                SpUtils.getInstance().putLocaleLanguage("zh-CN");
                //存储选择的语言
                LanguageUtils.switchAppLanguage(LoginActivity.this);
                tvLoginLanguage.setText(getString(R.string.language_simple));
                break;
            /**
             * 繁体
             */
            case 1:
                SpUtils.getInstance().putLocaleLanguage("zh-TW");
                LanguageUtils.switchAppLanguage(LoginActivity.this);
                tvLoginLanguage.setText(getString(R.string.language_tradtional));

                break;
            /**
             * 英文
             */
            case 2:
                SpUtils.getInstance().putLocaleLanguage("en");
                LanguageUtils.switchAppLanguage(LoginActivity.this);
                tvLoginLanguage.setText(getString(R.string.language_english));

                break;
            default:
        }
        //切换界面的语言
        btnLogin.setText(getResources().getString(R.string.login_login));
        btnSet.setText(getResources().getString(R.string.login_set));
        cbLoginRempsw.setText(getResources().getString(R.string.login_remember_psw));
        //Dialog 内部
        tvLoginServerSet.setText(getResources().getString(R.string.server_set));
        tvLoginUrlTip.setText(getResources().getString(R.string.login_url_tip));
        tvLoginZuhuTip.setText(getResources().getString(R.string.login_zuhu));
        tvLoginLanguageTip.setText(getResources().getString(R.string.login_language));
        btnLoginComfirmSet.setText(getResources().getString(R.string.login_confirm_set));
        btnLoginComfirmCancel.setText(getResources().getString(R.string.cancel));

        etLoginUrl.setHint(getResources().getString(R.string.login_please_input_serverurl));
        etLoginZuhu.setHint(getResources().getString(R.string.login_please_input_zuhu));
        etLoginPassword.setHint(getResources().getString(R.string.login_inpiut_psw));
        etLoginUsername.setHint(getResources().getString(R.string.login_input_username));

        tvAppName.setText(R.string.home_title);
        tvHeadTitle.setText(R.string.user_log_tip);

        rdSimple.setText(R.string.language_simple);
        rdEnglish.setText(R.string.language_english);
        rdTradtional.setText(R.string.language_tradtional);
        //发送事件 更新主界面的文字
        BaseMessage.post(new HomeEvent(HomeEvent.LANGUAGE_UPDATE));

    }

    //显示和隐藏语言选择图片
    private ImageView ivLoginDown = null;
    //语言选择的布局
    private LinearLayout llSelectLauguage = null;

    /**
     * 显示配置服务的dialog
     */
    private void showServerSetDialogShow() {
        if (null == myDialog) {
            String languageStr = "";
            switch (SpUtils.getInstance().getLocaleLanguage()) {
                case "zh-CN":
                    languageStr = getString(R.string.language_simple);
                    break;
                case "en":
                    languageStr = getString(R.string.language_english);
                    break;
                case "zh-TW":
                    languageStr = getString(R.string.language_tradtional);
                    break;
                default:
                    languageStr = getString(R.string.language_simple);
                    break;
            }
            myDialog = new MyDialog(this, R.layout.dialog_login_server_set)
                    //设置url
                    .setTextViewContent(R.id.et_login_server, SpUtils.getInstance().getBaseUrl())
                    .setTextViewContent(R.id.et_login_zuhu, SpUtils.getInstance().gettenancyName())
                    .setTextViewContent(R.id.tv_login_language, TextUtils.isEmpty(languageStr) ? "" : languageStr)
                    //设置按钮
                    .setButtonListener(R.id.bt_login__confirm, getString(R.string.home_set), new MyDialog.DialogClickListener() {
                        @Override
                        public void dialogClick(MyDialog dialog) {
                            EditText etLoginServer = dialog.getEdittext(R.id.et_login_server);
                            String text = etLoginServer.getText().toString();
                            if (TextUtils.isEmpty(text)) {
                                ToastUtils.showShort(LoginActivity.this, R.string.login_please_input_serverurl);
                                return;
                            }
                            if (!text.contains("http") && text.endsWith("/")) {
                                ToastUtils.showShort(LoginActivity.this, R.string.login_please_input_right_serverurl);
                                //重置地址
                                etLoginServer.setText("");
                                return;
                            }
                            //存储url
                            SpUtils.getInstance().putBaseUrl(text);
                            //存储租户信息
                            String zuhuStr = dialog.getEdittext(R.id.et_login_zuhu).getText().toString();
                            if (!TextUtils.isEmpty(zuhuStr)) {
                                //存储url
                                SpUtils.getInstance().puttenancyName(zuhuStr);
                            }
                            //设置默认的语言（用户不选择时）
                            if (TextUtils.isEmpty(SpUtils.getInstance().getLocaleLanguage())) {
                                SpUtils.getInstance().putLocaleLanguage("zh-CN");
                            }
                            dialog.dismiss();
                        }
                    }).setLinearlayoutListener(R.id.ll_login_language, dialog -> {

                        //显示和隐藏选择语言的下拉框
                        if (null != llSelectLauguage && llSelectLauguage.getVisibility() == View.VISIBLE) {
                            Animation animation = AnimationUtils.loadAnimation(LoginActivity.this, R.anim.rotation_up);
                            animation.setFillAfter(true);
                            ivLoginDown.startAnimation(animation);
                            llSelectLauguage.setVisibility(View.GONE);
                        } else {
                            Animation animation = AnimationUtils.loadAnimation(LoginActivity.this, R.anim.rotation_down);
                            animation.setFillAfter(true);
                            ivLoginDown.startAnimation(animation);
                            llSelectLauguage.setVisibility(View.VISIBLE);
                        }
                    }).setImageViewListener(R.id.iv_close, dialog -> dialog.dismiss()).setButtonListener(R.id.btn_cancel, null, new MyDialog.DialogClickListener() {
                        @Override
                        public void dialogClick(MyDialog dialog) {
                            dialog.dismiss();
                            llSelectLauguage.setVisibility(View.GONE);
                        }
                    });
            //选择语言
            RadioGroup rgSelectLauguage = myDialog.findViewById(R.id.rg_select_lauguage);
            rgSelectLauguage.setOnCheckedChangeListener((group, checkedId) -> {
                switch (checkedId) {
                    case R.id.rd_simple:
                        setCurrentActivityLanguage(0);
                        break;
                    case R.id.rd_tradtional:
                        setCurrentActivityLanguage(1);
                        break;
                    case R.id.rd_english:
                        setCurrentActivityLanguage(2);
                        break;
                    default:
                }
            });
            llSelectLauguage = myDialog.findViewById(R.id.ll_select_lauguage);
            ivLoginDown = (ImageView) myDialog.getView(R.id.iv_login_down);
        }
        myDialog.show();
    }
}
