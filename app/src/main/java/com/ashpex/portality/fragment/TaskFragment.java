package com.ashpex.portality.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ashpex.portality.R;
import com.ashpex.portality.adapter.TaskBotAdapter;
import com.ashpex.portality.adapter.TaskTopAdapter;

import java.util.ArrayList;
import java.util.List;

public class TaskFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_task, container, false);
        RecyclerView ryc_top = view.findViewById(R.id.ryc_task_top);
        List<String> list = new ArrayList<String>();
        list.add("1");list.add("1");list.add("1");list.add("1");list.add("1");list.add("1");list.add("1");list.add("1");list.add("1");list.add("1");
        ryc_top.setLayoutManager(new LinearLayoutManager(view.getContext(), LinearLayoutManager.HORIZONTAL, false));
        ryc_top.setAdapter(new TaskTopAdapter(list));
        ryc_top.setHasFixedSize(true);

        RecyclerView ryc_bot = view.findViewById(R.id.ryc_task_bot);
        ryc_bot.setLayoutManager(new LinearLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL, false));
        ryc_bot.setAdapter(new TaskBotAdapter(list));
        ryc_bot.setHasFixedSize(true);
        return view;
    }
}
