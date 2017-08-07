package com.wally.pocket.modules.balance;

import android.util.Log;

import com.wally.pocket.model.CreditCard;
import com.wally.pocket.model.ExpenseInCreditCard;
import com.wally.pocket.model.Income;
import com.wally.pocket.modules.core.WallyPresenter;
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
        if (start < end) {
            Log.d(getClass().getSimpleName(), "start > end");
            return RecurrentExpense.find(RecurrentExpense.class,
                    "apply_day >= ? and apply_day < ?", String.valueOf(start), String.valueOf(end));
        } else {
            Log.d(getClass().getSimpleName(), "start < end");
            return RecurrentExpense.find(RecurrentExpense.class,
                    "apply_day >= ? OR apply_day < ?", String.valueOf(start), String.valueOf(end));
        }
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
        if (incomeList.size() == 0)
            return 1;
        Collections.sort(incomeList);
        int today = DateTime.now().getDayOfMonth();
        for (int i=0; i<incomeList.size(); i++){
            if (incomeList.get(i).getApplyDate() <= today)
                return incomeList.get(i).getApplyDate();
        }
        return incomeList.get(incomeList.size()-1).getApplyDate();
    }

    private int getEndPeriodDay() {
        final List<RecurrentIncome> incomeList = RecurrentIncome.listAll(RecurrentIncome.class);
        if (incomeList.size() == 0)
            return 1;
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
                applyExpenseToAccount(expense.getExpenseTotal());
                expense.setApplyStatus(1);
                expense.save();
            }
    }

    private Account getAccount(){
        return WallyPresenter.getAccount();
    }

    void applyExpense(Expense expense) {
        applyExpenseToAccount(expense.getExpenseAmount());
        expense.setExpenseStatus(0);
        expense.save();
    }

    private void applyExpenseToAccount(float amount){
        float newTotal = getAccount().getAccountTotal() - amount;
        getAccount().setAccountTotal(newTotal).save();
    }

    private void applyIncomeToAccount(float amount){
        float newTotal = getAccount().getAccountTotal() + amount;
        getAccount().setAccountTotal(newTotal).save();
    }

    void applyLuckyIncome(Income income) {
        applyIncomeToAccount(income.getIncomeAmount());
        income.save();
    }

    List<CreditCard> getCreditCards() {
        return CreditCard.listAll(CreditCard.class);
    }

    void applyChargeToCard(Expense expense, long cardId) {
        expense.setExpenseStatus(1);
        expense.save();
        CreditCard card = CreditCard.findById(CreditCard.class, cardId);
        ExpenseInCreditCard expenseInCreditCard = new ExpenseInCreditCard();
        expenseInCreditCard.setCreditCard(card);
        expenseInCreditCard.setExpense(expense);
        expenseInCreditCard.setApplyDate(DateTime.now().getMillis());
        expenseInCreditCard.setApplyStatus(1);
        expenseInCreditCard.save();
    }
}
