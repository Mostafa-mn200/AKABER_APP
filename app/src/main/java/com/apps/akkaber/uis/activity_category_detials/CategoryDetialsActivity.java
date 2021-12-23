package com.apps.akkaber.uis.activity_category_detials;

import android.os.Bundle;
import android.widget.Toast;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.apps.akkaber.R;
import com.apps.akkaber.adapter.OffersAdapter;
import com.apps.akkaber.adapter.Product2Adapter;
import com.apps.akkaber.databinding.ActivityCategoryDetialsBinding;
import com.apps.akkaber.databinding.ActivityContactUsBinding;
import com.apps.akkaber.model.ContactUsModel;
import com.apps.akkaber.model.UserModel;
import com.apps.akkaber.mvvm.ActivityCategoryDetialsMvvm;
import com.apps.akkaber.mvvm.ContactusActivityMvvm;
import com.apps.akkaber.preferences.Preferences;
import com.apps.akkaber.uis.activity_base.BaseActivity;

public class CategoryDetialsActivity extends BaseActivity {
    private ActivityCategoryDetialsBinding binding;
    private ActivityCategoryDetialsMvvm categoryDetialsMvvm;
    private UserModel userModel;
    private Preferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_category_detials);
        initView();

    }

    private void initView() {
        preferences = Preferences.getInstance();
        userModel = preferences.getUserData(this);
        categoryDetialsMvvm = ViewModelProviders.of(this).get(ActivityCategoryDetialsMvvm.class);
      //  setUpToolbar(binding.toolbar, getString(R.string.contact_us), R.color.white, R.color.black);

        binding.recView.setLayoutManager(new GridLayoutManager(this,2));
        binding.recView.setAdapter(new Product2Adapter(this));
    }


}