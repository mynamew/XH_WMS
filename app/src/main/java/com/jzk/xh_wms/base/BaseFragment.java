package com.jzk.xh_wms.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jzk.xh_wms.R;
import com.jzk.xh_wms.base.presenter.MvpPresenter;
import com.jzk.xh_wms.base.view.MvpView;
import com.jzk.xh_wms.base.view.iml.MvpBaseView;
import com.jzk.xh_wms.view.MyProgressDialog;
import com.zhy.autolayout.utils.AutoUtils;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * $dsc
 * author: timi
 * create at: 2017-08-25 16:05
 */

public abstract  class BaseFragment <V extends MvpView, P extends MvpPresenter<V>>extends Fragment implements MvpBaseView {
    private P presenter;
    private V view;
    private Unbinder unbinder;

    public P getPresenter() {
        return presenter;
    }

    public V getMvpView() {
        return view;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View itemView = inflater.inflate(setLayoutId(), null);
        unbinder = ButterKnife.bind(this, itemView);
        AutoUtils.autoSize(itemView);
        //绑定 presenter
        if (this.presenter == null) {
            this.presenter = createPresenter();
        }
        //view
        if (this.view == null) {
            this.view = createView();
        }
        //attach
        if (this.presenter != null && this.view != null) {
            this.presenter.attachView(view);
        }
        initBundle();
        initData();
        return itemView;
    }
    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void showProgressDialog() {
        MyProgressDialog.showProgressDialog(getActivity());
    }

    @Override
    public void dismisProgressDialog() {
        MyProgressDialog.hideProgressDialog();
    }
    /**
     * 绑定P层
     *
     * @return
     */
    public abstract P createPresenter();

    /**
     * 创建View层
     *
     * @return
     */
    public abstract V createView();
    //初始化数据
    public abstract void initData();
    //初始化数据
    public abstract void initBundle();
    //设置布局id
    public abstract int setLayoutId();
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (this.presenter != null) {
            this.presenter.dettachView();
            this.presenter = null;
        }
        unbinder.unbind();
    }
    /********
     * 页面跳转相关的方法
     **********************************************************************************************/
    final private String keyInterlude = "keyInterlude";

    @Override
    public void startActivity(Intent intent) {
        startActivity(intent, BaseActivity.Interlude.DEFAULT);
    }

    public void startActivity(Intent intent, BaseActivity.Interlude interlude) {
        intent.putExtra(keyInterlude, interlude.ordinal());
        super.startActivity(intent);

        if (interlude == BaseActivity.Interlude.DEFAULT) {
            getActivity().overridePendingTransition(R.transition.slide_in_right, R.anim.none);
        } else if (interlude == BaseActivity.Interlude.POP_FROM_BOTTOM) {
            //从下方弹出
           getActivity(). overridePendingTransition(R.transition.pop_in_bottom, R.anim.none);
        }
    }
    /**
     *设置 文本
     * @param tv
     * @param fomat
     * @param content
     */
    public void setTextViewText(TextView tv, @StringRes int fomat, String content) {
        tv.setText(String.format(getString(fomat), content));
    }
    /**
     *设置 文本
     * @param tv
     * @param fomat
     * @param content
     */
    public void setTextViewText(TextView tv, @StringRes int fomat, Object content) {
        tv.setText(String.format(getString(fomat), String.valueOf(content)));
    }
    /**
     * 设置 文本
     *
     * @param tv
     */
    public void setTextViewContent(TextView tv, Object object) {
        String content = "";
//        if (object instanceof String) {
//            content = TextUtils.isEmpty(String.valueOf(object)) ? getString(R.string.none) : String.valueOf(object);
//        } else {
        content = String.valueOf(object);
//        }
        tv.setText(content);
    }
}
