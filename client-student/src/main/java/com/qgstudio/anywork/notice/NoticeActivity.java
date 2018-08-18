package com.qgstudio.anywork.notice;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.qgstudio.anywork.R;
import com.qgstudio.anywork.data.ResponseResult;
import com.qgstudio.anywork.data.RetrofitClient;
import com.qgstudio.anywork.notice.data.Notice;
import com.qgstudio.anywork.utils.ToastUtil;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class NoticeActivity extends AppCompatActivity {
    @BindView(R.id.refresh_layout_notice)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.recycler_view_notice)
    RecyclerView recyclerView;
    NoticeAdapter adapter;
    NoticeApi noticeApi;
    AtomicInteger pageNum = new AtomicInteger(1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice);
        ButterKnife.bind(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new NoticeAdapter(new ArrayList<Notice>(), this);
        recyclerView.setAdapter(adapter);
        noticeApi = RetrofitClient.RETROFIT_CLIENT.getRetrofit().create(NoticeApi.class);
        refreshLayout.autoRefresh();
        refreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                noticeApi.getNotice(buildRequestParam(true))
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<ResponseResult<JsonObject>>() {
                            @Override
                            public void onCompleted() {

                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onNext(ResponseResult<JsonObject> jsonObjectResponseResult) {
                                List<Notice> noticeList = new Gson().fromJson(jsonObjectResponseResult
                                                .getData()
                                                .get("list")
                                        , new TypeToken<List<Notice>>() {}.getType());
                                if (noticeList.isEmpty()) {
                                    ToastUtil.showToast("没有更多了");
                                } else {
                                    adapter.list.addAll(noticeList);
                                    adapter.notifyDataSetChanged();
                                }
                            }
                        });
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {

                noticeApi.getNotice(buildRequestParam(false))
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<ResponseResult<JsonObject>>() {
                            @Override
                            public void onCompleted() {

                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onNext(ResponseResult<JsonObject> jsonObjectResponseResult) {
                                List<Notice> noticeList = new Gson().fromJson(jsonObjectResponseResult
                                                .getData()
                                                .get("list")
                                        , new TypeToken<List<Notice>>() {}.getType());
                                if (noticeList.isEmpty()) {
                                    ToastUtil.showToast("无公告");
                                } else {
                                    adapter.list.clear();
                                    adapter.list.addAll(noticeList);
                                    adapter.notifyDataSetChanged();
                                }
                            }
                        });
            }
        });
    }

    private Object buildRequestParam(boolean isLoadMore) {
        HashMap info = new HashMap();
        info.put("status", 0);
        info.put("pageSize", 10);
        if (!isLoadMore) {
            pageNum.set(1);
        }
        info.put("pageNum", pageNum.getAndIncrement());
        return info;
    }
}
