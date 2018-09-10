package com.jzk.xh_wms.data.ipqc;
/** 
  * 抽检校验的请求实体
  * @author   jzk
  * create at: 2018/8/5 11:16
  */  
public class CheckRecardInfoRequest {


    /**
     * LotNo : IPQC201808040002
     * InputBox : GM5X504B7963
     * Process : MD
     * TimePerod : 00-02
     * PlanDate : 2018-07-30
     * EqTypeCode : Molding
     * EqCode : 1A01
     */

    private String LotNo;
    private String InputBox;
    private String Process;
    private String TimePerod;
    private String PlanDate;
    private String EqTypeCode;
    private String EqCode;
    private boolean IsFirstOne;

    public String getLotNo() {
        return LotNo;
    }

    public void setLotNo(String LotNo) {
        this.LotNo = LotNo;
    }

    public String getInputBox() {
        return InputBox;
    }

    public void setInputBox(String InputBox) {
        this.InputBox = InputBox;
    }

    public String getProcess() {
        return Process;
    }

    public void setProcess(String Process) {
        this.Process = Process;
    }

    public String getTimePerod() {
        return TimePerod;
    }

    public void setTimePerod(String TimePerod) {
        this.TimePerod = TimePerod;
    }

    public String getPlanDate() {
        return PlanDate;
    }

    public void setPlanDate(String PlanDate) {
        this.PlanDate = PlanDate;
    }

    public String getEqTypeCode() {
        return EqTypeCode;
    }

    public void setEqTypeCode(String EqTypeCode) {
        this.EqTypeCode = EqTypeCode;
    }

    public String getEqCode() {
        return EqCode;
    }

    public void setEqCode(String EqCode) {
        this.EqCode = EqCode;
    }

    public boolean isFirstOne() {
        return IsFirstOne;
    }

    public void setFirstOne(boolean firstOne) {
        IsFirstOne = firstOne;
    }
}
