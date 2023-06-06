package com.example.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.io.File;
import java.util.List;

public class FileAdapter extends RecyclerView.Adapter<FileAdapter.ViewHolder> {
    private List<String> filePaths;

    // 어댑터 생성자
    public FileAdapter(List<String> filePaths) {
        this.filePaths = filePaths;
    }

    // 뷰 홀더 클래스
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView fileNameTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            fileNameTextView = itemView.findViewById(R.id.file_name_text_view);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fileinbox, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String filePath = filePaths.get(position);
        File file = new File(filePath);
        String fileName = file.getName();
        holder.fileNameTextView.setText(fileName);
    }

    @Override
    public int getItemCount() {
        return filePaths.size();
    }
}
