// Generated code from Butter Knife. Do not modify!
package com.qgstudio.anywork.workout;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.qgstudio.anywork.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class WorkoutCatalogFragment_ViewBinding implements Unbinder {
  private WorkoutCatalogFragment target;

  private View view2131624104;

  private View view2131624254;

  @UiThread
  public WorkoutCatalogFragment_ViewBinding(final WorkoutCatalogFragment target, View source) {
    this.target = target;

    View view;
    target.toolbar = Utils.findRequiredViewAsType(source, R.id.workout_frag_toolbar, "field 'toolbar'", Toolbar.class);
    target.recyclerView = Utils.findRequiredViewAsType(source, R.id.recycler_view_workout, "field 'recyclerView'", RecyclerView.class);
    view = Utils.findRequiredView(source, R.id.btn_back, "method 'clickBack'");
    view2131624104 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.clickBack();
      }
    });
    view = Utils.findRequiredView(source, R.id.btn_search, "method 'clickSearch'");
    view2131624254 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.clickSearch();
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    WorkoutCatalogFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.toolbar = null;
    target.recyclerView = null;

    view2131624104.setOnClickListener(null);
    view2131624104 = null;
    view2131624254.setOnClickListener(null);
    view2131624254 = null;
  }
}
