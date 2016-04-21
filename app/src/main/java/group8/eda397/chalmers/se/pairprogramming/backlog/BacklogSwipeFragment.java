package group8.eda397.chalmers.se.pairprogramming.backlog;


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
import group8.eda397.chalmers.se.pairprogramming.backlog.details.BacklogDetailActivity;
import group8.eda397.chalmers.se.pairprogramming.backlog.model.BacklogItem;

/**
 * A simple {@link Fragment} subclass.
 */
public class BacklogSwipeFragment extends Fragment implements BacklogSwipeContract.View {

    private BacklogSwipeContract.Presenter mPresenter;
    private BacklogSwipeAdapter mAdapter;

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
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAdapter = new BacklogSwipeAdapter(new ArrayList<BacklogItem>(), mBacklogItemListener);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_backlog_swipe, container, false);
        ;

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.backlog_item_list);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(mAdapter);

        return view;
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

    @Override
    public void showItems(List<BacklogItem> items) {
        mAdapter.replaceData(items);
    }

    @Override
    public void showBacklogDetailsView(BacklogItem item) {
        startActivity(BacklogDetailActivity.getCallingIntent(getContext(), item));
    }

    private final BacklogSwipeAdapter.BacklogItemListener mBacklogItemListener = new BacklogSwipeAdapter.BacklogItemListener() {
        @Override
        public void onBacklogItemClick(BacklogItem item) {
            if (item != null) {
                mPresenter.onBacklogItemClick(item);
            }
        }
    };
}
