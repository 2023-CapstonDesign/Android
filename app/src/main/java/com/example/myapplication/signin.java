package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class signin extends AppCompatActivity {


    private EditText emailEditText;
    private EditText passwordEditText;

    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.createaccount);

        emailEditText = findViewById(R.id.email);
        passwordEditText = findViewById(R.id.password);


        databaseHelper = new DatabaseHelper(this);

        Button signupButton = (Button) findViewById(R.id.signin);
        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailEditText.getText().toString();
                String password = passwordEditText.getText().toString();

                if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
                    Toast.makeText(signin.this, "Please enter email and password", Toast.LENGTH_SHORT).show();
                } else {
                    // 회원가입 처리
                    addUserToDatabase(email, password);
                    Intent intent = new Intent(signin.this, singin_complete.class);
                    startActivity(intent);
                    finish(); // 회원가입 액티비티 종료
               }
            }
        });

    }

    private void addUserToDatabase(String email, String password) {
        databaseHelper.addUser(email, password);
        Toast.makeText(this, "Signup successful", Toast.LENGTH_SHORT).show();
        finish(); // 회원가입 액티비티 종료
    }
}


