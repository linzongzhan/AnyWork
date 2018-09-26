// Generated code from Butter Knife. Do not modify!
package com.qgstudio.anywork.main;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.qgstudio.anywork.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class HomeFragment_ViewBinding implements Unbinder {
  private HomeFragment target;

  private View view2131624183;

  private View view2131624181;

  private View view2131624180;

  @UiThread
  public HomeFragment_ViewBinding(final HomeFragment target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.btn_my_class, "field 'btnMyClass' and method 'clickMyClass'");
    target.btnMyClass = Utils.castView(view, R.id.btn_my_class, "field 'btnMyClass'", TextView.class);
    view2131624183 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.clickMyClass();
      }
    });
    target.toolbar = Utils.findRequiredView(source, R.id.toolbar, "field 'toolbar'");
    view = Utils.findRequiredView(source, R.id.btn_notice_all, "method 'clickNoticeAll'");
    view2131624181 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.clickNoticeAll();
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_notice, "method 'clickNoticeText'");
    view2131624180 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.clickNoticeText();
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    HomeFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.btnMyClass = null;
    target.toolbar = null;

    view2131624183.setOnClickListener(null);
    view2131624183 = null;
    view2131624181.setOnClickListener(null);
    view2131624181 = null;
    view2131624180.setOnClickListener(null);
    view2131624180 = null;
  }
}
