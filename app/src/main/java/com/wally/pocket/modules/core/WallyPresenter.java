package com.wally.pocket.modules.core;

import android.content.Context;

import com.wally.pocket.model.Account;
import com.wally.pocket.model.CreditCard;
import com.wally.pocket.model.Expense;
import com.wally.pocket.model.ExpenseInCreditCard;
import com.wally.pocket.model.RecurrentExpense;
import com.wally.pocket.model.RecurrentIncome;
import com.wally.pocket.util.BuildNotificationIntent;
import com.wally.pocket.util.NFormatter;

import org.joda.time.DateTime;

import java.util.List;

/**
 * Created by MAV1GA on 03/08/2017.
 */

public class WallyPresenter implements RequiredPresenterOps.RequiredBalancePresenterOps,
        RequiredPresenterOps.RequiredExpAndIncOps, RequiredPresenterOps.RequiredCreditCardPresenterOps{

    private static WallyPresenter instance;
    private RequiredViewOps view;

    private WallyPresenter(){}

    public synchronized static WallyPresenter getInstance(RequiredViewOps view) {
        if (instance == null)
            instance = new WallyPresenter();
        instance.setView(view);
        return instance;
    }

    public static void updateDay(BuildNotificationIntent callback, Context context){
        //First lets take care of the credit Cards
        int today = DateTime.now().getDayOfMonth();

        for (CreditCard c: CreditCard.listAll(CreditCard.class)){
            switch (c.getPayStatus()){
                case CreditCard.NOTIFIED:
                    if (c.getPayDay() > today){
                        c.setPayStatus(CreditCard.DELAYED);
                        c.save();
                        callback.onCardPayDelayed(c, context);
                        break;
                    }
                    if (c.getPayDay() == today){
                        callback.onCardPayDay(c, context);
                        break;
                    }
                    callback.onCardReadyToPay(c, context);
                    break;
                case CreditCard.READY:
                    if (c.getCutDay() >= today){
                        c.setPayStatus(CreditCard.NOTIFIED);
                        c.save();
                        callback.onCardReadyToPay(c, context);
                    }
                    break;
                case CreditCard.DELAYED:
                    callback.onCardPayDelayed(c, context);
                    break;
            }
        }

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
        e.save();
    }

    private void applyRecurrentExpense(RecurrentExpense e){
        e.setApplyStatus(RecurrentExpense.APPLIED);
        e.setLatestMonthApply(DateTime.now().getMonthOfYear());
        e.save();
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
            total += c.getTotalDebt();
        }
        return total;
    }

    private List<CreditCard> getCreditCards(){
        return CreditCard.listAll(CreditCard.class);
    }


    private void setView(RequiredViewOps view) {
        this.view = view;
    }


    /*
    Balance Operations
     */
    @Override
    public String getPendingExpensesTotal() {
        return NFormatter.maskMoney(getPendingRecurrentExpensesTotal());
    }

    @Override
    public void applyExpenseToCreditCard(Expense expense, long cardId) {
        CreditCard creditCard = CreditCard.findById(CreditCard.class, cardId);

        expense.setExpenseApplyDate(DateTime.now().getMillis());
        expense.setExpenseStatus(Expense.APPLIED);
        expense.save();
        ExpenseInCreditCard ex = new ExpenseInCreditCard();
        ex.setApplyStatus(ExpenseInCreditCard.APPLIED);
        ex.setExpense(expense);
        ex.setCreditCard(creditCard);
        ex.save();
        float creditCardDebt = creditCard.getTotalDebt();
        creditCard.setTotalDebt(creditCardDebt + expense.getExpenseAmount());

        creditCard.save();
        view.onOperationSuccess();
    }

    @Override
    public void applyExpenseToAccount(Expense e) {
        float accountTotal = getAccount().getAccountTotal();
        getAccount().setAccountTotal(accountTotal - e.getExpenseAmount()).save();
        e.setExpenseApplyDate(DateTime.now().getMillis());
        e.setExpenseStatus(Expense.APPLIED);
        e.save();
        view.onOperationSuccess();
    }

    @Override
    public void applyRecurrentExpense(long id) {
        RecurrentExpense expense = RecurrentExpense.findById(RecurrentExpense.class, id);
        if (expense != null) {
            applyRecurrentExpense(expense);
            Expense e = new Expense();
            e.setExpenseConcept(expense.getExpenseConcept());
            e.setExpenseAmount(expense.getExpenseTotal());
            applyExpenseToAccount(e);
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
        total = (total - recurrentExpenses);
        return NFormatter.maskMoney(total);
    }

    @Override
    public List<CreditCard> getCreditCardsToPay(){
        return CreditCard.listAll(CreditCard.class);
    }

    private List<RecurrentExpense> getPendingRecurrentExpenses(){

        for (RecurrentExpense e : RecurrentExpense.listAll(RecurrentExpense.class)){
            if (e.getLatestMonthApply() != DateTime.now().getMonthOfYear()){
                int status = e.getApplyStatus();
                if (status == RecurrentExpense.APPLIED) {
                    e.setApplyStatus(RecurrentExpense.PENDING);
                    e.save();
                }
            }
        }

        return RecurrentExpense.find(RecurrentExpense.class,
                "apply_status = ? OR apply_status = ?",
                String.valueOf(RecurrentExpense.PENDING),
                String.valueOf(RecurrentExpense.DELAYED));
    }

    /*
    IndAndExp Operations
     */

    @Override
    public void addNewRegularExpense(RecurrentExpense expense) {
        if (DateTime.now().getDayOfMonth() <= expense.getApplyDay()){
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

       /*
    Credit Card methods
     */

    @Override
    public List<CreditCard> getAllCreditCards() {
        return CreditCard.listAll(CreditCard.class);
    }

    @Override
    public CreditCard getCreditCard(long cardId) {
        return CreditCard.findById(CreditCard.class, cardId);
    }

    @Override
    public void updateCreditCard(long creditCardId, CreditCard creditCard) {
        creditCard.save();
        view.onOperationSuccess();
    }

    @Override
    public void addCreditCard(CreditCard card) {
        card.save();
        ((RequiredViewOps.RequiredCardViewOps)view).onNewCardAdded(card);
    }


    @Override
    public void updateCreditCards() {

    }

    @Override
    public long getMonthDebtInCard(long cardId) {

    }
}
