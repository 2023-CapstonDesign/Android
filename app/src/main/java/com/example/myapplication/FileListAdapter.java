package com.example.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
public class FileListAdapter extends RecyclerView.Adapter<FileListAdapter.ViewHolder> {
    private List<FileItem> fileList;

    public FileListAdapter(List<FileItem> fileList) {
        this.fileList = fileList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        FileItem fileItem = fileList.get(position);
        holder.tvFileName.setText(fileItem.getFileName());
        // 추가적인 바인딩 작업 수행
    }

    @Override
    public int getItemCount() {
        return fileList.size();
    }

    public void updateData(List<FileItem> newData) {
        fileList.clear();
        fileList.addAll(newData);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvFileName;
        // 추가적인 뷰 요소들을 선언 (예: 파일 크기, 수정 일자 등)

        public ViewHolder(View itemView) {
            super(itemView);
            tvFileName = itemView.findViewById(R.id.tvFileName);
            // 추가적인 뷰 요소들 초기화 (예: 파일 크기, 수정 일자 등)
        }
    }
}
