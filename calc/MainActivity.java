package com.example.calculator;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText etNum1, etNum2;
    private TextView resultTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialize views
        etNum1 = findViewById(R.id.et_num1);
        etNum2 = findViewById(R.id.et_num2);
        resultTv = findViewById(R.id.result_tv);

        // Set click listeners for buttons
        findViewById(R.id.btn_add).setOnClickListener(this);
        findViewById(R.id.btn_sub).setOnClickListener(this);
        findViewById(R.id.btn_mul).setOnClickListener(this);
        findViewById(R.id.btn_div).setOnClickListener(this);
        findViewById(R.id.btn_c).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        if (id == R.id.btn_c) {
            etNum1.setText("");
            etNum2.setText("");
            resultTv.setText("0");
            return;
        }

        String s1 = etNum1.getText().toString();
        String s2 = etNum2.getText().toString();

        if (s1.isEmpty() || s2.isEmpty()) {
            Toast.makeText(this, "Please enter both numbers", Toast.LENGTH_SHORT).show();
            return;
        }

        double num1, num2;
        try {
            num1 = Double.parseDouble(s1);
            num2 = Double.parseDouble(s2);
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Invalid input", Toast.LENGTH_SHORT).show();
            return;
        }

        double result = 0;

        if (id == R.id.btn_add) {
            result = num1 + num2;
        } else if (id == R.id.btn_sub) {
            result = num1 - num2;
        } else if (id == R.id.btn_mul) {
            result = num1 * num2;
        } else if (id == R.id.btn_div) {
            if (num2 != 0) {
                result = num1 / num2;
            } else {
                resultTv.setText("Error");
                return;
            }
        }

        // Format and display the result
        if (result == (long) result) {
            resultTv.setText(String.valueOf((long) result));
        } else {
            resultTv.setText(String.valueOf(result));
        }
    }
}
