package com.wally.pocket.modules.core;

import com.wally.pocket.model.Account;

import java.util.List;

/**
 * Created by MAV1GA on 03/08/2017.
 */

public class WallyPresenter {

    private static Account account;

    public synchronized static Account getAccount(){
        if (account == null){
            List<Account> accountList = Account.listAll(Account.class);
            if (accountList.size() == 0){
                account = new Account();
                account.save();
            }else{
                account = accountList.get(0);
            }
        }
        return account;
    }

}
