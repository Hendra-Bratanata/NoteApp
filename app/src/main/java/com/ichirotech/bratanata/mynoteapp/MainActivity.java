package com.ichirotech.bratanata.mynoteapp;

import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import Adapter.NoteAdapter;
import butterknife.BindView;
import butterknife.ButterKnife;

import static DataBaseContract.DatabaseContract.CONTENT_URI;
import static com.ichirotech.bratanata.mynoteapp.FormAddUpdateActivity.REQUEST_UPDATE;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
@BindView(R.id.rv_note)
    RecyclerView rvNote;
@BindView(R.id.progresbar)
    ProgressBar progressBar;
@BindView(R.id.fab_add)
    FloatingActionButton fabAdd;

private Cursor list;
private NoteAdapter adapter ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        getSupportActionBar().setTitle("Notes");
        rvNote.setLayoutManager(new LinearLayoutManager(this));
        rvNote.setHasFixedSize(true);

        fabAdd.setOnClickListener(this);

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
    private class LoadNoteAsync extends AsyncTask<Void, Void, Cursor> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);


        }

        @Override
        protected Cursor doInBackground(Void... voids) {
           return getContentResolver().query(CONTENT_URI,null,null,null,null);

        }

        @Override
        protected void onPostExecute(Cursor notes) {
            super.onPostExecute(notes);
            progressBar.setVisibility(View.GONE);

            list = notes ;
            adapter.setListNote(list);
            adapter.notifyDataSetChanged();

            if(list.getCount() == 0){

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


            }
        }
        else if(requestCode == REQUEST_UPDATE){
            if(resultCode == FormAddUpdateActivity.RESULT_UPDATE){
                new LoadNoteAsync().execute();
                showSnackbarMessage("Satu item  berhasil di ubah");

            }
            else if(resultCode == FormAddUpdateActivity.RESULT_DELETE){
                new LoadNoteAsync().execute();
                showSnackbarMessage("satu item berhasil di hapus");


            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
    private void  showSnackbarMessage(String text){
        Snackbar.make(rvNote,text,Snackbar.LENGTH_SHORT).show();
    }
}
