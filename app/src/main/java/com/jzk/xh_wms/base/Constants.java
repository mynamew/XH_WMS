package com.jzk.xh_wms.base;

/**
 * 需要的静态变量
 */

public class Constants {
    /**
     * base url
     */
    public static final String BASE_URL = "http://szjuqent.imwork.net:91/";
    //超时时间   2分钟
    public static int DEFAULT_TIMEOUT = 2;
    /**
     * 是否下载了最新版本的安装包
     */
    public static final String IS_HAVE_DOWNLOAD_NEW = "is_have_download_new";
    //apk 名称
    public static final String APK_NAME = "Xh_Cps.apk";
    /**********
     * 状态栏颜色
     *********************************************************************************************/
    public static final String StatusColorStr = "#c6ae75";
    /**********
     * SharePerference　存储用户信息 相关
     *********************************************************************************************/
    /**
     * 记录密码
     */
    public static final String REMENBER_PSW = "REMENBER_PSW";
    /**
     * 是否是第一次登录
     */
    public static final String IS_FIRST_LOG = "IS_FIRST_LOG";
    /**
     * 用户名
     */
    public static final String USER_NAME = "USER_NAME";
    /**
     * 用户姓名
     */
    public static final String NCIK_NAME = "NCIK_NAME";
    /**
     * 用户编号
     */
    public static final String USER_NUM = "USER_NUM";
    /**
     * 用户密码
     */
    public static final String USER_PSW = "USER_PSW";
    /**
     * 用户部门
     */
    public static final String USER_DEPART = "USER_DEPART";
    /**
     * 用户性别
     */
    public static final String USER_SEX = "USER_SEX";
    /**
     * 用户手机
     */
    public static final String USER_TEL = "USER_TEL";
    /**
     * 用户所属组织
     */
    public static final String USER_FROM = "USER_FROM";
    /**
     * 组织id
     */
    public static final String ORGANAZATION_ID = "ORGANAZATION_ID";
    /**
     * 用户权限组织
     */
    public static final String USER_ROOT = "USER_ROOT";
    /**
     * 登录的id
     */
    public static final String CUSER_ID = "USER_ID";
    /**
     * 租户信息
     */
    public static final String TENANCY_NAME = "TENANCY_NAME";
    /**
     * 用户所有的信息
     */
    public static final String USER_INFO = "USER_INFO";
    /**
     * 是否重置注塑状态
     */
    public static final String USER_RESET_INJECT= "USER_RESET_INJECT";
    /***********
     * 扫码相关
     *********************************************************************************************/
    /**
     * 产品序列号
     */
    public static final int REQUEST_SCAN_CODE_BARCODE = 1001;
    /**
     * CNC夹具
     */
    public static final int REQUEST_SCAN_CODE_CNC_TONGS = 1002;
    /**
     * 注塑机
     */
    public static final int REQUEST_SCAN_CODE_INJECT_MACHINE = 1003;
    /**
     * 不良代码
     */
    public static final int REQUEST_SCAN_CODE_BAD_CODE = 1004;
    /**
     * 批号
     */
    public static final int REQUEST_SCAN_CODE_BATCH_NO = 1005;
    /**
     * 产品序列号
     */
    public static final int REQUEST_SCAN_CODE_PRODUCT_SERIAL_NO = 1006;
    /**
     * 箱号
     */
    public static final int REQUEST_SCAN_CODE_BOX_NO = 1007;
    /**
     * 稀释剂
     */
    public static final int REQUEST_SCAN_CODE_DILUENT = 1008;
    /**
     * 喷漆
     */
    public static final int REQUEST_SCAN_CODE_PAINT = 1009;  /**
     * 设备
     */
    public static final int REQUEST_SCAN_CODE_DEVICE= 1010;
    /***********
     * SharePerference存储token   key /  value的前缀
     *********************************************************************************************/
    public static final String AUTHORIZATION = "Authorization";
    public static final String AUTHORIZATION_VALUE = "Bearer ";
    /***********
     * SharePerference存储工序  key /  value的前缀
     *********************************************************************************************/

    public static final String PROCESS_SELECT = "PROCESS_SELECT";
    public static final String PROCESS_SELECT_CODE = "PROCESS_SELECT_CODE";
    /***********
     * SharePerference存储设备  key /  value的前缀
     ********************************************************************************************/
    public static final String DEVICE_SELECT_NAME = "DEVICE_SELECT_NAME";
    public static final String DEVICE_SELECT_CODE = "DEVICE_SELECT_CODE";
    /***********
     *SharePerference　存储 应用语言设置 中文简体（zh-CN）、中文繁体（zh-TW）、English（en）
     *********************************************************************************************/
    public static final String LOCALE_LAUGUAGE = "Abp.Localization.CultureName";
    /***********
     * SharePerference存储用户选的baseurl
     *********************************************************************************************/
    public static final String SP_BASE_URL = "SP_BASE_URL";
    /***********
     * Activity之间传递抽检外观的数据
     *********************************************************************************************/
    public static final String QUALITY_APPEARANCE_BEAN = "QUALITY_APPEARANCE_BEAN";
    public static final String QUALITY_APPEARANCE_RESULT_PROCESS= "QUALITY_APPEARANCE_RESULT_PROCESS";
    /***********
     * 跳转 传递的相关字段
     *********************************************************************************************/
    /***********
     * 跳转到登录界面 是否显示服务配置的弹框（ 可能是来自于服务配置按钮的点击）
     *********************************************************************************************/
    public static final String IS_NEED_SHOW_SHOW_SERVER_SET = "isNeedShowServerSet";
    /***********
     * 权限控制菜单的字段
     * 1、PERMISSION_SUPPLY 供货上料权限
     * 2、PERMISSION_INJECT 注塑过站权限
     * 3、PERMISSION_CNC1   CNC1权限
     * 4、PERMISSION_CNC2   CNC2权限
     * 5、PERMISSION_POLISH  抛光
     * 6、PERMISSION_PAINT  喷漆
     *********************************************************************************************/
    public static final String PERMISSION_SUPPLY = "Pages.ProductionPlan.DataCollection.OnWipMaterial.AddMaterial";
    public static final String PERMISSION_INJECT = "Pages.ProductionPlan.DataCollection.Molding";
    public static final String PERMISSION_CNC1 = "Pages.ProductionPlan.DataCollection.CNC";
    public static final String PERMISSION_CNC2 = "Pages.ProductionPlan.DataCollection.CNC2";
    public static final String PERMISSION_POLISH = "Pages.ProductionPlan.DataCollection.Polish";
    public static final String PERMISSION_QUALITY = "Pages.QualityControl.IPQC.IPQCManage.IPQCCollection";
    public static final String PERMISSION_PAINT = "Pages.ProductionPlan.DataCollection.Paint";
    public static final String PERMISSION_QUALITY_RECORD = "Pages.QualityControl.IPQC.IPQCManage.IPQCQuery";
    /**
     * 安装的请求码
     */
    public static  final  int PERMISSION_INSTALL_PACKAGES_REQUESTCODE = 4001;
    public static final  int PERMISSION_GET_UNKNOWN_APP_SOURCES=4002;
    /***********
     * 设备类型
     * 1、INJECT_MOLD 注塑机
     * 2、MOULD 模具
     * 3、FEED  供料机
     *********************************************************************************************/
    public enum DeviceType {
        /**
         * 注塑机
         */
        MOLDING,
        /**
         * 模具
         */
        MOULD,
        /**
         * 上料机
         */
        FEED,
        /**
         * CNC1
         */
        CNC1,
        /**
         * CNC2
         */
        CNC2,
        /**
         * 抛光设备
         */
        POLISH,
    }

}
