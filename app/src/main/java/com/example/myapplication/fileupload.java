package com.example.myapplication;


import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class fileupload extends AppCompatActivity {
    private static final int REQUEST_CODE_FILE = 1;
    private List<FileItem> fileList;
    private FileListAdapter fileAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fileupload);

        fileList = new ArrayList<>();
        fileAdapter = new FileListAdapter(fileList);

        // fileupload 버튼을 눌러서 파일 관리자 띄우기
        Button selectFileButton = findViewById(R.id.fileupload);
        selectFileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFileChooser();
            }
        });

        // save 버튼 눌러서 mainpage로 넘어가기
        Button saveButton = findViewById(R.id.save);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(fileupload.this, mainpage.class);
                startActivity(intent);
            }
        });
    }

    private void onFileSelected(String fileName, String filePath) {
        Intent intent = new Intent(this, fileinbox.class);
        intent.putExtra("file_name", fileName);
        intent.putExtra("file_path", filePath);
        startActivity(intent);
    }

    private void openFileChooser() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*");
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        startActivityForResult(intent, REQUEST_CODE_FILE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_FILE && resultCode == RESULT_OK && data != null) {
            Uri uri = data.getData();
            if (uri != null) {
                String fileName = getFileNameFromUri(uri);
                String filePath = getPathFromUri(uri);

                if (fileName != null && filePath != null) {
                    Intent intent = new Intent(this, fileinbox.class);
                    intent.putExtra("file_name", fileName);
                    intent.putExtra("file_path", filePath);
                    startActivity(intent);
                }
            }
        }
    }
    private String getPathFromUri(Uri uri) {
        String filePath = null;
        if (ContentResolver.SCHEME_CONTENT.equals(uri.getScheme())) {
            Cursor cursor = getContentResolver().query(uri, null, null, null, null);
            if (cursor != null && cursor.moveToFirst()) {
                int index = cursor.getColumnIndex(MediaStore.Files.FileColumns.DATA);
                if (index >= 0) {
                    filePath = cursor.getString(index);
                }
                cursor.close();
            }
        } else if (ContentResolver.SCHEME_FILE.equals(uri.getScheme())) {
            filePath = uri.getPath();
        }
        return filePath;
    }


    private String getFileNameFromUri(Uri uri) {
        String fileName = null;
        Cursor cursor = getContentResolver().query(uri, null, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            int nameIndex = cursor.getColumnIndex(MediaStore.MediaColumns.DISPLAY_NAME);
            if (nameIndex != -1) {
                fileName = cursor.getString(nameIndex);
            }
            cursor.close();
        }
        return fileName;
    }
}
