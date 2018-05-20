package com.ichirotech.bratanata.mynoteapp;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.LinkedList;

import Adapter.NoteAdapter;
import DataBaseContract.NoteHelper;
import butterknife.BindView;
import butterknife.ButterKnife;
import entity.Note;

import static com.ichirotech.bratanata.mynoteapp.FormAddUpdateActivity.REQUEST_UPDATE;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
@BindView(R.id.rv_note)
    RecyclerView rvNote;
@BindView(R.id.progresbar)
    ProgressBar progressBar;
@BindView(R.id.fab_add)
    FloatingActionButton fabAdd;

private LinkedList<Note> list;
private NoteAdapter adapter ;
private NoteHelper helper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        getSupportActionBar().setTitle("Notes");
        rvNote.setLayoutManager(new LinearLayoutManager(this));
        rvNote.setHasFixedSize(true);

        fabAdd.setOnClickListener(this);
        helper = new NoteHelper(this);
        helper.open();
        list = new LinkedList<>();
        adapter = new NoteAdapter(this);
        adapter.setListNote(list);
        rvNote.setAdapter(adapter);

        new LoadNoteAsync().execute();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.fab_add){
            Intent intent = new Intent(MainActivity.this,FormAddUpdateActivity.class);
            startActivityForResult(intent,FormAddUpdateActivity.REQUES_ADD);
        }
    }
    private class LoadNoteAsync extends AsyncTask<Void ,Void ,ArrayList<Note>>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);

            if(list.size() > 0){
                list.clear();
            }
        }

        @Override
        protected ArrayList<Note> doInBackground(Void... voids) {
            return helper.query();
        }

        @Override
        protected void onPostExecute(ArrayList<Note> notes) {
            super.onPostExecute(notes);
            progressBar.setVisibility(View.GONE);
            list.addAll(notes);
            adapter.setListNote(list);
            adapter.notifyDataSetChanged();

            if(list.size() == 0){
                showSnackbarMessage("tidak ada data saat ini");
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == FormAddUpdateActivity.REQUES_ADD){
            if(resultCode == FormAddUpdateActivity.RESULT_ADD){
                new LoadNoteAsync().execute();
                showSnackbarMessage("Satu item sudah ditambahkan");
            rvNote.getLayoutManager().smoothScrollToPosition(rvNote,new RecyclerView.State(),0);

            }
        }
        else if(requestCode == REQUEST_UPDATE){
            if(resultCode == FormAddUpdateActivity.RESULT_UPDATE){
                new LoadNoteAsync().execute();
                showSnackbarMessage("Satu item  berhasil di ubah");
                int position =data.getIntExtra(FormAddUpdateActivity.EXTRA_POSITION,0);
                rvNote.getLayoutManager().smoothScrollToPosition(rvNote,new RecyclerView.State(),position);
            }
            else if(resultCode == FormAddUpdateActivity.RESULT_DELETE){
                int position = data.getIntExtra(FormAddUpdateActivity.EXTRA_POSITION,0);
                list.remove(position);
                adapter.setListNote(list);
                adapter.notifyDataSetChanged();
                showSnackbarMessage("satu item berhasil di hapus");

            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(helper != null){
            helper.close();
        }
    }
    private void  showSnackbarMessage(String text){
        Snackbar.make(rvNote,text,Snackbar.LENGTH_SHORT).show();
    }
}
