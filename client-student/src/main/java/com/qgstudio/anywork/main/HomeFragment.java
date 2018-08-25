package com.qgstudio.anywork.main;


import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.hitomi.cslibrary.CrazyShadow;
import com.hitomi.cslibrary.base.CrazyShadowDirection;
import com.qgstudio.anywork.R;
import com.qgstudio.anywork.data.model.Organization;
import com.qgstudio.anywork.grade.GradeContract;
import com.qgstudio.anywork.mvp.MVPBaseFragment;
import com.qgstudio.anywork.paper.PaperActivity;
import com.qgstudio.anywork.utils.DesityUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomeFragment extends MVPBaseFragment<HomeContract.HomeView, HomePresenter> implements HomeContract.HomeView {
    @BindView(R.id.btn_my_class)
    TextView btnMyClass;
    @BindView(R.id.toolbar)
    View toolbar;

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
        mPresenter.getJoinOrganization();
    }

    @Override
    public void loadData() {

    }

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }

    @OnClick(R.id.btn_my_class)
    public void clickMyClass() {
        Organization myClass = (Organization) btnMyClass.getTag();
        if (myClass != null) {
            //存在我的班级，去试卷界面
            PaperActivity.start(getContext(), myClass.getOrganizationId());
        } else {

        }
    }

    @Override
    public void onMyClassGot(Organization organization) {
        btnMyClass.setTag(organization);
        btnMyClass.setText(organization == null ? "无班级，快去pick你的班级吧  >" : organization.getOrganizationName());
    }
}