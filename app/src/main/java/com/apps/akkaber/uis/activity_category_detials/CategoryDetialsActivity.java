package com.apps.akkaber.uis.activity_category_detials;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.apps.akkaber.R;
import com.apps.akkaber.adapter.OffersAdapter;
import com.apps.akkaber.adapter.Product2Adapter;
import com.apps.akkaber.databinding.ActivityCategoryDetialsBinding;
import com.apps.akkaber.databinding.ActivityContactUsBinding;
import com.apps.akkaber.model.ContactUsModel;
import com.apps.akkaber.model.ProductModel;
import com.apps.akkaber.model.SingleDepartmentDataModel;
import com.apps.akkaber.model.UserModel;
import com.apps.akkaber.mvvm.ActivityCategoryDetialsMvvm;
import com.apps.akkaber.mvvm.ContactusActivityMvvm;
import com.apps.akkaber.preferences.Preferences;
import com.apps.akkaber.uis.activity_base.BaseActivity;
import com.apps.akkaber.uis.activity_product_detials.ProductDetialsActivity;

import java.util.ArrayList;
import java.util.List;

public class CategoryDetialsActivity extends BaseActivity {
    private ActivityCategoryDetialsBinding binding;
    private ActivityCategoryDetialsMvvm categoryDetialsMvvm;
    private UserModel userModel;
    private Preferences preferences;
    private String catid;
    private Product2Adapter product2Adapter;
    private ActivityResultLauncher<Intent> launcher;
    private int req = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_category_detials);
        getDataFromIntent();
        initView();

    }


    private void getDataFromIntent() {
        Intent intent = getIntent();
        catid = intent.getStringExtra("catid");

    }

    private void initView() {
        preferences = Preferences.getInstance();
        userModel = preferences.getUserData(this);
        categoryDetialsMvvm = ViewModelProviders.of(this).get(ActivityCategoryDetialsMvvm.class);
        categoryDetialsMvvm.getIsLoading().observe(this, isLoading -> {
            if (isLoading) {
                // binding.cardNoData.setVisibility(View.GONE);
                binding.progBar.setVisibility(View.VISIBLE);
                binding.nested.setVisibility(View.GONE);
            }
            // binding.swipeRefresh.setRefreshing(isLoading);
        });
        categoryDetialsMvvm.getCategoryData().observe(this, new Observer<SingleDepartmentDataModel>() {
            @Override
            public void onChanged(SingleDepartmentDataModel singleDepartmentDataModel) {
                binding.progBar.setVisibility(View.GONE);
                binding.nested.setVisibility(View.VISIBLE);
                if (singleDepartmentDataModel.getData() != null) {
                    binding.setModel(singleDepartmentDataModel.getData());
                    if (singleDepartmentDataModel.getData().getProducts() != null && singleDepartmentDataModel.getData().getProducts().size() > 0) {
                        product2Adapter.updateList(singleDepartmentDataModel.getData().getProducts());
                        binding.cardNoData.setVisibility(View.GONE);
                    } else {
                        binding.cardNoData.setVisibility(View.VISIBLE);

                    }
                }
            }
        });
        //  setUpToolbar(binding.toolbar, getString(R.string.contact_us), R.color.white, R.color.black);
        binding.setLang(getLang());

        product2Adapter = new Product2Adapter(this);
        binding.recView.setLayoutManager(new GridLayoutManager(this, 2));
        binding.recView.setAdapter(product2Adapter);
        binding.llBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        categoryDetialsMvvm.getDepartmentDetials(getLang(), catid);
        launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
            if (req == 2 && result.getResultCode() == Activity.RESULT_OK) {
                setResult(RESULT_OK);
            }
        });
    }


    public void showProductDetials(String productid) {
        req = 2;
        Intent intent = new Intent(this, ProductDetialsActivity.class);
        intent.putExtra("proid", productid);
        launcher.launch(intent);
    }
}