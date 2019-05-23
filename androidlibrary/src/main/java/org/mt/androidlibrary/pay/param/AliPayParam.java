package org.mt.androidlibrary.pay.param;


import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Creat by mzj on 2017/1/6 14:56
 * Des: 不要动
 */

public class AliPayParam {

    /**
     * service : mobile.securitypay.pay
     * partner : 2088421285632542
     * _input_charset : utf-8
     * sign_type : RSA
     * notify_url : /Front/mobile_alipay_response_post
     * out_trade_no : 20170106020154_weihuo_0_482
     * subject : 订单付款
     * payment_type : 1
     * seller_id : zhenghuikeji@hotmail.com
     * total_fee : 50
     * body : 订单付款
     * sign : JX5PeD4XWnO9ChRLthr4g0yeDD0vYYNs/9MvbqgP8saaEgjZDw2BLn2LmbDPuVeRVrwcN9eUNTC+6fZ3BCRGUPeWW37VEITMJHvkMZ4/muBC1YYSF4MJPd5qUEaj6quWkU88Z6a8Ip9TIiBDMnQ8nqXKmWCsQEJLKSbpw7PwvSQ=
     */

    public String service;
    public String partner;
    public String _input_charset;
    public String notify_url;
    public String out_trade_no;
    public String subject;
    public String payment_type;
    public String seller_id;
    public String total_fee;
    public String body;
    public String sign;


    public String getInfo() {
        List<NameValuePair> params = new LinkedList<>();
        params.add(new BasicNameValuePair("partner", "\"" + partner + "\""));
        params.add(new BasicNameValuePair("seller_id", "\"" + seller_id + "\""));
        params.add(new BasicNameValuePair("out_trade_no", "\"" + out_trade_no + "\""));
        params.add(new BasicNameValuePair("subject", "\"" + subject + "\""));
        params.add(new BasicNameValuePair("body", "\"" + body + "\""));
        params.add(new BasicNameValuePair("total_fee", "\"" + total_fee + "\""));
        params.add(new BasicNameValuePair("notify_url", "\"" + notify_url + "\""));
        params.add(new BasicNameValuePair("service", "\"" + service + "\""));
        params.add(new BasicNameValuePair("payment_type", "\"" + payment_type + "\""));
        params.add(new BasicNameValuePair("_input_charset", "\"" + _input_charset + "\""));
        String orderInfo = paramEncryptStrForPay(params);

        try {
            // 仅需对sign 做URL编码
            sign = URLEncoder.encode(sign, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        // 完整的符合支付宝参数规范的订单信息

        return orderInfo + "&sign=\"" + sign + "\"&" + "sign_type=\"RSA\"";
    }

    private static String paramEncryptStrForPay(List<NameValuePair> params) {
        // 首先对字符串进行拼接
        // 字典排序
        Collections.sort(params, (lhs, rhs) -> lhs.getName().compareTo(rhs.getName()));
        StringBuilder content = new StringBuilder(params.get(0).getName() + "="
                + params.get(0).getValue());
        for (int i = 1; i < params.size(); i++) {
            content.append("&").append(params.get(i).getName()).append("=").append(params.get(i).getValue());
        }
        return content.toString();
    }
}
