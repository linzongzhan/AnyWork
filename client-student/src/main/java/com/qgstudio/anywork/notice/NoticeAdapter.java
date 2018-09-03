package com.qgstudio.anywork.notice;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.qgstudio.anywork.R;
import com.qgstudio.anywork.notice.data.Notice;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NoticeAdapter extends RecyclerView.Adapter<NoticeAdapter.ViewHolder> {
    List<Notice> list;
    Context context;

    public NoticeAdapter(List<Notice> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public NoticeAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_notice, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(NoticeAdapter.ViewHolder holder, int position) {
       // holder.textView.setText(list.get(position).toString());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
//        @BindView(R.id.textview_notice)
//        TextView textView;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
