package se.chalmers.eda397.group8.pairprogramming.backlog;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import se.chalmers.eda397.group8.pairprogramming.R;
import se.chalmers.eda397.group8.pairprogramming.backlog.addedit.AddEditBacklogActivity;
import se.chalmers.eda397.group8.pairprogramming.backlog.model.BacklogStatus;

public class BacklogFragment extends Fragment implements BacklogContract.View {

    private BacklogStatusPagerAdapter mBacklogStatusAdapter;
    private BacklogContract.Presenter mPresenter;
    private int mSelectedPageIndex;

    public BacklogFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment BacklogFragment.
     */
    public static BacklogFragment newInstance() {
        BacklogFragment fragment = new BacklogFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FragmentManager fm = getActivity().getSupportFragmentManager();
        mBacklogStatusAdapter = new BacklogStatusPagerAdapter(fm, new ArrayList<BacklogStatus>(0));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_backlog, container, false);

        // Setup the view pager
        ViewPager viewPager = (ViewPager) view.findViewById(R.id.pager);
        viewPager.setAdapter(mBacklogStatusAdapter);
        viewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                mSelectedPageIndex = position;
            }
        });

        // Setup the tabs in the actionbar, connected to the view pager
        TabLayout tabLayout = (TabLayout) getActivity().findViewById(R.id.toolbar_tabs);
        tabLayout.setupWithViewPager(viewPager);

        // Setup the add FAB
        FloatingActionButton fab = (FloatingActionButton) getActivity().findViewById(R.id.backlog_add_fab);
        if (fab != null) {
            fab.setOnClickListener(mFabAddBacklogItemClickListener);
        }

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            Toast.makeText(getContext(), "Item created", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getContext(), "Creation cancelled", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void setPresenter(@NonNull BacklogContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void showAddBacklogItemView(String backlogStatusId) {
        Intent intent = AddEditBacklogActivity.getCallingIntent(getContext(), null, backlogStatusId);
        startActivityForResult(intent, 0);
    }

    @Override
    public void showBacklogStatuses(List<BacklogStatus> backlogStatuses) {
        mBacklogStatusAdapter.replaceData(backlogStatuses);
    }

    private final View.OnClickListener mFabAddBacklogItemClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (mPresenter != null) {
                BacklogStatus status = mBacklogStatusAdapter.getBacklogStatus(mSelectedPageIndex);
                mPresenter.onAddClicked(status);
            }
        }
    };


}
