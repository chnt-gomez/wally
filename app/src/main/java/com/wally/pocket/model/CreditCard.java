package com.wally.pocket.model;

import com.orm.SugarRecord;
import com.wally.pocket.util.NFormatter;

/**
 * Created by MAV1GA on 25/07/2017.
 */

public class CreditCard extends SugarRecord {

    private String creditCardName;
    private float creditLimit;
    private float totalDebt;
    private float currentDebt;
    private float pendingDebt;
    private float interestRate;
    private int payDay;
    private int cutDay;

    public float getPendingDebt() {
        return pendingDebt;
    }

    public void setPendingDebt(float pendingDebt) {
        this.pendingDebt = pendingDebt;
    }


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

    public float getCurrentDebt() {
        return currentDebt;
    }

    public String getCurrentDebtString(){
        return NFormatter.maskMoney(currentDebt);
    }

    public void setCurrentDebt(float currentDebt) {
        this.currentDebt = currentDebt;
    }

    public float getInterestRate() {
        return interestRate;
    }

    public String getInterestRateString(){
        return NFormatter.maskNumber(interestRate);
    }

    public void setInterestRate(float interestRate) {
        this.interestRate = interestRate;
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
