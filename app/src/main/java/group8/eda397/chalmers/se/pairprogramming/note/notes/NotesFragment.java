package group8.eda397.chalmers.se.pairprogramming.note.notes;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import group8.eda397.chalmers.se.pairprogramming.R;
import group8.eda397.chalmers.se.pairprogramming.note.addeditnote.AddEditNoteActivity;
import group8.eda397.chalmers.se.pairprogramming.note.notedetail.NoteDetailActivity;

/**
 * The implementation of the Notes View interface, i.e. it is a
 * view for displaying notes.
 */
public class NotesFragment extends Fragment implements NotesContract.View {

    private static final int REQUEST_CODE_ADD_NOTE = 1;

    private NotesContract.Presenter mPresenter;
    private NotesAdapter mNotesAdapter;

    public static NotesFragment newInstance() {
        return new NotesFragment();
    }

    public NotesFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mNotesAdapter = new NotesAdapter(new ArrayList<Note>(0), onNoteClickListener);
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notes, container, false);

        // Setup the recycler view
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.notes_list);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(mNotesAdapter);

        // Setup the add FAB
        FloatingActionButton fab = (FloatingActionButton) getActivity().findViewById(R.id.fab_add_note);
        if (fab != null) {
            fab.setOnClickListener(fabAddNoteClickListener);
        }

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE_ADD_NOTE) {
            mPresenter.onAddNoteResult(resultCode);
            return;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void setPresenter(@NonNull NotesContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void showNotes(@NonNull List<Note> notes) {
        mNotesAdapter.replaceData(notes);
    }

    @Override
    public void showNoteDetailView(String noteId) {
        Intent intent = NoteDetailActivity.getCallingIntent(getContext(), noteId);
        startActivity(intent);
    }

    @Override
    public void showAddNoteView() {
        Intent intent = AddEditNoteActivity.getCallingIntent(getContext(), null);
        startActivityForResult(intent, REQUEST_CODE_ADD_NOTE);
    }

    private View.OnClickListener fabAddNoteClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            NotesFragment.this.mPresenter.onAddClicked();
        }
    };

    private NotesAdapter.NoteItemClickListener onNoteClickListener = new NotesAdapter.NoteItemClickListener() {
        @Override
        public void onNoteClick(Note clickedNote) {
            if (NotesFragment.this.mPresenter != null && clickedNote != null) {
                NotesFragment.this.mPresenter.onNoteClicked(clickedNote);
            }
        }
    };
}
