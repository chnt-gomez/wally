package com.wally.pocket.model;

import com.wally.pocket.util.NFormatter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MAV1GA on 31/07/2017.
 */

public class RecurrentIncomeAdapter {

    private RecurrentIncome income;

    public RecurrentIncomeAdapter(RecurrentIncome income){
        this.income = income;
    }

    public String getIncomeTotal(){
        return NFormatter.maskNumber(income.getIncomeAmount());
    }

    public String getApplyDay(){
        return String.valueOf(income.getApplyDate());
    }

    public String getConcept(){
        return income.getIncomeConcept();
    }

    public static List<RecurrentIncomeAdapter> fromList (List<RecurrentIncome> items){
        List<RecurrentIncomeAdapter> list = new ArrayList<>();
        for (RecurrentIncome income : items){
            list.add(new RecurrentIncomeAdapter(income));
        }
        return list;
    }



}
