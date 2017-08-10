package com.wally.pocket.modules.core;
import android.app.Dialog;
import android.graphics.Color;
import android.os.Handler;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import com.wally.pocket.R;
import com.wally.pocket.dialogs.DialogBuilder;

import butterknife.ButterKnife;

/**
 * Created by MAV1GA on 08/08/2017.
 */

public abstract class WallyActivity extends AppCompatActivity implements LoadingDataListener, RequiredViewOps {

    protected CoordinatorLayout coordinatorLayout;
    protected Dialog loadingDialog;

    protected void init(){
        ButterKnife.bind(this);
        setPresenter();
    }

    public abstract void setPresenter();

    protected abstract void start();

    protected void initCoordinatorLayout(int coordinatorLayoutId){
        coordinatorLayout = (CoordinatorLayout) findViewById(coordinatorLayoutId);
    }

    @Override
    public void startLoading() {
        loadingDialog = DialogBuilder.newLoadingDialog(WallyActivity.this);
        loadingDialog.show();
    }

    @Override
    public void onLoading() {

    }

    @Override
    public void onLoadingDone() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (loadingDialog!= null && loadingDialog.isShowing())
                    loadingDialog.dismiss();
            }
        }, 500);

    }

    @Override
    public void onOperationSuccess() {
        if (coordinatorLayout != null){
            Snackbar.make(coordinatorLayout, getString(R.string.success), Snackbar.LENGTH_SHORT)
                    .show();
        }

    }

    @Override
    public void onOperationSuccess(int messageRes) {
        if (coordinatorLayout != null){
            Snackbar.make(coordinatorLayout, getString(messageRes), Snackbar.LENGTH_SHORT)
                    .show();
        }
    }

    @Override
    public void onOperationError() {
        if (coordinatorLayout != null) {
            Snackbar snack = Snackbar.make(coordinatorLayout, R.string.error, Snackbar.LENGTH_SHORT);
            TextView tv = (TextView) snack.getView().findViewById((android.support.design.R.id.snackbar_text));
            tv.setTextColor(Color.parseColor("#ff7f7f"));
            snack.show();
        }

    }

    @Override
    public void onOperationError(int messageRes) {
        if (coordinatorLayout != null) {
            Snackbar snack = Snackbar.make(coordinatorLayout, messageRes, Snackbar.LENGTH_SHORT);
            TextView tv = (TextView) snack.getView().findViewById((android.support.design.R.id.snackbar_text));
            tv.setTextColor(Color.parseColor("#ff7f7f"));
            snack.show();
        }
    }

    @Override
    public void showMessage(int messageRes) {
        if (coordinatorLayout != null){
            Snackbar.make(coordinatorLayout, getString(messageRes), Snackbar.LENGTH_SHORT)
                    .show();
        }
    }

    @Override
    public void showMessage(String message) {
        if (coordinatorLayout != null){
            Snackbar.make(coordinatorLayout, message, Snackbar.LENGTH_SHORT)
                    .show();
        }
    }

    @Override
    public void onReload() {

    }
}
