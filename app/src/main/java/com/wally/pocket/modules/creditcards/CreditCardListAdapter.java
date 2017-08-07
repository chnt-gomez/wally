package com.wally.pocket.modules.creditcards;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.wally.pocket.R;
import com.wally.pocket.model.CreditCard;

import java.util.List;

/**
 * Created by MAV1GA on 04/08/2017.
 */

public class CreditCardListAdapter extends ArrayAdapter<CreditCard> {

    public CreditCardListAdapter(Context context, int resource, List<CreditCard> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null)
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.row_credit_card_item, null);
        CreditCard item = getItem(position);
        TextView tvCardName = (TextView)convertView.findViewById(R.id.tv_card_name);
        TextView tvTotalDebt = (TextView) convertView.findViewById(R.id.tv_total_debt);
        TextView tvPayDay = (TextView)convertView.findViewById(R.id.tv_pay_day);
        if (item == null){
            item = new CreditCard();
        }
        tvCardName.setText(item.getFormattedCardName());
        tvTotalDebt.setText(item.getFormattedCalculatedDebt());
        tvPayDay.setText(item.getFormattedPayDay());
        return convertView;

    }
}
