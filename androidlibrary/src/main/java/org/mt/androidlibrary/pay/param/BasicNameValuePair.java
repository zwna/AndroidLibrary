package org.mt.androidlibrary.pay.param;

/**
 *@Description:
 *@Author:zwna
 *@Date:2019-05-23
 */
class BasicNameValuePair implements NameValuePair {
    private String name;
    private String value;

    public BasicNameValuePair(String name, String value) {
        this.name = name;
        this.value = value;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getValue() {
        return value;
    }


}
