package com.wally.pocket.modules.creditcards;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.wally.pocket.R;
import com.wally.pocket.dialogs.DialogBuilder;
import com.wally.pocket.dialogs.RequiredDialogOps;
import com.wally.pocket.model.CreditCard;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CreditCardsActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.fab)
    FloatingActionButton fab;

    @BindView(R.id.spn_cards)
    Spinner spnCardList;

    private CreditCardsPresenter presenter = CreditCardsPresenter.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credit_cards);
        init();
    }

    private void init(){
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogBuilder.newCreditCardDialog(CreditCardsActivity.this, new RequiredDialogOps.NewCreditCardListener() {
                    @Override
                    public void onNewCreditCard(CreditCard card) {
                        presenter.addCreditCard(card);
                    }
                }).show();
            }
        });
        final CreditCardAdapter adapter = new CreditCardAdapter(getApplicationContext()
                , android.R.layout.simple_spinner_item, presenter.buildCreditCardList());
        spnCardList.setAdapter(adapter);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private static class CreditCardAdapter extends ArrayAdapter<CreditCard> {

        public CreditCardAdapter(Context context, int resource, List<CreditCard> objects) {
            super(context, resource, objects);
        }

        @NonNull
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            TextView label = new TextView(getContext());
            label.setText(getItem(position).getCreditCardName());
            return label;
        }

        @Override
        public View getDropDownView(int position, View convertView, ViewGroup parent) {
            TextView label = new TextView(getContext());
            label.setText(getItem(position).getCreditCardName());
            return label;
        }
    }
}
