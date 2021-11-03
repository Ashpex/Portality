package com.ashpex.portality.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ashpex.portality.CourseActivity;
import com.ashpex.portality.R;
import com.ashpex.portality.adapter.ForumAdapter;

import java.util.ArrayList;
import java.util.List;

public class ForumFragment extends Fragment {
    private LinearLayout layout_course_forum;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_forum, container, false);
        RecyclerView ryc = view.findViewById(R.id.ryc);
        layout_course_forum = view.findViewById(R.id.layout_course_forum);

        List<String> list = new ArrayList<String>();
        list.add("1");list.add("1");list.add("1");list.add("1");list.add("1");
        ryc.setLayoutManager(new LinearLayoutManager(view.getContext()));
        ryc.setAdapter(new ForumAdapter(list));

        layout_course_forum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), CourseActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }

}
