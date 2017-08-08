package com.wally.pocket.modules.expandinc;

import com.wally.pocket.model.RecurrentExpense;
import com.wally.pocket.model.RecurrentIncome;

import java.util.List;

/**
 * Created by MAV1GA on 31/07/2017.
 */

class ExpAndIncPresenter {


    private static ExpAndIncPresenter instance;

    private ExpAndIncPresenter(){}


    static ExpAndIncPresenter getInstance(){
        if (instance == null)
            instance = new ExpAndIncPresenter();
        return instance;
    }

    List<RecurrentExpense> getRecurrentExpensesList(){
        return RecurrentExpense.listAll(RecurrentExpense.class);
    }

    List<RecurrentIncome> getRecurrentIncomesList(){
        return RecurrentIncome.listAll(RecurrentIncome.class);
    }


}
