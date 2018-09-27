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

  private View view2131624129;

  private View view2131624130;

  private View view2131624135;

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
    view2131624129 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.changePic();
      }
    });
    target.name = Utils.findRequiredViewAsType(source, R.id.name, "field 'name'", TextView.class);
    target.studentId = Utils.findRequiredViewAsType(source, R.id.student_id, "field 'studentId'", TextView.class);
    target.email = Utils.findRequiredViewAsType(source, R.id.email, "field 'email'", EditText.class);
    target.phone = Utils.findRequiredViewAsType(source, R.id.phone, "field 'phone'", EditText.class);
    view = Utils.findRequiredView(source, R.id.edit, "field 'edit' and method 'edit'");
    target.edit = Utils.castView(view, R.id.edit, "field 'edit'", Button.class);
    view2131624130 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.edit();
      }
    });
    view = Utils.findRequiredView(source, R.id.exit, "method 'exit'");
    view2131624135 = view;
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
    target.studentId = null;
    target.email = null;
    target.phone = null;
    target.edit = null;

    view2131624129.setOnClickListener(null);
    view2131624129 = null;
    view2131624130.setOnClickListener(null);
    view2131624130 = null;
    view2131624135.setOnClickListener(null);
    view2131624135 = null;
  }
}
