package com.wally.pocket.model;

import com.orm.SugarRecord;
import com.orm.dsl.Ignore;
import com.wally.pocket.util.NFormatter;

/**
 * Created by MAV1GA on 25/07/2017.
 */

public class CreditCard extends SugarRecord {

    private String creditCardName;
    private float creditLimit;
    private int payDay;
    private int cutDay;

    public float getCalculatedDebt() {
        return calculatedDebt;
    }

    public void setCalculatedDebt(float calculatedDebt) {
        this.calculatedDebt = calculatedDebt;
    }

    @Ignore
    private float calculatedDebt;

    public int getCutDay() {
        return cutDay;
    }

    public void setCutDay(int cutDay) {
        this.cutDay = cutDay;
    }

    private float interestRate;

    public String getCreditCardName() {
        return creditCardName;
    }

    public void setCreditCardName(String creditCardName) {
        this.creditCardName = creditCardName;
    }

    public float getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(float creditLimit) {
        this.creditLimit = creditLimit;
    }

    public int getPayDay() {
        return payDay;
    }

    public void setPayDay(int payDay) {
        this.payDay = payDay;
    }

    public float getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(float interestRate) {
        this.interestRate = interestRate;
    }

    public String getFormattedCardName(){
        return creditCardName != null ? creditCardName : "";
    }

    public String getFormattedCalculatedDebt(){
        return NFormatter.maskNumber(getCalculatedDebt());
    }

    public String getFormattedCutDay(){
        return String.valueOf(getCutDay());
    }

    public String getFormattedPayDay(){
        return String.valueOf(getPayDay());
    }
}
