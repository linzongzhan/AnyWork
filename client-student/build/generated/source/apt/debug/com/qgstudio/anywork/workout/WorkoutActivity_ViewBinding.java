// Generated code from Butter Knife. Do not modify!
package com.qgstudio.anywork.workout;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.qgstudio.anywork.R;
import com.qgstudio.anywork.widget.QuestionView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class WorkoutActivity_ViewBinding implements Unbinder {
  private WorkoutActivity target;

  @UiThread
  public WorkoutActivity_ViewBinding(WorkoutActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public WorkoutActivity_ViewBinding(WorkoutActivity target, View source) {
    this.target = target;

    target.questionView = Utils.findRequiredViewAsType(source, R.id.question_view, "field 'questionView'", QuestionView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    WorkoutActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.questionView = null;
  }
}
