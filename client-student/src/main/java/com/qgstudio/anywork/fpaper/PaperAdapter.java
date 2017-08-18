package com.qgstudio.anywork.fpaper;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.qgstudio.anywork.R;
import com.qgstudio.anywork.data.model.Testpaper;
import com.qgstudio.anywork.fexam.ExamActivity;
import com.qgstudio.anywork.utils.DateUtil;
import com.qgstudio.anywork.utils.ToastUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

import static com.qgstudio.anywork.data.ApiStores.API_DEFAULT_URL;


/**
 * @author Yason 2017/7/10.
 */

public class PaperAdapter extends RecyclerView.Adapter<PaperAdapter.Holder> {

    private Context mContext;
    private List<Testpaper> mPapers;

    public PaperAdapter(Context context, List<Testpaper> papers) {
        mContext = context;
        mPapers = papers;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_paper, parent, false);
        return new Holder(v);
    }

    @Override
    public void onBindViewHolder(Holder holder, final int position) {
        final Testpaper paper = mPapers.get(position);
        final int type = paper.getTestpaperType();

        //设置标题，章节，类型
        holder.tv_title.setText(paper.getTestpaperTitle());

        String chapter = paper.getChapterName();
        holder.tv_chapter.setText(getTextString(chapter));

        holder.tv_type.setText(type == 1 ? "考试" : "练习");

        //设置时间
        if (type == 1) {
            String pattern = "yyyy/MM/dd";
            String create = DateUtil.longToString(paper.getCreateTime(), pattern);
            String ending = DateUtil.longToString(paper.getEndingTime(), pattern);
            holder.tv_date.setText(create + " ~ " + ending);
        } else {
            holder.tv_date.setText("——");
        }

        //设置头像
        String url = API_DEFAULT_URL + "picture/organization/" + paper.getOrganizationId() + ".jpg";
        Glide.with(mContext).load(url).into(holder.civ);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (type == 1) {
                    long now = System.currentTimeMillis();
                    if (now < paper.getCreateTime()) {
                        ToastUtil.showToast("未到达考试时间");
                        return;
                    }
                    if (now > paper.getEndingTime()) {
                        ToastUtil.showToast("考试时间已截止");
                        return;
                    }
                }
                ExamActivity.start(v.getContext(), mPapers.get(position).getTestpaperId(), type);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mPapers.size();
    }

    public void addAll(List<Testpaper> papers) {
        if (mPapers.size() == 0) {
            mPapers.addAll(papers);
            notifyDataSetChanged();
            return;
        }
        int start = mPapers.size() + 1;
        int count = papers.size();
        mPapers.addAll(papers);
        notifyItemRangeInserted(start, count);
    }

    private String getTextString(String text) {
        if (text == null || text.equals("")) {
            return "——";
        }
        return text;
    }

    class Holder extends RecyclerView.ViewHolder {

        @BindView(R.id.civ_paper) CircleImageView civ;
        @BindView(R.id.textView5) TextView tv_title;
        @BindView(R.id.textView6) TextView tv_chapter;
        @BindView(R.id.textView7) TextView tv_type;
        @BindView(R.id.textView8) TextView tv_date;

        public Holder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
