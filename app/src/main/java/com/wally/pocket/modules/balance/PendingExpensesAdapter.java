package com.wally.pocket.modules.balance;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.wally.pocket.R;

import org.w3c.dom.Text;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by MAV1GA on 26/07/2017.
 */

public class PendingExpensesAdapter extends ArrayAdapter<PendingExpense> {

    private List<PendingExpense> items;

    @BindView(R.id.tv_expense_concept)
    TextView expenseConcept;

    @BindView(R.id.tv_expense_amount)
    TextView expenseAmount;

    public PendingExpensesAdapter(Context context, int resource, List<PendingExpense> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null)
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.row_pending_expense_item, null);
        ButterKnife.bind(this, convertView);
        PendingExpense item = getItem(position);
        final String expenseName = item.getConcept();
        final String expenseTotal = item.getAmount();
        expenseConcept.setText(expenseName);
        expenseAmount.setText(expenseTotal);
        return convertView;
    }
}
