package com.wally.pocket.modules.account;

/**
 * Created by vicente on 27/07/17.
 */

public class RegularIncome {

    private String incomeConcept;
    private String incomeTotal;
    private String incomeApplyDay;

    public RegularIncome(String incomeConcept, String incomeTotal, String incomeApplyDay) {
        this.incomeConcept = incomeConcept;
        this.incomeTotal = incomeTotal;
        this.incomeApplyDay = incomeApplyDay;
    }

    public String getIncomeConcept() {

        return incomeConcept;
    }

    public void setIncomeConcept(String incomeConcept) {
        this.incomeConcept = incomeConcept;
    }

    public String getIncomeTotal() {
        return incomeTotal;
    }

    public void setIncomeTotal(String incomeTotal) {
        this.incomeTotal = incomeTotal;
    }

    public String getIncomeApplyDay() {
        return incomeApplyDay;
    }

    public void setIncomeApplyDay(String incomeApplyDay) {
        this.incomeApplyDay = incomeApplyDay;
    }
}
