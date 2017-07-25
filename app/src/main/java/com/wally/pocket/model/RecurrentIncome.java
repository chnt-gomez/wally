package com.wally.pocket.model;

import com.orm.SugarRecord;

/**
 * Created by MAV1GA on 25/07/2017.
 */

public class RecurrentIncome extends SugarRecord implements Comparable<RecurrentIncome>{

    private int applyDate;
    private float incomeAmount;

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
    public int compareTo(RecurrentIncome income) {
        int compareApplyDate = income.getApplyDate();
        return this.applyDate <= compareApplyDate ? 1 : 0;
    }
}
