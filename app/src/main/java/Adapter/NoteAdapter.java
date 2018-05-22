package Adapter;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ichirotech.bratanata.mynoteapp.FormAddUpdateActivity;
import com.ichirotech.bratanata.mynoteapp.R;

import java.net.URI;
import java.util.LinkedList;

import DataBaseContract.DatabaseContract;
import Support.CostomOnItemClickListener;
import butterknife.BindView;
import butterknife.ButterKnife;
import entity.Note;

import static DataBaseContract.DatabaseContract.CONTENT_URI;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteViewHolder> {

        private Cursor listNote;
        private Activity activity;

    public NoteAdapter(Activity activity) {
        this.activity = activity;
    }

    public Cursor getListNote() {
        return listNote;
    }

    public void setListNote(Cursor listNote) {
        this.listNote = listNote;
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_note,parent,false);

        return new NoteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, final int position) {
        final Note note =   getItem(position);
        holder.tvTitle.setText(note.getTitle());
        holder.tvDate.setText(note.getDate());
        holder.tvDesc.setText(note.getDecs());

        holder.cvNote.setOnClickListener(new CostomOnItemClickListener(position, new CostomOnItemClickListener.OnItemClickCallBack() {
            @Override
            public void onItemClicked(View view, int pos) {
                Intent intent = new Intent(activity, FormAddUpdateActivity.class);
                Uri uri =Uri.parse(CONTENT_URI+"/"+note.getId());
                intent.setData(uri);
                activity.startActivityForResult(intent,FormAddUpdateActivity.RESULT_UPDATE);

            }
        }));
    }

    @Override
    public int getItemCount() {
        if(listNote == null)return 0;
        return listNote.getCount();
    }

    public class NoteViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_item_title)
        TextView tvTitle;
        @BindView(R.id.tv_item_date)
        TextView tvDate;
        @BindView(R.id.tv_item_desc)
        TextView tvDesc;
        @BindView(R.id.cv_item_note)
        CardView cvNote;


        public NoteViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);

        }
    }
    private Note getItem(int position){
        if (!listNote.moveToPosition(position)) {
            throw new IllegalStateException("Position invalid");
        }
        return new Note(listNote);
    }
}
