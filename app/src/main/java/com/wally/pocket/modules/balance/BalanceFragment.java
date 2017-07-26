package com.wally.pocket.modules.balance;

import android.os.Bundle;

import com.wally.pocket.R;
import com.wally.pocket.modules.core.WallyFragment;

/**
 * Created by MAV1GA on 26/07/2017.
 */

public class BalanceFragment extends WallyFragment {

    public static BalanceFragment newInstance() {
        Bundle args = new Bundle();
        args.putInt(LAYOUT_RES, R.layout.fragment_balance);
        BalanceFragment fragment = new BalanceFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
