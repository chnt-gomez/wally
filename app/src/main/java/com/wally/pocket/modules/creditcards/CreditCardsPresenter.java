package com.wally.pocket.modules.creditcards;

import com.wally.pocket.model.CreditCard;
import com.wally.pocket.model.ExpenseInCreditCard;

import java.util.List;

/**
 * Created by MAV1GA on 07/08/2017.
 */

public class CreditCardsPresenter {

    private static CreditCardsPresenter instance;

    static CreditCardsPresenter getInstance() {
        if (instance == null)
            instance = new CreditCardsPresenter();
        return instance;
    }

    private CreditCardsPresenter(){}

    List<CreditCard> buildCreditCardList(){
        List<CreditCard> list = CreditCard.listAll(CreditCard.class);
        for (CreditCard c : list){
            c.setCalculatedDebt(calculateDebt(c.getId()));
        }
        return list;
    }

    private float calculateDebt(long cardId){
        float total = 0.0F;
        final List<ExpenseInCreditCard> expenseInCreditCard = ExpenseInCreditCard.findWithQuery(
                ExpenseInCreditCard.class,
                "SELECT * FROM Expense, Credit_Card, Expense_In_Credit_Card WHERE " +
                        "Expense.Id = Expense_In_Credit_Card.Id and " +
                        "Credit_Card.Id = Expense_In_Credit_Card.Id and " +
                        "Credit_Card.Id = ? and Expense.expense_Status = 1",
                String.valueOf(cardId)
        );
        for (ExpenseInCreditCard e : expenseInCreditCard){
            total += e.getExpense().getExpenseAmount();
        }
        return total;
    }

    private static String formatForQuery(String rawQuery){
        return rawQuery.replace(" ", "%");
    }

    void addCreditCard(CreditCard card) {
        card.save();
    }
}
