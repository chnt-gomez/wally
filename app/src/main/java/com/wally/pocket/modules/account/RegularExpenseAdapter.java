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

public class RegularExpenseAdapter extends ArrayAdapter<RegularExpense> {

    @BindView(R.id.tv_expense_concept)
    TextView expenseConcept;

    @BindView(R.id.tv_expense_amount)
    TextView expenseAmount;

    @BindView(R.id.tv_expense_apply_day)
    TextView applyDay;

    public RegularExpenseAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<RegularExpense> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null)
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.row_regular_expense_item, null);
        ButterKnife.bind(this, convertView);
        RegularExpense expense = getItem(position);
        expenseConcept.setText(expense.getExpenseConcept());
        expenseAmount.setText(expense.getExpenseTotal());
        applyDay.setText(expense.getExpenseApplyDay());
        return convertView;
    }
}
