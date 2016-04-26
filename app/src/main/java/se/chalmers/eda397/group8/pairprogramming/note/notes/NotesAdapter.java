package se.chalmers.eda397.group8.pairprogramming.note.notes;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import group8.eda397.chalmers.se.pairprogramming.R;
import se.chalmers.eda397.group8.pairprogramming.note.Note;

/**
 * A RecyclerView adapter for notes, showing each note as a custom
 * view.
 */
public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.ViewHolder> {

    private List<Note> mNotes;
    private final NoteItemClickListener mNoteItemClickListener;

    public interface NoteItemClickListener {
        void onNoteClick(Note clickedNote);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView title;
        public final TextView text;

        public ViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            text = (TextView) view.findViewById(R.id.text);
        }
    }

    /**
     * Creates a new NotesAdapter displaying some list of notes.
     *
     * @param notes                 The initial list of notes to display.
     * @param noteItemClickListener The listener for when a note is clicked.
     */
    public NotesAdapter(@NonNull List<Note> notes, NoteItemClickListener noteItemClickListener) {
        this.mNotes = notes;
        this.mNoteItemClickListener = noteItemClickListener;
    }

    /**
     * Replaces the list of notes displayed in the list by a new
     * list of notes.
     *
     * @param notes The new list of notes to be displayed.
     */
    public void replaceData(@NonNull List<Note> notes) {
        this.mNotes = notes;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_note, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Note note = mNotes.get(position);
        holder.title.setText(note.getTitle());
        holder.text.setText(note.getText());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (NotesAdapter.this.mNoteItemClickListener != null) {
                    NotesAdapter.this.mNoteItemClickListener.onNoteClick(note);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mNotes.size();
    }
}
