package com.qgstudio.anywork.workout;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.mobile.utils.ActivityUtilsKt;
import com.qgstudio.anywork.R;
import com.qgstudio.anywork.data.model.Question;
import com.qgstudio.anywork.data.model.StudentAnswerResult;
import com.qgstudio.anywork.exam.ExamView;
import com.qgstudio.anywork.utils.ActivityUtil;
import com.qgstudio.anywork.utils.GsonUtil;
import com.qgstudio.anywork.widget.QuestionView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WorkoutActivity extends AppCompatActivity implements ExamView {
    @BindView(R.id.question_view)
    QuestionView questionView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout);
        ButterKnife.bind(this);
        Question question = GsonUtil.GsonToBean("{\"questionId\":377,\"type\":1,\"content\":\"程序在第7行、第8行和第9行后是什么状态（a和b的取值如何）#include <stdio.h> \\nint main(void) { \\n    int a, b; \\n    a = 5; \\n    b=2; \\n    b=a; \\n   a=b; \\n   printf(\\\"%d %d\\\\n\\\", b, a); \\n    return 0; \\n} \",\"key\":\"\",\"socre\":15.0,\"testpaperId\":51,\"other\":0,\"analysis\":null,\"a\":\"A a=5,b=2\",\"b\":\"B a=2,b=5\",\"c\":\"C a=5,b=5\",\"d\":\"D a=2,b=2\"}", Question.class);
        question.setKey("写在前面\n" +
                "Google I/O 2014 发布的 Material Design 势必将会成为统一 Android Mobile、Android Table、Desktop Chrome 等全平台设计语言规范，对从业人员意义重大，我们已经通过互联网的方式将其翻译成中文，希望能帮到大家！\n" +
                "\n" +
                "到来的朋友请 点赞 或 分享吆喝 支持，想进一步深入交流，请加 QQ 群：137198122");
        question.setType(Question.Type.FILL_BLANK.code);
        questionView.setQuestion(question, 0, 1);
        questionView.setTestMode(false);

    }

    @Override
    public void addQuestions(List<Question> questions) {

    }

    @Override
    public void startGradeAty(double socre, List<StudentAnswerResult> analysis) {

    }

    @Override
    public void destroySelf() {

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
    public Context getContext() {
        return null;
    }
}
