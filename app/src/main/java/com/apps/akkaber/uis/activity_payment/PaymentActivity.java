package com.apps.akkaber.uis.activity_payment;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.apps.akkaber.R;
import com.apps.akkaber.adapter.OrderProductAdapter;
import com.apps.akkaber.databinding.ActivityPaymentBinding;
import com.apps.akkaber.model.CartDataModel;
import com.apps.akkaber.model.ItemCartModel;
import com.apps.akkaber.model.LocationModel;
import com.apps.akkaber.model.UserModel;
import com.apps.akkaber.mvvm.ActivityPaymentMvvm;
import com.apps.akkaber.preferences.Preferences;
import com.apps.akkaber.uis.activity_base.BaseActivity;
import com.apps.akkaber.uis.activity_map.MapActivity;
import com.apps.akkaber.uis.activity_my_orders.MyOrderActivity;

import java.util.ArrayList;
import java.util.List;

public class PaymentActivity extends BaseActivity {
    private ActivityPaymentBinding binding;
    private String lang;
    private LinearLayoutManager manager;
    private UserModel userModel;
    private Preferences preferences;
    private List<ItemCartModel> itemCartModelList;
    private CartDataModel cartDataModel;
    private OrderProductAdapter orderProductAdapter;
    private double total;
    private ActivityResultLauncher<Intent> launcher;
    private int req = 0;
    private ActivityPaymentMvvm activityPaymentMvvm;
    private ActivityResultLauncher<String> permissionLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_payment);
        initView();

    }


    private void initView() {
        itemCartModelList = new ArrayList<>();
        manager = new GridLayoutManager(this, 1);
        binding.recView.setLayoutManager(manager);

        orderProductAdapter = new OrderProductAdapter(itemCartModelList, this);
        binding.recView.setAdapter(orderProductAdapter);

        binding.setLang(getLang());
        preferences = Preferences.getInstance();
        userModel = getUserModel();
        activityPaymentMvvm = ViewModelProviders.of(this).get(ActivityPaymentMvvm.class);
        checkdata();
        cartDataModel.setPay("cash");
        activityPaymentMvvm.setContext(this);
        activityPaymentMvvm.setLang(getLang());
        activityPaymentMvvm.getLocationData().observe(this, locationModel -> {
            cartDataModel.setAddress(locationModel.getAddress());
            cartDataModel.setLatitude(locationModel.getLat());
            cartDataModel.setLongitude(locationModel.getLng());

            binding.setLocationModel(locationModel);
            activityPaymentMvvm.getShip(locationModel.getLat(), locationModel.getLng());

        });
        activityPaymentMvvm.getSend().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean) {
                    preferences.clearCart(PaymentActivity.this);
                    Intent intent = new Intent(PaymentActivity.this, MyOrderActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(PaymentActivity.this, getResources().getString(R.string.wallet_not), Toast.LENGTH_LONG).show();
                }
            }
        });
        activityPaymentMvvm.getShip().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                if (s != null && !s.isEmpty()) {
                    cartDataModel.setShipping(Double.parseDouble(s));
                    cartDataModel.setTotal(cartDataModel.getSub_total() + cartDataModel.getShipping());
                    binding.setModel(cartDataModel);
                }
            }
        });

        launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
            if (req == 1 && result.getResultCode() == Activity.RESULT_OK) {
                Intent intent = result.getData();
                LocationModel locationModel = (LocationModel) intent.getSerializableExtra("location");
                cartDataModel.setAddress(locationModel.getAddress());
                cartDataModel.setLatitude(locationModel.getLat());
                cartDataModel.setLongitude(locationModel.getLng());
                binding.setLocationModel(locationModel);
                activityPaymentMvvm.getShip(locationModel.getLat(), locationModel.getLng());
                req = 0;
            }
        });
        permissionLauncher = registerForActivityResult(new ActivityResultContracts.RequestPermission(), isGranted -> {
            if (isGranted) {
                if (req == 1) {
                    navigatetomapActivity();
                } else {
                    activityPaymentMvvm.initGoogleApi();
                }
            } else {
                Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show();

            }
        });


        binding.llBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                back();
            }
        });
        binding.radiocash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cartDataModel.setPay("cash");
                binding.radioonline.setChecked(false);
                binding.radiowallet.setChecked(false);

            }
        });
        binding.radioonline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cartDataModel.setPay("online");
                binding.radiocash.setChecked(false);
                binding.radiowallet.setChecked(false);

            }
        });
        binding.radiowallet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cartDataModel.setPay("wallet");
                binding.radiocash.setChecked(false);
                binding.radioonline.setChecked(false);

            }
        });
        binding.btChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                req = 1;
                checkPermission();
            }
        });
        binding.btnComplete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cartDataModel.getAddress().isEmpty()) {
                    Toast.makeText(PaymentActivity.this, getResources().getString(R.string.ch_address), Toast.LENGTH_LONG).show();
                } else {
                    cartDataModel.setUser_id(userModel.getData().getId());
                    activityPaymentMvvm.sendOrder(cartDataModel, userModel);
                }
            }
        });
        checkPermission();
    }

    private void checkPermission() {
        if (ActivityCompat.checkSelfPermission(this, BaseActivity.fineLocPerm) != PackageManager.PERMISSION_GRANTED) {
            permissionLauncher.launch(BaseActivity.fineLocPerm);
        } else {
            if (req == 1) {
                navigatetomapActivity();
            } else {
                activityPaymentMvvm.initGoogleApi();
            }
        }
    }

    public void navigatetomapActivity() {

        Intent intent = new Intent(PaymentActivity.this, MapActivity.class);
        launcher.launch(intent);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 100 && resultCode == Activity.RESULT_OK) {

            activityPaymentMvvm.startLocationUpdate();

        }
    }


    @Override
    public void onBackPressed() {
        back();
    }


    public void back() {

        finish();
    }


    private void checkdata() {
        if (preferences != null) {
            cartDataModel = preferences.getCartData(this);
            if (cartDataModel != null) {
                itemCartModelList.clear();
                itemCartModelList.addAll(cartDataModel.getDetails());
                orderProductAdapter.notifyDataSetChanged();

                binding.setModel(cartDataModel);
            }
        }
    }


}
