package com.hcmute.appnote.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hcmute.appnote.ui.MainActivity;
import com.hcmute.appnote.R;
import com.hcmute.appnote.model.Note;

import java.util.ArrayList;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteViewHolder> {

    private MainActivity mainActivity;
    private ArrayList<Note> listNotes;

    private LayoutInflater layoutInflater;

    public NoteAdapter(MainActivity context, ArrayList<Note> listNotes) {
        this.mainActivity = context;
        this.listNotes = listNotes;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.item_note, parent, false);
        return new NoteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        // gan data cho view
        Note note = listNotes.get(position);
        holder.txtNameNote.setText(note.getNameNote());

        holder.imgEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainActivity.DialogEdit(note.getNoteId(), note.getNameNote());
            }
        });

        // delete
        holder.imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainActivity.DialogDelete(note.getNoteId(), note.getNameNote());
            }
        });
    }

    @Override
    public int getItemCount() {
        return listNotes.size();
    }

    public class NoteViewHolder extends RecyclerView.ViewHolder{
        private TextView txtNameNote;
        private ImageView imgEdit;
        private ImageView imgDelete;

        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);
            txtNameNote = itemView.findViewById(R.id.txtNoteName);
            imgEdit = itemView.findViewById(R.id.imgEdit);
            imgDelete = itemView.findViewById(R.id.imgDelete);
        }
    }
}
