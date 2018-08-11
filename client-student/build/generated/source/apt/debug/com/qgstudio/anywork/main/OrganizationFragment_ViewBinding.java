// Generated code from Butter Knife. Do not modify!
package com.qgstudio.anywork.main;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.qgstudio.anywork.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class OrganizationFragment_ViewBinding implements Unbinder {
  private OrganizationFragment target;

  @UiThread
  public OrganizationFragment_ViewBinding(OrganizationFragment target, View source) {
    this.target = target;

    target.mRecyclerView = Utils.findRequiredViewAsType(source, R.id.recycler_all, "field 'mRecyclerView'", RecyclerView.class);
    target.mSwipeRefreshLayout = Utils.findRequiredViewAsType(source, R.id.refresh, "field 'mSwipeRefreshLayout'", SwipeRefreshLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    OrganizationFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mRecyclerView = null;
    target.mSwipeRefreshLayout = null;
  }
}
