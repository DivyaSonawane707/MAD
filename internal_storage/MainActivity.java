package com.example.internal_storage;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.button.MaterialButton;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private static final String FILE_NAME = "example.txt";
    
    private EditText editText;
    private TextView textView;

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

        editText = findViewById(R.id.editText);
        textView = findViewById(R.id.textView);
        MaterialButton btnSave = findViewById(R.id.btnSave);
        MaterialButton btnLoad = findViewById(R.id.btnLoad);
        MaterialButton btnDelete = findViewById(R.id.btnDelete);

        btnSave.setOnClickListener(v -> saveFile());
        btnLoad.setOnClickListener(v -> loadFile());
        btnDelete.setOnClickListener(v -> deleteInternalFile());
    }

    private void saveFile() {
        String text = editText.getText().toString().trim();
        if (text.isEmpty()) {
            editText.setError("Please enter some text");
            return;
        }

        // Using try-with-resources for automatic resource management (API 19+)
        try (FileOutputStream fos = openFileOutput(FILE_NAME, Context.MODE_PRIVATE)) {
            fos.write(text.getBytes(StandardCharsets.UTF_8));
            editText.setText("");
            showToast("Saved successfully to " + FILE_NAME);
            Log.d(TAG, "File saved: " + getFilesDir() + "/" + FILE_NAME);
        } catch (IOException e) {
            Log.e(TAG, "Error saving file", e);
            showToast("Error saving file");
        }
    }

    private void loadFile() {
        StringBuilder sb = new StringBuilder();
        
        try (FileInputStream fis = openFileInput(FILE_NAME);
             InputStreamReader isr = new InputStreamReader(fis, StandardCharsets.UTF_8);
             BufferedReader br = new BufferedReader(isr)) {

            String text;
            while ((text = br.readLine()) != null) {
                sb.append(text).append("\n");
            }
            
            if (sb.length() > 0) {
                textView.setText(sb.toString().trim());
                showToast("Loaded from " + FILE_NAME);
            } else {
                textView.setText("File is empty");
            }

        } catch (IOException e) {
            Log.e(TAG, "Error reading file", e);
            textView.setText("");
            showToast("File not found or empty");
        }
    }

    private void deleteInternalFile() {
        boolean deleted = deleteFile(FILE_NAME);
        if (deleted) {
            textView.setText("");
            showToast("File deleted");
        } else {
            showToast("File not found");
        }
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
