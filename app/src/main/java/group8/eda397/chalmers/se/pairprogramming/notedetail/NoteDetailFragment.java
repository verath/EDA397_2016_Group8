package group8.eda397.chalmers.se.pairprogramming.notedetail;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import group8.eda397.chalmers.se.pairprogramming.R;

public class NoteDetailFragment extends Fragment implements NoteDetailContract.View {

    private NoteDetailContract.Presenter mPresenter;
    private TextView mTitleView;
    private TextView mTextView;

    public static NoteDetailFragment newInstance() {
        return new NoteDetailFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_note_detail, container, false);

        mTitleView = (TextView) view.findViewById(R.id.note_detail_title);
        mTextView = (TextView) view.findViewById(R.id.note_detail_text);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
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
}
