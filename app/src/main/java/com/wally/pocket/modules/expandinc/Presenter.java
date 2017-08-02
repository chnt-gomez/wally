package com.wally.pocket.modules.expandinc;

import com.wally.pocket.model.RecurrentExpense;
import com.wally.pocket.model.RecurrentIncome;

import java.util.List;

/**
 * Created by MAV1GA on 31/07/2017.
 */

class Presenter {


    private static Presenter instance;

    private Presenter(){}


    static Presenter getInstance(){
        if (instance == null)
            instance = new Presenter();
        return instance;
    }

    List<RecurrentExpense> getRecurrentExpensesList(){
        return RecurrentExpense.listAll(RecurrentExpense.class);
    }

    List<RecurrentIncome> getRecurrentIncomesList(){
        return RecurrentIncome.listAll(RecurrentIncome.class);
    }


}
