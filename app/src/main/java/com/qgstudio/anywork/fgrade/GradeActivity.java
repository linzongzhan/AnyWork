package com.qgstudio.anywork.fgrade;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.qgstudio.anywork.R;
import com.qgstudio.anywork.data.model.StudentTestResult;
import com.qgstudio.anywork.mvp.MVPBaseActivity;
import com.qgstudio.anywork.utils.GsonUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by chenyi on 2017/7/10.
 */

public class GradeActivity extends MVPBaseActivity<GradeContract.View, GradePresenter> implements GradeContract.View {

    @BindView(R.id.toolbar) Toolbar toolbar;

    @BindView(R.id.recyclerView) RecyclerView recyclerView;

    private GradeAdapter gradeAdapter;

    private StudentTestResult testResult;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grade);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        testResult = GsonUtil.GsonToBean(intent.getStringExtra("GRADE"), StudentTestResult.class);
        Log.i("tag", "onCreate: " + intent.getStringExtra("GRADE"));

        initToolbar();
        initRecyclerView();
    }

    private void initToolbar() {
        toolbar.setNavigationIcon(R.mipmap.back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initRecyclerView() {
        gradeAdapter = new GradeAdapter(this);
        GridLayoutManager manager = new GridLayoutManager(this, 5);
        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if (gradeAdapter.getDatasType(position) == GradeInfo.TYPE1) {
                    return 5;
                } else {
                    return 1;
                }
            }
        });
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(gradeAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.getGrade();
    }

    public static void startToActivity(Context context,String grade) {
        Intent intent = new Intent(context, GradeActivity.class);
        intent.putExtra("GRADE", grade);
        context.startActivity(intent);
    }

    @Override
    public void showSuccess() {

    }

    @Override
    public void showError(String s) {

    }

    @Override
    public void showGrade(List<GradeInfo> datas) {
        Log.i("TAG", "showGrade: "+datas.size());
        gradeAdapter.setmDatas(datas);
        gradeAdapter.notifyDataSetChanged();
    }

    @Override
    public void showProgressDialog() {

    }

    @Override
    public void hidProgressDialog() {

    }
}
