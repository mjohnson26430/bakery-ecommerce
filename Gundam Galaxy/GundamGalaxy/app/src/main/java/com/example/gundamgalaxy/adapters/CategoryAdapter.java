package com.example.gundamgalaxy.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gundamgalaxy.Category;
import com.example.gundamgalaxy.R;

import java.util.List;

public class CategoryAdapter extends BaseAdapter {

    private Context context;
    private List<Category> categoryList;

    public CategoryAdapter(Context context, List<Category> categoryList) {
        this.context = context;
        this.categoryList = categoryList;
    }

    @Override
    public int getCount() {
        return categoryList.size();
    }

    @Override
    public Object getItem(int position) {
        return categoryList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.grid_item_layout, null);
        }

        ImageView categoryImage = view.findViewById(R.id.categoryImage);
        TextView categoryName = view.findViewById(R.id.categoryName);

        Category category = categoryList.get(position);
        categoryImage.setImageResource(category.getImageResource());
        categoryName.setText(category.getName());

        return view;
    }
}
