package group8.eda397.chalmers.se.pairprogramming.notes;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import group8.eda397.chalmers.se.pairprogramming.R;

/**
 * The implementation of the Notes View interface, i.e. it is a
 * view for displaying notes.
 */
public class NotesFragment extends Fragment implements NotesContract.View {

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
        mNotesAdapter = new NotesAdapter(new ArrayList<Note>(0));
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

        return view;
    }

    @Override
    public void setPresenter(@NonNull NotesContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void showNotes(@NonNull List<Note> notes) {
        mNotesAdapter.replaceData(notes);
    }

}
