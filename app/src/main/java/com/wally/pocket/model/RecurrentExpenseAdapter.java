package com.wally.pocket.model;

import com.wally.pocket.util.NFormatter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MAV1GA on 31/07/2017.
 */

public class RecurrentExpenseAdapter {

    private RecurrentExpense expense;

    public RecurrentExpenseAdapter(RecurrentExpense expense){
        this.expense = expense;
    }

    public String getExpenseAmount(){
        return NFormatter.maskNumber(expense.getExpenseTotal());
    }

    public String getExpenseApplyDay(){
        return String.valueOf(expense.getApplyDay());
    }

    public String getExpenseConcept(){
        return expense.getExpenseConcept();
    }

    public static List<RecurrentExpenseAdapter> fromList(List<RecurrentExpense> expenses){
        List<RecurrentExpenseAdapter> list = new ArrayList<>();
        for (RecurrentExpense e : expenses){
            list.add(new RecurrentExpenseAdapter(e));
        }
        return list;
    }



}
