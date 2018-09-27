package com.qgstudio.anywork.main;


import android.arch.lifecycle.Observer;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.qgstudio.anywork.R;
import com.qgstudio.anywork.collection.CollectionActivity;
import com.qgstudio.anywork.data.model.Organization;
import com.qgstudio.anywork.grade.GradeContract;
import com.qgstudio.anywork.mvp.MVPBaseFragment;
import com.qgstudio.anywork.notice.NoticeActivity;
import com.qgstudio.anywork.paper.PaperActivity;
import com.qgstudio.anywork.user.ChangeInfoActivity;
import com.qgstudio.anywork.user.ChangePasswordActivity;
import com.qgstudio.anywork.utils.DesityUtil;
import com.qgstudio.anywork.websocket.WebSocketHolder;
import com.qgstudio.anywork.workout.WorkoutContainerActivity;
import com.qgstudio.anywork.workout.WorkoutType;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomeFragment extends MVPBaseFragment<HomeContract.HomeView, HomePresenter> implements HomeContract.HomeView {
    @BindView(R.id.btn_my_class)
    TextView btnMyClass;
    @BindView(R.id.toolbar)
    View toolbar;
    @BindView(R.id.tv_online_count)
    TextView tvOnlineCount;
    @BindView(R.id.top_view)
    View topView;

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showToast(String s) {

    }

    @Override
    public int getRootId() {
        return R.layout.fragment_home;
    }

    @Override
    public void initView() {
        ButterKnife.bind(this, mRoot);
        btnMyClass.setTag(null);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getActivity().getWindow().setStatusBarColor(getActivity().getResources().getColor(R.color.sample_blue));
        }
        //监听在线人数，使用livedata，自动适应生命周期
        WebSocketHolder.getDefault().onlineCount.observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable Integer integer) {
                tvOnlineCount.setText(integer == null ? "0" : integer.toString());
            }
        });
        //获得系统状态栏高度
        int result = 0;
        int resourceId = getContext().getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getContext().getResources().getDimensionPixelOffset(resourceId);
        }
        Log.e("gaodu", result + "");
        topView.getLayoutParams().height = result;
        topView.setLayoutParams(topView.getLayoutParams());
    }

    @Override
    public void loadData() {

    }

    @OnClick(R.id.btn_my_class)
    public void clickMyClass() {
        startActivity(new Intent(getActivity(), NewOrganizationActivity.class));
    }

    @OnClick(R.id.btn_preview)
    public void clickPreview() {
        WorkoutContainerActivity.start(getActivity(), WorkoutType.PREVIEW, ((Organization) btnMyClass.getTag()).getOrganizationId());

    }

    @OnClick(R.id.btn_exercise)
    public void clickExercise() {
        WorkoutContainerActivity.start(getActivity(), WorkoutType.EXERCISE, ((Organization) btnMyClass.getTag()).getOrganizationId());

    }

    @OnClick(R.id.btn_exam)
    public void clickExam() {
        WorkoutContainerActivity.start(getActivity(), WorkoutType.EXAM, ((Organization) btnMyClass.getTag()).getOrganizationId());

    }

    @OnClick(R.id.btn_collection)
    public void clickCollection() {
        startActivity(new Intent(getActivity(), CollectionActivity.class));
    }

    /**
     * 跳转到公告列表活动
     */
    @OnClick(R.id.btn_notice_all)
    public void clickNoticeAll() {
        startActivity(new Intent(getActivity(), NoticeActivity.class));
    }
    @OnClick(R.id.tv_notice)
    public void clickNoticeText() {

    }

    @Override
    public void onMyClassGot(Organization organization) {
        btnMyClass.setTag(organization == null ? new Organization(-1) : organization);
        btnMyClass.setText(organization == null ? "无班级，快去pick你的班级吧  >" : organization.getOrganizationName() + "  >");
    }

    @Override
    public void onResume() {
        super.onResume();
        //每次进入碎片都要重新拉取数据
        mPresenter.getJoinOrganization();
    }
}
