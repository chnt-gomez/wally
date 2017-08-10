package com.wally.pocket.modules.core;

import com.wally.pocket.model.Account;
import com.wally.pocket.model.CreditCard;
import com.wally.pocket.model.Expense;
import com.wally.pocket.model.RecurrentExpense;
import com.wally.pocket.model.RecurrentIncome;
import com.wally.pocket.util.NFormatter;

import org.joda.time.DateTime;

import java.util.List;

/**
 * Created by MAV1GA on 03/08/2017.
 */

public class WallyPresenter implements RequiredPresenterOps.RequiredBalancePresenterOps,
        RequiredPresenterOps.RequiredExpAndIncOps{

    private static WallyPresenter instance;
    private RequiredViewOps view;

    private WallyPresenter(){}

    public synchronized static WallyPresenter getInstance(RequiredViewOps view) {
        if (instance == null)
            instance = new WallyPresenter();
        instance.setView(view);
        return instance;
    }

    @Override
    public Account getAccount(){
        List<Account> accountList = Account.listAll(Account.class);
        if (accountList.size() == 0){
            Account account = new Account();
            account.save();
            return account;
        }else{
            return accountList.get(0);
        }
    }

    /*
    Basic Operations
     */

    private void applyExpense(Expense e){
        float accountTotal = getAccount().getAccountTotal();
        getAccount().setAccountTotal(accountTotal - e.getExpenseAmount()).save();
        e.setExpenseApplyDate(DateTime.now().getMillis());
        e.setExpenseStatus(Expense.APPLIED);
        e.save();
    }

    private void applyRecurrentExpense(RecurrentExpense e){
        e.setApplyStatus(RecurrentExpense.APPLIED);
        e.save();
    }

    private List<CreditCard> getCreditCards(){
        return CreditCard.listAll(CreditCard.class);
    }

    private List<RecurrentExpense> getPendingRecurrentExpenses(){
        return RecurrentExpense.find(RecurrentExpense.class,
                "apply_status = ? OR apply_status = ?",
                String.valueOf(RecurrentExpense.PENDING),
                String.valueOf(RecurrentExpense.DELAYED));
    }

    private float getPendingRecurrentExpensesTotal(){
        float total = 0F;
        for (RecurrentExpense e : getPendingRecurrentExpenses()){
            total += e.getExpenseTotal();
        }
        return total;
    }

    private float getCreditCardsDebtTotal(){
        float total = 0F;
        for (CreditCard c : getCreditCards()){
            total += c.getCurrentDebt();
        }
        return total;
    }


    private void setView(RequiredViewOps view) {
        this.view = view;
    }

    @Override
    public String getPendingExpensesTotal() {
        return NFormatter.maskMoney(getPendingRecurrentExpensesTotal());
    }

    @Override
    public void applyRecurrentExpense(long id) {
        RecurrentExpense expense = RecurrentExpense.findById(RecurrentExpense.class, id);
        if (expense != null) {
            applyRecurrentExpense(expense);
            Expense e = new Expense();
            e.setExpenseConcept(expense.getExpenseConcept());
            e.setExpenseAmount(expense.getExpenseTotal());
            applyExpense(e);
        }

        view.onOperationSuccess();
    }

    @Override
    public List<RecurrentExpense> getPeriodExpenses() {
        return getPendingRecurrentExpenses();
    }



    @Override
    public String getAccountTotal() {
        float total = getAccount().getAccountTotal();
        return NFormatter.maskMoney(total);
    }

    @Override
    public String getCreditCardsDebt() {
        return NFormatter.maskMoney(getCreditCardsDebtTotal());
    }

    @Override
    public String getPeriodAvailable() {
        float total = getAccount().getAccountTotal();
        float recurrentExpenses = getPendingRecurrentExpensesTotal();
        float creditCardsDebt = getCreditCardsDebtTotal();
        total = (total - recurrentExpenses - creditCardsDebt);
        return NFormatter.maskMoney(total);
    }

    /*
    IndAndExp Operations
     */

    @Override
    public void addNewRegularExpense(RecurrentExpense expense) {
        if (DateTime.now().getMillis() <= expense.getApplyDay()){
            expense.setApplyStatus(RecurrentExpense.PENDING);
        }else{
            expense.setApplyStatus(RecurrentExpense.APPLIED);
        }
        expense.save();
    }

    @Override
    public void addNewRegularIncome(RecurrentIncome income) {
        income.save();
    }

    @Override
    public List<RecurrentIncome> getRecurrentIncomes() {
        return RecurrentIncome.listAll(RecurrentIncome.class);
    }

    @Override
    public List<RecurrentExpense> getRecurrentExpenses() {
        return RecurrentExpense.listAll(RecurrentExpense.class);
    }
}
