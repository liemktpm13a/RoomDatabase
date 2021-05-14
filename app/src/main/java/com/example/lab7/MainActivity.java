package com.example.lab7;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private UserAdapter adapter;
    private RecyclerView recyclerView;

    private List<User> listUserName=new ArrayList<>();

    private  AppDatabase db;
    private Button btnAdd;
    private TextView tvName;
    private Button btnRemove;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = AppDatabase.getInstance(MainActivity.this);
        UserDAO userDao = db.userDAO();
        listUserName = userDao.getAll();
        recyclerView = findViewById(R.id.rcView);
        btnAdd = findViewById(R.id.btnAdd);
        adapter = new UserAdapter(MainActivity.this, listUserName);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this);
        tvName = findViewById(R.id.tvInput);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(linearLayoutManager);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = tvName.getText().toString();
                User user = new User(name);
                userDao.insertAll(user);
                listUserName = userDao.getAll();

                adapter = new UserAdapter(MainActivity.this, listUserName);
                recyclerView.setAdapter(adapter);
                InputMethodManager manager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                manager.hideSoftInputFromWindow(v.getApplicationWindowToken(),0);
            }
        });
        btnRemove = findViewById(R.id.btnRemove);
        btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = String.valueOf(tvName.getText());
                User user = new User(name);
                userDao.delete(user);
                listUserName = userDao.getAll();
                adapter = new UserAdapter(MainActivity.this, listUserName);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this);
                recyclerView.setAdapter(adapter);
                recyclerView.setLayoutManager(linearLayoutManager);
                InputMethodManager manager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                manager.hideSoftInputFromWindow(v.getApplicationWindowToken(),0);
            }
        });

    }
}