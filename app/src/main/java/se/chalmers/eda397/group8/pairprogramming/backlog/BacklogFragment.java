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
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

import se.chalmers.eda397.group8.pairprogramming.R;
import se.chalmers.eda397.group8.pairprogramming.backlog.addedit.AddEditBacklogActivity;
import se.chalmers.eda397.group8.pairprogramming.backlog.detail.BacklogDetailActivity;
import se.chalmers.eda397.group8.pairprogramming.backlog.model.BacklogItem;
import se.chalmers.eda397.group8.pairprogramming.backlog.model.BacklogStatus;
import se.chalmers.eda397.group8.pairprogramming.backlog.model.BacklogStatusRepository;

public class BacklogFragment extends Fragment implements BacklogContract.View, BacklogSwipeFragment.Listener {

    private static final BacklogStatus[] TAB_STATUSES = {
            BacklogStatusRepository.getInstance().get("1"),
            BacklogStatusRepository.getInstance().get("2"),
            BacklogStatusRepository.getInstance().get("3"),
            BacklogStatusRepository.getInstance().get("4")
    };

    private final BacklogSwipeFragment[] mTabFragments = new BacklogSwipeFragment[TAB_STATUSES.length];

    private CollectionPagerAdapter mCollectionPagerAdapter;

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
        mCollectionPagerAdapter = new CollectionPagerAdapter(fm);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_backlog, container, false);

        // Setup the view pager
        ViewPager viewPager = (ViewPager) view.findViewById(R.id.pager);
        viewPager.setAdapter(mCollectionPagerAdapter);
        viewPager.addOnPageChangeListener(mPageListener);

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
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            Toast.makeText(getContext(), "Item created", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getContext(), "Creation cancelled", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Override
    public void setPresenter(@NonNull BacklogContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void showBacklogForStatus(BacklogStatus status, List<BacklogItem> items) {
        int tabIndex = backlogStatusToTabIndex(status);
        if (tabIndex > -1 && mTabFragments[tabIndex] != null) {
            mTabFragments[tabIndex].showItems(items);
        }
    }

    @Override
    public void showAddBacklogItemView() {
        Intent intent = AddEditBacklogActivity.getCallingIntent(getContext(), null,
                TAB_STATUSES[mSelectedPageIndex].getId());
        startActivityForResult(intent, 0);
    }

    @Override
    public void onSwipeFragmentResume(String statusId) {
        mPresenter.onSwipeFragmentResume(statusId);
    }

    @Override
    public void onSwipeFragmentBacklogItemClicked(String statusId, BacklogItem backlogItem) {
        mPresenter.onBacklogItemClicked(backlogItem);
    }

    @Override
    public void showBacklogItemDetails(String backlogItemId) {

        startActivity(BacklogDetailActivity.getCallingIntent(getContext(), backlogItemId));

    }

    private int backlogStatusToTabIndex(BacklogStatus status) {
        for (int i = 0; i < TAB_STATUSES.length; i++) {
            if (status == TAB_STATUSES[i]) {
                return i;
            }
        }
        return -1;
    }


    private final View.OnClickListener mFabAddBacklogItemClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (mPresenter != null) {
                mPresenter.onAddClicked();
            }
        }
    };

    private class CollectionPagerAdapter extends FragmentPagerAdapter {
        public CollectionPagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        @Override
        public Fragment getItem(int position) {
            BacklogStatus status = TAB_STATUSES[position];
            return BacklogSwipeFragment.newInstance(status.getId());
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            Object item = super.instantiateItem(container, position);

            BacklogSwipeFragment fragment = (BacklogSwipeFragment) item;
            mTabFragments[position] = fragment;
            fragment.setListener(BacklogFragment.this);

            return fragment;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            super.destroyItem(container, position, object);
            mTabFragments[position].setListener(null);
            mTabFragments[position] = null;
        }

        @Override
        public int getCount() {
            return TAB_STATUSES.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            BacklogStatus status = TAB_STATUSES[position];
            return status.getName();
        }
    }

    private final ViewPager.OnPageChangeListener mPageListener = new ViewPager.SimpleOnPageChangeListener() {

        @Override
        public void onPageSelected(int position) {
            mSelectedPageIndex = position;
        }
    };
}
