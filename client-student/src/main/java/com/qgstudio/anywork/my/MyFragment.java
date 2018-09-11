package com.qgstudio.anywork.my;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
    private Button feedback;
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

    private void initView(View view) {
        head = (CircleImageView) view.findViewById(R.id.my_head);
        feedback = (Button) view.findViewById(R.id.feedback);
        edit = (Button) view.findViewById(R.id.edit_message);
        name = (TextView) view.findViewById(R.id.my_name);
        studentId = (TextView) view.findViewById(R.id.my_student_id);

        GlideUtil.setPictureWithOutCache(head, App.getInstance().getUser().getUserId(), R.drawable.ic_user_default);
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
}
