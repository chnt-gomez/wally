package com.wally.pocket.dialogs;

import com.wally.pocket.model.Expense;
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
        void onNewQuickExpenseListener (Expense expense);
    }
}
