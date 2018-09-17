package com.qgstudio.anywork.ranking;

import android.animation.ValueAnimator;
import android.app.ActionBar;
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
import android.widget.TextView;
import android.widget.Toast;

import com.qgstudio.anywork.R;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class RankingFragment extends Fragment {

    private TextView ranking1;
    private TextView ranking2;
    private ArrowsView arrows1;
    private ArrowsView arrows2;
    private ListView listView1;
    private ListView listView2;
    private PopupWindow popupWindow1;
    private PopupWindow popupWindow2;

    //界面中的两个下拉框的适配器
    private ArrayList<String> data1 = new ArrayList<>();
    private ArrayAdapter<String> arrayAdapter1;
    private ArrayList<String> data2 = new ArrayList<>();
    private ArrayAdapter<String> arrayAdapter2;

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
        ranking1 = (TextView) rootView.findViewById(R.id.ranking1);
        ranking2 = (TextView) rootView.findViewById(R.id.ranking2);
        arrows1 = (ArrowsView) rootView.findViewById(R.id.arrows1);
        arrows2 = (ArrowsView) rootView.findViewById(R.id.arrows2);

        //设置点击ranking1和ranking2时让其显示下拉列表窗口
        ranking1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (popupWindow2.isShowing()) {
                    popupWindow2.dismiss();
                }
                popupWindow1.showAsDropDown(ranking1, 0, 0);
                arrows1.mUp();
            }
        });
        ranking2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (popupWindow1.isShowing()) {
                    popupWindow1.dismiss();
                }
                popupWindow2.showAsDropDown(ranking2, 0, 0);
                arrows2.mUp();
            }
        });

        initSelectPopup();
    }

    /**
     * 设置下拉列表窗口
     */
    private void initSelectPopup() {
        listView1 = new ListView(getActivity());
        listView2 = new ListView(getActivity());

        data1.add("学生所在班级");
        data1.add("教师所教班级");

        arrayAdapter1 = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, data1);
        arrayAdapter2 = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, data2);
        listView1.setAdapter(arrayAdapter1);
        listView2.setAdapter(arrayAdapter2);
        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String kind = data1.get(position);
                ranking1.setText(kind);
                popupWindow1.dismiss();
            }
        });
        listView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String kind = data2.get(position);
                ranking2.setText(kind);
                popupWindow2.dismiss();
            }
        });

        popupWindow1 = new PopupWindow(ranking1, ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT, true);
        popupWindow2 = new PopupWindow(ranking2, ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT, true);

        //下拉列表窗口消失时的监听器
        popupWindow1.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                arrows1.mDown();
            }
        });
        popupWindow2.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                arrows2.mDown();
            }
        });
    }
}
