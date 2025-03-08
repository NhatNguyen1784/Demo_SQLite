package com.hcmute.appnote;

import android.app.Dialog;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rvNotes;
    private DatabaseHandler databaseHandler;

    private NoteAdapter noteAdapter;
    private ArrayList<Note> listNotes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        anhxa();
        initDatabaseSqlite();
        insertData();
        getData();

        noteAdapter = new NoteAdapter(this, listNotes);
        rvNotes.setAdapter(noteAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvNotes.setLayoutManager(linearLayoutManager);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_note, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.menuAddNotes){
            DialogThem();
        }
        return super.onOptionsItemSelected(item);
    }

    public void DialogThem(){
        Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_add_note);

        // anh xa cac item tren dialog
        EditText edtNameNote = (EditText) dialog.findViewById(R.id.edtNoteName);
        Button btnEdit = (Button) dialog.findViewById(R.id.btnEdit);
        Button btnCancel = (Button) dialog.findViewById(R.id.btnCancel);

        dialog.show();
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        // set su kien khi bam nut Edit va Cancel
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameNote = edtNameNote.getText().toString();
                if(nameNote.equals("")){
                    Toast.makeText(MainActivity.this, "Please enter a name note", Toast.LENGTH_LONG).show();
                }
                else {
                    databaseHandler.queryData("INSERT INTO Notes (NameNotes) VALUES('"+ nameNote +"')");
                    Toast.makeText(MainActivity.this, "Add note successfully", Toast.LENGTH_LONG).show();
                    dialog.dismiss();

                    // goi ham de refresh data
                    getData();
                    noteAdapter.notifyDataSetChanged();
                }

            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
    }

    // Tao bang
    private void initDatabaseSqlite(){
        databaseHandler = new DatabaseHandler(this, "note.sqlite", null, 1);
        databaseHandler.queryData("CREATE TABLE IF NOT EXISTS Notes(Id INTEGER Primary key AUTOINCREMENT, NameNotes VARCHAR(200))");
    }

    // insert data vao bang
    private void insertData(){
        databaseHandler.queryData("INSERT INTO Notes (NameNotes) VALUES ('Lau Nha Met')");
        databaseHandler.queryData("INSERT INTO Notes (NameNotes) VALUES ('Rua Bat')");
        databaseHandler.queryData("INSERT INTO Notes (NameNotes) VALUES ('Giat Do')");
        databaseHandler.queryData("INSERT INTO Notes (NameNotes) VALUES ('Truc Sinh')");
        databaseHandler.queryData("INSERT INTO Notes (NameNotes) VALUES ('Truc Sinh')");
        databaseHandler.queryData("INSERT INTO Notes (NameNotes) VALUES ('Truc Sinh')");
    }

    // lay data tu bang
    private void getData(){
        String name;
        Integer id;
        Cursor cursor = databaseHandler.getData("SELECT * FROM Notes");
        while (cursor.moveToNext()){
            id = cursor.getInt(0);
            name = cursor.getString(1);

            // add vao list note de dua vao adapter
            listNotes.add(new Note(id, name));
        }
        //noteAdapter.notifyDataSetChanged();
    }

    private void anhxa(){
        rvNotes = (RecyclerView) findViewById(R.id.rvNotes);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        listNotes = new ArrayList<>();
    }
}