package com.apps.akkaber.uis.activity_contact_us;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.apps.akkaber.R;
import com.apps.akkaber.databinding.ActivityContactUsBinding;
import com.apps.akkaber.model.ContactUsModel;
import com.apps.akkaber.model.SettingModel;
import com.apps.akkaber.model.UserModel;
import com.apps.akkaber.mvvm.ContactusActivityMvvm;
import com.apps.akkaber.preferences.Preferences;
import com.apps.akkaber.uis.activity_base.BaseActivity;

import java.util.List;

public class ContactUsActivity extends BaseActivity {
    private ActivityContactUsBinding binding;
    private ContactUsModel contactUsModel;
    private ContactusActivityMvvm contactusActivityMvvm;
    private UserModel userModel;
    private SettingModel settingModel;
    private Preferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_contact_us);
        initView();

    }

    private void initView() {
        binding.setLang(getLang());

        preferences = Preferences.getInstance();
        userModel = preferences.getUserData(this);
        binding.setSettingModel(settingModel);
        contactusActivityMvvm = ViewModelProviders.of(this).get(ContactusActivityMvvm.class);
//         setUpToolbar(binding.toolbar, getString(R.string.contact_us), R.color.white, R.color.black);
        contactusActivityMvvm.getIsLoading().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean) {
                    binding.progBar.setVisibility(View.VISIBLE);
                    binding.llContactus.setVisibility(View.INVISIBLE);
                } else {
                    binding.progBar.setVisibility(View.GONE);
                    binding.llContactus.setVisibility(View.VISIBLE);
                }
            }
        });
        contactusActivityMvvm.getMutableLiveData().observe(this, settingModels -> {
            settingModel = settingModels;
            binding.setSettingModel(settingModel);
        });

        contactusActivityMvvm.getSetting(getLang());


        contactUsModel = new ContactUsModel();
        if (userModel != null) {
            contactUsModel.setName(userModel.getData().getFirst_name() + " " + userModel.getData().getLast_name());

        }
        binding.setContactUsModel(contactUsModel);

        binding.btnSend.setOnClickListener(view -> {
            if (contactUsModel.isDataValid(this)) {
                contactusActivityMvvm.contactus(this, contactUsModel);
            }
        });
        contactusActivityMvvm.send.observe(this, aBoolean -> {
            if (aBoolean) {
                Toast.makeText(ContactUsActivity.this, getResources().getString(R.string.suc), Toast.LENGTH_LONG).show();
                finish();
            }
        });
        binding.llBack.setOnClickListener(view -> finish());

    }


}