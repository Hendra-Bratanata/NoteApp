package com.ichirotech.bratanata.mynoteapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import DataBaseContract.NoteHelper;
import butterknife.BindView;
import butterknife.ButterKnife;
import entity.Note;

public class FormAddUpdateActivity extends AppCompatActivity implements View.OnClickListener {
@BindView(R.id.edt_desc)
    EditText edtDesc;
@BindView(R.id.edt_judul)
    EditText edtJudul;
@BindView(R.id.btn_subMit)
    Button btnSubmit;

    public static String EXTRA_NOTE = "extra note";
    public static String EXTRA_POSITION = "extra position";

    private  boolean isEdit=false;
    public static int REQUES_ADD = 100;
    public static int RESULT_ADD = 101;
    public static int REQUEST_UPDATE = 200;
    public static int RESULT_UPDATE = 201;
    public static int RESULT_DELETE =301;

    private Note note;
    private  int position;
    private NoteHelper noteHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_add_update);
        ButterKnife.bind(this);

        btnSubmit.setOnClickListener(this);
        noteHelper = new NoteHelper(this);
        noteHelper.open();
        note = getIntent().getParcelableExtra(EXTRA_NOTE);

        if(note != null) {
            position = getIntent().getIntExtra(EXTRA_POSITION, 0);
            isEdit = true;


        }
        String actionBarTitle = null;
        String btnTitle = null;
        if(isEdit){
                actionBarTitle = "Ubah";
                btnTitle = "Update";
                edtJudul.setText(note.getTitle());
                edtDesc.setText(note.getDecs());

            }else {
                actionBarTitle ="Tambah";
                btnTitle = "Simpan";
            }
            getSupportActionBar().setTitle(actionBarTitle);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            btnSubmit.setText(btnTitle);

        }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(noteHelper != null){
            noteHelper.close();
        }
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btn_subMit){
            String title =edtJudul.getText().toString().trim();
            String desc = edtDesc.getText().toString().trim();
            boolean isEmpety = false;

            if(TextUtils.isEmpty(title)){
                isEmpety = true;
                edtJudul.setError("Tidak Boleh Kosong");

            }
            if(!isEmpety){
                Note newNote = new Note();
                newNote.setTitle(title);
                newNote.setDecs(desc);

                Intent intent = new Intent();

                if(isEdit){
                    newNote.setDate(note.getDate());
                    newNote.setId(note.getId());
                    noteHelper.update(newNote);

                    intent.putExtra(EXTRA_POSITION,position);
                    setResult(RESULT_UPDATE,intent);
                    finish();
                }else{
                    newNote.setDate(getCurrentDate());
                    noteHelper.insert(newNote);
                    setResult(RESULT_ADD);
                    finish();
                }
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if(isEdit){
            getMenuInflater().inflate(R.menu.menu_form,menu);
        }

        return super.onCreateOptionsMenu(menu);


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_delete:
                showAlertDialog(ALERT_DIALOG_DELETE);
                break;
            case R.id.home:
                showAlertDialog(ALERT_DIALOG_CLOSE);
                break;


        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        showAlertDialog(ALERT_DIALOG_CLOSE);

    }

    final int ALERT_DIALOG_DELETE = 20;
    final int ALERT_DIALOG_CLOSE = 10;

    private void   showAlertDialog(int type){
        final boolean isDialogClose = type == ALERT_DIALOG_CLOSE;
        String dialogTitle = null, dialogMessage = null;

        if(isDialogClose){
            dialogTitle = "Batal";
            dialogMessage = "Apahkah anda ingin membatalkan peubahan pada form?";
        }else {
            dialogMessage = "Apahkah anda yakin ingin menghapus item ini?";
            dialogTitle ="Hapus Note";
        }
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle(dialogTitle);
        alertDialogBuilder
                .setMessage(dialogMessage)
                .setCancelable(false)
                .setPositiveButton("ya", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(isDialogClose){
                            finish();
                        }else {
                            noteHelper.delete(note.getId());
                            Intent intent = new Intent();
                            intent.putExtra(EXTRA_POSITION,position);
                            setResult(RESULT_DELETE,intent);
                            finish();
                        }
                    }
                }).setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
    private String getCurrentDate(){
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
    }
}
