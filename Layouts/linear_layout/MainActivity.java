package com.example.linearlayout;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button1 = findViewById(R.id.button1);
        Button button2 = findViewById(R.id.button2);
        Button button3 = findViewById(R.id.button3);

        button1.setOnClickListener(v -> Toast.makeText(this, "Button 1 Clicked", Toast.LENGTH_SHORT).show());
        button2.setOnClickListener(v -> Toast.makeText(this, "Button 2 Clicked", Toast.LENGTH_SHORT).show());
        button3.setOnClickListener(v -> Toast.makeText(this, "Button 3 Clicked", Toast.LENGTH_SHORT).show());
    }
}