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
        return CreditCard.listAll(CreditCard.class);
    }


    private static String formatForQuery(String rawQuery){
        return rawQuery.replace(" ", "%");
    }

    void addCreditCard(CreditCard card) {
        card.save();
    }
}
