package com.apps.akkaber.uis.activity_home.fragments_home_navigaion;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.apps.akkaber.R;

import com.apps.akkaber.adapter.DepartmentAdapter;
import com.apps.akkaber.adapter.ProductAdapter;
import com.apps.akkaber.adapter.OffersAdapter;
import com.apps.akkaber.adapter.SliderAdapter;
import com.apps.akkaber.mvvm.FragmentHomeMvvm;
import com.apps.akkaber.uis.activity_base.BaseFragment;
import com.apps.akkaber.databinding.FragmentHomeBinding;
import com.apps.akkaber.uis.activity_home.HomeActivity;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


public class FragmentHome extends BaseFragment {
    private static final String TAG = FragmentHome.class.getName();
    private HomeActivity activity;
    private FragmentHomeBinding binding;
    private FragmentHomeMvvm fragmentHomeMvvm;
    private DepartmentAdapter departmentAdapter;
    private OffersAdapter offersAdapter;
    private ProductAdapter productAdapter;
    private SliderAdapter sliderAdapter;
    private CompositeDisposable disposable = new CompositeDisposable();

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        activity = (HomeActivity) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Observable.timer(130, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        disposable.add(d);
                    }

                    @Override
                    public void onNext(@NonNull Long aLong) {
                        initView();

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    private void initView() {

        fragmentHomeMvvm = ViewModelProviders.of(this).get(FragmentHomeMvvm.class);


        departmentAdapter=new DepartmentAdapter(activity,this);
        binding.recyclerDepartment.setLayoutManager(new LinearLayoutManager(activity,LinearLayoutManager.HORIZONTAL,false));
        binding.recyclerDepartment.setAdapter(departmentAdapter);

        offersAdapter=new OffersAdapter(activity,this);
        binding.recyclerOffers.setLayoutManager(new LinearLayoutManager(activity,LinearLayoutManager.HORIZONTAL,false));
        binding.recyclerOffers.setAdapter(offersAdapter);

        productAdapter =new ProductAdapter(activity,this);
        binding.nestedRecycler.setLayoutManager(new LinearLayoutManager(activity));
        binding.nestedRecycler.setAdapter(productAdapter);

        sliderAdapter=new SliderAdapter(getContext());
        binding.pager.setAdapter(sliderAdapter);
        binding.pager.setClipToPadding(false);
        binding.pager.setPadding(80, 0, 80, 0);
        binding.pager.setPageMargin(20);
        Timer timer=new Timer();
        timer.scheduleAtFixedRate(new MyTask(),3000,3000);

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        disposable.clear();
    }

    public class MyTask extends TimerTask {
        @Override
        public void run() {
            activity.runOnUiThread(() -> {
                int current_page = binding.pager.getCurrentItem();
                if (current_page < sliderAdapter.getCount() - 1) {
                    binding.pager.setCurrentItem(binding.pager.getCurrentItem() + 1);
                } else {
                    binding.pager.setCurrentItem(0);

                }
            });

        }

    }


}