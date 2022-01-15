package com.ashpex.portality.adapter;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.helper.widget.Layer;
import androidx.recyclerview.widget.RecyclerView;

import com.ashpex.portality.R;
import com.ashpex.portality.model.CourseSigned;
import com.ashpex.portality.model.UserCourseOnStudying;

import java.time.temporal.Temporal;
import java.util.List;

public class UserFeeAdpater extends  RecyclerView.Adapter<UserFeeAdpater.FeeViewHolder>{

    private Context mContext;
    private List<UserCourseOnStudying> mListCourses;

    public UserFeeAdpater(Context mContext){
        this.mContext = mContext;
    }

    public UserFeeAdpater(Context mContext, List<UserCourseOnStudying> mListCourses) {
        this.mContext = mContext;
        this.mListCourses = mListCourses;
    }

    public void setData(List<UserCourseOnStudying> list){
        this.mListCourses = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public FeeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_fee, parent,false);
        return new UserFeeAdpater.FeeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FeeViewHolder holder, int position) {
        UserCourseOnStudying userCourse = mListCourses.get(position);
        if(userCourse == null){
            return;
        }
        holder.tvFee.setText(userCourse.getFee());
        holder.tvCourseName.setText(userCourse.getCourse_name());
    }

    @Override
    public int getItemCount() {
        if(mListCourses != null){
            return mListCourses.size();
        }
        return 0;
    }



    public class FeeViewHolder extends RecyclerView.ViewHolder{

        private ImageView imgFee;
        private TextView tvFee;
        private TextView tvCourseName;
        public FeeViewHolder(@NonNull View itemView){
            super(itemView);
            imgFee = itemView.findViewById(R.id.imgCourse_item_fee);
            tvFee = itemView.findViewById(R.id.fee_item_fee);
            tvCourseName = itemView.findViewById(R.id.course_item_fee);
        }
    }

}
