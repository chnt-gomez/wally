package com.wally.pocket.modules.balance;

import android.util.Log;
import com.wally.pocket.model.Account;
import com.wally.pocket.model.Expense;
import com.wally.pocket.model.RecurrentExpense;
import com.wally.pocket.model.RecurrentIncome;
import com.wally.pocket.util.NFormatter;
import org.joda.time.DateTime;
import java.util.Collections;
import java.util.List;

/**
 * Created by MAV1GA on 02/08/2017.
 */

public class BalancePresenter {

    private BalancePresenter(){}

    private static BalancePresenter instance;

    static BalancePresenter getInstance(){
        if (instance == null)
            instance = new BalancePresenter();
        return instance;
    }

    List<RecurrentExpense> getPeriodExpenses(){
        int start = getPeriodStartDay();
        int end = getEndPeriodDay();
        Log.d(getClass().getSimpleName(), String.format("Period is this: Start: %d, End: %d", start,end));
        if (start < end)
            return RecurrentExpense.find(RecurrentExpense.class,
                    "apply_day >= ? and apply_day < ? and apply_status = 0", String.valueOf(start), String.valueOf(end));
        else
            return RecurrentExpense.find(RecurrentExpense.class,
                    "apply_day < ? and apply_day >= ? and apply_status = 0", String.valueOf(end), String.valueOf(start));
    }

    String getPeriodTotal(){
        return NFormatter.maskNumber(calculatePeriodTotal());
    }

    private float calculatePeriodTotal(){
        float total = 0.0F;
        for (RecurrentExpense e : getPeriodExpenses()){
            total += e.getExpenseTotal();
        }
        return total;
    }


    private int getPeriodStartDay(){
        final List<RecurrentIncome> incomeList = RecurrentIncome.listAll(RecurrentIncome.class);
        Collections.sort(incomeList);
        int today = DateTime.now().getDayOfMonth();
        for (int i=0; i<incomeList.size(); i++){
            if (incomeList.get(i).getApplyDate() <= today)
                return incomeList.get(i).getApplyDate();
        }
        return incomeList.get(0).getApplyDate();
    }

    private int getEndPeriodDay() {
        final List<RecurrentIncome> incomeList = RecurrentIncome.listAll(RecurrentIncome.class);
        Collections.sort(incomeList);
        int today = DateTime.now().getDayOfMonth();
        for (int i=0; i<incomeList.size(); i++){
            if (today < incomeList.get(i).getApplyDate())
                return incomeList.get(i).getApplyDate();
        }
        return incomeList.get(0).getApplyDate();

    }

    String getAccountTotal() {
        return getAccount().getFormattedAccountTotal();
    }

    String getPeriodSpendinsAvailable(){
        float accountTotal = Account.listAll(Account.class).get(0).getAccountTotal();
        float periodTotal = calculatePeriodTotal();
        return NFormatter.maskNumber(accountTotal - periodTotal);
    }

    void applyRecurrentExpense(long expenseId){
        RecurrentExpense expense = RecurrentExpense.findById(RecurrentExpense.class, expenseId);
            if (expense != null) {
                float newTotal = getAccount().getAccountTotal() - expense.getExpenseTotal();
                getAccount().setAccountTotal(newTotal).save();
                expense.setApplyStatus(1);
                expense.save();
            }
    }

    private Account getAccount(){
        return Account.listAll(Account.class).get(0);
    }
}
