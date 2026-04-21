package com.example.background_color;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.background_color.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    // Updated Parrot image link from Unsplash
    private final String PARROT_IMAGE_URL = "https://images.unsplash.com/photo-1544923408-75c5cef46f14?fm=jpg&q=60&w=3000&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxzZWFyY2h8M3x8cGFycm90fGVufDB8fDB8fHww";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Red Button - Change background to Red and Hide Image
        binding.btnRed.setOnClickListener(v -> {
            binding.main.setBackgroundColor(Color.RED);
            binding.imageView.setVisibility(View.GONE);
        });

        // Blue Button - Change background to Blue and Hide Image
        binding.btnBlue.setOnClickListener(v -> {
            binding.main.setBackgroundColor(Color.BLUE);
            binding.imageView.setVisibility(View.GONE);
        });

        // Show Image Button - Load Parrot Image from URL and Make Visible
        binding.btnShowImage.setOnClickListener(v -> {
            binding.imageView.setVisibility(View.VISIBLE);
            
            // Load the parrot image using Glide
            Glide.with(this)
                    .load(PARROT_IMAGE_URL)
                    .centerCrop()
                    .placeholder(android.R.drawable.progress_horizontal)
                    .error(android.R.drawable.stat_notify_error)
                    .into(binding.imageView);
        });
    }
}
