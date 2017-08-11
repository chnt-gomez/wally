package com.wally.pocket.dialogs;

import android.support.annotation.Nullable;

import com.wally.pocket.model.CreditCard;
import com.wally.pocket.model.Expense;
import com.wally.pocket.model.Income;
import com.wally.pocket.model.RecurrentExpense;
import com.wally.pocket.model.RecurrentIncome;

/**
 * Created by MAV1GA on 31/07/2017.
 */

public interface RequiredDialogOps {

    interface NewRegularIncomeListener {
        void onNewRegularIncomeListener(RecurrentIncome income);
    }

    interface NewRegularExpenseListener {
        void onNewRegularExpenseListener(RecurrentExpense expense);
    }

    interface NewQuickExpenseListener {
        void onNewQuickExpenseListener (Expense expense, boolean addToCard, long cardId);
    }

    interface NewLuckyIncomeListener {
        void onNewLuckyIncome (Income income);
    }

    interface AskForExpenseListeber{
        void onAskForExpense (Expense expense);
    }

    interface NewCreditCardListener {
        void onNewCreditCard(CreditCard card);
    }

    interface NewCreditCardCharge {
        void onNewChargeToCard(Expense expense, long cardId);
    }
}
