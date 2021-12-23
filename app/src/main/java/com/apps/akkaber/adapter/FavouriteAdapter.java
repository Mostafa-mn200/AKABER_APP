package com.apps.akkaber.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.apps.akkaber.R;
import com.apps.akkaber.databinding.FavouriteRowBinding;

import java.util.List;

public class FavouriteAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private List<Object> list;
    private Context context;
    private LayoutInflater inflater;


    public FavouriteAdapter(Context context) {
        this.context=context;
        inflater=LayoutInflater.from(context);

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        FavouriteRowBinding binding = DataBindingUtil.inflate(inflater, R.layout.favourite_row, parent, false);
        return new MyHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MyHolder myHolder=(MyHolder) holder;
    }

    @Override
    public int getItemCount() {
        if (list!=null){
            return list.size();
        }else {
            return 2;
        }
    }

    public static class MyHolder extends RecyclerView.ViewHolder {
        public FavouriteRowBinding binding;

        public MyHolder(FavouriteRowBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

        }
    }
    public void updateList(List<Object> list){
        if (list!=null){
            this.list = list;

        }
        notifyDataSetChanged();
    }
}
