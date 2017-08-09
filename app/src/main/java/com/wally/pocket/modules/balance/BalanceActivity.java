package com.wally.pocket.modules.balance;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import com.wally.pocket.R;
import com.wally.pocket.modules.account.ActivityAccount;
import com.wally.pocket.modules.core.DataLoader;
import com.wally.pocket.modules.core.WallyActivity;
import com.wally.pocket.modules.creditcards.CreditCardsActivity;
import com.wally.pocket.modules.expandinc.ExpAndIncActivity;
import butterknife.BindView;
import butterknife.OnClick;

public class BalanceActivity extends WallyActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    @BindView(R.id.lv_expenses_list)
    ListView expensesList;

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
    TextView tvPeriodSpendsRemaining;

    @BindView(R.id.tv_credit_cards_debt)
    TextView tvCreditCardsDebt;

    @BindView(R.id.btn_debt_machine)
    ImageButton btnDebtMachine;

    @BindView (R.id.btn_pay_with_cash)
    ImageButton btnPayWithCash;

    private BalancePresenter presenter = BalancePresenter.getInstance(this);
    private ViewAdapter viewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_balance);
        init();
    }

    @Override
    protected void init() {

        super.init();
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        btnDebtMachine.setOnClickListener(this);
        btnPayWithCash.setOnClickListener(this);

        start();


        expensesList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                presenter.applyRecurrentExpense(id);
                return true;
            }
        });
    }

    @Override
    protected void start() {
        DataLoader loader = new DataLoader(this);
        loader.execute();
    }

    @Override
    protected void setCoordinatorLayout() {
        try {
            coordinatorLayout = (CoordinatorLayout)findViewById(R.id.lyt_coordinator_layout);
        }catch (Exception e){
            Log.e(getClass().getSimpleName(), "Coordinator layout was not found!!!");
        }
    }

    @Override
    public void onLoading() {
        super.onLoading();
        viewAdapter = new ViewAdapter();
        viewAdapter.setAdapter(new PendingExpensesAdapter(getApplicationContext(), R.layout.row_pending_expense_item,
                presenter.getPeriodExpenses()));
        viewAdapter.setPeriodTotal(presenter.getPeriodTotal());
        viewAdapter.setAccountTotal(presenter.getAccountTotal());
        viewAdapter.setCreditCardsDebt(presenter.getFormattedCreditCardsDebt());
        viewAdapter.setPeriodSpendsAvailable(presenter.getPeriodSpendsAvailable());
    }

    @Override
    public void onLoadingDone() {

        if (viewAdapter != null) {
            expensesList.setAdapter(viewAdapter.getAdapter());
            tvExpensesTotal.setText(viewAdapter.getPeriodTotal());
            tvAccountTotal.setText(viewAdapter.getAccountTotal());
            tvCreditCardsDebt.setText(viewAdapter.getCreditCardsDebt());
            tvPeriodSpendsRemaining.setText(viewAdapter.getPeriodSpendsAvailable());
        }
        super.onLoadingDone();
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

    @Override
    public void onReload() {
        start();
    }

    @Override
    public void onClick(View view) {
        final int id = view.getId();
        if (id == R.id.btn_debt_machine){
            //
        }
        if (id == R.id.btn_pay_with_cash){
            //
        }
    }

    class ViewAdapter{
        private String periodTotal, accountTotal, creditCardsDebt, periodSpendsAvailable;
        private PendingExpensesAdapter adapter;

        public String getPeriodTotal() {
            return periodTotal;
        }

        public void setPeriodTotal(String periodTotal) {
            this.periodTotal = periodTotal;
        }

        public String getAccountTotal() {
            return accountTotal;
        }

        public void setAccountTotal(String accountTotal) {
            this.accountTotal = accountTotal;
        }

        public String getCreditCardsDebt() {
            return creditCardsDebt;
        }

        public void setCreditCardsDebt(String creditCardsDebt) {
            this.creditCardsDebt = creditCardsDebt;
        }

        public String getPeriodSpendsAvailable() {
            return periodSpendsAvailable;
        }

        public void setPeriodSpendsAvailable(String periodSpendsAvailable) {
            this.periodSpendsAvailable = periodSpendsAvailable;
        }

        public PendingExpensesAdapter getAdapter() {
            return adapter;
        }

        public void setAdapter(PendingExpensesAdapter adapter) {
            this.adapter = adapter;
        }
    }
}
