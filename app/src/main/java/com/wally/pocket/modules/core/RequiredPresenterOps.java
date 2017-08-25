package com.wally.pocket.modules.core;

import com.wally.pocket.model.Account;
import com.wally.pocket.model.CreditCard;
import com.wally.pocket.model.Expense;
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
        List<CreditCard> getCreditCardsToPay();
        void applyExpenseToCreditCard(Expense expense, long cardId);
        void applyExpenseToAccount(Expense expense);
    }

    interface RequiredExpAndIncOps {

        void addNewRegularExpense(RecurrentExpense expense);
        void addNewRegularIncome(RecurrentIncome income);
        List<RecurrentIncome> getRecurrentIncomes();
        List<RecurrentExpense> getRecurrentExpenses();
    }

    interface RequiredCreditCardPresenterOps {
        List<CreditCard> getAllCreditCards();
        CreditCard getCreditCard(long cardId);
        void updateCreditCard(long creditCardId, CreditCard creditCard);
        void addCreditCard(CreditCard card);
        void updateCreditCards();
        long getMonthDebtInCard(long cardId);
    }
}
