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
public class login extends AppCompatActivity {

    private EditText usernameEditText;
    private EditText passwordEditText;
    private Button loginButton;

    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        usernameEditText = findViewById(R.id.email);
        passwordEditText = findViewById(R.id.password);
        loginButton = findViewById(R.id.logintomainpage);

        databaseHelper = new DatabaseHelper(this);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = usernameEditText.getText().toString();
                String password = passwordEditText.getText().toString();

                if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
                    Toast.makeText(login.this, "Please enter username and password", Toast.LENGTH_SHORT).show();
                } else {
                    // 로그인 처리
                    if (checkUserFromDatabase(username, password)) {
                        Toast.makeText(login.this, "Login successful", Toast.LENGTH_SHORT).show();
                        // 로그인 성공 처리
                        Intent intent = new Intent(login.this, mainpage.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(login.this, "Invalid username or password", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
    }

    private boolean checkUserFromDatabase(String username, String password) {
        return databaseHelper.checkUser(username, password);
    }
}
