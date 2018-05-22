package adapter;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.ichirotech.bratanata.ichironoteapp.DatabaseContract;
import com.ichirotech.bratanata.ichironoteapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.ichirotech.bratanata.ichironoteapp.DatabaseContract.NoteColumns.DATE;
import static com.ichirotech.bratanata.ichironoteapp.DatabaseContract.NoteColumns.DESC;
import static com.ichirotech.bratanata.ichironoteapp.DatabaseContract.NoteColumns.TITLE;
import static com.ichirotech.bratanata.ichironoteapp.DatabaseContract.getColumnString;

public class IchiroNotesAdapter extends CursorAdapter {
@BindView(R.id.tv_item_date)
    TextView tvItemDate;
@BindView(R.id.tv_item_desc)
    TextView tvItemDesc;
@BindView(R.id.tv_item_title)
    TextView tvItemTitle;

    public IchiroNotesAdapter(Context context, Cursor c,boolean autoRequery) {
        super(context, c,autoRequery);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_ichiro_note, parent, false);
        return view;
    }
    @Override
    public Cursor getCursor() {
        return super.getCursor();
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        if(cursor != null){
            ButterKnife.bind(this,view);
            tvItemTitle.setText(getColumnString(cursor, TITLE));
            tvItemDesc.setText(getColumnString(cursor,DESC));
            tvItemDate.setText(getColumnString(cursor,DATE));
        }

    }
}
