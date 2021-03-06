package se.chalmers.eda397.group8.pairprogramming.note.detail;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import se.chalmers.eda397.group8.pairprogramming.R;
import se.chalmers.eda397.group8.pairprogramming.note.addedit.AddEditNoteActivity;

public class NoteDetailFragment extends Fragment implements NoteDetailContract.View {

    private static final int REQUEST_CODE_EDIT_NOTE = 1;

    private NoteDetailContract.Presenter mPresenter;
    private TextView mTitleView;
    private TextView mTextView;

    public static NoteDetailFragment newInstance() {
        return new NoteDetailFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_note_detail, container, false);

        mTitleView = (TextView) view.findViewById(R.id.note_detail_title);
        mTextView = (TextView) view.findViewById(R.id.note_detail_text);

        // Setup the floating action button
        FloatingActionButton fab = (FloatingActionButton) getActivity().findViewById(R.id.fab_edit_note);
        if (fab != null) {
            fab.setOnClickListener(mFabEditNoteClickListener);
        }

        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.note_detail_menu, menu);
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_delete_note:
                mPresenter.onDeleteClicked();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE_EDIT_NOTE) {
            mPresenter.onEditNoteResult(resultCode);
            return;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void setPresenter(@NonNull NoteDetailContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void hideTitle() {
        mTitleView.setVisibility(View.GONE);
    }

    @Override
    public void showTitle(String title) {
        mTitleView.setVisibility(View.VISIBLE);
        mTitleView.setText(title);
    }

    @Override
    public void hideText() {
        mTextView.setVisibility(View.GONE);
    }

    @Override
    public void showText(String text) {
        mTextView.setVisibility(View.VISIBLE);
        mTextView.setText(text);
    }

    @Override
    public void showNoteEditView(String noteId) {
        Intent intent = AddEditNoteActivity.getCallingIntent(getContext(), noteId);
        // We start the activity for result so that we can remove ourselves
        // if the edit was successful, returning the user to the list of notes
        startActivityForResult(intent, REQUEST_CODE_EDIT_NOTE);
    }

    @Override
    public void showNotesView() {
        getActivity().finish();
    }

    @Override
    public void showMissingNote() {
        // TODO: show some message saying this note is invalid?
    }

    private final View.OnClickListener mFabEditNoteClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            mPresenter.onEditClicked();
        }
    };
}
