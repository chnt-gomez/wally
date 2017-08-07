package com.wally.pocket.model;

import com.orm.SugarRecord;

/**
 * Created by MAV1GA on 04/08/2017.
 */

public class ExpenseInCreditCard extends SugarRecord {

    public CreditCard getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(CreditCard creditCard) {
        this.creditCard = creditCard;
    }

    public Expense getExpense() {
        return expense;
    }

    public void setExpense(Expense expense) {
        this.expense = expense;
    }

    public long getApplyDate() {
        return applyDate;
    }

    public void setApplyDate(long applyDate) {
        this.applyDate = applyDate;
    }

    private CreditCard creditCard;
    private Expense expense;
    private long applyDate;
    private int expenseType;
    private int applyStatus;
    private int exhibitPay;

    public int getExpenseType() {
        return expenseType;
    }

    public void setExpenseType(int expenseType) {
        this.expenseType = expenseType;
    }

    public int getApplyStatus() {
        return applyStatus;
    }

    public void setApplyStatus(int applyStatus) {
        this.applyStatus = applyStatus;
    }

    public int getExhibitPay() {
        return exhibitPay;
    }

    public void setExhibitPay(int exhibitPay) {
        this.exhibitPay = exhibitPay;
    }

}
