package com.apps.akkaber.uis.activity_share;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.apps.akkaber.R;
import com.apps.akkaber.databinding.ActivityShareBinding;
import com.apps.akkaber.model.UserModel;
import com.apps.akkaber.preferences.Preferences;
import com.apps.akkaber.uis.activity_base.BaseActivity;
import com.apps.akkaber.uis.activity_login.LoginActivity;

public class ShareActivity extends BaseActivity {
    private ActivityShareBinding binding;
    private Preferences preferences;
    private UserModel userModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_share);
        initView();
    }

    private void initView() {
        preferences = Preferences.getInstance();
        userModel = preferences.getUserData(this);
        if (userModel != null) {
            binding.setModel(userModel);
        }
        binding.llShare.setOnClickListener(view -> {
            String app_url = "https://play.google.com/store/apps/details?id=" + this.getPackageName();
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain");
            intent.putExtra(Intent.EXTRA_TITLE, "تطبيق اكابر للذبائح");
            intent.putExtra(Intent.EXTRA_TEXT, app_url);
            startActivity(intent);
        });

        binding.llBack.setOnClickListener(view -> finish());
    }

    private void navigateToLoginActivity() {
        Intent intent = new Intent(ShareActivity.this, LoginActivity.class);
        startActivity(intent);
    }
}