package com.example.todo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import java.util.ArrayList;

public class TaskAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<String> tasks;
    private LayoutInflater inflater;

    public TaskAdapter(Context context, ArrayList<String> tasks) {
        this.context = context;
        this.tasks = tasks;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return tasks.size();
    }

    @Override
    public Object getItem(int position) {
        return tasks.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.list_item, parent, false);
        }

        TextView textViewTask = convertView.findViewById(R.id.textViewTask);
        Button buttonEdit = convertView.findViewById(R.id.buttonEdit);
        Button buttonDelete = convertView.findViewById(R.id.buttonDelete);

        textViewTask.setText(tasks.get(position));

        buttonEdit.setOnClickListener(v -> {
            if (context instanceof MainActivity) {
                ((MainActivity) context).editTask(position);
            }
        });

        buttonDelete.setOnClickListener(v -> {
            tasks.remove(position);
            notifyDataSetChanged();
        });

        return convertView;
    }
}