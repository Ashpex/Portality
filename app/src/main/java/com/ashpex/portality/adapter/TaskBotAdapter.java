package com.ashpex.portality.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ashpex.portality.R;

import java.util.List;

public class TaskBotAdapter extends RecyclerView.Adapter<TaskBotAdapter.TaskBotHolder>{

    @NonNull
    private List<String> mlist;
    public TaskBotAdapter(List<String> list) {
        mlist = list;
    }
    @Override
    public TaskBotAdapter.TaskBotHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TaskBotAdapter.TaskBotHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_task_bot, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull TaskBotHolder holder, int position) {
        holder.bindData(mlist.get(position));
    }


    @Override
    public int getItemCount() {
        return mlist.size();
    }

    class TaskBotHolder extends RecyclerView.ViewHolder {
        private TextView nameCourse_task_bot;
        private TextView content_task_bot;
        private TextView timeTop;
        private TextView timeBot;

        public TaskBotHolder(@NonNull View itemView) {
            super(itemView);
            nameCourse_task_bot = itemView.findViewById(R.id.nameCourse_task_bot);
            content_task_bot = itemView.findViewById(R.id.content_task_bot);
            timeTop = itemView.findViewById(R.id.timeTop);
            timeBot = itemView.findViewById(R.id.timeBot);
        }

        public void bindData(String pos) {
            nameCourse_task_bot.setText("Lập trình hướng đối tượng");
            content_task_bot.setText("Nội dung: Làm bài tập đi thằng mập");
            timeTop.setText("11:30 PM");
            timeBot.setText("32/10/2020");
        }
    }
}
