// Generated code from Butter Knife. Do not modify!
package com.qgstudio.anywork.user;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.qgstudio.anywork.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ChangeInfoActivity_ViewBinding implements Unbinder {
  private ChangeInfoActivity target;

  private View view2131624149;

  private View view2131624098;

  @UiThread
  public ChangeInfoActivity_ViewBinding(ChangeInfoActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public ChangeInfoActivity_ViewBinding(final ChangeInfoActivity target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.edit, "method 'edit'");
    view2131624149 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.edit();
      }
    });
    view = Utils.findRequiredView(source, R.id.imageView_head, "method 'changePic'");
    view2131624098 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.changePic();
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    target = null;


    view2131624149.setOnClickListener(null);
    view2131624149 = null;
    view2131624098.setOnClickListener(null);
    view2131624098 = null;
  }
}
