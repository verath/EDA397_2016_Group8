package group8.eda397.chalmers.se.pairprogramming.notedetail;


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

import group8.eda397.chalmers.se.pairprogramming.R;
import group8.eda397.chalmers.se.pairprogramming.addeditnote.AddEditNoteActivity;

public class NoteDetailFragment extends Fragment implements NoteDetailContract.View {

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
            fab.setOnClickListener(fabEditNoteClickListener);
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
        }
        return super.onOptionsItemSelected(item);
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
        startActivity(intent);
    }

    private View.OnClickListener fabEditNoteClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            mPresenter.onEditClicked();
        }
    };
}
