package com.apps.akkaber.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.apps.akkaber.R;
import com.apps.akkaber.databinding.SubProductItemRowBinding;
import com.apps.akkaber.model.ProductModel;

import java.util.List;

public class SubProductAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private List<ProductModel> list;
    private Context context;
    private LayoutInflater inflater;


    public SubProductAdapter(List<ProductModel> list, Context context) {
        this.list=list;
        this.context=context;
        inflater=LayoutInflater.from(context);

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        SubProductItemRowBinding binding = DataBindingUtil.inflate(inflater, R.layout.sub_product_item_row, parent, false);
        return new MyHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
     MyHolder myHolder = (MyHolder) holder;
     myHolder.binding.setModel(list.get(position));
    }

    @Override
    public int getItemCount() {
        if (list!=null){
            return list.size();
        }else {
            return 0;
        }
    }


    public static class MyHolder extends RecyclerView.ViewHolder {
        public SubProductItemRowBinding binding;

        public MyHolder(SubProductItemRowBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

        }
    }
}
