package com.wally.pocket.modules.expandinc;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.wally.pocket.R;
import com.wally.pocket.model.RecurrentIncomeAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by MAV1GA on 31/07/2017.
 */

public class IncomesAdapter extends ArrayAdapter<RecurrentIncomeAdapter> {

    @BindView(R.id.tv_income_amount)
    TextView tvIncomeAmount;

    @BindView(R.id.tv_income_apply_day)
    TextView tvIncomeApplyDay;

    @BindView(R.id.tv_income_concept)
    TextView tvIncomeConcept;

    public IncomesAdapter(Context context, int resource, List<RecurrentIncomeAdapter> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null)
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.row_regular_income_item, null);
        ButterKnife.bind(this, convertView);
        RecurrentIncomeAdapter item = getItem(position);
        tvIncomeAmount.setText(item.getIncomeTotal());
        tvIncomeApplyDay.setText(item.getApplyDay());
        tvIncomeConcept.setText(item.getConcept());
        return convertView;
    }
}
