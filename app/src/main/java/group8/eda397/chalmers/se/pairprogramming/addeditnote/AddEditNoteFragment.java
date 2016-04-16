package group8.eda397.chalmers.se.pairprogramming.addeditnote;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import group8.eda397.chalmers.se.pairprogramming.R;

public class AddEditNoteFragment extends Fragment implements AddEditNoteContract.View {

    private AddEditNoteContract.Presenter mPresenter;

    public static AddEditNoteFragment newInstance() {
        return new AddEditNoteFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_edit_note, container, false);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Override
    public void setPresenter(@NonNull AddEditNoteContract.Presenter presenter) {
        mPresenter = presenter;
    }
}
