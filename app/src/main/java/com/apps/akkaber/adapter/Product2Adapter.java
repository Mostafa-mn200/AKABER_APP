package com.apps.akkaber.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.apps.akkaber.R;
import com.apps.akkaber.databinding.ProductRowBinding;

import java.util.List;

public class Product2Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private List<Object> list;
    private Context context;
    private LayoutInflater inflater;


    public Product2Adapter(Context context) {
        this.context=context;
        inflater=LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ProductRowBinding binding = DataBindingUtil.inflate(inflater, R.layout.product_row, parent, false);
        return new MyHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MyHolder myHolder = (MyHolder) holder;
//Minced

//Others

    }

    @Override
    public int getItemCount() {
        if (list!=null){
            return list.size();
        }else {
            return 4;
        }
    }

    public static class MyHolder extends RecyclerView.ViewHolder {
        public ProductRowBinding binding;

        public MyHolder(ProductRowBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

        }
    }
}
