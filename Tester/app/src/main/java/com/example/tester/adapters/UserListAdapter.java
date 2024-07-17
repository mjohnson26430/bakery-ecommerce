package com.example.tester.adapters;

import com.example.tester.R;
import com.example.tester.models.UserBean;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class UserListAdapter extends RecyclerView.Adapter<UserListAdapter.ViewHolder>{

    // declare 'list' array instance variable
    private ArrayList<UserBean> list;

    public UserListAdapter( ArrayList<UserBean> list) {

        // initialize 'list' array in constructor
        this.list = list;

    }// end 'UserListAdapter' constructor

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        // initialize 'LayoutInflater' object to get the context
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

        // use 'item_layout.xm' to inflate the recyclerview in 'activity_main.xml'
        View listItem = layoutInflater.inflate(R.layout.item_layout, parent, false);

        // save 'item_layout.xml' layout
        return new ViewHolder(listItem);

    }// end 'onCreateViewHolder' method

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        final UserBean myListData = list.get(position);

        //  use 'holder' object to set data to the textviews in 'item_layout.xml'
        holder.txtName.setText(myListData.getName());
        holder.txtEmail.setText(myListData.getEmail());

        // use 'Glide' to load image into imageview in 'item_layout.xml' layout
        Glide.with(holder.itemView.getContext())
                .load(myListData.getImage())
                .into(holder.imageView);

    }// end 'onBindViewHolder' method


    @Override
    public int getItemCount() {

        return list.size();

    }// end 'getItemCount' method

    public static class ViewHolder extends RecyclerView.ViewHolder {

        // declare instance variables: 'imageView', 'txtName', 'txtEmail'
        public ImageView imageView;
        public TextView txtName;
        public TextView txtEmail;

        public ViewHolder(View itemView) {

            super(itemView);

            // hold reference to textviews and imageview in 'item_layout.xml. layout
            this.imageView = (ImageView) itemView.findViewById(R.id.imgUser);
            this.txtName = (TextView) itemView.findViewById(R.id.txtFirstName);
            this.txtEmail = (TextView) itemView.findViewById(R.id.txtEmail);

        }// end 'ViewHolder' method

    }// end 'ViewHolder' inner class

}// end 'UserListAdapter' class
