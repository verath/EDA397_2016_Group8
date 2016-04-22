package group8.eda397.chalmers.se.pairprogramming.backlog;


import android.os.Bundle;
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
import group8.eda397.chalmers.se.pairprogramming.backlog.model.BacklogItem;

/**
 * A simple {@link Fragment} subclass.
 */
public class BacklogSwipeFragment extends Fragment {

    private static final String ARGS_BACKLOG_STATUS = "group8.eda397.chalmers.se.pairprogramming.ARGS_BACKLOG_STATUS";


    public interface Listener {

        void onSwipeFragmentResume(BacklogItem.Status status);

        void onSwipeFragmentBacklogItemClicked(BacklogItem.Status status, BacklogItem backlogItem);

    }

    private Listener mListener;
    private BacklogItemAdapter mAdapter;

    public BacklogSwipeFragment() {
        // Required empty public constructor
    }

    public static BacklogSwipeFragment newInstance(BacklogItem.Status status) {
        BacklogSwipeFragment fragment = new BacklogSwipeFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARGS_BACKLOG_STATUS, status);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAdapter = new BacklogItemAdapter(new ArrayList<BacklogItem>(), mBacklogItemListener);
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
        if (mListener != null) {
            mListener.onSwipeFragmentResume(getStatus());
        }
    }

    public void setListener(@Nullable Listener listener) {
        mListener = listener;
        if (mListener != null && isResumed()) {
            mListener.onSwipeFragmentResume(getStatus());
        }
    }

    public void showItems(List<BacklogItem> items) {
        mAdapter.replaceData(items);
    }

    private BacklogItem.Status getStatus() {
        return (BacklogItem.Status) getArguments().getSerializable(ARGS_BACKLOG_STATUS);
    }

    private final BacklogItemAdapter.BacklogItemListener mBacklogItemListener = new BacklogItemAdapter.BacklogItemListener() {
        @Override
        public void onBacklogItemClick(BacklogItem item) {
            if (mListener != null && item != null) {
                mListener.onSwipeFragmentBacklogItemClicked(getStatus(), item);
            }
        }
    };
}
