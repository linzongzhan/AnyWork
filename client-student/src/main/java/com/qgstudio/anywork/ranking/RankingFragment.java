package com.qgstudio.anywork.ranking;

import android.animation.ValueAnimator;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ListPopupWindow;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.Toast;

import com.qgstudio.anywork.R;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class RankingFragment extends Fragment {

    //控制Spinner右旁的箭头
    private boolean downScore = true;
    private boolean downTask = true;

    private Spinner rankingScore;
    private Spinner rankingTask;
    private ArrowsView arrowsView1;
    private ArrowsView arrowsView2;

    //界面中的两个Spinner的适配器
    private ArrayList<String> datasScore = new ArrayList<>();
    private ArrayAdapter<String> arrayAdapterScore;
    private ArrayList<String> dataTask = new ArrayList<>();
    private ArrayAdapter<String> arrayAdapterTask;

    public RankingFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_ranking, container, false);

        try {
            initSpinner(rootView);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return rootView;
    }

    /**
     * 初始化Spinner
     *
     * @param rootView
     */
    private void initSpinner(View rootView) throws NoSuchFieldException, IllegalAccessException {
        rankingScore = (Spinner) rootView.findViewById(R.id.ranking_score);
        rankingTask = (Spinner) rootView.findViewById(R.id.ranking_task);
        arrowsView1 = (ArrowsView) rootView.findViewById(R.id.arrows1);
        arrowsView2 = (ArrowsView) rootView.findViewById(R.id.arrows2);

        //为rankingScore设置适配器
        datasScore.add("得分最高");
        datasScore.add("用时最短");
        arrayAdapterScore = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, datasScore);
        arrayAdapterScore.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        rankingScore.setAdapter(arrayAdapterScore);
        //为rankingScore设置控制右旁的箭头的监听器
        rankingScore.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (downScore) {
                    arrowsView1.mUp();
                    downScore = false;
                }
                return false;
            }
        });
        rankingScore.setOnHierarchyChangeListener(new ViewGroup.OnHierarchyChangeListener() {
            @Override
            public void onChildViewAdded(View parent, View child) {

            }

            @Override
            public void onChildViewRemoved(View parent, View child) {
                if (!downScore) {
                    arrowsView1.mDown();
                    downScore = true;
                }
            }
        });

        //为rankingTask设置适配器
        dataTask.add("本次任务");
        dataTask.add("上次任务");
        arrayAdapterTask = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, dataTask);
        arrayAdapterTask.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        rankingTask.setAdapter(arrayAdapterTask);
        //为rankingTask设置控制右旁的箭头的监听器
        rankingTask.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (downTask) {
                    arrowsView2.mUp();
                    downTask = false;
                }
                return false;
            }
        });
        rankingTask.setOnHierarchyChangeListener(new ViewGroup.OnHierarchyChangeListener() {
            @Override
            public void onChildViewAdded(View parent, View child) {

            }

            @Override
            public void onChildViewRemoved(View parent, View child) {
                if (!downTask) {
                    arrowsView2.mDown();
                    downTask = true;
                }
            }
        });
    }
}
