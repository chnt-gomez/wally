package com.wally.pocket.modules.expandinc;

import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import com.wally.pocket.R;
import com.wally.pocket.dialogs.DialogBuilder;
import com.wally.pocket.dialogs.RequiredDialogOps;
import com.wally.pocket.model.RecurrentExpense;
import com.wally.pocket.model.RecurrentIncome;
import com.wally.pocket.modules.core.WallyFragment;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ExpAndIncActivity extends AppCompatActivity {

    private SectionsPagerAdapter mSectionsPagerAdapter;

    @BindView(R.id.tabs)
    TabLayout tabLayout;

    @BindView(R.id.container)
    ViewPager mViewPager;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_incandex);
        init();

    }

    private void init(){
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mSectionsPagerAdapter);
        tabLayout.setupWithViewPager(mViewPager);
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

    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
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

        @BindView(R.id.tv_report_header)
        TextView reportHeader;

        @BindView(R.id.btn_add)
        ImageButton btnAdd;

        ExpAndIncPresenter expAndIncPresenter = ExpAndIncPresenter.getInstance();

        protected static final int FRAGMENT_EXPENSES = 1;
        protected static final int FRAGMENT_INCOMES = 2;
        protected static final String FRAGMENT_TYPE = "fragment_type";
        public static ExpAndIncomeFragment newInstance(FragmentType type) {

            Bundle args = new Bundle();
            args.putInt(LAYOUT_RES, R.layout.fragment_account);
            args.putInt(FRAGMENT_TYPE, type == FragmentType.EXPENSES ? 1 : 2);
            ExpAndIncomeFragment fragment = new ExpAndIncomeFragment();
            fragment.setArguments(args);
            return fragment;
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
            reportHeader.setText("Regular expenses");

            incomesLists.setAdapter(new ExpensesAdapter(
                    getContext(), R.layout.row_regular_expense_item, expAndIncPresenter.getRecurrentExpensesList()));
            btnAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DialogBuilder.newRegularExpense(getContext(), new RequiredDialogOps.NewRegularExpenseListener() {
                        @Override
                        public void onNewRegularExpenseListener(RecurrentExpense expense) {
                            expense.setApplyStatus(RecurrentExpense.PENDING);
                            expense.save();
                        }
                    }).show();
                }
            });

        }

        private void initAsIncomes(){
            cardIcon.setImageResource(R.drawable.ic_bank_note_big);
            cardIcon.setColorFilter(ContextCompat.getColor(getContext(), R.color.colorAccent));
            reportHeader.setText("Regular incomes");
            incomesLists.setAdapter(new IncomesAdapter(getContext(), R.layout.row_regular_income_item,
                    expAndIncPresenter.getRecurrentIncomesList()));
            btnAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DialogBuilder.newRegularIncomeDialog(getContext(), new RequiredDialogOps.NewRegularIncomeListener() {
                        @Override
                        public void onNewRegularIncomeListener(RecurrentIncome income) {
                            income.save();
                        }
                    }).show();
                }
            });
        }
    }
}
