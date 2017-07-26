package com.wally.pocket.modules.balance;

import android.os.Bundle;
import android.widget.ListView;

import com.wally.pocket.R;
import com.wally.pocket.modules.core.WallyFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by MAV1GA on 26/07/2017.
 */

public class BalanceFragment extends WallyFragment {

    @BindView(R.id.lv_expenses_list)
    ListView expensesList;

    public static BalanceFragment newInstance() {
        Bundle args = new Bundle();
        args.putInt(LAYOUT_RES, R.layout.fragment_balance);
        BalanceFragment fragment = new BalanceFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void init() {
        expensesList.setAdapter(new PendingExpensesAdapter(getContext(), R.layout.row_pending_expense_item,
                generateMockList()));
    }

    @Deprecated
    List<PendingExpense> generateMockList(){
        List<PendingExpense> mockList = new ArrayList<>(25);
        for (int i=0; i<24; i++){
            mockList.add(new PendingExpense("Mock concept", "$ 1,568.00"));
        }
        return mockList;
    }


}
