package com.wally.pocket.modules.core;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.wally.pocket.R;

import butterknife.ButterKnife;

/**
 * Created by MAV1GA on 26/07/2017.
 */

public class WallyFragment extends Fragment {

    protected View view;
    protected final static String LAYOUT_RES = "layout_res";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        int layoutRes = getArguments().getInt(LAYOUT_RES, -1);
        view = inflater.inflate(layoutRes, container, false);
        ButterKnife.bind(this, view);
        init();
        return view;
    }

    protected void init(){

    }

}
