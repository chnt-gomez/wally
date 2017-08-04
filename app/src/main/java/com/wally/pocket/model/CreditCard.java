package com.wally.pocket.model;

import com.orm.SugarRecord;

/**
 * Created by MAV1GA on 25/07/2017.
 */

public class CreditCard extends SugarRecord {

    private String creditCardName;
    private float creditLimit;
    private int payDay;
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
}
