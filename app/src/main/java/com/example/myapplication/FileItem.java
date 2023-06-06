package com.example.myapplication;

import java.util.ArrayList;
import java.util.List;

public class FileItem {
    private String fileName;
    private String filePath;

    public FileItem(String fileName, String filePath) {
        this.fileName = fileName;
        this.filePath = filePath;
    }

    public String getFileName() {
        return fileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public static void main(String[] args) {
        // FileItem 객체 생성
        FileItem file1 = new FileItem("File 1", "/path/to/file1.pdf");
        FileItem file2 = new FileItem("File 2", "/path/to/file2.doc");
        // 추가적인 FileItem 객체 생성 가능

        // 파일 목록 리스트 생성 및 FileItem 객체 추가
        List<FileItem> fileList = new ArrayList<>();
        fileList.add(file1);
        fileList.add(file2);
        // 추가적인 FileItem 객체 추가 가능
    }
}


