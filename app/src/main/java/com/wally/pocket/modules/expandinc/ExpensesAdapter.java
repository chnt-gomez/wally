package com.wally.pocket.modules.expandinc;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.wally.pocket.R;
import com.wally.pocket.model.RecurrentExpenseAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by MAV1GA on 31/07/2017.
 */

public class ExpensesAdapter extends ArrayAdapter<RecurrentExpenseAdapter> {

    @BindView(R.id.tv_expense_amount)
    TextView tvExpenseAmount;

    @BindView(R.id.tv_expense_apply_day)
    TextView tvExpenseApplyDay;

    @BindView(R.id.tv_expense_concept)
    TextView tvExpenseConcept;

    public ExpensesAdapter(Context context, int resource, List<RecurrentExpenseAdapter> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView ==  null)
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.row_regular_expense_item, null);
        ButterKnife.bind(this, convertView);
        RecurrentExpenseAdapter item = getItem(position);
        tvExpenseAmount.setText(item.getExpenseAmount());
        tvExpenseApplyDay.setText(item.getExpenseApplyDay());
        tvExpenseConcept.setText(item.getExpenseConcept());
        return convertView;

    }
}
