package com.example.tuan7bt1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class ToDoAdapter extends ArrayAdapter<ToDo> {
    private Context context;
    private List<ToDo> tasks;

    public ToDoAdapter(Context context, List<ToDo> tasks){
        super(context, 0,tasks);
        this.context = context;
        this.tasks = tasks;

    }
    @Override
    public View getView(int position, View converView, ViewGroup parent){
        ToDo task = getItem(position);
        if (converView == null){
            converView = LayoutInflater.from(getContext()).inflate(R.layout.item_todo,parent,false);
        }
        TextView taskTitle = converView.findViewById(R.id.task_title);
        TextView taskContent = converView.findViewById(R.id.task_content);
        TextView taskDate = converView.findViewById(R.id.task_date);
        TextView taskType = converView.findViewById(R.id.task_type);

        taskTitle.setText(task.getTitle());
        taskContent.setText(task.getContent());
        taskDate.setText(task.getDate());
        taskType.setText(task.getType());

        return converView;

    }
}
