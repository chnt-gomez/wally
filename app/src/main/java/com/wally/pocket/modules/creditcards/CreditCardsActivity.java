package com.wally.pocket.modules.creditcards;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.wally.pocket.R;
import com.wally.pocket.dialogs.DialogBuilder;
import com.wally.pocket.dialogs.RequiredDialogOps;
import com.wally.pocket.model.CreditCard;
import com.wally.pocket.modules.core.DataLoader;
import com.wally.pocket.modules.core.RequiredPresenterOps;
import com.wally.pocket.modules.core.WallyActivity;
import com.wally.pocket.modules.core.WallyPresenter;

import java.util.List;
import java.util.concurrent.Callable;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CreditCardsActivity extends WallyActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.fab)
    FloatingActionButton fab;

    @BindView(R.id.spn_cards)
    Spinner spnCardList;

    @BindView(R.id.et_card_name)
    EditText etCreditCardName;

    @BindView(R.id.et_cut_day)
    EditText etCutDay;

    @BindView(R.id.et_pay_day)
    EditText etPayDay;

    private ViewAdapter viewAdapter;

    private RequiredPresenterOps.RequiredCreditCardPresenterOps presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credit_cards);
        init();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.credit_cards_menu, menu);
        return true;
    }


    @Override
    protected void init(){
        super.init();
        setSupportActionBar(toolbar);
        initCoordinatorLayout(R.id.lyt_coordinator_layout);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                save();

            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        start();
    }

    private void save() {
        CreditCard card = (CreditCard)spnCardList.getSelectedItem();
        card.setCreditCardName(etCreditCardName.getText().toString());
        card.setCutDay(Integer.valueOf(etCutDay.getText().toString()));
        card.setPayDay(Integer.valueOf(etPayDay.getText().toString()));
        presenter.updateCreditCard(spnCardList.getSelectedItemId(),card);
    }

    @Override
    public void onOperationSuccess() {
        super.onOperationSuccess();
        start();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_add){
            DialogBuilder.newCreditCardDialog(CreditCardsActivity.this, new RequiredDialogOps.NewCreditCardListener() {
                @Override
                public void onNewCreditCard(CreditCard card) {
                    presenter.addCreditCard(card);
                }
            }).show();
            return true;
        }
        return false;
    }

    @Override
    public void setPresenter() {
        presenter = WallyPresenter.getInstance(this);
    }

    @Override
    protected void start() {
        DataLoader loader = new DataLoader(this);
        loader.execute();
    }

    @Override
    public void onLoading() {
        viewAdapter = new ViewAdapter();
        viewAdapter.setCardList(presenter.getAllCreditCards());
    }

    @Override
    public void onLoadingDone() {
        super.onLoadingDone();
        final CreditCardAdapter adapter = new CreditCardAdapter(getApplicationContext()
                , android.R.layout.simple_spinner_item, viewAdapter.getCardList());
        spnCardList.setAdapter(adapter);
        loadCard(0);
        spnCardList.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                loadCard(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void loadCard(int position) {
        CreditCard card = (CreditCard) spnCardList.getSelectedItem();
        etCreditCardName.setText(card.getCreditCardName());
        etCutDay.setText(String.valueOf(card.getCutDay()));
        etPayDay.setText(String.valueOf(card.getPayDay()));
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

        @Override
        public long getItemId(int position) {
            return getItem(position).getId();
        }


    }



    class ViewAdapter {
        private List<CreditCard> cardList;

        public List<CreditCard> getCardList() {
            return cardList;
        }

        public void setCardList(List<CreditCard> cardList) {
            this.cardList = cardList;
        }
    }
}
