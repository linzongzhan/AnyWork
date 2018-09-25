// Generated code from Butter Knife. Do not modify!
package com.qgstudio.anywork.workout;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.qgstudio.anywork.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class AnswerCardAdapter$ItemHolder_ViewBinding implements Unbinder {
  private AnswerCardAdapter.ItemHolder target;

  @UiThread
  public AnswerCardAdapter$ItemHolder_ViewBinding(AnswerCardAdapter.ItemHolder target,
      View source) {
    this.target = target;

    target.text = Utils.findRequiredViewAsType(source, R.id.text, "field 'text'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    AnswerCardAdapter.ItemHolder target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.text = null;
  }
}
