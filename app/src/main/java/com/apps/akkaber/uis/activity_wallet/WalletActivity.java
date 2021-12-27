package com.apps.akkaber.uis.activity_wallet;

import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;

import com.apps.akkaber.R;
import com.apps.akkaber.databinding.ActivityWalletBinding;
import com.apps.akkaber.uis.activity_base.BaseActivity;
import com.apps.akkaber.uis.activity_share.ShareActivity;

public class WalletActivity extends BaseActivity {
    private ActivityWalletBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_wallet);
        initView();
    }

    private void initView() {

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