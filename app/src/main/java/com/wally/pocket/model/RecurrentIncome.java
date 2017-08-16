package com.wally.pocket.model;

import android.support.annotation.NonNull;

import com.orm.SugarRecord;
import com.orm.dsl.Ignore;
import com.wally.pocket.util.NFormatter;

/**
 * Created by MAV1GA on 25/07/2017.
 */

public class RecurrentIncome extends SugarRecord implements Comparable<RecurrentIncome>{

    @Ignore
    public static final int APPLIED = 0;

    @Ignore
    public static final int APPLIYING = 1;

    @Ignore
    public static final int READY = 3;

    private int applyDate;
    private float incomeAmount;
    private int applyStatus;

    public String getIncomeConcept() {
        return incomeConcept;
    }

    public void setIncomeConcept(String incomeConcept) {
        this.incomeConcept = incomeConcept;
    }

    public float getIncomeAmount() {
        return incomeAmount;
    }

    public void setIncomeAmount(float incomeAmount) {
        this.incomeAmount = incomeAmount;
    }

    public int getApplyDate() {
        return applyDate;
    }

    public void setApplyDate(int applyDate) {
        this.applyDate = applyDate;
    }

    private String incomeConcept;

    @Override
    public int compareTo(@NonNull RecurrentIncome income) {
        return (this.applyDate - income.getApplyDate());
    }

    public String getFormattedIncomeAmount(){
        return NFormatter.maskNumber(getIncomeAmount());
    }

    public String getFormattedApplyDay(){
        return String.valueOf(getApplyDate());
    }

    public String getFormattedConcept(){
        return getIncomeConcept() == null ? "" : getIncomeConcept();
    }

    public int getApplyStatus() {
        return applyStatus;
    }

    public void setApplyStatus(int applyStatus) {
        this.applyStatus = applyStatus;
    }
}
