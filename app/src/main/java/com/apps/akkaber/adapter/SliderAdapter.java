package com.apps.akkaber.adapter;

import android.content.Context;
import android.net.Uri;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.viewpager.widget.PagerAdapter;


import com.apps.akkaber.R;
import com.apps.akkaber.databinding.SliderBinding;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SliderAdapter  extends PagerAdapter {


    Integer[] IMAGES={R.drawable.lamb,R.drawable.slider};
    private LayoutInflater inflater;
    Context context;

    public SliderAdapter(Context context) {
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return IMAGES.length;
    }

    @Override
    public Object instantiateItem(ViewGroup view, int position) {
        SliderBinding rowBinding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.slider,view,false);
        view.addView(rowBinding.getRoot());
        rowBinding.sliderImage.setImageResource(IMAGES[position]);
        return rowBinding.getRoot();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }


}