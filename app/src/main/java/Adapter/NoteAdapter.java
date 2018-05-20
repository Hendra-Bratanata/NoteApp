package Adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ichirotech.bratanata.mynoteapp.FormAddUpdateActivity;
import com.ichirotech.bratanata.mynoteapp.R;

import java.util.LinkedList;

import Support.CostomOnItemClickListener;
import butterknife.BindView;
import butterknife.ButterKnife;
import entity.Note;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteViewHolder> {

        private LinkedList<Note> listNote;
        private Activity activity;

    public NoteAdapter(Activity activity) {
        this.activity = activity;
    }

    public LinkedList<Note> getListNote() {
        return listNote;
    }

    public void setListNote(LinkedList<Note> listNote) {
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
        holder.tvTitle.setText(getListNote().get(position).getTitle());
        holder.tvDate.setText(getListNote().get(position).getDate());
        holder.tvDesc.setText(getListNote().get(position).getDecs());

        holder.cvNote.setOnClickListener(new CostomOnItemClickListener(position, new CostomOnItemClickListener.OnItemClickCallBack() {
            @Override
            public void onItemClicked(View view, int pos) {
                Intent intent = new Intent(activity, FormAddUpdateActivity.class);
                intent.putExtra(FormAddUpdateActivity.EXTRA_POSITION,pos);
                intent.putExtra(FormAddUpdateActivity.EXTRA_NOTE,getListNote().get(pos));
                activity.startActivityForResult(intent,FormAddUpdateActivity.REQUEST_UPDATE);

            }
        }));
    }

    @Override
    public int getItemCount() {
        return getListNote().size();
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
}
