package com.example.tuan7bt1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ToDoDAO toDoDAO;
    EditText titleinput, conteninput, dateinput, typeinput;
    Button addBtn, addDelete, addUpdate;
    ListView todoListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        titleinput = findViewById(R.id.titleinput);
        conteninput = findViewById(R.id.contentinput);
        dateinput = findViewById(R.id.dateinput);
        typeinput = findViewById(R.id.typeinput);
        addBtn = findViewById(R.id.addBtn);
        addDelete= findViewById(R.id.updateBtn);
        addUpdate= findViewById(R.id.deleteBtn);
        todoListView = findViewById(R.id.todoListView);

        toDoDAO = new ToDoDAO(this);

        ArrayList<ToDo> list = toDoDAO.getListTodo();
        ToDoAdapter toDoAdapter = new ToDoAdapter(this, list);
        todoListView.setAdapter(toDoAdapter);

        Button addButton = findViewById(R.id.addBtn);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addToDo();
            }
        });
    }
    private void addToDo() {
        ToDo newToDo = new ToDo(0,
                titleinput.getText().toString(),
                conteninput.getText().toString(),
                dateinput.getText().toString(),
                typeinput.getText().toString(),
                0);

        boolean isSuccess = toDoDAO.addTodo(newToDo);

        if (isSuccess) {
            Toast.makeText(MainActivity.this, "Thêm công việc thành công", Toast.LENGTH_SHORT).show();
            refreshToDoList();
        } else {
            Toast.makeText(MainActivity.this, "Thêm công việc thất bại", Toast.LENGTH_SHORT).show();
        }
    }
    private void refreshToDoList() {
        ArrayList<ToDo> toDoList = toDoDAO.getListTodo();
        ToDoAdapter toDoAdapter = new ToDoAdapter(this, toDoList);
        todoListView.setAdapter(toDoAdapter);
    }
}