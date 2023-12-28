package com.example.tuan7bt1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private int selectedTodoId = -1;

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
        addDelete= findViewById(R.id.deleteBtn);
        addUpdate= findViewById(R.id.updateBtn);
        todoListView = findViewById(R.id.todoListView);

        toDoDAO = new ToDoDAO(this);

        ArrayList<ToDo> list = toDoDAO.getListTodo();
        ToDoAdapter toDoAdapter = new ToDoAdapter(this, list);
        todoListView.setAdapter(toDoAdapter);

        Button addButton = findViewById(R.id.addBtn);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addToDo();
            }
        });
        addUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateToDo();
            }
        });

        // Xử lý sự kiện cho nút Delete
        addDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteToDo();
            }
        });
        todoListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Lấy ToDo được chọn từ danh sách
                ToDo selectedToDo = (ToDo) parent.getItemAtPosition(position);

                // Hiển thị thông tin ToDo lên EditText để có thể cập nhật hoặc xóa
                displaySelectedToDo(selectedToDo);
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
    private void displaySelectedToDo(ToDo selectedToDo) {
        titleinput.setText(selectedToDo.getTitle());
        conteninput.setText(selectedToDo.getContent());
        dateinput.setText(selectedToDo.getDate());
        typeinput.setText(selectedToDo.getType());

        // Lưu ID vào biến selectedTodoId để sử dụng sau này
        selectedTodoId = selectedToDo.getId();
    }

    private void updateToDo() {
        String title = titleinput.getText().toString();
        String content = conteninput.getText().toString();
        String date = dateinput.getText().toString();
        String type = typeinput.getText().toString();

        ToDo updatedToDo = new ToDo(selectedTodoId, title, content, date, type, 0);

        boolean isSuccess = toDoDAO.updateTodo(updatedToDo);

        if (isSuccess) {
            Toast.makeText(MainActivity.this, "Cập nhật công việc thành công", Toast.LENGTH_SHORT).show();
            refreshToDoList();
        } else {
            Toast.makeText(MainActivity.this, "Cập nhật công việc thất bại", Toast.LENGTH_SHORT).show();
        }
    }
    private void deleteToDo() {
        if (selectedTodoId != -1) {
            boolean isSuccess = toDoDAO.deleteTodo(selectedTodoId);

            if (isSuccess) {
                Toast.makeText(MainActivity.this, "Xóa công việc thành công", Toast.LENGTH_SHORT).show();
                refreshToDoList();
            } else {
                Toast.makeText(MainActivity.this, "Xóa công việc thất bại", Toast.LENGTH_SHORT).show();
            }

            // Sau khi xóa, đặt lại giá trị của selectedTodoId
            selectedTodoId = -1;
        } else {
            Toast.makeText(MainActivity.this, "Vui lòng chọn công việc để xóa", Toast.LENGTH_SHORT).show();
        }
    }

}