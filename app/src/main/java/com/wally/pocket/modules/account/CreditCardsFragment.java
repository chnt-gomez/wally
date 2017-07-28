package com.wally.pocket.modules.account;

import com.wally.pocket.modules.core.WallyFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vicente on 27/07/17.
 */

public class CreditCardsFragment extends WallyFragment {

    @Deprecated
    private List<RegularIncome> makeMockIncomes(){
        List<RegularIncome> mockData = new ArrayList<>(2);
        mockData.add(new RegularIncome("PayDay", "7500.00", "12"));
        mockData.add(new RegularIncome("PayDay", "7500.00", "127"));
        return mockData;
    }
}
