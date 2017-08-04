package com.wally.pocket.modules.creditcards;

import android.content.Context;
import android.support.annotation.NonNull;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

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
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.row_credit_card_item, parent);
        CreditCard item = getItem(position);
        return convertView;

    }
}
