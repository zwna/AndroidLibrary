package org.mt.androidlibrary.pay

import android.app.Activity
import com.xgr.easypay.EasyPay
import com.xgr.easypay.alipay.AliPay
import com.xgr.easypay.alipay.AlipayInfoImpli
import com.xgr.easypay.callback.IPayCallback
import com.xgr.easypay.unionpay.Mode
import com.xgr.easypay.unionpay.UnionPay
import com.xgr.easypay.unionpay.UnionPayInfoImpli
import com.xgr.easypay.wxpay.WXPay
import com.xgr.easypay.wxpay.WXPayInfoImpli
import org.mt.androidlibrary.pay.param.AliPayParam
import org.mt.androidlibrary.pay.param.WxPayParam

/**
 *@Description:支付工具类
 *@Author:zwna
 *@Date:2019-05-23
 */
class PayUtils {

    companion object{

        /**
         * 银联支付的方法
         * @param tn 银联支付的TN值
         * @param mode 支付的环境 Mode.TEST:测试模式  Mode.RELEASE:正式环境
         * @param activity 上下文对象
         * @param iPayCallback 支付的回调
         */
        fun unionPay(activity: Activity, tn: String, mode: Mode, iPayCallback: IPayCallback) {
            //实例化银联支付策略
            val unionPay = UnionPay()
            //构造银联订单实体。一般都是由服务端直接返回。测试时可以用Mode.TEST,发布时用Mode.RELEASE。
            val unionPayInfoImpli = UnionPayInfoImpli()
            unionPayInfoImpli.tn = tn
            unionPayInfoImpli.mode = mode
            //策略场景类调起支付方法开始支付，以及接收回调。
            EasyPay.pay(unionPay, activity, unionPayInfoImpli, iPayCallback)
        }

        /**
         * 微信支付的方法
         * @param activity 上下文对象
         * @param weChatPayAppId 微信支付的APPID
         * @param wxPayParam 微信支付的参数对象,里面包含微信支付需要的各种数据
         * @param iPayCallback 支付回调
         */
        fun weChatPay(
            activity: Activity,
            weChatPayAppId: String,
            wxPayParam: WxPayParam,
            iPayCallback: IPayCallback
        ) {
            //实例化微信支付策略
            val wxPay = WXPay.getInstance(activity, weChatPayAppId)
            //构造微信订单实体。一般都是由服务端直接返回。
            val wxPayInfoImpli = WXPayInfoImpli()
            wxPayInfoImpli.timestamp = wxPayParam.timestamp
            wxPayInfoImpli.sign = wxPayParam.paySign
            wxPayInfoImpli.prepayId = wxPayParam.prepayid
            wxPayInfoImpli.partnerid = wxPayParam.partnerid
            wxPayInfoImpli.appid = wxPayParam.appid
            wxPayInfoImpli.nonceStr = wxPayParam.noncestr
            wxPayInfoImpli.packageValue = wxPayParam.packageValue
            //策略场景类调起支付方法开始支付，以及接收回调。
            EasyPay.pay(wxPay, activity, wxPayInfoImpli, iPayCallback)
        }

        /**
         * 支付宝支付的方法
         * @param activity 上下文对象
         * @param aliPayParam 支付宝支付的参数对象，里面包含支付宝支付需要的各种数据
         * @param iPayCallback 支付回调
         */
        fun aliPay(activity: Activity, aliPayParam: AliPayParam, iPayCallback: IPayCallback) {
            //实例化支付宝支付策略
            val aliPay = AliPay()
            //构造支付宝订单实体。一般都是由服务端直接返回。
            val alipayInfoImpli = AlipayInfoImpli()
            alipayInfoImpli.orderInfo = aliPayParam.info
            //策略场景类调起支付方法开始支付，以及接收回调。
            EasyPay.pay(aliPay, activity, alipayInfoImpli, iPayCallback)
        }
    }
}