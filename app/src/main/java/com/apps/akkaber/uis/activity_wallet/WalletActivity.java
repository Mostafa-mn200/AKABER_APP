package com.apps.akkaber.uis.activity_wallet;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;

import com.apps.akkaber.R;
import com.apps.akkaber.adapter.WalletAdapter;
import com.apps.akkaber.databinding.ActivityWalletBinding;
import com.apps.akkaber.uis.activity_base.BaseActivity;
import com.apps.akkaber.uis.activity_share.ShareActivity;

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
        binding.lShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(WalletActivity.this, ShareActivity.class);
                startActivity(intent);
            }
        });
        binding.lShare.setPaintFlags(binding.lShare.getPaintFlags()| Paint.UNDERLINE_TEXT_FLAG);
    }
}