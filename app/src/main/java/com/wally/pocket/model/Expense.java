package com.wally.pocket.model;

import com.orm.SugarRecord;
import com.orm.dsl.Ignore;
import com.wally.pocket.util.NFormatter;

/**
 * Created by MAV1GA on 25/07/2017.
 */

public class Expense extends SugarRecord {

    @Ignore
    public static final int APPLIED = 0;
    @Ignore
    public static final int PENDING = 1;
    @Ignore
    public static final int CANCELED = 2;
    @Ignore
    public static final int DELAYED = 3;

    private float expenseAmount;
    private long expenseApplyDate;
    private String expenseConcept;
    private Category expenseCategory;
    private int expenseStatus;
    private float lat;
    private float len;

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public float getLen() {
        return len;
    }

    public void setLen(float len) {
        this.len = len;
    }

    public float getLat() {
        return lat;
    }

    public void setLat(float lat) {
        this.lat = lat;
    }

    private String zipCode;

    public int getExpenseStatus() {
        return expenseStatus;
    }

    public void setExpenseStatus(int expenseStatus) {
        this.expenseStatus = expenseStatus;
    }

    public float getExpenseAmount() {
        return expenseAmount;
    }

    public void setExpenseAmount(float expenseAmount) {
        this.expenseAmount = expenseAmount;
    }

    public long getExpenseApplyDate() {
        return expenseApplyDate;
    }

    public void setExpenseApplyDate(long expenseApplyDate) {
        this.expenseApplyDate = expenseApplyDate;
    }

    public String getExpenseConcept() {
        return expenseConcept;
    }

    public void setExpenseConcept(String expenseConcept) {
        this.expenseConcept = expenseConcept;
    }

    public Category getExpenseCategory() {
        return expenseCategory;
    }

    public void setExpenseCategory(Category expenseCategory) {
        this.expenseCategory = expenseCategory;
    }

    public String getFormattedExpenseAmount(){
        return NFormatter.maskNumber(getExpenseAmount());
    }
}
