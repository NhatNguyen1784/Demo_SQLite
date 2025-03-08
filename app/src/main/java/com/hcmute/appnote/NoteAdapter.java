package com.hcmute.appnote;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteViewHolder> {

    private Context context;
    private ArrayList<Note> listNotes;

    private LayoutInflater layoutInflater;

    public NoteAdapter(Context context, ArrayList<Note> listNotes) {
        this.context = context;
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
    }

    @Override
    public int getItemCount() {
        return listNotes.size();
    }

    protected class NoteViewHolder extends RecyclerView.ViewHolder{
        private TextView txtNameNote;

        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);
            txtNameNote = itemView.findViewById(R.id.txtNoteName);
        }
    }
}
