package com.apps.akkaber.uis.activity_favourite;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.apps.akkaber.R;
import com.apps.akkaber.adapter.FavouriteAdapter;
import com.apps.akkaber.databinding.ActivityFavouriteBinding;
import com.apps.akkaber.model.ProductModel;
import com.apps.akkaber.mvvm.ActivityFavouriteMvvm;
import com.apps.akkaber.uis.activity_base.BaseActivity;
import com.apps.akkaber.uis.activity_product_detials.ProductDetialsActivity;

import java.util.ArrayList;

public class FavouriteActivity extends BaseActivity {
    private ActivityFavouriteBinding binding;
    private ActivityFavouriteMvvm activityFavouriteMvvm;
    private FavouriteAdapter adapter;
    private ActivityResultLauncher<Intent> launcher;
    private int req = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_favourite);
        initView();
    }


    private void initView() {
        binding.setLang(getLang());
        activityFavouriteMvvm = ViewModelProviders.of(this).get(ActivityFavouriteMvvm.class);
        activityFavouriteMvvm.getIsLoading().observe(this, isLoading -> {
            if (isLoading) {
                binding.cardNoData.setVisibility(View.GONE);
            }
            binding.swipeRefresh.setRefreshing(isLoading);
        });


        activityFavouriteMvvm.getFavouriteList().observe(this, list -> {
            adapter.updateList(new ArrayList<>());

            if (list != null && list.size() > 0) {
                adapter.updateList(list);
                binding.cardNoData.setVisibility(View.GONE);
            } else {
                binding.cardNoData.setVisibility(View.VISIBLE);

            }
        });


        binding.swipeRefresh.setOnRefreshListener(() -> {
            activityFavouriteMvvm.getFavourites(getUserModel(), getLang());
        });

        binding.llBack.setOnClickListener(view -> finish());

        adapter = new FavouriteAdapter(this);
        LinearLayoutManager layoutManager = new GridLayoutManager(getBaseContext(), 2);
        binding.recyclerFavourite.setLayoutManager(layoutManager);
        binding.recyclerFavourite.setAdapter(adapter);
        activityFavouriteMvvm.getFavourites(getUserModel(), getLang());
        launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
            if (req == 2 && result.getResultCode() == Activity.RESULT_OK) {
                activityFavouriteMvvm.getFavourites(getUserModel(), getLang());
                setResult(RESULT_OK);

            }
        });

    }

    public void showdetials(ProductModel productModel) {
        req = 2;
        Intent intent = new Intent(this, ProductDetialsActivity.class);
        intent.putExtra("proid", productModel.getId());
        launcher.launch(intent);

    }
}