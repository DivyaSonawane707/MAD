package com.example.input_control;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.materialswitch.MaterialSwitch;
import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {

    private TextInputEditText editTextName;
    private RadioGroup radioGroupGender;
    private AutoCompleteTextView autoCompleteCountry;
    private MaterialSwitch switchNotifications;
    private CheckBox checkBoxTerms;
    private MaterialButton buttonSubmit;
    private TextView textViewResult;
    private View cardResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.toolbar), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(0, systemBars.top, 0, 0);
            return insets;
        });

        // Initialize views
        editTextName = findViewById(R.id.editTextName);
        radioGroupGender = findViewById(R.id.radioGroupGender);
        autoCompleteCountry = findViewById(R.id.autoCompleteCountry);
        switchNotifications = findViewById(R.id.switchNotifications);
        checkBoxTerms = findViewById(R.id.checkBoxTerms);
        buttonSubmit = findViewById(R.id.buttonSubmit);
        textViewResult = findViewById(R.id.textViewResult);
        cardResult = findViewById(R.id.cardResult);

        // Setup Country Dropdown (Material 3 style)
        String[] countries = {"India", "USA", "UK", "Canada", "Australia", "Germany", "France"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, countries);
        autoCompleteCountry.setAdapter(adapter);

        // Submit button logic
        buttonSubmit.setOnClickListener(v -> submitForm());
    }

    private void submitForm() {
        String name = editTextName.getText().toString().trim();
        String country = autoCompleteCountry.getText().toString();
        
        // Validation
        if (name.isEmpty()) {
            editTextName.setError("Name is required");
            return;
        }

        if (country.isEmpty()) {
            Toast.makeText(this, "Please select a country", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!checkBoxTerms.isChecked()) {
            Toast.makeText(this, "Please accept the Terms and Conditions", Toast.LENGTH_SHORT).show();
            return;
        }

        // Get Gender
        int selectedId = radioGroupGender.getCheckedRadioButtonId();
        String gender = "Not Specified";
        if (selectedId != -1) {
            RadioButton selectedRadio = findViewById(selectedId);
            gender = selectedRadio.getText().toString();
        }

        // Get Notifications
        boolean notificationsEnabled = switchNotifications.isChecked();

        // Build Result String
        StringBuilder sb = new StringBuilder();
        sb.append("REGISTRATION COMPLETE\n\n");
        sb.append("👤 Name: ").append(name).append("\n");
        sb.append("🚻 Gender: ").append(gender).append("\n");
        sb.append("🌍 Country: ").append(country).append("\n");
        sb.append("🔔 Notifications: ").append(notificationsEnabled ? "Enabled" : "Disabled");

        // Display Result
        textViewResult.setText(sb.toString());
        cardResult.setVisibility(View.VISIBLE);
        
        Toast.makeText(this, "Profile Registered!", Toast.LENGTH_SHORT).show();
    }
}