package com.apps.akkaber.uis.activity_wallet;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;

import com.apps.akkaber.R;
import com.apps.akkaber.adapter.WalletAdapter;
import com.apps.akkaber.databinding.ActivityWalletBinding;
import com.apps.akkaber.uis.activity_base.BaseActivity;

public class WalletActivity extends BaseActivity {
    private ActivityWalletBinding binding;
    private WalletAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_wallet);
        initView();
    }

    private void initView() {
        adapter=new WalletAdapter(this);
        LinearLayoutManager layoutManager=new GridLayoutManager(getBaseContext(),2);
        binding.recyclerWallet.setLayoutManager(layoutManager);
        binding.recyclerWallet.setAdapter(adapter);
    }
}