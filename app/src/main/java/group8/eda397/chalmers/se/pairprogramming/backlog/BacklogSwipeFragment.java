package group8.eda397.chalmers.se.pairprogramming.backlog;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import group8.eda397.chalmers.se.pairprogramming.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class BacklogSwipeFragment extends Fragment implements BacklogSwipeContract.View {

    private BacklogSwipeContract.Presenter mPresenter;

    public BacklogSwipeFragment() {
        // Required empty public constructor
    }

    public static BacklogSwipeFragment newInstance() {
        BacklogSwipeFragment fragment = new BacklogSwipeFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_backlog_swipe, container, false);
    }

    @Override
    public void setPresenter(@NonNull BacklogSwipeContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }
}
