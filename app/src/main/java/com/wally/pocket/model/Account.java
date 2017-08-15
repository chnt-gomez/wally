package com.wally.pocket.model;

import com.orm.SugarRecord;
import com.orm.dsl.Ignore;
import com.wally.pocket.util.NFormatter;

/**
 * Created by MAV1GA on 25/07/2017.
 */

public class Account extends SugarRecord {

    private float accountTotal;
    private int accountPeriodHandling;

    @Ignore
    public static final int TYPE_FREELANCER = 0;
    @Ignore
    public static final int TYPE_TWO_WEEKS = 1;
    @Ignore
    public static final int TYPE_MONTHLY = 2;

    public float getAccountTotal() {
        return accountTotal;
    }

    public Account setAccountTotal(float accountTotal) {
        this.accountTotal = accountTotal;
        return this;
    }

    public int getAccountPeriodHandling() {
        return accountPeriodHandling;
    }

    public void setAccountPeriodHandling (int accountPeriodHandling) {
        this.accountPeriodHandling = accountPeriodHandling;
    }

}
