package com.wally.pocket.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.wally.pocket.R;
import com.wally.pocket.model.CreditCard;
import com.wally.pocket.model.Expense;
import com.wally.pocket.model.Income;
import com.wally.pocket.model.RecurrentExpense;
import com.wally.pocket.model.RecurrentIncome;
import com.wally.pocket.util.NFormatter;

import org.joda.time.DateTime;

import java.util.List;

import static android.R.attr.label;

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

    public static Dialog newCreditCardDialog(final Context context,
                                             final RequiredDialogOps.NewCreditCardListener callback) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = LayoutInflater.from(context);
        final View dialogView = inflater.inflate(R.layout.dialog_new_credit_card, null);
        builder.setView(dialogView);
        builder.setTitle("New credit Card");

        final EditText etCreditCardName = (EditText) dialogView.findViewById(R.id.et_card_name);
        final EditText etPayDay = (EditText)dialogView.findViewById(R.id.et_pay_day);
        final EditText etCreditLimit = (EditText) dialogView.findViewById(R.id.et_credit_limit);

        builder.setPositiveButton(R.string.add, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String cardAlias = etCreditCardName.getText().toString();
                int payDay = NFormatter.toInt(etPayDay.getText().toString());
                float creditLimit = NFormatter.toFloat(etCreditLimit.getText().toString());
                CreditCard card = new CreditCard();
                card.setCreditCardName(cardAlias);
                card.setPayDay(payDay);
                card.setCreditLimit(creditLimit);
                callback.onNewCreditCard(card);
            }
        });
        return builder.create();

    }

    public static Dialog newChargeToCreditCard(final Context context,
                                               final RequiredDialogOps.NewCreditCardCharge callback,
                                               List<CreditCard> creditCards) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = LayoutInflater.from(context);
        final View dialogView = inflater.inflate(R.layout.dialog_add_expense_to_cedit_card, null);
        builder.setView(dialogView);
        final EditText etExpenseConcept = (EditText)dialogView.findViewById(R.id.et_expense_concept);
        final EditText etExpenseAmount = (EditText)dialogView.findViewById(R.id.et_expense_amount);
        final Spinner spCreditCardList = (Spinner) dialogView.findViewById(R.id.sp_credit_card_list);
        final CreditCardAdapter adapter = new CreditCardAdapter(context, android.R.layout.simple_spinner_item, creditCards);
        spCreditCardList.setAdapter(adapter);
        builder.setPositiveButton(R.string.add_charge, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String expenseConcept = etExpenseConcept.getText().toString();
                float expenseAmount = NFormatter.toFloat(etExpenseAmount.getText().toString());
                long id = ((CreditCard)spCreditCardList.getSelectedItem()).getId();
                Expense expense = new Expense();
                expense.setExpenseConcept(expenseConcept);
                expense.setExpenseAmount(expenseAmount);
                callback.onNewChargeToCard(expense, id);
            }
        });
        return builder.create();
    }

    private static class CreditCardAdapter extends ArrayAdapter<CreditCard>{

        public CreditCardAdapter(Context context, int resource, List<CreditCard> objects) {
            super(context, resource, objects);
        }

        @NonNull
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            TextView label = new TextView(getContext());
            label.setText(getItem(position).getFormattedCardName());
            return label;
        }

        @Override
        public View getDropDownView(int position, View convertView, ViewGroup parent) {
            TextView label = new TextView(getContext());
            label.setText(getItem(position).getFormattedCardName());
            return label;
        }
    }
}
