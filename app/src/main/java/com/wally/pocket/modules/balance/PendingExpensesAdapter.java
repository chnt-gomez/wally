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
import com.wally.pocket.model.RecurrentExpense;
import com.wally.pocket.util.NFormatter;

import org.w3c.dom.Text;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by MAV1GA on 26/07/2017.
 */

public class PendingExpensesAdapter extends ArrayAdapter<RecurrentExpense> {

    @BindView(R.id.tv_expense_concept)
    TextView expenseConcept;

    @BindView(R.id.tv_expense_amount)
    TextView expenseAmount;

    public PendingExpensesAdapter(Context context, int resource, List<RecurrentExpense> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null)
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.row_pending_expense_item, null);
        ButterKnife.bind(this, convertView);
        RecurrentExpense item = getItem(position);
        if (item == null)
            item = new RecurrentExpense();
        final String expenseName = item.getExpenseConcept();
        final String expenseTotal = NFormatter.maskMoney(item.getExpenseTotal());
        expenseConcept.setText(expenseName);
        expenseAmount.setText(expenseTotal);
        return convertView;
    }

    @Override
    public long getItemId(int position) {
        return getItem(position).getId();
    }
}
