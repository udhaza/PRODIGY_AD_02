package com.example.todo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<String> tasks;
    private TaskAdapter adapter;
    private ListView listViewTasks;
    private EditText editTextTask;
    private Button buttonAddTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextTask = findViewById(R.id.editTextTask);
        buttonAddTask = findViewById(R.id.buttonAddTask);
        listViewTasks = findViewById(R.id.listViewTasks);

        tasks = new ArrayList<>();
        adapter = new TaskAdapter(this, tasks);
        listViewTasks.setAdapter(adapter);

        buttonAddTask.setOnClickListener(v -> {
            String task = editTextTask.getText().toString().trim();
            if (!task.isEmpty()) {
                tasks.add(task);
                adapter.notifyDataSetChanged();
                editTextTask.setText("");
            } else {
                Toast.makeText(MainActivity.this, "Please enter a task", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void editTask(int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Edit Task");

        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_edit_task, null);
        builder.setView(dialogView);

        EditText editTextEditTask = dialogView.findViewById(R.id.editTextEditTask);
        editTextEditTask.setText(tasks.get(position));

        builder.setPositiveButton("Save", (dialog, which) -> {
            String updatedTask = editTextEditTask.getText().toString().trim();
            if (!updatedTask.isEmpty()) {
                tasks.set(position, updatedTask);
                adapter.notifyDataSetChanged();
            } else {
                Toast.makeText(MainActivity.this, "Task cannot be empty", Toast.LENGTH_SHORT).show();
            }
        });

        builder.setNegativeButton("Cancel", (dialog, which) -> dialog.cancel());

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}
