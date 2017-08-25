package com.wally.pocket.modules.creditcards;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import com.wally.pocket.R;
import com.wally.pocket.dialogs.DialogBuilder;
import com.wally.pocket.dialogs.RequiredDialogOps;
import com.wally.pocket.model.CreditCard;
import com.wally.pocket.model.ExpenseInCreditCard;
import com.wally.pocket.modules.core.DataLoader;
import com.wally.pocket.modules.core.RequiredPresenterOps;
import com.wally.pocket.modules.core.RequiredViewOps;
import com.wally.pocket.modules.core.WallyActivity;
import com.wally.pocket.modules.core.WallyPresenter;
import com.wally.pocket.util.AlarmReceiver;
import com.wally.pocket.util.NFormatter;
import com.wally.pocket.widgets.Label;

import org.joda.time.DateTime;
import java.util.List;

import butterknife.BindView;

public class CreditCardsActivity extends WallyActivity implements RequiredViewOps.RequiredCardViewOps{

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

    @BindView(R.id.lb_current_debt)
    Label lbCurrentDebt;

    @BindView(R.id.lb_cut_day)
    Label lbCutDay;

    @BindView(R.id.lb_pay_day)
    Label lbPayDay;

    @BindView(R.id.lb_total_debt)
    Label lbTotalDebt;

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
                , R.layout.spn_spinner_item, viewAdapter.getCardList());
        spnCardList.setAdapter(adapter);
        loadCard();
        spnCardList.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                loadCard();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void loadCard() {
        CreditCard card = (CreditCard) spnCardList.getSelectedItem();
        if (card != null) {
            etCreditCardName.setText(card.getCreditCardName());
            lbTotalDebt.setDisplay(NFormatter.maskMoney(card.getTotalDebt()));
            lbCurrentDebt.setDisplay(viewAdapter.getMonthPurchasesInCard(card.getId()));
        }
    }

    @Override
    public void onNewCardAdded(CreditCard creditCard) {
        showMessage("Added card");
        Intent intent = new Intent(getApplicationContext(), AlarmReceiver.class);
        intent.setAction(AlarmReceiver.UPDATE);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(),
                0, intent, PendingIntent.FLAG_CANCEL_CURRENT);
        AlarmManager alarm = (AlarmManager)getApplicationContext().getSystemService(Context.ALARM_SERVICE);
        alarm.cancel(pendingIntent);
        DateTime morningTime = new DateTime();
        morningTime.withTime(9, 0, 0, 0);
        alarm.setRepeating(AlarmManager.RTC_WAKEUP, morningTime.getMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);
    }

    private static class CreditCardAdapter extends ArrayAdapter<CreditCard> {

        public CreditCardAdapter(Context context, int resource, List<CreditCard> objects) {
            super(context, resource, objects);
        }

        @NonNull
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null){
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.spn_spinner_item,
                        null);
            }
            TextView label = (TextView)convertView.findViewById(R.id.spn_item);
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
        String getMonthPurchasesInCard(long cardId){
            return NFormatter.maskMoney(presenter.getMonthDebtInCard(cardId));
        }
    }
}
