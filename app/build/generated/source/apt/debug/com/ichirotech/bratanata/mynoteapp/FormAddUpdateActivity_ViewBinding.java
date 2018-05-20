// Generated code from Butter Knife. Do not modify!
package com.ichirotech.bratanata.mynoteapp;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;

public class FormAddUpdateActivity_ViewBinding implements Unbinder {
  private FormAddUpdateActivity target;

  @UiThread
  public FormAddUpdateActivity_ViewBinding(FormAddUpdateActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public FormAddUpdateActivity_ViewBinding(FormAddUpdateActivity target, View source) {
    this.target = target;

    target.edtDesc = Utils.findRequiredViewAsType(source, R.id.edt_desc, "field 'edtDesc'", EditText.class);
    target.edtJudul = Utils.findRequiredViewAsType(source, R.id.edt_judul, "field 'edtJudul'", EditText.class);
    target.btnSubmit = Utils.findRequiredViewAsType(source, R.id.btn_subMit, "field 'btnSubmit'", Button.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    FormAddUpdateActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.edtDesc = null;
    target.edtJudul = null;
    target.btnSubmit = null;
  }
}
