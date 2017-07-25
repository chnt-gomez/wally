package com.wally.pocket.modules.home;

import com.wally.pocket.model.Account;
import com.wally.pocket.model.RecurrentExpense;
import com.wally.pocket.model.RecurrentIncome;
import com.wally.pocket.util.NFormatter;

import org.joda.time.DateTime;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by MAV1GA on 25/07/2017.
 */
public class HomePresenter {

    private static HomePresenter instance;

    public static synchronized HomePresenter getInstance() {
        if (instance == null) {
            instance = new HomePresenter();
        }
        return instance;
    }

    private HomePresenter() {
    }

    public String getMaskTotalRemaining(){
        float accountTotal = Account.findById(Account.class, 1L).getAccountTotal();
        return NFormatter.maskNumber(accountTotal);
    }

    public float getTotalRemaining(){
        return Account.findById(Account.class, 1L).getAccountTotal();
    }

    public String getMaskPeriodDebt(){
        //TODO:(1) Sum up all the Recurent Expenses from entire period that are pending}
        int endOfPeriod = getEndOfPeriod();
        float periodDebt = 0L;
        final List<RecurrentExpense> expensesToApply = RecurrentExpense.find(
                RecurrentExpense.class,
                "apply_day <= ? ",
                String.valueOf(endOfPeriod)
        );
        for (RecurrentExpense recurrentExpense : expensesToApply){
            periodDebt += recurrentExpense.getExpenseTotal();
        }
        return NFormatter.maskNumber(periodDebt);
    }

    private int getEndOfPeriod() {
        final List<RecurrentIncome> incomeList = RecurrentIncome.listAll(RecurrentIncome.class);
        Collections.sort(incomeList);
        int today = DateTime.now().getDayOfMonth();
        for (int i=0; i<incomeList.size(); i++){
            if (incomeList.get(i).getApplyDate() >= today)
                return incomeList.get(i).getApplyDate();
        }
        return incomeList.get(0).getApplyDate();
    }
}
