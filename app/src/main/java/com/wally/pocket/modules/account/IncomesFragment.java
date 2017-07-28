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

public class IncomesFragment extends WallyFragment {


    @BindView(R.id.lv_incomes_list)
    ListView incomesLists;

    public static IncomesFragment newInstance() {

        Bundle args = new Bundle();
        args.putInt(LAYOUT_RES, R.layout.fragment_account);
        IncomesFragment fragment = new IncomesFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void init() {
        incomesLists.setAdapter(new RegularIncomeAdapter(getContext(), R.layout.row_regular_income_item,
                makeMockIncomes()));
    }

    @Deprecated
    private List<RegularIncome> makeMockIncomes(){
        List<RegularIncome> mockData = new ArrayList<>();
        mockData.add(new RegularIncome("$ 7,500", "27", "Bancomer"));
        mockData.add(new RegularIncome("$ 7,500", "27", "Bancomer"));
        mockData.add(new RegularIncome("$ 7,500", "27", "Bancomer"));
        mockData.add(new RegularIncome("$ 7,500", "27", "Bancomer"));
        mockData.add(new RegularIncome("$ 7,500", "27", "Bancomer"));
        mockData.add(new RegularIncome("$ 7,500", "27", "Bancomer"));
        mockData.add(new RegularIncome("$ 7,500", "27", "Bancomer"));
        mockData.add(new RegularIncome("$ 7,500", "27", "Bancomer"));
        mockData.add(new RegularIncome("$ 7,500", "27", "Bancomer"));
        mockData.add(new RegularIncome("$ 7,500", "27", "Bancomer"));
        mockData.add(new RegularIncome("$ 7,500", "27", "Bancomer"));
        mockData.add(new RegularIncome("$ 7,500", "27", "Bancomer"));
        mockData.add(new RegularIncome("$ 7,500", "27", "Bancomer"));
        mockData.add(new RegularIncome("$ 7,500", "27", "Bancomer"));
        mockData.add(new RegularIncome("$ 7,500", "27", "Bancomer"));
        mockData.add(new RegularIncome("$ 7,500", "27", "Bancomer"));
        mockData.add(new RegularIncome("$ 7,500", "27", "Bancomer"));
        mockData.add(new RegularIncome("$ 7,500", "27", "Bancomer"));
        mockData.add(new RegularIncome("$ 7,500", "27", "Bancomer"));
        mockData.add(new RegularIncome("$ 7,500", "27", "Bancomer"));
        mockData.add(new RegularIncome("$ 7,500", "13", "Walmart"));
        return mockData;
    }



}
