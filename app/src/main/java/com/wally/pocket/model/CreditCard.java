package com.wally.pocket.model;

import com.orm.SugarRecord;
import com.orm.dsl.Ignore;
import com.wally.pocket.util.NFormatter;

/**
 * Created by MAV1GA on 25/07/2017.
 */

public class CreditCard extends SugarRecord {

    @Ignore
    public static final int NOTIFIED = 0;

    @Ignore
    public static final int READY = 1;

    @Ignore
    public static final int DELAYED = 2;

    @Ignore
    public static final int PAID = 3;

    private String creditCardName;
    private float creditLimit;
    private float totalDebt;
    private int payDay;
    private int cutDay;

    public int getLatestMonthApplied() {
        return latestMonthApplied;
    }

    public void setLatestMonthApplied(int latestMonthApplied) {
        this.latestMonthApplied = latestMonthApplied;
    }

    private int latestMonthApplied;

    public int getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(int payStatus) {
        this.payStatus = payStatus;
    }

    private int payStatus;

    public String getCreditCardName() {
        return creditCardName != null ? creditCardName : "";
    }

    public void setCreditCardName(String creditCardName) {
        this.creditCardName = creditCardName;
    }

    public float getCreditLimit() {
        return creditLimit;
    }

    public String getCreditLimitString(){return NFormatter.maskMoney(creditLimit);}

    public void setCreditLimit(float creditLimit) {
        this.creditLimit = creditLimit;
    }

    public float getTotalDebt() {
        return totalDebt;
    }

    public String getTotalDebtString(){
        return NFormatter.maskMoney(totalDebt);
    }

    public void setTotalDebt(float totalDebt) {
        this.totalDebt = totalDebt;
    }

    public int getPayDay() {
        return payDay;
    }

    public void setPayDay(int payDay) {
        this.payDay = payDay;
    }

    public int getCutDay() {
        return cutDay;
    }

    public void setCutDay(int cutDay) {
        this.cutDay = cutDay;
    }

}
