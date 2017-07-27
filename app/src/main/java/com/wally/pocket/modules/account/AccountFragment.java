package com.wally.pocket.modules.account;

import android.os.Bundle;
import android.widget.ListView;

import com.wally.pocket.R;
import com.wally.pocket.modules.core.WallyFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by vicente on 27/07/17.
 */

public class AccountFragment extends WallyFragment {

    @BindView(R.id.lv_income_list)
    ListView incomeList;

    @BindView(R.id.lv_expenses_list)
    ListView expenseList;

    @BindView(R.id.lv_credit_card_list)
    ListView creditCardList;

    public static AccountFragment newInstance() {

        Bundle args = new Bundle();
        args.putInt(LAYOUT_RES, R.layout.fragment_account);
        AccountFragment fragment = new AccountFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void init() {
        incomeList.setAdapter(new RegularIncomeAdapter(getContext(), R.layout.row_regular_income_item,
                makeMockIncomes()));
        expenseList.setAdapter(new RegularExpenseAdapter(getContext(), R.layout.row_regular_expense_item,
                makeMockExpenses()));
        creditCardList.setAdapter(new CreditCardAdapter(getContext(), R.layout.row_credit_card_item,
                makeMockCreditCards()));
    }

    @Deprecated
    private List<Card> makeMockCreditCards(){
        List<Card> mockData = new ArrayList<>(3);
        mockData.add(new Card("$ 10,091.98", "16", "Bancomer"));
        mockData.add(new Card("$ 261.60", "15", "Walmart"));
        mockData.add(new Card("$ 8,092", "31", "Amex"));
        mockData.add(new Card("$ 540.67", "12", "Banorte"));
        return mockData;
    }

    @Deprecated
    private List<RegularExpense> makeMockExpenses(){
        List<RegularExpense> mockData = new ArrayList<>(15);
        for (int i = 0; i < 14; i++) {
            mockData.add(new RegularExpense("Expense", "$1683.00", "12"));
        }
        return mockData;
    }

    @Deprecated
    private List<RegularIncome> makeMockIncomes(){
        List<RegularIncome> mockData = new ArrayList<>(2);
        mockData.add(new RegularIncome("PayDay", "7500.00", "12"));
        mockData.add(new RegularIncome("PayDay", "7500.00", "127"));
        return mockData;
    }


}
