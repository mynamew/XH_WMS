package com.jzk.xh_wms.mvp.ipqc.record;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;

import com.borax12.materialdaterangepicker.date.DatePickerDialog;
import com.jzk.spinnerlibrary.MaterialSpinner;
import com.jzk.xh_wms.R;
import com.jzk.xh_wms.base.BaseActivity;
import com.jzk.xh_wms.base.Constants;
import com.jzk.xh_wms.base.adapter.BaseRecyclerAdapter;
import com.jzk.xh_wms.base.adapter.RecyclerViewHolder;
import com.jzk.xh_wms.data.device.DeviceResponse;
import com.jzk.xh_wms.data.ipqc.IpqcCommonResult;
import com.jzk.xh_wms.data.ipqc.record.IpqcProcessResult;
import com.jzk.xh_wms.data.ipqc.record.IpqcRecordRequest;
import com.jzk.xh_wms.data.ipqc.record.IpqcRecordResult;
import com.jzk.xh_wms.data.station.InjectMoldBean;
import com.jzk.xh_wms.utils.CommonDialogUtils;
import com.jzk.xh_wms.utils.DateUtils;
import com.jzk.xh_wms.utils.LogUitls;
import com.jzk.xh_wms.view.MyDialog;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;

/**
 * 抽检记录
 *
 * @author jzk
 * create at: 2018/8/27 19:34
 */
public class IpqcRecordActivity extends BaseActivity<IpqcRecordView, IpqcRecordPresenter> implements IpqcRecordView {


    @BindView(R.id.rlv_ipqc_record)
    RecyclerView rlvIpqcRecord;
    /**
     * 查询的弹出框
     */
    MyDialog myQueryConditionDialog;
    /**
     * 工序
     */
    private MaterialSpinner spinnerProcess;
    List<IpqcProcessResult.DpListBean> mData = new ArrayList<>();
    /********工单***********************************************************************************************/
    /**
     * 工单
     */
    private MaterialSpinner spinnerTime;
    List<IpqcCommonResult.DpListBean> qualityTimes = new ArrayList<>();
    /********设备类型***********************************************************************************************/

    private MaterialSpinner spinnerDeviceType;
    List<DeviceResponse> deviceTypes = new ArrayList<>();

    /********设备***********************************************************************************************/
    private MaterialSpinner spinnerDevice;
    List<InjectMoldBean.EqpmentsBean> devices = new ArrayList<>();
    EditText etDevice;
    /**
     * 批次号
     */
    EditText etBatchNo;
    /**
     * 适配器及数据源
     */
    BaseRecyclerAdapter<IpqcRecordResult.IPQCRecordListBean> adapter;
    List<IpqcRecordResult.IPQCRecordListBean> mRecordList = new ArrayList<>();

    IpqcRecordResult mIpqcRecordResult;
    List<IpqcRecordResult.IPQCRcardListBean> mRecardList = new ArrayList<>();

    @Override
    public int setLayoutId() {
        return R.layout.activity_ipqc_record;
    }

    @Override
    public void initBundle(Bundle savedInstanceState) {
        setActivityTitle(R.string.title_check_record);
    }

    @Override
    public void initView() {
        myQueryConditionDialog = new MyDialog(IpqcRecordActivity.this, R.layout.dialog_ipqc_record_query);
        spinnerProcess = myQueryConditionDialog.findViewById(R.id.spinner_process);
        spinnerTime = myQueryConditionDialog.findViewById(R.id.spinner_time_frame);
        spinnerDeviceType = myQueryConditionDialog.findViewById(R.id.spinner_device_type);
        etDevice = myQueryConditionDialog.findViewById(R.id.et_device);
        spinnerDevice = myQueryConditionDialog.findViewById(R.id.spinner_device);
        etBatchNo = myQueryConditionDialog.findViewById(R.id.et_batch_no);
        myQueryConditionDialog.setTextViewListener(R.id.tv_start_time, new MyDialog.DialogClickListener() {
            @Override
            public void dialogClick(final MyDialog dialog) {
                Calendar now = Calendar.getInstance();
                DatePickerDialog dpd = DatePickerDialog.newInstance(
                        (view, year, monthOfYear, dayOfMonth, yearEnd, monthOfYearEnd, dayOfMonthEnd) -> {
                            /**
                             * 选择 开始和结束时间的返回
                             */
                            String date = "You picked the following date: From- " + dayOfMonth + "/" + (++monthOfYear) + "/" + year + " To " + dayOfMonthEnd + "/" + (++monthOfYearEnd) + "/" + yearEnd;
                            LogUitls.e("时间--->", date);
                            myQueryConditionDialog.getTextView(R.id.tv_start_time).setText(DateUtils.dateStr2CommonDateStr(year, monthOfYear, dayOfMonth));
                            myQueryConditionDialog.getTextView(R.id.tv_end_time).setText(DateUtils.dateStr2CommonDateStr(yearEnd, monthOfYearEnd, dayOfMonthEnd));

                        },
                        now.get(Calendar.YEAR),
                        now.get(Calendar.MONTH),
                        now.get(Calendar.DAY_OF_MONTH)
                );
                dpd.setAutoHighlight(true);
                dpd.show(getFragmentManager(), "Datepickerdialog");
            }
        });
        myQueryConditionDialog.setImageViewListener(R.id.iv_batch_scan, dialog
                -> scan(Constants.REQUEST_SCAN_CODE_BATCH_NO, (requestCode, result)
                -> etBatchNo.setText(result)));
        setEdittextListener(etBatchNo, Constants.REQUEST_SCAN_CODE_BATCH_NO,
                R.string.input_batch_no_quality, 0, result -> {

                });
        /**
         * 设置默认当前时间
         */
        myQueryConditionDialog.getTextView(R.id.tv_start_time).setText(DateUtils.ms2DateOnlyDay(System.currentTimeMillis()));
        myQueryConditionDialog.getTextView(R.id.tv_end_time).setText(DateUtils.ms2DateOnlyDay(System.currentTimeMillis()));
        /**
         * 选择时间弹出框
         */
        myQueryConditionDialog.setTextViewListener(R.id.tv_end_time,
                dialog -> {
                    Calendar now = Calendar.getInstance();
                    DatePickerDialog dpd = DatePickerDialog.newInstance(
                            (view, year, monthOfYear, dayOfMonth, yearEnd, monthOfYearEnd, dayOfMonthEnd) -> {
                                /**
                                 * 选择 开始和结束时间的返回
                                 */
                                String date = "You picked the following date: From- " + dayOfMonth + "/" + (++monthOfYear) + "/" + year + " To " + dayOfMonthEnd + "/" + (++monthOfYearEnd) + "/" + yearEnd;
                                LogUitls.e("时间--->", date);
                                myQueryConditionDialog.getTextView(R.id.tv_start_time).setText(DateUtils.dateStr2CommonDateStr(year, monthOfYear, dayOfMonth));
                                myQueryConditionDialog.getTextView(R.id.tv_end_time).setText(DateUtils.dateStr2CommonDateStr(yearEnd, monthOfYearEnd, dayOfMonthEnd));

                            },
                            now.get(Calendar.YEAR),
                            now.get(Calendar.MONTH),
                            now.get(Calendar.DAY_OF_MONTH)
                    );
                    dpd.setAutoHighlight(true);
                    dpd.show(getFragmentManager(), "Datepickerdialog");
                });
        myQueryConditionDialog.setViewListener(R.id.view_left,
                dialog -> dialog.dismiss())
                .setButtonListener(R.id.btn_cancel, null,
                        dialog -> dialog.dismiss())
                .setButtonListener(R.id.btn_query, null, dialog -> {
                    dialog.dismiss();
                    IpqcRecordRequest recordRequest = new IpqcRecordRequest();
                    recordRequest.setLotNo(etBatchNo.getText().toString().trim());
                    recordRequest.setPlanDateStart(myQueryConditionDialog.getTextView(R.id.tv_start_time).getText().toString().trim());
                    recordRequest.setPlanDateEnd(myQueryConditionDialog.getTextView(R.id.tv_end_time).getText().toString().trim());
                    recordRequest.setProcess(mData.get(spinnerProcess.getSelectedIndex()).getValue());
                    recordRequest.setTimePerod(qualityTimes.get(spinnerTime.getSelectedIndex()).getValue());
                    recordRequest.setEqTypeCode(deviceTypes.get(spinnerDeviceType.getSelectedIndex()).getEquipmentTypeCode());
                    /**
                     * 是否选择过设备类型，如果没选择过则直接传空
                     */
                    recordRequest.setEqCode(devices.isEmpty() ? "" : devices.get(spinnerDevice.getSelectedIndex()).getValue());
                    showProgressDialog();
                    getPresenter().getIPQCInfoAsync(true,recordRequest);
                });
        setEdittextListener(etDevice, Constants.REQUEST_SCAN_CODE_DEVICE, R.string.input_device, 0, new EdittextInputListener() {
            @Override
            public void verticalSuccess(String result) {
                boolean isAliveDevice=false;
                for (int i = 0; i <devices.size(); i++) {
                    if(devices.get(i).getValue().equals(result)){
                        isAliveDevice=true;
                        spinnerDevice.setSelectedIndex(i);
                    }
                }
                if(!isAliveDevice){
                    CommonDialogUtils.showErrorTipDialog(IpqcRecordActivity.this,getString(R.string.error_title),getString(R.string.tip_input_device_noalive));
                }
            }
        });
        setRightImg(R.mipmap.quatily_fliter, new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                myQueryConditionDialog.show();
            }
        });
    }

    @Override
    public void initData() {
        showProgressDialog();
        //工序和时段
        getPresenter().getTimePerodAsync();
        getPresenter().getProcessSelectSubscriber();
        getPresenter().getEquipmentTypeListasync();
        IpqcRecordRequest recordRequest = new IpqcRecordRequest();
        String currentDate = DateUtils.ms2DateOnlyDay(System.currentTimeMillis());
        recordRequest.setPlanDateStart(currentDate);
        recordRequest.setPlanDateEnd(currentDate);
        getPresenter().getIPQCInfoAsync(false,recordRequest);
    }

    @Override
    public IpqcRecordPresenter createPresenter() {
        return new IpqcRecordPresenter(this);
    }

    @Override
    public IpqcRecordView createView() {
        return this;
    }

    @Override
    public void getTimePerodAsync(IpqcCommonResult o) {
        if (null != o.getDpList() && !o.getDpList().isEmpty()) {
            IpqcCommonResult.DpListBean dpListBean = new IpqcCommonResult.DpListBean();
            dpListBean.setDisplayText(getString(R.string.please_select));
            dpListBean.setValue("");
            qualityTimes.add(dpListBean);
            qualityTimes.addAll(o.getDpList());

            ArrayList<String> strs = new ArrayList<>();
            for (int i = 0; i < qualityTimes.size(); i++) {
                strs.add(qualityTimes.get(i).getDisplayText());
            }
            spinnerTime.setItems(strs);
        } else {
            spinnerProcess.setText(R.string.tip_no_time);
        }
        //隐藏加载框
        dismissProgressDialog();
    }

    @Override
    public void getProcessSelectSubscriber(IpqcProcessResult data) {
        if (null == data.getDpList() || data.getDpList().isEmpty()) {
            spinnerProcess.setText(R.string.no_process);
        } else {
            mData.clear();
            IpqcProcessResult.DpListBean dpListBean = new IpqcProcessResult.DpListBean();
            dpListBean.setDisplayText(getString(R.string.please_select));
            dpListBean.setValue("");
            mData.add(dpListBean);
            mData.addAll(data.getDpList());
            List<String> mStrs = new ArrayList<>();
            for (int i = 0; i < mData.size(); i++) {
                mStrs.add(mData.get(i).getDisplayText());
            }
            spinnerProcess.setItems(mStrs);
        }
        //隐藏加载框
        dismissProgressDialog();
    }

    @Override
    public void getIPQCInfoAsync(IpqcRecordResult o) {
        if (null != o && !o.getIPQCRecordList().isEmpty() && !o.getIPQCRcardList().isEmpty()) {
            mIpqcRecordResult = o;
            mRecordList.clear();
            mRecordList.addAll(mIpqcRecordResult.getIPQCRecordList());
            mRecardList.clear();
            mRecardList.addAll(mIpqcRecordResult.getIPQCRcardList());
            if (null == adapter) {
                adapter = new BaseRecyclerAdapter<IpqcRecordResult.IPQCRecordListBean>(this, mRecordList) {
                    @Override
                    protected int getItemLayoutId(int viewType) {
                        return R.layout.item_ipqc_record;
                    }

                    @Override
                    protected void bindData(RecyclerViewHolder holder, int position, IpqcRecordResult.IPQCRecordListBean item) {
                        holder.setTextView(R.id.tv_process_code, item.getProcessCode());
                        int[] ints = DateUtils.dateExchangeByServerLet(Integer.parseInt(item.getPlanDay()));
                        String time = DateUtils.dateStr2CommonDateStr(ints[0], ints[1], ints[2]);
                        holder.setTextView(R.id.tv_ipqc_date, time);
                        holder.setTextView(R.id.tv_time_frame_name, item.getPlanTpCode());
                        holder.setTextView(R.id.tv_ipqc_batch, item.getLotNo());
                        holder.setTextView(R.id.tv_status, item.getEqCode());
                        for (int i = 0; i < mRecardList.size(); i++) {
                            IpqcRecordResult.IPQCRcardListBean ipqcRcardListBean = mRecardList.get(i);
                            if (ipqcRcardListBean.getLotNo().equals(item.getLotNo())) {
                                boolean good = ipqcRcardListBean.getStatus().equals("GOOD");
                                holder.setTextView(R.id.tv_ipqc_result, good ? getString(R.string.pass) : getString(R.string.unpass));
                                holder.getTextView(R.id.tv_ipqc_result)
                                        .setTextColor(good ? getResources().getColor(R.color.colorPrimary)
                                                : getResources().getColor(R.color.red));

                            }
                        }
                    }
                };
                rlvIpqcRecord.setAdapter(adapter);
                rlvIpqcRecord.setLayoutManager(new LinearLayoutManager(this));
            } else {
                adapter.notifyDataSetChanged();
            }
            dismissProgressDialog();
        } else {
            super.dismisProgressDialog();
            CommonDialogUtils.showErrorTipDialog(this, getString(R.string.query_tip), getString(R.string.tip_no_check_record));
            if(null!=mRecordList&&!mRecordList.isEmpty()){
                mRecordList.clear();
                adapter.notifyDataSetChanged();
            }
        }
    }

    @Override
    public void getEquipmentTypeListasync(List<DeviceResponse> data) {
        if (null == data || data.isEmpty()) {
            spinnerDeviceType.setText(R.string.no_process);
        } else {
            deviceTypes.clear();
            DeviceResponse response=new DeviceResponse();
            response.setEquipmentTypeName(getString(R.string.please_select));
            response.setEquipmentTypeCode("");
            deviceTypes.add(response);
            deviceTypes.addAll(data);
            ArrayList<String> strs = new ArrayList<>();
            for (int i = 0; i < deviceTypes.size(); i++) {
                strs.add(deviceTypes.get(i).getEquipmentTypeName());
            }
            spinnerDeviceType.setItems(strs);

            /**
             * 设置监听器
             */
            spinnerDeviceType.setOnItemSelectedListener((view, position, id, item) -> {
                showProgressDialog();
                getPresenter().getEqCodeAsync(deviceTypes.get(position).getEquipmentTypeCode());
            });
        }
    }

    @Override
    public void getEqCodeAsync(IpqcCommonResult o) {
        if (null != o.getEqCodeList() && !o.getEqCodeList().isEmpty()) {
            devices.clear();
            InjectMoldBean.EqpmentsBean eqpmentsBean = new InjectMoldBean.EqpmentsBean();
            eqpmentsBean.setDisplayText(getString(R.string.please_select));
            eqpmentsBean.setValue("");
            devices.add(eqpmentsBean);
            devices.addAll(o.getEqCodeList());
            ArrayList<String> strings = new ArrayList<>();
            for (int i = 0; i < devices.size(); i++) {
                strings.add(devices.get(i).getDisplayText());
            }
            spinnerDevice.setItems(strings);
        }
    }

    /**
     * 判断是否隐藏加载框
     */
    public void dismissProgressDialog() {
        if (!mData.isEmpty() && !qualityTimes.isEmpty()&&!mRecordList.isEmpty()) {
            dismisProgressDialog();
        }
    }
}
