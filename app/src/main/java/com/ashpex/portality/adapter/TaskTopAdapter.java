package com.ashpex.portality.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ashpex.portality.R;

import java.util.List;

public class TaskTopAdapter extends RecyclerView.Adapter<TaskTopAdapter.TaskTopHolder>{

    @NonNull
    private List<String> mlist;
    public TaskTopAdapter(List<String> list) {
        mlist = list;
    }
    @Override
    public TaskTopAdapter.TaskTopHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TaskTopAdapter.TaskTopHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_task_top, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull TaskTopAdapter.TaskTopHolder holder, int position) {
        holder.bindData(mlist.get(position));
    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

    class TaskTopHolder extends RecyclerView.ViewHolder {
        private TextView nameCourse_task_top;
        private TextView countTask_top;

        public TaskTopHolder(@NonNull View itemView) {
            super(itemView);
            nameCourse_task_top = itemView.findViewById(R.id.nameCourse_task_top);
            countTask_top = itemView.findViewById(R.id.countTask_top);
        }

        public void bindData(String pos) {
            nameCourse_task_top.setText("Toán tổ hợp");
            countTask_top.setText("+03 nhiệm vụ");
        }
    }
}
