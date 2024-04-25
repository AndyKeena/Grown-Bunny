package com.app.GrownBunny;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.app.GrownBunny.databinding.ActivityLoginBinding;
import com.google.firebase.auth.FirebaseAuth;

public class ContactVetActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_vet);

        Button registerButton = findViewById(R.id.register_now);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ContactVetActivity.this, VetRegisterActivity.class);
                startActivity(intent);
            }
        });
    }
}
