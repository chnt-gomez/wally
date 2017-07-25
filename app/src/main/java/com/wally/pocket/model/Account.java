package com.wally.pocket.model;

import com.orm.SugarRecord;

/**
 * Created by MAV1GA on 25/07/2017.
 */

public class Account extends SugarRecord {

    public float getAccountTotal() {
        return accountTotal;
    }

    public void setAccountTotal(float accountTotal) {
        this.accountTotal = accountTotal;
    }

    private float accountTotal;



}
