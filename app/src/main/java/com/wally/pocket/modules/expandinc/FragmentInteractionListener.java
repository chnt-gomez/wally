package com.wally.pocket.modules.expandinc;

import com.wally.pocket.model.RecurrentExpense;
import com.wally.pocket.model.RecurrentIncome;

import java.util.List;

/**
 * Created by MAV1GA on 10/08/2017.
 */

interface FragmentInteractionListener {

    List<RecurrentIncome> getIncomes();
    List<RecurrentExpense> getExpenses();

}
