package com.wally.pocket.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.wally.pocket.R;
import com.wally.pocket.model.Expense;
import com.wally.pocket.model.Income;
import com.wally.pocket.model.RecurrentExpense;
import com.wally.pocket.model.RecurrentIncome;
import com.wally.pocket.util.NFormatter;

import org.joda.time.DateTime;

/**
 * Created by MAV1GA on 31/07/2017.
 */

public class DialogBuilder {

    public static Dialog newRegularIncomeDialog (final Context context,
                                                 final RequiredDialogOps.NewRegularIncomeListener callback){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = LayoutInflater.from(context);

        final View dialogView = inflater.inflate(R.layout.dialog_new_income, null);
        final EditText incomeConcept = (EditText)dialogView.findViewById(R.id.et_income_concept);
        final EditText incomeApplyDay = (EditText)dialogView.findViewById(R.id.et_apply_day);
        final EditText incomeAmount = (EditText)dialogView.findViewById(R.id.et_income_amount);
        builder.setView(dialogView).
                setTitle("New Regular Income").
                setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        RecurrentIncome income = new RecurrentIncome();
                        income.setIncomeConcept(incomeConcept.getText().toString());
                        income.setIncomeAmount(NFormatter.toFloat(incomeAmount.getText().toString()));
                        income.setApplyDate(NFormatter.toInt(incomeApplyDay.getText().toString()));
                        callback.onNewRegularIncomeListener(income);
                    }
                });
        return builder.create();
    }

    public static Dialog newLuckyIncomeDialog(final Context context,
                                              final RequiredDialogOps.NewLuckyIncomeListener callback){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = LayoutInflater.from(context);

        final View dialogView = inflater.inflate(R.layout.dialog_new_lucky_income, null);
        final EditText etIncomeConcept = (EditText)dialogView.findViewById(R.id.et_income_concept);
        final EditText etIncomeAmount = (EditText)dialogView.findViewById(R.id.et_income_amount);
        builder.setView(dialogView).
                setTitle("Lucky income").
                setPositiveButton("Apply", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Income income = new Income();
                        income.setIncomeConcept(etIncomeConcept.getText().toString());
                        income.setIncomeAmount(NFormatter.toFloat(etIncomeAmount.getText().toString()));
                        income.setIncomeApplyDate(DateTime.now().getMillis());
                        callback.onNewLuckyIncome(income);
                    }
                });
        return builder.create();
    }

    public static Dialog newQuickExpenseDialog(final Context context,
                                               final RequiredDialogOps.NewQuickExpenseListener callback){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = LayoutInflater.from(context);

        final View dialogView = inflater.inflate(R.layout.dialog_new_quick_expense, null);
        final EditText etExpenseConcept = (EditText)dialogView.findViewById(R.id.et_expense_concept);
        final EditText etExpenseAmount = (EditText)dialogView.findViewById(R.id.et_expense_amount);
        builder.setView(dialogView).
                setTitle("Quick expense").
                setPositiveButton("Apply", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Expense expense = new Expense();
                        expense.setExpenseConcept(etExpenseConcept.getText().toString());
                        expense.setExpenseAmount(NFormatter.toFloat(etExpenseAmount.getText().toString()));
                        expense.setExpenseApplyDate(DateTime.now().getMillis());
                        callback.onNewQuickExpenseListener(expense);
                    }
                });
        return builder.create();
    }

    public static Dialog newRegularExpense (final Context context,
                                                 final RequiredDialogOps.NewRegularExpenseListener callback){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = LayoutInflater.from(context);

        final View dialogView = inflater.inflate(R.layout.dialog_new_income, null);
        final EditText incomeConcept = (EditText)dialogView.findViewById(R.id.et_income_concept);
        final EditText incomeApplyDay = (EditText)dialogView.findViewById(R.id.et_apply_day);
        final EditText incomeAmount = (EditText)dialogView.findViewById(R.id.et_income_amount);

        builder.setView(dialogView).
                setTitle("New Regular Expense").
                setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        RecurrentExpense expense = new RecurrentExpense();
                        expense.setExpenseConcept(incomeConcept.getText().toString());
                        expense.setExpenseTotal(NFormatter.toFloat(incomeAmount.getText().toString()));
                        expense.setApplyDay(NFormatter.toInt(incomeApplyDay.getText().toString()));
                        callback.onNewRegularExpenseListener(expense);
                    }
                });
        return builder.create();

    }

}
