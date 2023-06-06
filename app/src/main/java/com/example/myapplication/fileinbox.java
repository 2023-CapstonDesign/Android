package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class fileinbox extends AppCompatActivity {
    private List<FileItem> fileList;
    private FileListAdapter fileListAdapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fileinbox);


        fileList = new ArrayList<>();
        fileListAdapter = new FileListAdapter(fileList);

        recyclerView = findViewById(R.id.file_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(fileListAdapter);


        recyclerView.setLayoutManager(new LinearLayoutManager(fileinbox.this));

        // 파일 관리자에서 전달받은 파일 정보 가져오기

        Intent intent = getIntent();
        String fileName = intent.getStringExtra("file_name");
        String filePath = intent.getStringExtra("file_path");

        if (fileName != null && filePath != null) {
            // 파일 정보를 FileItem으로 변환하여 목록에 추가
            FileItem fileItem = new FileItem(fileName, filePath);
            fileList.add(fileItem);

            // 새로운 데이터로 fileListAdapter 업데이트
            List<FileItem> newData = new ArrayList<>(fileList);
            fileListAdapter.updateData(newData);
            fileListAdapter.notifyDataSetChanged();
        }

        Button open = (Button) findViewById(R.id.openfile);
        open.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(), fileviewer.class);
                startActivity(intent);
            }
        });



    }
}
