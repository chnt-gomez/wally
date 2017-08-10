package com.wally.pocket.modules.core;

import com.wally.pocket.model.Account;
import com.wally.pocket.model.RecurrentExpense;
import com.wally.pocket.model.RecurrentIncome;

import java.util.List;

/**
 * Created by MAV1GA on 10/08/2017.
 */

public interface RequiredPresenterOps {

    Account getAccount();

    interface RequiredBalancePresenterOps extends RequiredPresenterOps{
        void applyRecurrentExpense(long id);
        List<RecurrentExpense> getPeriodExpenses();
        String getAccountTotal();
        String getCreditCardsDebt();
        String getPeriodAvailable();
        String getPendingExpensesTotal();
    }

    interface RequiredExpAndIncOps {

        void addNewRegularExpense(RecurrentExpense expense);
        void addNewRegularIncome(RecurrentIncome income);
        List<RecurrentIncome> getRecurrentIncomes();
        List<RecurrentExpense> getRecurrentExpenses();
    }
}
