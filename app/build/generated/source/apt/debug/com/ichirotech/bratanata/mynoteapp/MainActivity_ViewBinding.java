// Generated code from Butter Knife. Do not modify!
package com.ichirotech.bratanata.mynoteapp;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;

public class MainActivity_ViewBinding implements Unbinder {
  private MainActivity target;

  @UiThread
  public MainActivity_ViewBinding(MainActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public MainActivity_ViewBinding(MainActivity target, View source) {
    this.target = target;

    target.rvNote = Utils.findRequiredViewAsType(source, R.id.rv_note, "field 'rvNote'", RecyclerView.class);
    target.progressBar = Utils.findRequiredViewAsType(source, R.id.progresbar, "field 'progressBar'", ProgressBar.class);
    target.fabAdd = Utils.findRequiredViewAsType(source, R.id.fab_add, "field 'fabAdd'", FloatingActionButton.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    MainActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.rvNote = null;
    target.progressBar = null;
    target.fabAdd = null;
  }
}
