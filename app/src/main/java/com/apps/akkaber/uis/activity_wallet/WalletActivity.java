package com.apps.akkaber.uis.activity_wallet;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;

import com.apps.akkaber.R;
import com.apps.akkaber.databinding.ActivityWalletBinding;
import com.apps.akkaber.model.OrderModel;
import com.apps.akkaber.model.UserModel;
import com.apps.akkaber.mvvm.ActivityMyOrdersMvvm;
import com.apps.akkaber.mvvm.ActivityWalletMvvm;
import com.apps.akkaber.preferences.Preferences;
import com.apps.akkaber.uis.activity_base.BaseActivity;
import com.apps.akkaber.uis.activity_share.ShareActivity;

import java.util.List;

public class WalletActivity extends BaseActivity {
    private ActivityWalletBinding binding;
    private ActivityWalletMvvm activityWalletMvvm;
    private Preferences preferences;
    private UserModel userModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_wallet);
        initView();
    }


    private void initView() {
        preferences=Preferences.getInstance();
        userModel=getUserModel();
        activityWalletMvvm = ViewModelProviders.of(this).get(ActivityWalletMvvm.class);
        //  setUpToolbar(binding.toolbar, getString(R.string.contact_us), R.color.white, R.color.black);
        binding.setLang(getLang());
        activityWalletMvvm.getIsLoading().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean) {
                    binding.progBar.setVisibility(View.VISIBLE);
                    binding.fl.setVisibility(View.GONE);
                } else {
                    binding.progBar.setVisibility(View.GONE);
                    binding.fl.setVisibility(View.VISIBLE);
                }
            }
        });
        activityWalletMvvm.userModelMutableLiveData.observe(this, new Observer<UserModel>() {
            @Override
            public void onChanged(UserModel userModel) {
                binding.setModel(userModel);
            }
        });
        activityWalletMvvm.getProfile(userModel.getData().getId()+"");
        binding.lShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(WalletActivity.this, ShareActivity.class);
                startActivity(intent);
            }
        });
        binding.lShare.setPaintFlags(binding.lShare.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        binding.llBack.setOnClickListener(view -> finish());
    }
}