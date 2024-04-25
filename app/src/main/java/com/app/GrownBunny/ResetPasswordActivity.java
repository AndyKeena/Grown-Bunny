package com.app.GrownBunny;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.app.GrownBunny.databinding.ActivityResetPasswordBinding;
import com.google.firebase.auth.FirebaseAuth;

public class ResetPasswordActivity extends AppCompatActivity {
    ActivityResetPasswordBinding binding;
    FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityResetPasswordBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mAuth = FirebaseAuth.getInstance();

        final EditText reset_email = findViewById(R.id.reset_email);
        final Button reset_password_button = findViewById(R.id.reset_password_button);
        reset_email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(Patterns.EMAIL_ADDRESS.matcher(reset_email.getText().toString()).matches()){
                    reset_password_button.setEnabled(true);
                }
                else{
                    reset_password_button.setEnabled(false);
                    reset_email.setError("Invalid EmailAddress");
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        binding.resetPasswordButton.setOnClickListener(view -> {
            String rEmail =  binding.resetEmail.getText().toString();
            if (rEmail.equals("")) {
                Toast.makeText(this, "Please Enter Email", Toast.LENGTH_SHORT).show();
            } else {
                mAuth.sendPasswordResetEmail(binding.resetEmail.getText().toString()).addOnCompleteListener(task -> {
                    if(task.isSuccessful()){
                        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                        startActivity(intent);
                        finish();
                        Toast.makeText(ResetPasswordActivity.this, "Please check your email!", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(ResetPasswordActivity.this, "Enter valid email!", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(e -> Toast.makeText(ResetPasswordActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show());


            }
        });
    }
}