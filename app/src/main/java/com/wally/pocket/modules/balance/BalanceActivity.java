package com.wally.pocket.modules.balance;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import com.wally.pocket.R;
import com.wally.pocket.dialogs.DialogBuilder;
import com.wally.pocket.dialogs.RequiredDialogOps;
import com.wally.pocket.model.CreditCard;
import com.wally.pocket.model.Expense;
import com.wally.pocket.model.Income;
import com.wally.pocket.modules.account.ActivityAccount;
import com.wally.pocket.modules.creditcards.CreditCardsActivity;
import com.wally.pocket.modules.expandinc.ExpAndIncActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

public class BalanceActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.lv_expenses_list)
    ListView expensesList;

    @BindView(R.id.fab)
    FloatingActionButton fab;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;

    @BindView(R.id.nav_view)
    NavigationView navigationView;

    @BindView(R.id.tv_expenses_total)
    TextView tvExpensesTotal;

    @BindView(R.id.tv_account_total)
    TextView tvAccountTotal;

    @BindView(R.id.tv_period_remaining)
    TextView tvPeriodSpendinsRemaining;

    @BindView(R.id.btn_simley)
    ImageButton btnSmiley;

    @BindView(R.id.btn_ask)
    ImageButton btnAsk;

    @BindView(R.id.btn_pay_with_card)
    ImageButton btnPayWIthCredit;

    private BalancePresenter presenter = BalancePresenter.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_balance);
        init();

    }

    private void init() {
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogBuilder.newQuickExpenseDialog(BalanceActivity.this, new RequiredDialogOps.NewQuickExpenseListener() {
                    @Override
                    public void onNewQuickExpenseListener(Expense expense) {
                        presenter.applyExpense(expense);
                    }
                }).show();
            }
        });

         btnSmiley.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                DialogBuilder.newLuckyIncomeDialog(BalanceActivity.this, new RequiredDialogOps.NewLuckyIncomeListener() {
                    @Override
                    public void onNewLuckyIncome(Income income) {
                        presenter.applyLuckyIncome(income);
                    }
                }).show();
             }
         });

        btnPayWIthCredit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogBuilder.newChargeToCreditCard(BalanceActivity.this, new RequiredDialogOps.NewCreditCardCharge() {
                    @Override
                    public void onNewChargeToCard(Expense expense, long cardId) {
                        presenter.applyChargeToCard(expense, cardId);
                    }
                }, presenter.getCreditCards()).show();
            }
        });

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        expensesList.setAdapter(new PendingExpensesAdapter(getApplicationContext(), R.layout.row_pending_expense_item,
                presenter.getPeriodExpenses()));
        tvExpensesTotal.setText(presenter.getPeriodTotal());
        tvAccountTotal.setText(presenter.getAccountTotal());
        tvPeriodSpendinsRemaining.setText(presenter.getPeriodSpendinsAvailable());
        expensesList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                presenter.applyRecurrentExpense(id);
                return true;
            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.balance, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        switch (id){
            case R.id.action_incandexp:
                startActivity(new Intent(BalanceActivity.this, ExpAndIncActivity.class));
                break;
            case R.id.action_account:
                startActivity(new Intent(BalanceActivity.this, ActivityAccount.class));
                break;
            case R.id.action_credit_cards:
                startActivity(new Intent(BalanceActivity.this, CreditCardsActivity.class));
                break;
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
