package group8.eda397.chalmers.se.pairprogramming.backlog;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

import group8.eda397.chalmers.se.pairprogramming.R;
import group8.eda397.chalmers.se.pairprogramming.backlog.add.AddBacklogActivity;
import group8.eda397.chalmers.se.pairprogramming.backlog.model.BacklogItem;

public class BacklogFragment extends Fragment implements BacklogContract.View {

    private CollectionPagerAdapter mCollectionPagerAdapter;
    private ViewPager mViewPager;
    private BacklogContract.Presenter mPresenter;
    private FloatingActionButton mFab;

    public BacklogFragment() {

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_backlog, container, false);
        mFab = (FloatingActionButton) view.findViewById(R.id.backlog_add_fab);
        mCollectionPagerAdapter = new CollectionPagerAdapter(getActivity().getSupportFragmentManager());
        mViewPager = (ViewPager) view.findViewById(R.id.pager);
        mViewPager.setAdapter(mCollectionPagerAdapter);

        TabLayout tabLayout = (TabLayout) getActivity().findViewById(R.id.toolbar_tabs);
        tabLayout.setupWithViewPager(mViewPager);

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
    public void setPresenter(BacklogContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public FloatingActionButton getFab(){
        return mFab;
    }

    @Override
    public void showAddBacklogItemView(){
        Intent intent = AddBacklogActivity.getCallingIntent(getContext());
        startActivityForResult(intent, 0);
    }

    private class CollectionPagerAdapter extends FragmentStatePagerAdapter {

        private final BacklogSwipeFragment[] mFragments;

        public CollectionPagerAdapter(FragmentManager fm) {
            super(fm);

            this.mFragments = new BacklogSwipeFragment[BacklogItem.Status.values().length];
            for (int i = 0; i < mFragments.length; i++) {
                BacklogSwipeFragment backlogFragment = BacklogSwipeFragment.newInstance();
                new BacklogSwipePresenter(backlogFragment, BacklogItem.Status.values()[i]);
                mFragments[i] = backlogFragment;
            }
        }

        @Override
        public Fragment getItem(int i) {
            return mFragments[i];
        }

        @Override
        public int getCount() {
            return BacklogItem.Status.values().length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return BacklogItem.Status.values()[position].getName(getContext());
        }
    }
}
