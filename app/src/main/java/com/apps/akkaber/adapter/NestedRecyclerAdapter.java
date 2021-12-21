package com.apps.akkaber.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.apps.akkaber.R;
import com.apps.akkaber.databinding.NestedRecyclerBinding;
import com.apps.akkaber.databinding.OthersItemRowBinding;

import java.util.List;

public class NestedRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private List<Object> list;
    private Context context;
    private LayoutInflater inflater;
    private Fragment fragment;
    MincedAdapter mincedAdapter;
    OtherItemsAdapter otherItemsAdapter;
    TrottersAdapter trottersAdapter;
    LambForBarbecueAdapter lambForBarbecueAdapter;
    HomeCookingAdapter homeCookingAdapter;

    public NestedRecyclerAdapter(Context context,Fragment fragment) {
        this.context=context;
        inflater=LayoutInflater.from(context);
        this.fragment=fragment;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        NestedRecyclerBinding binding = DataBindingUtil.inflate(inflater, R.layout.nested_recycler, parent, false);
        return new MyHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MyHolder myHolder = (MyHolder) holder;
//Minced
        mincedAdapter=new MincedAdapter(list,context);
        LinearLayoutManager layoutManager=new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false);
        myHolder.binding.recyclerMinced.setLayoutManager(layoutManager);
        myHolder.binding.recyclerMinced.setAdapter(mincedAdapter);
//Others
        otherItemsAdapter=new OtherItemsAdapter(list,context);
        LinearLayoutManager layoutManager2=new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false);
        myHolder.binding.recyclerOthers.setLayoutManager(layoutManager2);
        myHolder.binding.recyclerOthers.setAdapter(otherItemsAdapter);
//Trotters
        trottersAdapter=new TrottersAdapter(list,context);
        LinearLayoutManager layoutManager3=new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false);
        myHolder.binding.recyclerTrotters.setLayoutManager(layoutManager3);
        myHolder.binding.recyclerTrotters.setAdapter(trottersAdapter);
//Lamb
        lambForBarbecueAdapter=new LambForBarbecueAdapter(list,context);
        LinearLayoutManager layoutManager4=new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false);
        myHolder.binding.recyclerLambForBarbecue.setLayoutManager(layoutManager4);
        myHolder.binding.recyclerLambForBarbecue.setAdapter(lambForBarbecueAdapter);
//HomeCooking
        homeCookingAdapter=new HomeCookingAdapter(list,context);
        LinearLayoutManager layoutManager5=new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false);
        myHolder.binding.recyclerHomeCooking.setLayoutManager(layoutManager5);
        myHolder.binding.recyclerHomeCooking.setAdapter(homeCookingAdapter);
    }

    @Override
    public int getItemCount() {
        if (list!=null){
            return list.size();
        }else {
            return 1;
        }
    }

    public static class MyHolder extends RecyclerView.ViewHolder {
        public NestedRecyclerBinding binding;

        public MyHolder(NestedRecyclerBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

        }
    }
}
