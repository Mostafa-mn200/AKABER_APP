package com.apps.akkaber.uis.activity_favourite;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.View;

import com.apps.akkaber.R;
import com.apps.akkaber.adapter.FavouriteAdapter;
import com.apps.akkaber.databinding.ActivityFavouriteBinding;
import com.apps.akkaber.mvvm.ActivityFavouriteMvvm;
import com.apps.akkaber.uis.activity_base.BaseActivity;

public class FavouriteActivity extends BaseActivity {
    private ActivityFavouriteBinding binding;
    private ActivityFavouriteMvvm activityFavouriteMvvm;
    private FavouriteAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_favourite);
        initView();
    }

    private void initView() {
        activityFavouriteMvvm = ViewModelProviders.of(this).get(ActivityFavouriteMvvm.class);
        activityFavouriteMvvm.getIsLoading().observe(this, isLoading -> {
            if (isLoading) {
                binding.cardNoData.setVisibility(View.GONE);
            }
            binding.swipeRefresh.setRefreshing(isLoading);
        });


        activityFavouriteMvvm.getFavouriteList().observe(this, list -> {
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

    }
}