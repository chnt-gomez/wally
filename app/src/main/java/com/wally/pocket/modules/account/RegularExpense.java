package com.wally.pocket.modules.account;

/**
 * Created by vicente on 27/07/17.
 */

public class RegularExpense {

    private String expenseConcept;
    private String expenseTotal;
    private String expenseApplyDay;

    public RegularExpense(String expenseConcept, String expenseTotal, String expenseApplyDay) {
        this.expenseConcept = expenseConcept;
        this.expenseTotal = expenseTotal;
        this.expenseApplyDay = expenseApplyDay;
    }

    public String getExpenseConcept() {

        return expenseConcept;
    }

    public void setExpenseConcept(String expenseConcept) {
        this.expenseConcept = expenseConcept;
    }

    public String getExpenseTotal() {
        return expenseTotal;
    }

    public void setExpenseTotal(String expenseTotal) {
        this.expenseTotal = expenseTotal;
    }

    public String getExpenseApplyDay() {
        return expenseApplyDay;
    }

    public void setExpenseApplyDay(String expenseApplyDay) {
        this.expenseApplyDay = expenseApplyDay;
    }
}
