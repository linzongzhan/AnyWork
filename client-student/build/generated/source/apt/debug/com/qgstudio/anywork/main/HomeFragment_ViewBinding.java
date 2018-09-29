// Generated code from Butter Knife. Do not modify!
package com.qgstudio.anywork.main;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
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

  private View view2131624210;

  private View view2131624194;

  private View view2131624197;

  private View view2131624200;

  private View view2131624203;

  private View view2131624207;

  private View view2131624206;

  @UiThread
  public HomeFragment_ViewBinding(final HomeFragment target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.btn_my_class, "field 'btnMyClass' and method 'clickMyClass'");
    target.btnMyClass = Utils.castView(view, R.id.btn_my_class, "field 'btnMyClass'", TextView.class);
    view2131624210 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.clickMyClass();
      }
    });
    target.toolbar = Utils.findRequiredView(source, R.id.toolbar, "field 'toolbar'");
    target.tvOnlineCount = Utils.findRequiredViewAsType(source, R.id.tv_online_count, "field 'tvOnlineCount'", TextView.class);
    target.topView = Utils.findRequiredView(source, R.id.top_view, "field 'topView'");
    target.emptyView = Utils.findRequiredView(source, R.id.empty_view, "field 'emptyView'");
    target.recyclerView = Utils.findRequiredViewAsType(source, R.id.recycler_view_notice, "field 'recyclerView'", RecyclerView.class);
    view = Utils.findRequiredView(source, R.id.btn_preview, "method 'clickPreview'");
    view2131624194 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.clickPreview();
      }
    });
    view = Utils.findRequiredView(source, R.id.btn_exercise, "method 'clickExercise'");
    view2131624197 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.clickExercise();
      }
    });
    view = Utils.findRequiredView(source, R.id.btn_exam, "method 'clickExam'");
    view2131624200 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.clickExam();
      }
    });
    view = Utils.findRequiredView(source, R.id.btn_collection, "method 'clickCollection'");
    view2131624203 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.clickCollection();
      }
    });
    view = Utils.findRequiredView(source, R.id.btn_notice_all, "method 'clickNoticeAll'");
    view2131624207 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.clickNoticeAll();
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_notice, "method 'clickNoticeText'");
    view2131624206 = view;
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
    target.tvOnlineCount = null;
    target.topView = null;
    target.emptyView = null;
    target.recyclerView = null;

    view2131624210.setOnClickListener(null);
    view2131624210 = null;
    view2131624194.setOnClickListener(null);
    view2131624194 = null;
    view2131624197.setOnClickListener(null);
    view2131624197 = null;
    view2131624200.setOnClickListener(null);
    view2131624200 = null;
    view2131624203.setOnClickListener(null);
    view2131624203 = null;
    view2131624207.setOnClickListener(null);
    view2131624207 = null;
    view2131624206.setOnClickListener(null);
    view2131624206 = null;
  }
}
