// Generated code from Butter Knife. Do not modify!
package com.qgstudio.anywork.user;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.qgstudio.anywork.R;
import de.hdodenhof.circleimageview.CircleImageView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class UserActivity_ViewBinding implements Unbinder {
  private UserActivity target;

  private View view2131558547;

  private View view2131558548;

  private View view2131558552;

  @UiThread
  public UserActivity_ViewBinding(UserActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public UserActivity_ViewBinding(final UserActivity target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.head_pic, "field 'pic' and method 'changePic'");
    target.pic = Utils.castView(view, R.id.head_pic, "field 'pic'", CircleImageView.class);
    view2131558547 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.changePic();
      }
    });
    target.name = Utils.findRequiredViewAsType(source, R.id.name, "field 'name'", EditText.class);
    target.email = Utils.findRequiredViewAsType(source, R.id.mail, "field 'email'", TextView.class);
    target.phone = Utils.findRequiredViewAsType(source, R.id.phone, "field 'phone'", EditText.class);
    view = Utils.findRequiredView(source, R.id.edit, "field 'edit' and method 'edit'");
    target.edit = Utils.castView(view, R.id.edit, "field 'edit'", Button.class);
    view2131558548 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.edit();
      }
    });
    view = Utils.findRequiredView(source, R.id.exit, "method 'exit'");
    view2131558552 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.exit();
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    UserActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.pic = null;
    target.name = null;
    target.email = null;
    target.phone = null;
    target.edit = null;

    view2131558547.setOnClickListener(null);
    view2131558547 = null;
    view2131558548.setOnClickListener(null);
    view2131558548 = null;
    view2131558552.setOnClickListener(null);
    view2131558552 = null;
  }
}