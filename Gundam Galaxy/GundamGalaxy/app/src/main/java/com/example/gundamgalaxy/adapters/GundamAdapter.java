package com.example.gundamgalaxy.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gundamgalaxy.OnItemClickListener;
import com.example.gundamgalaxy.R;
import com.example.gundamgalaxy.models.GundamModel;

import java.util.List;

public class GundamAdapter extends RecyclerView.Adapter<GundamAdapter.ViewHolder> {
    private Context context;
    private final List<GundamModel> gundamModelList;
    private final OnItemClickListener onItemClickListener;

    public GundamAdapter(Context context, List<GundamModel> gundamModelList, OnItemClickListener onItemClickListener) {
        this.context = context;
        this.gundamModelList = gundamModelList;
        this.onItemClickListener = onItemClickListener;
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder( ViewHolder holder, int position) {
        GundamModel gundamModel = gundamModelList.get(position);
        holder.name.setText(gundamModel.getName());
        holder.releaseYear.setText(gundamModel.getReleaseYear());
        holder.image.setImageResource(gundamModel.getImage());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClicked(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return gundamModelList.size();
    }

    public GundamModel getItem(int position){
        return gundamModelList.get(position);
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name, releaseYear;
        ImageView image;

        public ViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.tv1);
            releaseYear = itemView.findViewById(R.id.tv2);
            image = itemView.findViewById(R.id.imageView2);
        }
    }
}
