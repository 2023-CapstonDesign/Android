package com.example.myapplication;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Camera;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class facedecryption extends AppCompatActivity {
    private static final int REQUEST_IMAGE_CAPTURE  = 1;
    private ImageView imageView;
    private String currentImagePath;

    private boolean ixExistCameraApplication(){
        PackageManager packageManager = getPackageManager();

        Intent cameraApp = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        List<ResolveInfo> cameraApps = packageManager.queryIntentActivities(cameraApp, PackageManager.MATCH_DEFAULT_ONLY);

        return cameraApps.size() > 0;

    }

    private File createImageFile() throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File imageFile = File.createTempFile(imageFileName, ".jpg", storageDir);
        currentImagePath = imageFile.getAbsolutePath();
        return imageFile;
    }

    private void saveImageToGallery() {
            try {
                File imageFile = new File(currentImagePath);
                File galleryFolder = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
                File destinationFile = new File(galleryFolder, imageFile.getName());
                if (imageFile.exists()) {
                    if (imageFile.renameTo(destinationFile)) {
                        // 갤러리에 이미지 추가를 알림
                        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
                        Uri contentUri = Uri.fromFile(destinationFile);
                        mediaScanIntent.setData(contentUri);
                        this.sendBroadcast(mediaScanIntent);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.picture_decryption);

        imageView = findViewById(R.id.imageView);
        Button camerabutton = (Button) findViewById(R.id.camera);

        Button saveButton = findViewById(R.id.save);


        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 다음 액티비티 이동
                Intent fileViewerIntent = new Intent(facedecryption.this, facecheck_confirmation.class);
                startActivity(fileViewerIntent);
            }
        });

        camerabutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){

                if(ixExistCameraApplication()){
                    if (ContextCompat.checkSelfPermission(facedecryption.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(facedecryption.this, new String[]{Manifest.permission.CAMERA}, REQUEST_IMAGE_CAPTURE);
                    } else {
                        dispatchTakePictureIntent();
                    }
                } else {
                    Toast.makeText(facedecryption.this, "No camera application available", Toast.LENGTH_SHORT).show();
                }

            }
        });




    }
    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            File imageFile = null;
            try {
                imageFile = createImageFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (imageFile != null) {
                Uri imageUri = FileProvider.getUriForFile(this, "com.example.myapplication.fileprovider", imageFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            // 카메라로부터 이미지를 성공적으로 가져온 경우

            // 이미지를 ImageView에 표시
            Bitmap imageBitmap = BitmapFactory.decodeFile(currentImagePath);
            imageView.setImageBitmap(imageBitmap);

            // 디바이스에 이미지 저장
            saveImageToGallery();

            // 저장 완료 메시지 표시
            Toast.makeText(this, "Image saved to gallery", Toast.LENGTH_SHORT).show();
        }
    }



}