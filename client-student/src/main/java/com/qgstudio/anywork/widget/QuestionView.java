package com.qgstudio.anywork.widget;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.qgstudio.anywork.R;
import com.qgstudio.anywork.data.model.Question;
import com.qgstudio.anywork.exam.adapters.ChoiceAdapter;
import com.qgstudio.anywork.utils.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

public class QuestionView extends FrameLayout {
    Question mQuestion;

    TextView tvQuestionInfo;

    TextView tvQuestionContent;

    RecyclerView recyclerViewQuestionSelections;

    EditText editTextQuestionFillBlank;

    TextView btnAnswerControl;

    TextView tvAnswer;
    TextView tvAnswerInvisible;
    Drawable answerShowIcon;
    Drawable answerHideIcon;
    private boolean isAnswerBottomShowing;
    ValueAnimator showAnimator;
    ChoiceAdapter choiceAdapter;
    private boolean isTestMode = true;

    public QuestionView(@NonNull Context context) {
        super(context);
        init();
    }

    public QuestionView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public QuestionView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.view_workout, this, false);
        addView(view);
        //find view 为啥这里不用@BindView呢？，你懂的
        tvQuestionInfo = view.findViewById(R.id.tv_question_info);
        tvQuestionContent = view.findViewById(R.id.tv_question_content);
        recyclerViewQuestionSelections = view.findViewById(R.id.recycler_view_question_selections);
        editTextQuestionFillBlank = view.findViewById(R.id.editTv_question_fill_blank);
        tvAnswer = view.findViewById(R.id.tv_answer);
        btnAnswerControl = view.findViewById(R.id.btn_answer_control);
        tvAnswerInvisible = view.findViewById(R.id.tv_answer_invisible);
        //取图
        answerShowIcon = getContext().getResources().getDrawable(R.drawable.icon_on);
        answerHideIcon = getContext().getResources().getDrawable(R.drawable.icon_off);
        //设置初始icon
        setDrawableBounds(40);
        setBtnAnswerControlIcon(answerHideIcon);
        //默认隐藏答案
        tvAnswer.setVisibility(View.GONE);
        //设置answerControl监听
        btnAnswerControl.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleShowAnswerBottom();
            }
        });

        if (isTestMode) {
            tvAnswer.setVisibility(View.GONE);
            btnAnswerControl.setVisibility(GONE);
        } else {
            btnAnswerControl.setVisibility(VISIBLE);
        }

    }


    public void showAnswerBottom() {
        if (!isAnswerBottomShowing) {
            toggleShowAnswerBottom();
        }
    }

    public void hideAnswerBottom() {
        if (isAnswerBottomShowing) {
            toggleShowAnswerBottom();
        }
    }

    /**
     * 设置题目
     *
     * @param question
     */
    public void setQuestion(Question question, int pos, int sum) {
        mQuestion = question;
        setAnswerBottom(question.getKey());//设置解析
        setQuestionContent(mQuestion.getContent());//设置题目内容
        setPosition(pos, sum);
        choiceAdapter = new ChoiceAdapter(getContext(), mQuestion, pos);
        recyclerViewQuestionSelections.setAdapter(choiceAdapter);
        recyclerViewQuestionSelections.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    /**
     * 没有调用此方法则不显示题号相关信息
     *
     * @param pos 当前题号
     * @param sum 题目总数
     */
    public void setPosition(int pos, int sum) {
        Question.Type type = null;
        if (mQuestion != null) {
            type = mQuestion.getEnumType();
        }
        tvQuestionInfo.setText(pos + 1 + "/" + sum + "  (" + (type != null ? type.string : "") + ")");
    }

    public void setQuestionInfo(String info) {
        tvQuestionInfo.setText(info);
    }

    public void setQuestionContent(String content) {
        tvQuestionContent.setText(content);
    }

    private void toggleShowAnswerBottom() {
        //反转
        isAnswerBottomShowing = !isAnswerBottomShowing;

        if (showAnimator == null) {
            showAnimator = ValueAnimator.ofInt(0, tvAnswerInvisible.getHeight());
            showAnimator.setDuration(200);
            showAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
            showAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    ViewGroup.LayoutParams layoutParams = tvAnswer.getLayoutParams();
                    layoutParams.height = (int) animation.getAnimatedValue();
                    tvAnswer.setLayoutParams(layoutParams);
                }
            });
            showAnimator.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {
                    //show的动画，则先设置为可见
                    if (isAnswerBottomShowing) {
                        tvAnswer.setVisibility(View.VISIBLE);
                    }
                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    //hide动画，则最后设置gone
                    if (!isAnswerBottomShowing) {
                        tvAnswer.setVisibility(View.GONE);
                    }
                }

                @Override
                public void onAnimationCancel(Animator animation) {

                }

                @Override
                public void onAnimationRepeat(Animator animation) {

                }
            });
        }

        if (isAnswerBottomShowing) {
            showAnimator.start();
            btnAnswerControl.setText("收起解析");
            setBtnAnswerControlIcon(answerShowIcon);
        } else {
            btnAnswerControl.setText("展开解析");
            setBtnAnswerControlIcon(answerHideIcon);
            showAnimator.reverse();
        }
    }

    private void setAnswerBottom(String answer) {
        tvAnswer.setText(answer);
        tvAnswerInvisible.setText(answer);
    }

    private void setDrawableBounds(int param) {
        answerShowIcon.setBounds(0, 0, param, param);
        answerHideIcon.setBounds(0, 0, param, param);
    }

    private void setBtnAnswerControlIcon(Drawable drawable) {
        Drawable[] drawables = btnAnswerControl.getCompoundDrawables();
        btnAnswerControl.setCompoundDrawables(drawables[0], drawables[1], drawable, drawables[3]);
    }

    public void setTestMode(boolean enable) {
        isTestMode = enable;
        if (isTestMode) {
            tvAnswer.setVisibility(View.GONE);
            btnAnswerControl.setVisibility(GONE);
        } else {
            tvAnswer.setVisibility(View.GONE);
            btnAnswerControl.setVisibility(VISIBLE);
            btnAnswerControl.setText("展开解析");
            setBtnAnswerControlIcon(answerHideIcon);
        }
    }
}
