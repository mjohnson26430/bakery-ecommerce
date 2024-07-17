package com.example.todoapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;
import java.util.List;

public class TaskListActivity extends AppCompatActivity implements TaskListAdapter.OnDeleteClickListener {
    ListView listView;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_list);

        listView = findViewById(R.id.listViewTasks);
        dbHelper = new DBHelper(this);

        List<Task> taskList = dbHelper.getAllTasks();
        TaskListAdapter adapter = new TaskListAdapter(this, R.layout.list_item_task, taskList, this);
        listView.setAdapter(adapter);
    }

    @Override
    public void onDeleteClick(Task task) {
        dbHelper.deleteTask(task);
        refreshListView();
        Toast.makeText(this, "Task deleted", Toast.LENGTH_SHORT).show();
    }

    private void refreshListView() {
        List<Task> updatedTaskList = dbHelper.getAllTasks();
        TaskListAdapter adapter = new TaskListAdapter(this, R.layout.list_item_task, updatedTaskList, this);
        listView.setAdapter(adapter);
    }
}
