package com.example.todoapp;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    TimePicker timePicker;
    EditText editTextTitle, editTextDescription;
    List<Task> taskList;
    DBHelper dbHelper;
    FloatingActionButton setAlarmFab, cancelAlarmFab, viewTasksFab, addTaskFab, addFab;
    TextView setAlarmActionText, cancelAlarmActionText, viewTasksActionText, addTaskActionText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timePicker = findViewById(R.id.timePicker);
        editTextTitle = findViewById(R.id.editTextTitle);
        editTextDescription = findViewById(R.id.editTextDescription);
        taskList = new ArrayList<>();
        dbHelper = new DBHelper(this);

        setAlarmFab = findViewById(R.id.set_alarm_fab);
        cancelAlarmFab = findViewById(R.id.cancel_alarm_fab);
        viewTasksFab = findViewById(R.id.view_tasks_fab);
        addTaskFab = findViewById(R.id.add_task_fab);
        addFab = findViewById(R.id.add_fab);
        setAlarmActionText = findViewById(R.id.set_alarm_action_text);
        cancelAlarmActionText = findViewById(R.id.cancel_alarm_action_text);
        viewTasksActionText = findViewById(R.id.view_tasks_action_text);
        addTaskActionText = findViewById(R.id.task_add);

        addFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleFabsVisibility();
            }
        });

        addTaskFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addTask();
            }
        });

        viewTasksFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, TaskListActivity.class));
            }
        });

        setAlarmFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int hour = timePicker.getCurrentHour();
                int minute = timePicker.getCurrentMinute();

                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.HOUR_OF_DAY, hour);
                calendar.set(Calendar.MINUTE, minute);
                calendar.set(Calendar.SECOND, 0);


                addTask();


                setAlarm(calendar.getTimeInMillis());


                Toast.makeText(MainActivity.this, "Alarm set", Toast.LENGTH_SHORT).show();
            }
        });

        cancelAlarmFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void addTask() {
        String title = editTextTitle.getText().toString();
        String description = editTextDescription.getText().toString();

        if (!title.isEmpty() && !description.isEmpty()) {
            Task task = new Task(title, description);
            dbHelper.addTask(task);

            editTextTitle.setText("");
            editTextDescription.setText("");

            Toast.makeText(MainActivity.this, "Task added", Toast.LENGTH_SHORT).show();
            hideFabs();
        } else {
            Toast.makeText(MainActivity.this, "Please enter task title and description", Toast.LENGTH_SHORT).show();
        }
    }

    private void toggleFabsVisibility() {
        if (setAlarmFab.getVisibility() == View.VISIBLE) {
            hideFabs();
            hideFabTexts();
        } else {
            showFabs();
            showFabTexts();
        }
    }

    private void showFabs() {
        setAlarmFab.setVisibility(View.VISIBLE);
        cancelAlarmFab.setVisibility(View.VISIBLE);
        viewTasksFab.setVisibility(View.VISIBLE);
        addTaskFab.setVisibility(View.VISIBLE);
    }

    private void hideFabs() {
        setAlarmFab.setVisibility(View.INVISIBLE);
        cancelAlarmFab.setVisibility(View.INVISIBLE);
        viewTasksFab.setVisibility(View.INVISIBLE);
        addTaskFab.setVisibility(View.INVISIBLE);
    }

    private void showFabTexts() {
        setAlarmActionText.setVisibility(View.VISIBLE);
        cancelAlarmActionText.setVisibility(View.VISIBLE);
        viewTasksActionText.setVisibility(View.VISIBLE);
        addTaskActionText.setVisibility(View.VISIBLE);
    }

    private void hideFabTexts() {
        setAlarmActionText.setVisibility(View.INVISIBLE);
        cancelAlarmActionText.setVisibility(View.INVISIBLE);
        viewTasksActionText.setVisibility(View.INVISIBLE);
        addTaskActionText.setVisibility(View.INVISIBLE);
    }

    private void setAlarm(long timeInMillis) {
        String title = editTextTitle.getText().toString();
        String description = editTextDescription.getText().toString();

        if (!title.isEmpty() && !description.isEmpty()) {
            AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
            Intent intent = new Intent(MainActivity.this, AlarmReceiver.class);
            intent.putExtra("title", title);
            intent.putExtra("description", description);


            int requestCode = (int) System.currentTimeMillis();
            PendingIntent pendingIntent = PendingIntent.getBroadcast(MainActivity.this, requestCode, intent, PendingIntent.FLAG_UPDATE_CURRENT);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, timeInMillis, pendingIntent);
            } else {
                alarmManager.setExact(AlarmManager.RTC_WAKEUP, timeInMillis, pendingIntent);
            }
        } else {
            Toast.makeText(MainActivity.this, "Please enter task title and description", Toast.LENGTH_SHORT).show();
        }
    }
}

