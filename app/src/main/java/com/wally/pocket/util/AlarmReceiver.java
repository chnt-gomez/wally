package com.wally.pocket.util;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

import com.wally.pocket.R;
import com.wally.pocket.model.CreditCard;
import com.wally.pocket.model.Income;
import com.wally.pocket.modules.core.WallyPresenter;

/**
 * Created by MAV1GA on 16/08/2017.
 */

public class AlarmReceiver extends BroadcastReceiver implements BuildNotificationIntent{

    public static final String UPDATE = "com.wally.pocket.UPDATE";

    @Override
    public void onReceive(Context context, Intent intent) {

        if (intent.getAction().equals(UPDATE)){
            WallyPresenter.updateDay(this, context);
        }
    }

    @Override
    public void onCardReadyToPay(CreditCard card, Context context) {
        NotificationManager notificationManager =
                (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(context)
                .setSmallIcon(R.drawable.ic_credit_card_multiple_24)
                .setContentTitle("Remember to pay your "+card.getCreditCardName())
                .setContentText(card.getCreditCardName()+" is now available for payment");
        notificationManager.notify(101, builder.build());
    }

    @Override
    public void onCardPayDay(CreditCard card, Context context) {

    }

    @Override
    public void onCardPayDelayed(CreditCard card, Context context) {

    }

    @Override
    public void onPayDay(Income income, Context context) {

    }
}
