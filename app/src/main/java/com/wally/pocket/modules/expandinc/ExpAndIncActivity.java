package com.wally.pocket.modules.expandinc;

import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.wally.pocket.R;
import com.wally.pocket.dialogs.DialogBuilder;
import com.wally.pocket.dialogs.RequiredDialogOps;
import com.wally.pocket.model.RecurrentExpense;
import com.wally.pocket.model.RecurrentIncome;
import com.wally.pocket.modules.core.RequiredPresenterOps;
import com.wally.pocket.modules.core.WallyActivity;
import com.wally.pocket.modules.core.WallyFragment;
import com.wally.pocket.modules.core.WallyPresenter;

import java.util.List;

import butterknife.BindView;

public class ExpAndIncActivity extends WallyActivity implements FragmentInteractionListener{
    private SectionsPagerAdapter mSectionsPagerAdapter;

    @BindView(R.id.tabs)
    TabLayout tabLayout;

    @BindView(R.id.container)
    ViewPager mViewPager;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.fab)
    FloatingActionButton btnAdd;

    private RequiredPresenterOps.RequiredExpAndIncOps presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_incandex);
        init();
    }

    @Override
    protected void init(){
        super.init();
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mSectionsPagerAdapter);
        tabLayout.setupWithViewPager(mViewPager);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mViewPager.getCurrentItem() == 0){
                    DialogBuilder.newRegularIncomeDialog(ExpAndIncActivity.this,
                            new RequiredDialogOps.NewRegularIncomeListener() {
                        @Override
                        public void onNewRegularIncomeListener(RecurrentIncome income) {

                        }
                    }).show();
                } if(mViewPager.getCurrentItem() == 1){
                    DialogBuilder.newRegularExpense(ExpAndIncActivity.this,
                            new RequiredDialogOps.NewRegularExpenseListener() {
                                @Override
                                public void onNewRegularExpenseListener(RecurrentExpense expense) {
                                    presenter.addNewRegularExpense(expense);
                                }
                            }).show();
                }
            }
        });
    }

    @Override
    public void setPresenter() {
        presenter = WallyPresenter.getInstance(this);
    }

    @Override
    protected void start() {

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_account, menu);
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

    @Override
    public List<RecurrentIncome> getIncomes() {
        return presenter.getRecurrentIncomes();
    }

    @Override
    public List<RecurrentExpense> getExpenses() {
        return presenter.getRecurrentExpenses();
    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            if (position == 1)
                return ExpAndIncomeFragment.newInstance(FragmentType.EXPENSES);
            return ExpAndIncomeFragment.newInstance(FragmentType.INCOME);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "INCOMES";
                case 1:
                    return "EXPENSES";
            }
            return null;
        }
    }

    protected enum FragmentType {
        INCOME, EXPENSES
    }

    public static class ExpAndIncomeFragment extends WallyFragment{
        @BindView(R.id.lv_incomes_list)
        ListView incomesLists;

        @BindView(R.id.img_card_icon)
        ImageView cardIcon;

        private FragmentInteractionListener presenter;

        protected static final int FRAGMENT_EXPENSES = 1;
        protected static final int FRAGMENT_INCOMES = 2;
        protected static final String FRAGMENT_TYPE = "fragment_type";
        public static ExpAndIncomeFragment newInstance(FragmentType type) {

            Bundle args = new Bundle();
            args.putInt(LAYOUT_RES, R.layout.fragment_incomes_and_expenses);
            args.putInt(FRAGMENT_TYPE, type == FragmentType.EXPENSES ? 1 : 2);
            ExpAndIncomeFragment fragment = new ExpAndIncomeFragment();
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public void onAttach(Context context) {
            super.onAttach(context);
            this.presenter = (FragmentInteractionListener)context;
        }

        @Override
        public void onDetach() {
            this.presenter = null;
            super.onDetach();
        }

        @Override
        protected void init() {
            int fragmentType = getArguments().getInt(FRAGMENT_TYPE);
            switch (fragmentType){
               case FRAGMENT_EXPENSES:
                   initAsExpenses();
                   break;
               case FRAGMENT_INCOMES:
                   initAsIncomes();
                   break;
           }


        }

        private void initAsExpenses() {
            cardIcon.setImageResource(R.drawable.ic_banking_spendings_big);
            cardIcon.setColorFilter(ContextCompat.getColor(getContext(), R.color.colorAccentSecondary));

            incomesLists.setAdapter(new ExpensesAdapter(
                    getContext(), R.layout.row_regular_expense_item, presenter.getExpenses()));

        }

        private void initAsIncomes(){
            cardIcon.setImageResource(R.drawable.ic_bank_note_big);
            cardIcon.setColorFilter(ContextCompat.getColor(getContext(), R.color.colorAccent));
            incomesLists.setAdapter(new IncomesAdapter(getContext(), R.layout.row_regular_income_item,
                    presenter.getIncomes()));
        }
    }


}


