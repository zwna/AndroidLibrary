package org.mt.androidlibrary.pay.param;

/**
 *@Description:微信支付的参数类
 *@Author:zwna
 *@Date:2019-05-23
 */
public class WxPayParam {
    public String appid;
    public String timestamp;
    public String noncestr;
    public String prepayid;
    public String signType;
    public String paySign;
    public String partnerid;
    public String packageValue;


    @Override
    public String toString() {
        return "WxPayParam{" +
                "appid='" + appid + '\'' +
                ", timestamp='" + timestamp + '\'' +
                ", noncestr='" + noncestr + '\'' +
                ", prepayid='" + prepayid + '\'' +
                ", signType='" + signType + '\'' +
                ", paySign='" + paySign + '\'' +
                ", partnerid='" + partnerid + '\'' +
                ", packageValue='" + packageValue + '\'' +
                '}';
    }
}
