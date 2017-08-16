package com.wally.pocket.util;

import android.content.Context;

import com.wally.pocket.model.CreditCard;
import com.wally.pocket.model.Income;

/**
 * Created by MAV1GA on 16/08/2017.
 */
public interface BuildNotificationIntent {

    void onCardReadyToPay(CreditCard card, Context context);
    void onCardPayDay(CreditCard card, Context context);
    void onCardPayDelayed(CreditCard card, Context context);
    void onPayDay(Income income, Context context);

}
