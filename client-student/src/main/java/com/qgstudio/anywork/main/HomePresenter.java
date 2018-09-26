package com.qgstudio.anywork.main;

import com.google.gson.JsonObject;
import com.qgstudio.anywork.data.RetrofitClient;
import com.qgstudio.anywork.data.RetrofitSubscriber;
import com.qgstudio.anywork.data.model.Organization;
import com.qgstudio.anywork.main.data.OrganizationApi;
import com.qgstudio.anywork.mvp.BasePresenterImpl;
import com.qgstudio.anywork.notice.NoticeApi;
import com.qgstudio.anywork.utils.LogUtil;

import java.util.HashMap;
import java.util.List;

import retrofit2.Retrofit;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class HomePresenter extends BasePresenterImpl<HomeContract.HomeView> implements HomeContract.HomePresenter {
    private OrganizationApi mOrganizationApi;
    private NoticeApi mNoticeApi;
    public static final String TAG = "HomePresenter";

    public HomePresenter() {
        Retrofit retrofit = RetrofitClient.RETROFIT_CLIENT.getRetrofit();
        mOrganizationApi = retrofit.create(OrganizationApi.class);
        mNoticeApi = retrofit.create(NoticeApi.class);
    }

    @Override
    public void attachView(HomeContract.HomeView view) {
        mView = view;
    }

    @Override
    public void detachView() {
        mView = null;
    }

    @Override
    public void getJoinOrganization() {
        prepareLoading();
        mOrganizationApi.getJoinOrganization()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RetrofitSubscriber<List<Organization>>() {
                    @Override
                    protected void onSuccess(List<Organization> data) {
                        LogUtil.d2(TAG, "getJoinOrganization", "onSuccess -> " + data);

                        afterLoading();
                        mView.onMyClassGot(data.isEmpty() ? null : data.get(0));
                    }

                    @Override
                    protected void onFailure(String info) {
                        LogUtil.d2(TAG, "getJoinOrganization", "onFailure -> " + info);

                        afterLoading();
                        mView.showToast("获取信息失败");
                    }

                    @Override
                    protected void onMistake(Throwable t) {
                        LogUtil.d2(TAG, "getJoinOrganization", "onMistake -> " + t.getMessage());

                        afterLoading();
                        mView.showToast("获取信息失败");
                    }
                });
    }

    @Override
    public void getNoticeNew() {
        mNoticeApi.getNotice(buildRequestParam())
                .subscribeOn(Schedulers.io())
                .observeOn((AndroidSchedulers.mainThread()))
                .subscribe(new RetrofitSubscriber<JsonObject>() {
                    @Override
                    protected void onSuccess(JsonObject data) {

                    }

                    @Override
                    protected void onFailure(String info) {

                    }

                    @Override
                    protected void onMistake(Throwable t) {

                    }
                });
    }

//    @Override
//    public void postReadNew(String id) {
//        mNoticeApi.markWatched(buildReadRequestParam(id))
//                .subscribeOn(Schedulers.io())
//                .observeOn((AndroidSchedulers.mainThread()))
//                .subscribe(new RetrofitSubscriber<JsonObject>() {
//                    @Override
//                    protected void onSuccess(JsonObject data) {
//
//                    }
//
//                    @Override
//                    protected void onFailure(String info) {
//
//                    }
//
//                    @Override
//                    protected void onMistake(Throwable t) {
//
//                    }
//                });
//    }

    private Object buildRequestParam() {
        HashMap info = new HashMap();
        info.put("status", 0);
        info.put("pageSize", 10);
        info.put("pageNum", 1);
        return info;
    }

//    private Object buildReadRequestParam(String id) {
//        HashMap info = new HashMap();
//        info.put("messageId",id);
//        return info;
//    }

    private void afterLoading() {
        mView.hideLoading();
    }

    private void prepareLoading() {
        mView.showLoading();
    }
}
