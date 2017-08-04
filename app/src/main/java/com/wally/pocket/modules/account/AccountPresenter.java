package com.wally.pocket.modules.account;

import com.wally.pocket.model.Account;
import com.wally.pocket.modules.core.WallyPresenter;

/**
 * Created by MAV1GA on 04/08/2017.
 */

public class AccountPresenter {


    Account getAccount(){
        return WallyPresenter.getAccount();
    }

    String getAccountTotal(){
        return getAccount().getFormattedAccountTotal();
    }

}
