package se.chalmers.eda397.group8.pairprogramming.backlog.detail;


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
import se.chalmers.eda397.group8.pairprogramming.backlog.addedit.AddEditBacklogActivity;
import se.chalmers.eda397.group8.pairprogramming.backlog.model.BacklogItem;

/**
 * A simple {@link Fragment} subclass.
 */
public class BacklogDetailFragment extends Fragment implements BacklogDetailContract.View {

    private BacklogDetailContract.Presenter mPresenter;
    private TextView mTitleTv;
    private TextView mStatusTv;
    private TextView mContentTv;
    private TextView mPageTv;

    public BacklogDetailFragment() {
        // Required empty public constructor
    }

    public static BacklogDetailFragment newInstance() {
        BacklogDetailFragment fragment = new BacklogDetailFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_backlog_detail, container, false);
        mTitleTv = (TextView) view.findViewById(R.id.backlog_detail_title);
        mStatusTv = (TextView) view.findViewById(R.id.backlog_detail_status);
        mContentTv = (TextView) view.findViewById(R.id.backlog_detail_text);
        mPageTv = (TextView) view.findViewById(R.id.backlog_detail_page);

        // Setup the add FAB
        FloatingActionButton fab = (FloatingActionButton) getActivity().findViewById(R.id.fab_edit_backlog_item);
        if (fab != null) {
            fab.setOnClickListener(mFabEditBacklogItemClickListener);
        }

        return view;
    }

    @Override
    public void setPresenter(@NonNull BacklogDetailContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_delete_backlog_item, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_backlog_item_delete:
                mPresenter.onDeleteItemClicked();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void showEditView(String backlogItemId) {
        startActivity(AddEditBacklogActivity.getCallingIntent(getContext(), backlogItemId));
    }

    @Override
    public void showBacklog() {
        getActivity().finish();
    }

    @Override
    public void showBacklogItem(BacklogItem backlogItem) {
        if (mTitleTv != null) {
            mTitleTv.setText(backlogItem.getTitle());
        }
        if (mStatusTv != null) {
            mStatusTv.setText(backlogItem.getStatus().getName(getContext()));
        }
        if (mContentTv != null) {
            mContentTv.setText(backlogItem.getContent());
        }
        if (mPageTv != null) {
            mPageTv.setText(backlogItem.getPage());
        }
    }

    private final View.OnClickListener mFabEditBacklogItemClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (mPresenter != null) {
                mPresenter.onEditItemClicked();
            }
        }
    };
}
