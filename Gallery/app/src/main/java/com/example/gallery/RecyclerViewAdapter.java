package com.example.gallery;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder> {

    private Context context;
    private ArrayList<String> imagePathArrayList;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public RecyclerViewAdapter(Context context, ArrayList<String> imagePathArrayList) {
        this.context = context;
        this.imagePathArrayList = imagePathArrayList;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_layout, parent, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        String imagePath = imagePathArrayList.get(position);
        Picasso.get().load(new File(imagePath)).placeholder(R.drawable.ic_launcher_background).into(holder.imageIV);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    int position = holder.getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(position);
                    }
                }
            }
        });
    }


    @Override
    public int getItemCount() {
        return imagePathArrayList.size();
    }

    public static class RecyclerViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageIV;

        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            imageIV = itemView.findViewById(R.id.idIVImage);
        }
    }
}
