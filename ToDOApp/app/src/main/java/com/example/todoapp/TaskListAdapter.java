package com.example.todoapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import java.util.List;

public class TaskListAdapter extends ArrayAdapter<Task> {
    private int resourceId;
    private OnDeleteClickListener onDeleteClickListener;

    public TaskListAdapter(Context context, int resourceId, List<Task> objects, OnDeleteClickListener onDeleteClickListener) {
        super(context, resourceId, objects);
        this.resourceId = resourceId;
        this.onDeleteClickListener = onDeleteClickListener;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Task task = getItem(position);
        View view;
        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
        } else {
            view = convertView;
        }
        TextView titleTextView = view.findViewById(R.id.text_title);
        TextView descriptionTextView = view.findViewById(R.id.text_description);
        Button deleteButton = view.findViewById(R.id.btn_delete);
        titleTextView.setText(task.getTitle());
        descriptionTextView.setText(task.getDescription());


        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onDeleteClickListener.onDeleteClick(task);
            }
        });

        return view;
    }


    public interface OnDeleteClickListener {
        void onDeleteClick(Task task);
    }
}
