package com.wally.pocket.modules.account;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.wally.pocket.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by vicente on 27/07/17.
 */



public class CreditCardAdapter extends ArrayAdapter<Card> {

    @BindView(R.id.tv_card_name)
    TextView cardName;

    @BindView(R.id.tv_total_debt)
    TextView totalDebt;

    @BindView(R.id.tv_cut_day)
    TextView cutDay;

    public CreditCardAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<Card> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null)
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.row_credit_card_item, null);
        ButterKnife.bind(this, convertView);
        Card card = getItem(position);
        cardName.setText(card.getCardName());
        totalDebt.setText(card.getTotalDebt());
        cutDay.setText(card.getCutDay());
        return convertView;
    }
}
