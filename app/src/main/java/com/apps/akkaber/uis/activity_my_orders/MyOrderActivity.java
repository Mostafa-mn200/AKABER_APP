package com.apps.akkaber.uis.activity_my_orders;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;

import com.apps.akkaber.R;
import com.apps.akkaber.adapter.OrdersAdapter;
import com.apps.akkaber.adapter.Product2Adapter;
import com.apps.akkaber.databinding.ActivityCategoryDetialsBinding;
import com.apps.akkaber.databinding.ActivityOrdersBinding;
import com.apps.akkaber.model.UserModel;
import com.apps.akkaber.mvvm.ActivityCategoryDetialsMvvm;
import com.apps.akkaber.mvvm.ActivityMyOrdersMvvm;
import com.apps.akkaber.preferences.Preferences;
import com.apps.akkaber.uis.activity_base.BaseActivity;
import com.apps.akkaber.uis.activity_order_detials.OrderDetialsActivity;

public class MyOrderActivity extends BaseActivity {
    private ActivityOrdersBinding binding;
    private ActivityMyOrdersMvvm activityMyOrdersMvvm;
    private UserModel userModel;
    private Preferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_orders);
        initView();

    }

    private void initView() {
        preferences = Preferences.getInstance();
        userModel = preferences.getUserData(this);
        activityMyOrdersMvvm = ViewModelProviders.of(this).get(ActivityMyOrdersMvvm.class);
      //  setUpToolbar(binding.toolbar, getString(R.string.contact_us), R.color.white, R.color.black);
binding.setLang(getLang());
        binding.recView.setLayoutManager(new GridLayoutManager(this,1));
        binding.recView.setAdapter(new OrdersAdapter(this));
        binding.llBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }


    public void show() {
        Intent intent=new Intent(this, OrderDetialsActivity.class);
        startActivity(intent);
    }
}