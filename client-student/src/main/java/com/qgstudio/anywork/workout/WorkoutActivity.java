package com.qgstudio.anywork.workout;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.mobile.utils.ActivityUtilsKt;
import com.qgstudio.anywork.R;
import com.qgstudio.anywork.data.model.Question;
import com.qgstudio.anywork.utils.ActivityUtil;
import com.qgstudio.anywork.utils.GsonUtil;
import com.qgstudio.anywork.widget.QuestionView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WorkoutActivity extends AppCompatActivity {
    @BindView(R.id.question_view)
    QuestionView questionView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout);
        ButterKnife.bind(this);
        Question question = GsonUtil.GsonToBean("{\"questionId\":377,\"type\":1,\"content\":\"程序在第7行、第8行和第9行后是什么状态（a和b的取值如何）#include <stdio.h> \\nint main(void) { \\n    int a, b; \\n    a = 5; \\n    b=2; \\n    b=a; \\n   a=b; \\n   printf(\\\"%d %d\\\\n\\\", b, a); \\n    return 0; \\n} \",\"key\":\"\",\"socre\":15.0,\"testpaperId\":51,\"other\":0,\"analysis\":null,\"a\":\"A a=5,b=2\",\"b\":\"B a=2,b=5\",\"c\":\"C a=5,b=5\",\"d\":\"D a=2,b=2\"}", Question.class);
        questionView.setQuestion(question, 0, 1);
        questionView.setTestMode(false);

    }
}
