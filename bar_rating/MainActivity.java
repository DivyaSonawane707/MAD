package com.example.bar_and_rating;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private SeekBar seekBar;
    private TextView seekBarValue;
    private RatingBar ratingBar;
    private TextView ratingDescription;
    private Button submitButton;
    private TextView ratingResult;

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
        seekBar = findViewById(R.id.seekBar);
        seekBarValue = findViewById(R.id.seekBarValue);
        ratingBar = findViewById(R.id.ratingBar);
        ratingDescription = findViewById(R.id.ratingDescription);
        submitButton = findViewById(R.id.submitButton);
        ratingResult = findViewById(R.id.ratingResult);

        // SeekBar listener
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                seekBarValue.setText(progress + "%");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        // RatingBar listener to update description
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                updateRatingDescription(rating);
            }
        });

        // Initial description
        updateRatingDescription(ratingBar.getRating());

        // Button click listener
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int progress = seekBar.getProgress();
                float rating = ratingBar.getRating();
                
                String result = "Submitted: Satisfaction " + progress + "% | " + rating + " Stars";
                ratingResult.setText(result);
                
                Toast.makeText(MainActivity.this, "Thank you for your feedback!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void updateRatingDescription(float rating) {
        if (rating <= 1) {
            ratingDescription.setText("Poor");
        } else if (rating <= 2) {
            ratingDescription.setText("Fair");
        } else if (rating <= 3) {
            ratingDescription.setText("Good");
        } else if (rating <= 4) {
            ratingDescription.setText("Very Good");
        } else {
            ratingDescription.setText("Excellent");
        }
    }
}
