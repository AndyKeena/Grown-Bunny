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

import com.app.GrownBunny.databinding.ActivityLoginBinding;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;


public class LoginActivity extends AppCompatActivity {

    ActivityLoginBinding binding;
    FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        mAuth = FirebaseAuth.getInstance();
        setContentView(binding.getRoot());

        // Catching the data from user-input
        final EditText login_email = findViewById(R.id.login_email);
        final Button login_Btn = findViewById(R.id.login_btn);

        // Changing text color red to invalid email and disable/enable the login button
        login_email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (Patterns.EMAIL_ADDRESS.matcher(login_email.getText().toString()).matches()) {
                    login_Btn.setEnabled(true);
                } else {
                    login_Btn.setEnabled(false);
                    login_email.setError("Invalid EmailAddress");
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


        binding.loginBtn.setOnClickListener(view -> {
            // Retrieve the user inputted data
            String email = binding.loginEmail.getText().toString();
            String login_password = binding.loginPassword.getText().toString();

            // Checking if the fields are empty
            if (email.equals("") || login_password.equals("")) {
                Toast.makeText(LoginActivity.this, "All fields are mandatory",
                        Toast.LENGTH_SHORT).show();
            } else {
                // Sign-in the user using firebase authentication
                mAuth.signInWithEmailAndPassword(email, login_password)
                        .addOnCompleteListener(task -> {
                            if (task.isSuccessful()) {
                                if (Objects.requireNonNull(mAuth.getCurrentUser()).isEmailVerified()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Toast.makeText(LoginActivity.this, "Login Successful.",
                                            Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                    startActivity(intent);
                                    finish();
                                } else {
                                    Toast.makeText(LoginActivity.this, "Please verify your email.",
                                            Toast.LENGTH_SHORT).show();
                                }

                            } else {
                                // If sign in fails, display a message to the user.
                                Toast.makeText(LoginActivity.this, "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();

                            }
                        });

            }
        });


        // If user clicks register open the RegisterActivity
        binding.regRedirectText.setOnClickListener(view ->

        {
            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(intent);
            finish();
        });


        binding.resetPassword.setOnClickListener(view ->

        {
            Intent intent = new Intent(LoginActivity.this, ResetPasswordActivity.class);
            startActivity(intent);
        });
    }

}