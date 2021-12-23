package com.apps.akkaber.uis.activity_order_detials;

import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;

import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.apps.akkaber.R;
import com.apps.akkaber.adapter.NotificationAdapter;
import com.apps.akkaber.databinding.ActivityNotificationBinding;
import com.apps.akkaber.databinding.ActivityOrderDetialsBinding;
import com.apps.akkaber.mvvm.ActivityNotificationMvvm;
import com.apps.akkaber.mvvm.ActivityOrderDetialsMvvm;
import com.apps.akkaber.uis.activity_base.BaseActivity;

public class OrderDetialsActivity extends BaseActivity {

    private ActivityOrderDetialsBinding binding;
    private ActivityOrderDetialsMvvm activityOrderDetialsMvvm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_order_detials);
        initView();


    }


    private void initView() {
        binding.setLang(getLang());
        activityOrderDetialsMvvm = ViewModelProviders.of(this).get(ActivityOrderDetialsMvvm.class);
        activityOrderDetialsMvvm.getIsLoading().observe(this, loading -> {

        });
        binding.llBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        updateUi1();

    }

    private void updateUi1() {
        binding.tv1.setTextColor(getResources().getColor(R.color.color2));
        binding.tv2.setTextColor(getResources().getColor(R.color.gray11));
        binding.tv3.setTextColor(getResources().getColor(R.color.gray11));
        binding.tv4.setTextColor(getResources().getColor(R.color.gray11));
        binding.tv5.setTextColor(getResources().getColor(R.color.gray11));
        binding.tvOrder1.setVisibility(View.VISIBLE);
        binding.tvOrder2.setVisibility(View.GONE);
        binding.tvOrder3.setVisibility(View.GONE);
        binding.tvOrder4.setVisibility(View.GONE);
        binding.tvOrder5.setVisibility(View.GONE);
        binding.image1.setBackground(getResources().getDrawable(R.drawable.circle_color2));
        binding.image2.setBackground(getResources().getDrawable(R.drawable.circle_not_bg));
        binding.image3.setBackground(getResources().getDrawable(R.drawable.circle_not_bg));
        binding.image4.setBackground(getResources().getDrawable(R.drawable.circle_not_bg));
        binding.image5.setBackground(getResources().getDrawable(R.drawable.circle_not_bg));

        binding.image1.setColorFilter(ContextCompat.getColor(this, R.color.color2), PorterDuff.Mode.SRC_IN);
        binding.image2.setColorFilter(ContextCompat.getColor(this, R.color.gray11), PorterDuff.Mode.SRC_IN);
        binding.image3.setColorFilter(ContextCompat.getColor(this, R.color.gray11), PorterDuff.Mode.SRC_IN);
        binding.image4.setColorFilter(ContextCompat.getColor(this, R.color.gray11), PorterDuff.Mode.SRC_IN);
        binding.image5.setColorFilter(ContextCompat.getColor(this, R.color.gray11), PorterDuff.Mode.SRC_IN);
        binding.view11.setBackgroundColor(getResources().getColor(R.color.color2));
        binding.view1.setBackgroundColor(getResources().getColor(R.color.gray13));
        binding.view21.setBackgroundColor(getResources().getColor(R.color.gray13));
        binding.view2.setBackgroundColor(getResources().getColor(R.color.gray13));
        binding.view31.setBackgroundColor(getResources().getColor(R.color.gray13));
        binding.view3.setBackgroundColor(getResources().getColor(R.color.gray13));
        binding.view41.setBackgroundColor(getResources().getColor(R.color.gray13));
        binding.view4.setBackgroundColor(getResources().getColor(R.color.gray13));

    }

    private void updateUi2() {
        binding.tv1.setTextColor(getResources().getColor(R.color.gray11));
        binding.tv2.setTextColor(getResources().getColor(R.color.color2));
        binding.tv3.setTextColor(getResources().getColor(R.color.gray11));
        binding.tv4.setTextColor(getResources().getColor(R.color.gray11));
        binding.tv5.setTextColor(getResources().getColor(R.color.gray11));
        binding.tvOrder1.setVisibility(View.VISIBLE);
        binding.tvOrder2.setVisibility(View.VISIBLE);
        binding.tvOrder3.setVisibility(View.GONE);
        binding.tvOrder4.setVisibility(View.GONE);
        binding.tvOrder5.setVisibility(View.GONE);
        binding.image1.setBackground(getResources().getDrawable(R.drawable.circle_color2));
        binding.image2.setBackground(getResources().getDrawable(R.drawable.circle_color2));
        binding.image3.setBackground(getResources().getDrawable(R.drawable.circle_not_bg));
        binding.image4.setBackground(getResources().getDrawable(R.drawable.circle_not_bg));
        binding.image5.setBackground(getResources().getDrawable(R.drawable.circle_not_bg));
        binding.image1.setColorFilter(ContextCompat.getColor(this, R.color.color2), PorterDuff.Mode.SRC_IN);
        binding.image2.setColorFilter(ContextCompat.getColor(this, R.color.color2), PorterDuff.Mode.SRC_IN);
        binding.image3.setColorFilter(ContextCompat.getColor(this, R.color.gray11), PorterDuff.Mode.SRC_IN);
        binding.image4.setColorFilter(ContextCompat.getColor(this, R.color.gray11), PorterDuff.Mode.SRC_IN);
        binding.image5.setColorFilter(ContextCompat.getColor(this, R.color.gray11), PorterDuff.Mode.SRC_IN);
        binding.view11.setBackgroundColor(getResources().getColor(R.color.color2));
        binding.view1.setBackgroundColor(getResources().getColor(R.color.color2));
        binding.view21.setBackgroundColor(getResources().getColor(R.color.color2));
        binding.view2.setBackgroundColor(getResources().getColor(R.color.gray13));
        binding.view31.setBackgroundColor(getResources().getColor(R.color.gray13));
        binding.view3.setBackgroundColor(getResources().getColor(R.color.gray13));
        binding.view41.setBackgroundColor(getResources().getColor(R.color.gray13));
        binding.view4.setBackgroundColor(getResources().getColor(R.color.gray13));


    }

    private void updateUi3() {
        binding.tv1.setTextColor(getResources().getColor(R.color.gray11));
        binding.tv2.setTextColor(getResources().getColor(R.color.gray11));
        binding.tv3.setTextColor(getResources().getColor(R.color.color2));
        binding.tv4.setTextColor(getResources().getColor(R.color.gray11));
        binding.tv5.setTextColor(getResources().getColor(R.color.gray11));
        binding.tvOrder1.setVisibility(View.VISIBLE);
        binding.tvOrder2.setVisibility(View.VISIBLE);
        binding.tvOrder3.setVisibility(View.VISIBLE);
        binding.tvOrder4.setVisibility(View.GONE);
        binding.tvOrder5.setVisibility(View.GONE);
        binding.image1.setBackground(getResources().getDrawable(R.drawable.circle_color2));
        binding.image2.setBackground(getResources().getDrawable(R.drawable.circle_color2));
        binding.image3.setBackground(getResources().getDrawable(R.drawable.circle_color2));
        binding.image4.setBackground(getResources().getDrawable(R.drawable.circle_not_bg));
        binding.image5.setBackground(getResources().getDrawable(R.drawable.circle_not_bg));
        binding.image1.setColorFilter(ContextCompat.getColor(this, R.color.color2), PorterDuff.Mode.SRC_IN);
        binding.image2.setColorFilter(ContextCompat.getColor(this, R.color.color2), PorterDuff.Mode.SRC_IN);
        binding.image3.setColorFilter(ContextCompat.getColor(this, R.color.color2), PorterDuff.Mode.SRC_IN);
        binding.image4.setColorFilter(ContextCompat.getColor(this, R.color.gray11), PorterDuff.Mode.SRC_IN);
        binding.image5.setColorFilter(ContextCompat.getColor(this, R.color.gray11), PorterDuff.Mode.SRC_IN);
        binding.view11.setBackgroundColor(getResources().getColor(R.color.color2));
        binding.view1.setBackgroundColor(getResources().getColor(R.color.color2));
        binding.view21.setBackgroundColor(getResources().getColor(R.color.color2));
        binding.view2.setBackgroundColor(getResources().getColor(R.color.color2));
        binding.view31.setBackgroundColor(getResources().getColor(R.color.color2));
        binding.view3.setBackgroundColor(getResources().getColor(R.color.gray13));
        binding.view41.setBackgroundColor(getResources().getColor(R.color.gray13));
        binding.view4.setBackgroundColor(getResources().getColor(R.color.gray13));


    }

    private void updateUi4() {
        binding.tv1.setTextColor(getResources().getColor(R.color.gray11));
        binding.tv2.setTextColor(getResources().getColor(R.color.gray11));
        binding.tv3.setTextColor(getResources().getColor(R.color.gray11));
        binding.tv4.setTextColor(getResources().getColor(R.color.color2));
        binding.tv5.setTextColor(getResources().getColor(R.color.gray11));
        binding.tvOrder1.setVisibility(View.VISIBLE);
        binding.tvOrder2.setVisibility(View.VISIBLE);
        binding.tvOrder3.setVisibility(View.VISIBLE);
        binding.tvOrder4.setVisibility(View.VISIBLE);
        binding.tvOrder5.setVisibility(View.GONE);
        binding.image1.setBackground(getResources().getDrawable(R.drawable.circle_color2));
        binding.image2.setBackground(getResources().getDrawable(R.drawable.circle_color2));
        binding.image3.setBackground(getResources().getDrawable(R.drawable.circle_color2));
        binding.image4.setBackground(getResources().getDrawable(R.drawable.circle_color2));
        binding.image5.setBackground(getResources().getDrawable(R.drawable.circle_not_bg));
        binding.image1.setColorFilter(ContextCompat.getColor(this, R.color.color2), PorterDuff.Mode.SRC_IN);
        binding.image2.setColorFilter(ContextCompat.getColor(this, R.color.color2), PorterDuff.Mode.SRC_IN);
        binding.image3.setColorFilter(ContextCompat.getColor(this, R.color.color2), PorterDuff.Mode.SRC_IN);
        binding.image4.setColorFilter(ContextCompat.getColor(this, R.color.color2), PorterDuff.Mode.SRC_IN);
        binding.image5.setColorFilter(ContextCompat.getColor(this, R.color.gray11), PorterDuff.Mode.SRC_IN);
        binding.view11.setBackgroundColor(getResources().getColor(R.color.color2));
        binding.view1.setBackgroundColor(getResources().getColor(R.color.color2));
        binding.view21.setBackgroundColor(getResources().getColor(R.color.color2));
        binding.view2.setBackgroundColor(getResources().getColor(R.color.color2));
        binding.view31.setBackgroundColor(getResources().getColor(R.color.color2));
        binding.view3.setBackgroundColor(getResources().getColor(R.color.color2));
        binding.view41.setBackgroundColor(getResources().getColor(R.color.color2));
        binding.view4.setBackgroundColor(getResources().getColor(R.color.gray13));


    }

    private void updateUi5() {
        binding.tv1.setTextColor(getResources().getColor(R.color.gray11));
        binding.tv2.setTextColor(getResources().getColor(R.color.gray11));
        binding.tv3.setTextColor(getResources().getColor(R.color.gray11));
        binding.tv4.setTextColor(getResources().getColor(R.color.gray11));
        binding.tv5.setTextColor(getResources().getColor(R.color.color2));
        binding.tvOrder1.setVisibility(View.VISIBLE);
        binding.tvOrder2.setVisibility(View.VISIBLE);
        binding.tvOrder3.setVisibility(View.VISIBLE);
        binding.tvOrder4.setVisibility(View.VISIBLE);
        binding.tvOrder5.setVisibility(View.VISIBLE);
        binding.image1.setBackground(getResources().getDrawable(R.drawable.circle_color2));
        binding.image2.setBackground(getResources().getDrawable(R.drawable.circle_color2));
        binding.image3.setBackground(getResources().getDrawable(R.drawable.circle_color2));
        binding.image4.setBackground(getResources().getDrawable(R.drawable.circle_color2));
        binding.image5.setBackground(getResources().getDrawable(R.drawable.circle_color2));
        binding.image1.setColorFilter(ContextCompat.getColor(this, R.color.color2), PorterDuff.Mode.SRC_IN);
        binding.image2.setColorFilter(ContextCompat.getColor(this, R.color.color2), PorterDuff.Mode.SRC_IN);
        binding.image3.setColorFilter(ContextCompat.getColor(this, R.color.color2), PorterDuff.Mode.SRC_IN);
        binding.image4.setColorFilter(ContextCompat.getColor(this, R.color.color2), PorterDuff.Mode.SRC_IN);
        binding.image5.setColorFilter(ContextCompat.getColor(this, R.color.color2), PorterDuff.Mode.SRC_IN);
        binding.view11.setBackgroundColor(getResources().getColor(R.color.color2));
        binding.view1.setBackgroundColor(getResources().getColor(R.color.color2));
        binding.view21.setBackgroundColor(getResources().getColor(R.color.color2));
        binding.view2.setBackgroundColor(getResources().getColor(R.color.color2));
        binding.view31.setBackgroundColor(getResources().getColor(R.color.color2));
        binding.view3.setBackgroundColor(getResources().getColor(R.color.color2));
        binding.view41.setBackgroundColor(getResources().getColor(R.color.color2));
        binding.view4.setBackgroundColor(getResources().getColor(R.color.color2));


    }
}