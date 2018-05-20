// Generated code from Butter Knife. Do not modify!
package Adapter;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.ichirotech.bratanata.mynoteapp.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class NoteAdapter$NoteViewHolder_ViewBinding implements Unbinder {
  private NoteAdapter.NoteViewHolder target;

  @UiThread
  public NoteAdapter$NoteViewHolder_ViewBinding(NoteAdapter.NoteViewHolder target, View source) {
    this.target = target;

    target.tvTitle = Utils.findRequiredViewAsType(source, R.id.tv_item_title, "field 'tvTitle'", TextView.class);
    target.tvDate = Utils.findRequiredViewAsType(source, R.id.tv_item_date, "field 'tvDate'", TextView.class);
    target.tvDesc = Utils.findRequiredViewAsType(source, R.id.tv_item_desc, "field 'tvDesc'", TextView.class);
    target.cvNote = Utils.findRequiredViewAsType(source, R.id.cv_item_note, "field 'cvNote'", CardView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    NoteAdapter.NoteViewHolder target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.tvTitle = null;
    target.tvDate = null;
    target.tvDesc = null;
    target.cvNote = null;
  }
}
