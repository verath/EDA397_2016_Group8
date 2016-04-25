package group8.eda397.chalmers.se.pairprogramming.note.addeditnote;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import group8.eda397.chalmers.se.pairprogramming.R;

public class AddEditNoteFragment extends Fragment implements AddEditNoteContract.View {

    private AddEditNoteContract.Presenter mPresenter;
    private EditText mTitleEditText;
    private EditText mTextEditText;

    public static AddEditNoteFragment newInstance() {
        return new AddEditNoteFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_edit_note, container, false);

        mTitleEditText = (EditText) view.findViewById(R.id.add_edit_note_title);
        mTextEditText = (EditText) view.findViewById(R.id.add_edit_note_text);

        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.note_add_edit_menu, menu);
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();

        // Request focus on the input for "text"
        mTextEditText.requestFocus();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_note_save:
                mPresenter.onSaveClicked(mTitleEditText.getText().toString(),
                        mTextEditText.getText().toString());
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void setPresenter(@NonNull AddEditNoteContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void showNotesView() {
        getActivity().setResult(Activity.RESULT_OK);
        getActivity().finish();
    }

    @Override
    public void showTitle(String title) {
        mTitleEditText.setText(title);
    }

    @Override
    public void showText(String text) {
        mTextEditText.setText(text);
    }

    @Override
    public void showEmptyNoteError() {
        Snackbar.make(mTitleEditText, R.string.add_edit_note_empty_error, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void showMissingNote() {
        // TODO: show missing note error
    }
}
