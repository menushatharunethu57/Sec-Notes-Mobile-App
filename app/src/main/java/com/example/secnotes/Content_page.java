package com.example.secnotes;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.secnotes.recycleview.Message;
import com.example.secnotes.recycleview.MessageAdapter;

import java.util.ArrayList;
import java.util.List;

public class Content_page extends AppCompatActivity {

    RecyclerView recy;
    MessageAdapter adapter;
    Button back;
    TextView text;

    DBHelper dbHelper;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_page);

        recy = findViewById(R.id.recyclerView);
        back = findViewById(R.id.btn3);
        text = findViewById(R.id.no);
        dbHelper = new DBHelper(this);
        ArrayList<Message> messages = new ArrayList<>();
        ArrayList<Integer> ids = new ArrayList<>();

        Cursor c = dbHelper.getAllData();

        while (c.moveToNext()) {
            int id = c.getInt(0);
            ids.add(id);
            String topic = c.getString(1);
            String content = c.getString(2);

            messages.add(new Message(topic, content,1,1));
        }
        if (messages.isEmpty()) {
            text.setVisibility(View.VISIBLE);

        } else {
            text.setVisibility(View.GONE);

        }
        c.close();

        adapter = new MessageAdapter(this,messages,ids);
        recy.setHasFixedSize(true);
        recy.setLayoutManager(new LinearLayoutManager(this));
        recy.setAdapter(adapter);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Content_page.this,DataEntrypage.class);
                startActivity(i);
            }
        });

    }
}
