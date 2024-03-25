package com.app.GrownBunny;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    ImageButton logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        // Initialize the logout button
//        logout = findViewById(R.id.logout_btn);
//
//        // Logout the user if the logout button clicked
//        logout.setOnClickListener(view -> {
//            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
//            startActivity(intent);
//            FirebaseAuth.getInstance().signOut();
//            Toast.makeText(MainActivity.this, "Successfully Logged Out", Toast.LENGTH_SHORT).show();
//        });

        // Initializing the CardViews on the main menu
        CardView growth_prediction = findViewById(R.id.growth_prediction);
        CardView disease_detection = findViewById(R.id.disease_detection);
        CardView fertilizer_benefits = findViewById(R.id.fertilizer_benefits);

        growth_prediction.setOnClickListener(this);
        disease_detection.setOnClickListener(this);
        fertilizer_benefits.setOnClickListener(this);
    }

    // Declaring what should be happened after clicking each CardView
    @Override
    public void onClick(View v) {
        Intent i;
        if (v.getId() == R.id.growth_prediction) {
            i = new Intent(this, GrowthPredictionActivity.class);
            startActivity(i);
        } else if (v.getId() == R.id.disease_detection) {
            i = new Intent(this, DiseaseDetectionActivity.class);
            startActivity(i);
        } else if (v.getId() == R.id.fertilizer_benefits) {
            i = new Intent(this, FertilizerBenefitsActivity.class);
            startActivity(i);
        }
    }

//    @Override
//    public void onBackPressed() {
//        finishAffinity();
//    }
}
