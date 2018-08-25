package com.qgstudio.anywork.main;

import com.qgstudio.anywork.data.RetrofitClient;
import com.qgstudio.anywork.data.RetrofitSubscriber;
import com.qgstudio.anywork.data.model.Organization;
import com.qgstudio.anywork.main.data.OrganizationApi;
import com.qgstudio.anywork.mvp.BasePresenterImpl;
import com.qgstudio.anywork.utils.LogUtil;

import java.util.List;

import retrofit2.Retrofit;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class HomePresenter extends BasePresenterImpl<HomeContract.HomeView> implements HomeContract.HomePresenter {
    private OrganizationApi mOrganizationApi;
    public static final String TAG = "HomePresenter";

    public HomePresenter() {
        Retrofit retrofit = RetrofitClient.RETROFIT_CLIENT.getRetrofit();
        mOrganizationApi = retrofit.create(OrganizationApi.class);
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

    private void afterLoading() {
        mView.hideLoading();
    }

    private void prepareLoading() {
        mView.showLoading();
    }
}
