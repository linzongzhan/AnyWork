package com.qgstudio.anywork.ranking.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.qgstudio.anywork.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class RankingAdapter extends RecyclerView.Adapter<RankingAdapter.ViewHolder> {

    public RankingAdapter() {
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView numberImage;
        TextView numberText;
        CircleImageView headPic;
        TextView name;
        TextView studentId;

        public ViewHolder(View itemView) {
            super(itemView);
            numberImage = (ImageView) itemView.findViewById(R.id.ranking_number_image);
            numberText = (TextView) itemView.findViewById(R.id.ranking_number_text);
            headPic = (CircleImageView) itemView.findViewById(R.id.ranking_head_pic);
            name = (TextView) itemView.findViewById(R.id.ranking_name);
            studentId = (TextView) itemView.findViewById(R.id.ranking_student_id);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_ranking, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
