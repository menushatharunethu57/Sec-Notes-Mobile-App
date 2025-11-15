package com.example.secnotes;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class DataEntrypage extends AppCompatActivity {

    EditText topic;
    EditText des;
    Button send;
    Button view;

    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_entrypage);

        topic = findViewById(R.id.name);
        des = findViewById(R.id.pass);
        send = findViewById(R.id.send);
        view = findViewById(R.id.View);
        dbHelper = new DBHelper(this);


        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DataEntrypage.this,Content_page.class);
                startActivity(i);
            }
        });

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String to = topic.getText().toString();
                String de = des.getText().toString();
                String topictxt = topic.getText().toString().trim();
                String destxt = des.getText().toString().trim();
                topic.setText(" ");
                des.setText(" ");

                if (topictxt.isEmpty() || destxt.isEmpty()) {
                    Toast.makeText(DataEntrypage.this, "Please fill both fields", Toast.LENGTH_SHORT).show();
                    return;
                }

                long result = dbHelper.insertData(topictxt, destxt);
                if (result != -1) {
                    Toast.makeText(DataEntrypage.this, "Saved successfully", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(DataEntrypage.this, "Error saving data", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}