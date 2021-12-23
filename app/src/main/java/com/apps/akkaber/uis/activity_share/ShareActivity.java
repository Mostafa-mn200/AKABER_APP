package com.apps.akkaber.uis.activity_share;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;

import com.apps.akkaber.R;
import com.apps.akkaber.databinding.ActivityShareBinding;
import com.apps.akkaber.uis.activity_base.BaseActivity;

public class ShareActivity extends BaseActivity {
    ActivityShareBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_share);
        initView();
    }

    private void initView() {
        binding.llShare.setOnClickListener(view -> {
            String app_url = "https://play.google.com/store/apps/details?id=" + this.getPackageName();
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain");
            intent.putExtra(Intent.EXTRA_TITLE, "تطبيق اكابر للذبائح");
            intent.putExtra(Intent.EXTRA_TEXT, app_url);
            startActivity(intent);
        });
    }
}