package group8.eda397.chalmers.se.pairprogramming.notes;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import group8.eda397.chalmers.se.pairprogramming.R;

/**
 * A RecyclerView adapter for notes, showing each note as a custom
 * view.
 */
public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.ViewHolder> {

    private List<Note> mNotes;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public TextView text;

        public ViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            text = (TextView) view.findViewById(R.id.text);
        }
    }

    public NotesAdapter(@NonNull List<Note> notes) {
        this.mNotes = notes;
    }

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
        Note note = mNotes.get(position);
        holder.title.setText(note.getTitle());
        holder.text.setText(note.getText());
    }

    @Override
    public int getItemCount() {
        return mNotes.size();
    }
}
