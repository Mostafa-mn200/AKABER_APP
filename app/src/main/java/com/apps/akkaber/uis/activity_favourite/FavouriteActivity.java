package com.apps.akkaber.uis.activity_favourite;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.view.View;

import com.apps.akkaber.R;
import com.apps.akkaber.adapter.FavouriteAdapter;
import com.apps.akkaber.databinding.ActivityFavouriteBinding;
import com.apps.akkaber.model.FavouriteModel;
import com.apps.akkaber.mvvm.ActivityFavouriteMvvm;
import com.apps.akkaber.uis.activity_base.BaseActivity;

import java.util.List;

public class FavouriteActivity extends BaseActivity {
    ActivityFavouriteBinding binding;
    ActivityFavouriteMvvm activityFavouriteMvvm;
    FavouriteAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_favourite);
        initView();
    }

    private void initView() {
        activityFavouriteMvvm = ViewModelProviders.of(this).get(ActivityFavouriteMvvm.class);
        activityFavouriteMvvm.getIsLoading().observe(this, loading -> {
            binding.swipeRefresh.setRefreshing(loading);
        });

        activityFavouriteMvvm.getFavouriteList().observe(this, list -> {
            if (list.size() > 0) {
                adapter.updateList(list);
                binding.cardNoData.setVisibility(View.GONE);
            } else {
                binding.cardNoData.setVisibility(View.VISIBLE);

            }

        });

        binding.swipeRefresh.setOnRefreshListener(() -> {
            activityFavouriteMvvm.getFavourites(getUserModel(),getLang());
        });

        adapter = new FavouriteAdapter(this);
        LinearLayoutManager layoutManager = new GridLayoutManager(getBaseContext(), 2);
        binding.recyclerFavourite.setLayoutManager(layoutManager);
        binding.recyclerFavourite.setAdapter(adapter);
        activityFavouriteMvvm.getFavourites(getUserModel(),getLang());

    }
}