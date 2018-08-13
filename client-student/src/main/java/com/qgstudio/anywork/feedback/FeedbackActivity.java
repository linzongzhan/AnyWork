package com.qgstudio.anywork.feedback;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import com.qgstudio.anywork.R;
import com.qgstudio.anywork.common.DialogManagerActivity;
import com.qgstudio.anywork.dialog.LoadingDialog;
import com.qgstudio.anywork.feedback.data.FeedBack;
import com.qgstudio.anywork.mvp.MVPBaseActivity;
import com.qgstudio.anywork.utils.ToastUtil;

import java.util.ArrayList;

public class FeedbackActivity extends MVPBaseActivity<FeedbackContract.View, FeedbackPresenter> implements FeedbackContract.View {

    private ImageView noHint;
    private ImageView question;
    private ImageView suggestion;
    private EditText questionDetail;
    private Button addPicture;
    private Spinner module;
    private EditText contact;
    private Button commit;
    private Button back;

    private ArrayList<String> modules = new ArrayList<>();
    private ArrayAdapter<String> arrayAdapter;

    private String questionORsuggestion = "无";
    private String moduleDetail = "无";

    LoadingDialog loadingDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        initView();
        setData();
        setOnClickListener();
    }

    private void initView() {
        noHint = (ImageView) findViewById(R.id.feedback_no_hint);
        question = (ImageView) findViewById(R.id.feedback_question);
        suggestion = (ImageView) findViewById(R.id.feedback_suggestion);
        questionDetail = (EditText) findViewById(R.id.feedback_question_detail);
        addPicture = (Button) findViewById(R.id.feedback_picture);
        module = (Spinner) findViewById(R.id.feedback_module);
        contact = (EditText) findViewById(R.id.feedback_contact);
        commit = (Button) findViewById(R.id.feedback_commit);
        back = (Button) findViewById(R.id.feedback_back);
    }

    private void setData() {
        modules.add("练习");
        modules.add("考试");
        modules.add("登录");
        modules.add("注册");
        modules.add("用户");
        modules.add("组织");

        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, modules);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        module.setAdapter(arrayAdapter);
    }

    private void setOnClickListener() {
        noHint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.feedback_hint).setVisibility(View.GONE);
            }
        });
        question.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                question.setImageResource(R.drawable.feedback_select);
                suggestion.setImageResource(R.drawable.feedback_noselect);
                questionORsuggestion = "遇到的问题";
            }
        });
        suggestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                suggestion.setImageResource(R.drawable.feedback_select);
                question.setImageResource(R.drawable.feedback_noselect);
                questionORsuggestion = "新功能建议";
            }
        });
        addPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtil.showToast("此功能尚未开放");
            }
        });
        module.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                moduleDetail = modules.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                moduleDetail = "无";
            }
        });
        commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FeedBack feedBack = new FeedBack();
                feedBack.setType(questionORsuggestion);
                feedBack.setContent(questionDetail.getText().toString());
                feedBack.setModule(moduleDetail);
                feedBack.setContantWay(contact.getText().toString());
                mPresenter.uploadFeedback(feedBack);
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void showError(String errorInfo) {
        ToastUtil.showToast(errorInfo);
    }

    @Override
    public void showSuccess() {
        ToastUtil.showToast("信息反馈成功");
        finish();
    }

    @Override
    public void showLoad() {
        if (loadingDialog == null) {
            loadingDialog = new LoadingDialog();
        }
        loadingDialog.show(this.getSupportFragmentManager(), "");
    }

    @Override
    public void stopLoad() {
        loadingDialog.dismiss();
    }
}
