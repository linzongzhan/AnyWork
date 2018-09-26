// Generated code from Butter Knife. Do not modify!
package com.qgstudio.anywork.enter;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.FrameLayout;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.qgstudio.anywork.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class EnterActivity_ViewBinding implements Unbinder {
  private EnterActivity target;

  private View view2131624103;

  private View view2131624104;

  private View view2131624105;

  @UiThread
  public EnterActivity_ViewBinding(EnterActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public EnterActivity_ViewBinding(final EnterActivity target, View source) {
    this.target = target;

    View view;
    target.container = Utils.findRequiredViewAsType(source, R.id.container, "field 'container'", FrameLayout.class);
    view = Utils.findRequiredView(source, R.id.register, "method 'intoRegister'");
    view2131624103 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.intoRegister();
      }
    });
    view = Utils.findRequiredView(source, R.id.login, "method 'intoLogin'");
    view2131624104 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.intoLogin();
      }
    });
    view = Utils.findRequiredView(source, R.id.others, "method 'intoOthers'");
    view2131624105 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.intoOthers();
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    EnterActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.container = null;

    view2131624103.setOnClickListener(null);
    view2131624103 = null;
    view2131624104.setOnClickListener(null);
    view2131624104 = null;
    view2131624105.setOnClickListener(null);
    view2131624105 = null;
  }
}
