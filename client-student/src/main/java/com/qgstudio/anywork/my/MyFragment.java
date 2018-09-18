package com.qgstudio.anywork.my;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.qgstudio.anywork.App;
import com.qgstudio.anywork.R;
import com.qgstudio.anywork.feedback.FeedbackActivity;
import com.qgstudio.anywork.user.UserActivity;
import com.qgstudio.anywork.utils.GlideUtil;

import de.hdodenhof.circleimageview.CircleImageView;

public class MyFragment extends Fragment {

    private CircleImageView head;
    private Button edit;
    private TextView feedback;
    private TextView name;
    private TextView studentId;

    public MyFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my, container, false);

        initView(view);

        return view;
    }

    /**
     * 细节优化
     *
     * @param viewGroup
     */
    private void setDetails(View viewGroup) {
        //获得系统状态栏高度
        int result = 0;
        int resourceId = getContext().getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getContext().getResources().getDimensionPixelOffset(resourceId);
        }
        //将整个布局向下平移状态栏的高度
        View view = viewGroup.findViewById(R.id.view);
        view.setLayoutParams(new ViewGroup.LayoutParams(view.getWidth(), view.getHeight() + result));
        TextView name = (TextView) viewGroup.findViewById(R.id.my_name);
    }

    /**
     * 初始化控件
     *
     * @param view
     */
    private void initView(View view) {
        head = (CircleImageView) view.findViewById(R.id.my_head);
        feedback = (TextView) view.findViewById(R.id.feedback);
        edit = (Button) view.findViewById(R.id.edit_message);
        name = (TextView) view.findViewById(R.id.my_name);
        studentId = (TextView) view.findViewById(R.id.my_student_id);

        GlideUtil.setPictureWithOutCache(head, App.getInstance().getUser().getUserId(), R.drawable.ic_user_default);
        Log.d("linzongzhan", "initView: " + App.getInstance().getUser().toString());
        feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), FeedbackActivity.class);
                startActivity(intent);
            }
        });
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), UserActivity.class);
                startActivity(intent);
            }
        });
        name.setText(App.getInstance().getUser().getUserName());
        studentId.setText(App.getInstance().getUser().getStudentId());
    }

    @Override
    public void onDetach() {
        super.onDetach();
        getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
    }
}
