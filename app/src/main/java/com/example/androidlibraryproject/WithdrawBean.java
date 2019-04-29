package com.example.androidlibraryproject;

/**
 * @Description:
 * @Author:liubiao
 * @Date:2019-04-28
 */
public class WithdrawBean extends ErrorBean {


    /**
     * data : {"account":"111111111111111","apply_time":"1556421429","bank_name":"中国银行","money":"100","opening_bank":"啊啊啊"}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "WithdrawBean{" +
                "data=" + data +
                ", code=" + code +
                ", error_msg='" + error_msg + '\'' +
                '}';
    }

    public static class DataBean {
        /**
         * account : 111111111111111
         * apply_time : 1556421429
         * bank_name : 中国银行
         * money : 100
         * opening_bank : 啊啊啊
         */

        private String account;
        private String apply_time;
        private String bank_name;
        private String money;
        private String opening_bank;

        public String getAccount() {
            return account;
        }

        public void setAccount(String account) {
            this.account = account;
        }

        public String getApply_time() {
            return apply_time;
        }

        public void setApply_time(String apply_time) {
            this.apply_time = apply_time;
        }

        public String getBank_name() {
            return bank_name;
        }

        public void setBank_name(String bank_name) {
            this.bank_name = bank_name;
        }

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }

        public String getOpening_bank() {
            return opening_bank;
        }

        public void setOpening_bank(String opening_bank) {
            this.opening_bank = opening_bank;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "account='" + account + '\'' +
                    ", apply_time='" + apply_time + '\'' +
                    ", bank_name='" + bank_name + '\'' +
                    ", money='" + money + '\'' +
                    ", opening_bank='" + opening_bank + '\'' +
                    '}';
        }
    }
}
