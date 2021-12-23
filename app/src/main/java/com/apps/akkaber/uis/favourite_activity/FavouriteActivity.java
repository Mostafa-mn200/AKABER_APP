package com.apps.akkaber.uis.favourite_activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;

import com.apps.akkaber.R;
import com.apps.akkaber.adapter.FavouriteAdapter;
import com.apps.akkaber.databinding.ActivityFavouriteBinding;

public class FavouriteActivity extends AppCompatActivity {
    ActivityFavouriteBinding binding;
    FavouriteAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_favourite);
        initVeiw();
    }

    private void initVeiw() {
        adapter=new FavouriteAdapter(this);
        LinearLayoutManager layoutManager=new GridLayoutManager(getBaseContext(),2);
        binding.recyclerFavourite.setLayoutManager(layoutManager);
        binding.recyclerFavourite.setAdapter(adapter);
    }
}