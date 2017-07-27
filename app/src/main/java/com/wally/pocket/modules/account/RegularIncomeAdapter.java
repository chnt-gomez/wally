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

public class RegularIncomeAdapter extends ArrayAdapter<RegularIncome> {

    @BindView(R.id.tv_income_amount)
    TextView incomeAmount;

    @BindView(R.id.tv_income_apply_day)
    TextView applyDay;

    @BindView(R.id.tv_income_concept)
    TextView incomeConcept;

    public RegularIncomeAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<RegularIncome> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null)
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.row_regular_income_item, null);
        ButterKnife.bind(this, convertView);
        RegularIncome income = getItem(position);
        incomeAmount.setText(income.getIncomeTotal());
        incomeConcept.setText(income.getIncomeConcept());
        applyDay.setText(income.getIncomeApplyDay());
        return convertView;

    }
}
