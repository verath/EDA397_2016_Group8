package se.chalmers.eda397.group8.pairprogramming.backlog;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

import se.chalmers.eda397.group8.pairprogramming.backlog.model.BacklogStatus;

class BacklogStatusPagerAdapter extends FragmentStatePagerAdapter {

    private List<BacklogStatus> mBacklogStatuses;

    public BacklogStatusPagerAdapter(FragmentManager fragmentManager, @NonNull List<BacklogStatus> backlogStatuses) {
        super(fragmentManager);
        mBacklogStatuses = backlogStatuses;
    }

    @Override
    public Fragment getItem(int position) {
        BacklogStatus status = mBacklogStatuses.get(position);
        return BacklogSwipeFragment.newInstance(status.getId());
    }

    /**
     * Return the BacklogStatus associated with a specified position.
     */
    public BacklogStatus getBacklogStatus(int position) {
        return mBacklogStatuses.get(position);
    }

    @Override
    public int getCount() {
        return mBacklogStatuses.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        BacklogStatus status = mBacklogStatuses.get(position);
        return status.getName();
    }

    public void replaceData(@NonNull List<BacklogStatus> backlogStatuses) {
        mBacklogStatuses = backlogStatuses;
        notifyDataSetChanged();
    }
}
