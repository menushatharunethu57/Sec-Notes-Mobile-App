package com.example.secnotes;

import android.content.Intent;
import android.content.SharedPreferences;
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

public class MainActivity extends AppCompatActivity {

    Button enter;
    EditText pin;
    TextView forgot;
    private SharedPreferences sharedPreferences;
    private static final String key = "pin";
    private static final String defaultpin = "5741";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        enter = findViewById(R.id.btn1ID);
        pin = findViewById(R.id.pinID);
        forgot = findViewById(R.id.forgotID);

        sharedPreferences = getSharedPreferences("SecNotesPrefs", MODE_PRIVATE);

        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String num = pin.getText().toString();
                String storedPin = sharedPreferences.getString(key, defaultpin);

                if(num.equals(storedPin)){
                    Intent i = new Intent(MainActivity.this,DataEntrypage.class);
                    startActivity(i);
                }
                else{
                    Toast.makeText(MainActivity.this,"Invalid pin number!!",Toast.LENGTH_SHORT).show();
                }
            }
        });

        forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent i2 = new Intent(MainActivity.this,Forgot_Pin.class);
                    startActivity(i2);
            }
        });
    }
}