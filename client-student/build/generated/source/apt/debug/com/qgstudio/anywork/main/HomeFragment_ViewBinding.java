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

  private View view2131624177;

  private View view2131624163;

  private View view2131624166;

  private View view2131624169;

  private View view2131624172;

  private View view2131624175;

  @UiThread
  public HomeFragment_ViewBinding(final HomeFragment target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.btn_my_class, "field 'btnMyClass' and method 'clickMyClass'");
    target.btnMyClass = Utils.castView(view, R.id.btn_my_class, "field 'btnMyClass'", TextView.class);
    view2131624177 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.clickMyClass();
      }
    });
    target.toolbar = Utils.findRequiredView(source, R.id.toolbar, "field 'toolbar'");
    target.tvOnlineCount = Utils.findRequiredViewAsType(source, R.id.tv_online_count, "field 'tvOnlineCount'", TextView.class);
    view = Utils.findRequiredView(source, R.id.btn_preview, "method 'clickPreview'");
    view2131624163 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.clickPreview();
      }
    });
    view = Utils.findRequiredView(source, R.id.btn_exercise, "method 'clickExercise'");
    view2131624166 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.clickExercise();
      }
    });
    view = Utils.findRequiredView(source, R.id.btn_exam, "method 'clickExam'");
    view2131624169 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.clickExam();
      }
    });
    view = Utils.findRequiredView(source, R.id.btn_collection, "method 'clickCollection'");
    view2131624172 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.clickCollection();
      }
    });
    view = Utils.findRequiredView(source, R.id.btn_notice_all, "method 'clickNoticeAll'");
    view2131624175 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.clickNoticeAll();
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
    target.tvOnlineCount = null;

    view2131624177.setOnClickListener(null);
    view2131624177 = null;
    view2131624163.setOnClickListener(null);
    view2131624163 = null;
    view2131624166.setOnClickListener(null);
    view2131624166 = null;
    view2131624169.setOnClickListener(null);
    view2131624169 = null;
    view2131624172.setOnClickListener(null);
    view2131624172 = null;
    view2131624175.setOnClickListener(null);
    view2131624175 = null;
  }
}
