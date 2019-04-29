package org.mt.androidlibrary.net.https;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import java.security.SecureRandom;

/**
 * @Description:Retr
 * @Author:zwna
 * @Date:2019-04-29
 */
public class SslSocketFactory {

    public static SSLSocketFactory getSSLSocketFactory(){
        SSLSocketFactory sslSocketFactory = null;
        SSLContext sc = null;
        try {
            sc = SSLContext.getInstance("TLS");
            sc.init(null,  new UnSafeTrustManager[] { new UnSafeTrustManager() }, new SecureRandom());
            sslSocketFactory = sc.getSocketFactory();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return sslSocketFactory;
    }
}
