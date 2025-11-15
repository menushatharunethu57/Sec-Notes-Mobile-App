package com.example.secnotes;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class update_page extends AppCompatActivity {

    TextView id;
    EditText uptopic;
    EditText uppass;
    Button update;
    Button view;
    DBHelper dbHelper;
    int getID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_page);

        id = findViewById(R.id.UpID);
        uptopic = findViewById(R.id.Upname);
        uppass = findViewById(R.id.Updata);
        update = findViewById(R.id.upbtn);
        view = findViewById(R.id.View);
        dbHelper = new DBHelper(this);

        getID = getIntent().getIntExtra("id", -1);
        if (getID == -1) {
            Toast.makeText(this,"Invalid ID recieved",Toast.LENGTH_SHORT).show();
            finish();
            return;
        }
        id.setText("ID: " + getID);

        Cursor cursor = dbHelper.getDataById(getID);
        if (cursor.moveToFirst()) {
            String topic = cursor.getString(1);  // Column index 1: topic
            String content = cursor.getString(2);  // Column index 2: content
            uptopic.setText(topic);
            uppass.setText(content);
            cursor.close();
        } else {
            Toast.makeText(this, "No data found for this ID", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }



        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newTopic = uptopic.getText().toString().trim();
                String newContent = uppass.getText().toString().trim();


                if (newTopic.isEmpty() || newContent.isEmpty()) {
                    Toast.makeText(update_page.this, "Topic and content cannot be empty", Toast.LENGTH_SHORT).show();
                    return;
                }

                boolean success = dbHelper.updateData(getID, newTopic, newContent);
                if (success) {
                    Toast.makeText(update_page.this, "Updated successfully", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(update_page.this, Content_page.class);
                    startActivity(i);
                    finish();
                } else {
                    Toast.makeText(update_page.this, "Update failed", Toast.LENGTH_SHORT).show();
                }
            }
        });


        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(update_page.this,Content_page.class);
                startActivity(i);
                finish();
            }
        });
    }
}