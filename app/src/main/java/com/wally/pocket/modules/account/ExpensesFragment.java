package com.wally.pocket.modules.account;

import com.wally.pocket.modules.core.WallyFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vicente on 27/07/17.
 */

public class ExpensesFragment extends WallyFragment {

    @Deprecated
    private List<RegularExpense> makeMockExpenses(){
        List<RegularExpense> mockData = new ArrayList<>(15);
        for (int i = 0; i < 14; i++) {
            mockData.add(new RegularExpense("Expense", "$1683.00", "12"));
        }
        return mockData;
    }

}
