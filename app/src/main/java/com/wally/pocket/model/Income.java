package com.wally.pocket.model;

import com.orm.SugarRecord;

/**
 * Created by MAV1GA on 03/08/2017.
 */

public class Income extends SugarRecord {

    private float incomeAmount;
    private String incomeConcept;
    private long incomeApplyDate;

    public float getIncomeAmount() {
        return incomeAmount;
    }

    public void setIncomeAmount(float incomeAmount) {
        this.incomeAmount = incomeAmount;
    }

    public String getIncomeConcept() {
        return incomeConcept;
    }

    public void setIncomeConcept(String incomeConcept) {
        this.incomeConcept = incomeConcept;
    }

    public long getIncomeApplyDate() {
        return incomeApplyDate;
    }

    public void setIncomeApplyDate(long incomeApplyDate) {
        this.incomeApplyDate = incomeApplyDate;
    }
}
