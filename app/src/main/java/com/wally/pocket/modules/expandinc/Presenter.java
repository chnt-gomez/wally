package com.wally.pocket.modules.expandinc;

import com.wally.pocket.model.RecurrentExpense;
import com.wally.pocket.model.RecurrentExpenseAdapter;
import com.wally.pocket.model.RecurrentIncome;
import com.wally.pocket.model.RecurrentIncomeAdapter;

import java.util.List;

/**
 * Created by MAV1GA on 31/07/2017.
 */

class Presenter {


    private static Presenter instance;

    private Presenter(){}


    public static Presenter getInstance(){
        if (instance == null)
            instance = new Presenter();
        return instance;
    }

    protected List<RecurrentExpenseAdapter> getRecurrentExpensesList(){
        return RecurrentExpenseAdapter.fromList(RecurrentExpense.listAll(RecurrentExpense.class));
    }

    protected List<RecurrentIncomeAdapter> getRecurrentIncomesList(){
        return RecurrentIncomeAdapter.fromList(RecurrentIncome.listAll(RecurrentIncome.class));
    }


}
