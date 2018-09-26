// Generated code from Butter Knife. Do not modify!
package com.qgstudio.anywork.enter.login;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.qgstudio.anywork.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class LoginFragment_ViewBinding implements Unbinder {
  private LoginFragment target;

  private View view2131624194;

  private View view2131624187;

  @UiThread
  public LoginFragment_ViewBinding(final LoginFragment target, View source) {
    this.target = target;

    View view;
    target.account = Utils.findRequiredViewAsType(source, R.id.account, "field 'account'", EditText.class);
    target.password = Utils.findRequiredViewAsType(source, R.id.password, "field 'password'", EditText.class);
    view = Utils.findRequiredView(source, R.id.sign_in, "field 'btnLogin' and method 'login'");
    target.btnLogin = Utils.castView(view, R.id.sign_in, "field 'btnLogin'", Button.class);
    view2131624194 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.login();
      }
    });
    view = Utils.findRequiredView(source, R.id.btn_to_register, "method 'toRegister'");
    view2131624187 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.toRegister();
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    LoginFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.account = null;
    target.password = null;
    target.btnLogin = null;

    view2131624194.setOnClickListener(null);
    view2131624194 = null;
    view2131624187.setOnClickListener(null);
    view2131624187 = null;
  }
}
