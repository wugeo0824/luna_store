package com.xjcrepe.lunastore.dashboard.account;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xjcrepe.lunastore.R;

import javax.inject.Inject;

/**
 * Dummy fragment to hold my contact details
 */

public class AccountFragment extends Fragment {

    @Inject
    public  AccountFragment() {}

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_account, container, false);
    }
}
