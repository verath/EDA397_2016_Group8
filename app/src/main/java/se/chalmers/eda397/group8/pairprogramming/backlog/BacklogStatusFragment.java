package se.chalmers.eda397.group8.pairprogramming.backlog;

import android.content.Intent;
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

import se.chalmers.eda397.group8.pairprogramming.R;
import se.chalmers.eda397.group8.pairprogramming.backlog.detail.BacklogDetailActivity;
import se.chalmers.eda397.group8.pairprogramming.backlog.model.BacklogItem;

public class BacklogStatusFragment extends Fragment implements BacklogStatusContract.View {

    private static final String ARG_STATUS_ID = "status_id";

    private BacklogItemAdapter mAdapter;
    private BacklogStatusContract.Presenter mPresenter;

    public BacklogStatusFragment() {
        // Required empty public constructor
    }

    public static BacklogStatusFragment newInstance(String statusId) {
        BacklogStatusFragment fragment = new BacklogStatusFragment();
        Bundle args = new Bundle();
        args.putString(ARG_STATUS_ID, statusId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void setPresenter(@NonNull BacklogStatusContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAdapter = new BacklogItemAdapter(new ArrayList<BacklogItem>(), mBacklogItemListener);

        Bundle args = getArguments();
        if (args == null || !args.containsKey(ARG_STATUS_ID)) {
            throw new IllegalArgumentException("Invalid Fragment arguments");
        }
        String statusId = args.getString(ARG_STATUS_ID);

        // TODO: solve some other way? This is kind of ugly...
        ((BacklogActivity) getActivity()).createBacklogStatusPresenter(this, statusId);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_backlog_swipe, container, false);

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.backlog_item_list);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(mAdapter);

        return view;
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
    public void showBacklogItemDetails(String backlogItemId) {
        Intent intent = BacklogDetailActivity.getCallingIntent(getContext(), backlogItemId);
        startActivity(intent);
    }

    private final BacklogItemAdapter.BacklogItemListener mBacklogItemListener = new BacklogItemAdapter.BacklogItemListener() {
        @Override
        public void onBacklogItemClick(BacklogItem item) {
            if (mPresenter != null && item != null) {
                mPresenter.onBacklogItemClicked(item);
            }
        }
    };
}
