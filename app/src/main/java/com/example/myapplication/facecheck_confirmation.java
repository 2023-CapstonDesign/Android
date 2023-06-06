package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class facecheck_confirmation extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.facecheck_confirmation);

        Button fileupload = (Button) findViewById(R.id.gotofileviewer);
        fileupload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(facecheck_confirmation.this, fileviewer.class);
                startActivity(intent);
            }
        });
    }


}
