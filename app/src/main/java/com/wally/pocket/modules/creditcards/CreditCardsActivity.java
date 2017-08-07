package com.wally.pocket.modules.creditcards;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;

import com.wally.pocket.R;
import com.wally.pocket.dialogs.DialogBuilder;
import com.wally.pocket.dialogs.RequiredDialogOps;
import com.wally.pocket.model.CreditCard;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CreditCardsActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.fab)
    FloatingActionButton fab;

    @BindView(R.id.lv_cards)
    ListView lvCardList;

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
        lvCardList.setAdapter(new CreditCardListAdapter(getApplicationContext(),
                R.layout.row_credit_card_item, presenter.buildCreditCardList()));

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
