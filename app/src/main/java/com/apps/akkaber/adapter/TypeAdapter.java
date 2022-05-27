
package com.apps.akkaber.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;


import com.apps.akkaber.R;
import com.apps.akkaber.databinding.TypeRowBinding;
import com.apps.akkaber.model.TypeModel;
import com.apps.akkaber.uis.activity_product_detials.ProductDetialsActivity;

import java.util.List;

public class TypeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<TypeModel> list;
    private Context context;
    private LayoutInflater inflater;
    private int currentPos = 0;
    private int oldPos = 0;


    public TypeAdapter(Context context) {
        this.context = context;
        inflater = LayoutInflater.from(context);
    }


    @androidx.annotation.NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@androidx.annotation.NonNull ViewGroup parent, int viewType) {


        TypeRowBinding binding = DataBindingUtil.inflate(inflater, R.layout.type_row, parent, false);
        return new MyHolder(binding);


    }

    @Override
    public void onBindViewHolder(@androidx.annotation.NonNull RecyclerView.ViewHolder holder, int position) {

        MyHolder myHolder = (MyHolder) holder;


        myHolder.binding.setModel(list.get(position));
        myHolder.itemView.setOnClickListener(v -> {
            currentPos = myHolder.getAdapterPosition();
            if (oldPos != -1) {
                TypeModel old = list.get(oldPos);
                if (old.isIsselected()) {
                    old.setIsselected(false);
                    list.set(oldPos, old);
                    notifyItemChanged(oldPos);
                }

            }
            TypeModel currentModel = list.get(currentPos);
            if (!currentModel.isIsselected()) {
                currentModel.setIsselected(true);
                list.set(currentPos, currentModel);
                notifyItemChanged(currentPos);
                oldPos = currentPos;
            }
            if (context instanceof ProductDetialsActivity) {
                ProductDetialsActivity productDetialsActivity = (ProductDetialsActivity) context;
                productDetialsActivity.showSizes(currentModel);
            }

        });


    }

    @Override
    public int getItemCount() {
        if (list != null) {
            return list.size();
        } else {
            return 0;
        }
    }

    public static class MyHolder extends RecyclerView.ViewHolder {
        public TypeRowBinding binding;

        public MyHolder(TypeRowBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

        }
    }

    public void updateData(List<TypeModel> list) {

        if (list != null) {
            Log.e("dlldldl", list.size() + "");
            this.list = list;

        }
        notifyDataSetChanged();
    }


}
