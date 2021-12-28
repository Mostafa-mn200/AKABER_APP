package com.apps.akkaber.uis.activity_product_detials;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.apps.akkaber.R;
import com.apps.akkaber.adapter.SizeAdapter;
import com.apps.akkaber.adapter.TypeAdapter;
import com.apps.akkaber.databinding.ActivityProductDetialsBinding;
import com.apps.akkaber.model.SingleProductDataModel;
import com.apps.akkaber.model.SizeModel;
import com.apps.akkaber.model.TypeModel;
import com.apps.akkaber.model.UserModel;
import com.apps.akkaber.mvvm.ActivityProductDetialsMvvm;
import com.apps.akkaber.preferences.Preferences;
import com.apps.akkaber.uis.activity_base.BaseActivity;

public class ProductDetialsActivity extends BaseActivity {
    private ActivityProductDetialsBinding binding;
    private ActivityProductDetialsMvvm activityProductDetialsMvvm;
    private UserModel userModel;
    private Preferences preferences;
    private String proid;
    private TypeAdapter typeAdapter;
    private String user_id = null;
    private SizeAdapter sizeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_product_detials);
        getDataFromIntent();
        initView();

    }

    private void getDataFromIntent() {
        Intent intent = getIntent();
        proid = intent.getStringExtra("proid");

    }

    private void initView() {
        binding.priceOld.setPaintFlags(binding.priceOld.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        binding.amountOld.setPaintFlags(binding.amountOld.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        binding.priceOldtype.setPaintFlags(binding.priceOldtype.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        binding.amountOldtype.setPaintFlags(binding.amountOldtype.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        preferences = Preferences.getInstance();
        userModel = preferences.getUserData(this);
        if (userModel != null) {
            user_id = userModel.getData().getId() + "";
        }
        binding.setUserModel(userModel);
        activityProductDetialsMvvm = ViewModelProviders.of(this).get(ActivityProductDetialsMvvm.class);
        activityProductDetialsMvvm.getIsLoading().observe(this, isLoading -> {
            if (isLoading) {
                // binding.cardNoData.setVisibility(View.GONE);
                binding.progBar.setVisibility(View.VISIBLE);
                binding.nested.setVisibility(View.GONE);
            }
            // binding.swipeRefresh.setRefreshing(isLoading);
        });
        activityProductDetialsMvvm.getProductData().observe(this, new Observer<SingleProductDataModel>() {
            @Override
            public void onChanged(SingleProductDataModel singleProductDataModel) {
                binding.progBar.setVisibility(View.GONE);
                binding.nested.setVisibility(View.VISIBLE);
                if (singleProductDataModel.getData() != null) {
                    binding.setModel(singleProductDataModel.getData());
                    if (singleProductDataModel.getData().getTypes() != null && singleProductDataModel.getData().getTypes().size() > 0) {
                        TypeModel typeModel = singleProductDataModel.getData().getTypes().get(0);
                        typeModel.setIsselected(true);
                        singleProductDataModel.getData().getTypes().set(0, typeModel);
                        binding.setTypeModel(typeModel);
                        typeAdapter.updateData(singleProductDataModel.getData().getTypes());
                        if (typeModel.getPrice().equals("0")) {
                            SizeModel sizeModel = typeModel.getSizes().get(0);
                            sizeModel.setIsselected(true);
                            typeModel.getSizes().set(0, sizeModel);
                            sizeAdapter.updateData(typeModel.getSizes());
                        }
                        //binding.cardNoData.setVisibility(View.GONE);
                    }
                }
            }
        });
        //  setUpToolbar(binding.toolbar, getString(R.string.contact_us), R.color.white, R.color.black);
        binding.setLang(getLang());

        typeAdapter = new TypeAdapter(this);
        binding.recViewAges.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        binding.recViewAges.setAdapter(typeAdapter);
        sizeAdapter = new SizeAdapter(this);
        binding.recViewSizes.setLayoutManager(new LinearLayoutManager(this));
        binding.recViewSizes.setAdapter(sizeAdapter);
        binding.llBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        activityProductDetialsMvvm.getProductDetials(getLang(), proid, user_id);
    }


    public void showSizes(TypeModel currentModel) {
        binding.setTypeModel(currentModel);
        if (currentModel.getPrice().equals("0")) {

            sizeAdapter.updateData(currentModel.getSizes());
        }
    }
}