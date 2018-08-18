// Generated code from Butter Knife. Do not modify!
package com.qgstudio.anywork.main;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.qgstudio.anywork.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class HomeActivity_ViewBinding implements Unbinder {
  private HomeActivity target;

  private View view2131624085;

  private View view2131624083;

  @UiThread
  public HomeActivity_ViewBinding(HomeActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public HomeActivity_ViewBinding(final HomeActivity target, View source) {
    this.target = target;

    View view;
    target.mToolbar = Utils.findRequiredViewAsType(source, R.id.toolbar, "field 'mToolbar'", Toolbar.class);
    target.mDrawerLayout = Utils.findRequiredViewAsType(source, R.id.drawer, "field 'mDrawerLayout'", DrawerLayout.class);
    target.mNavigationView = Utils.findRequiredViewAsType(source, R.id.navigation, "field 'mNavigationView'", NavigationView.class);
    view = Utils.findRequiredView(source, R.id.button_notice_hint_cancel, "field 'mBtnNoticeCancle' and method 'hideNoticeHint'");
    target.mBtnNoticeCancle = Utils.castView(view, R.id.button_notice_hint_cancel, "field 'mBtnNoticeCancle'", ImageView.class);
    view2131624085 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.hideNoticeHint();
      }
    });
    target.mTvNoticeHint = Utils.findRequiredViewAsType(source, R.id.textView_notice_home, "field 'mTvNoticeHint'", TextView.class);
    view = Utils.findRequiredView(source, R.id.notice_hint, "field 'mNoticeHint' and method 'toNoticeActivity'");
    target.mNoticeHint = view;
    view2131624083 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.toNoticeActivity();
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    HomeActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mToolbar = null;
    target.mDrawerLayout = null;
    target.mNavigationView = null;
    target.mBtnNoticeCancle = null;
    target.mTvNoticeHint = null;
    target.mNoticeHint = null;

    view2131624085.setOnClickListener(null);
    view2131624085 = null;
    view2131624083.setOnClickListener(null);
    view2131624083 = null;
  }
}
