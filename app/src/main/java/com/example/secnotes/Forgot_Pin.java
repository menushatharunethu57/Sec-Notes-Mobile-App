package com.example.secnotes;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
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

public class Forgot_Pin extends AppCompatActivity {

    EditText pin;
    EditText repin;
    Button add;
    private SharedPreferences sharedPreferences;
    private static final String key = "pin";


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_pin);

        pin = findViewById(R.id.newPinID);
        repin = findViewById(R.id.newPinID2);
        add = findViewById(R.id.setPinID);

        sharedPreferences = getSharedPreferences("SecNotesPrefs",MODE_PRIVATE);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newPinText = repin.getText().toString();
                String rePinText = pin.getText().toString();

                if (newPinText.length() != 4 || !newPinText.matches("\\d+")) {
                    Toast.makeText(Forgot_Pin.this, "Enter a valid 4-digit PIN!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!newPinText.equals(rePinText)) {
                    Toast.makeText(Forgot_Pin.this, "PINs does not match re-check!", Toast.LENGTH_SHORT).show();
                    return;
                }

                sharedPreferences.edit().putString(key, newPinText).apply();
                Toast.makeText(Forgot_Pin.this, "PIN updated successfully!", Toast.LENGTH_SHORT).show();

                Intent i = new Intent(Forgot_Pin.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        });
    }
}