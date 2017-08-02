package com.wally.pocket.model;

import com.orm.SugarRecord;
import com.wally.pocket.util.NFormatter;

/**
 * Created by MAV1GA on 25/07/2017.
 */

public class Account extends SugarRecord {

    public float getAccountTotal() {
        return accountTotal;
    }

    public Account setAccountTotal(float accountTotal) {
        this.accountTotal = accountTotal;
        return this;
    }

    private float accountTotal;

    public String getFormattedAccountTotal(){
        return NFormatter.maskNumber(getAccountTotal());
    }
}
