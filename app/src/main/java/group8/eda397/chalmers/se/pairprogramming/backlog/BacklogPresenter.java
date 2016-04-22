package group8.eda397.chalmers.se.pairprogramming.backlog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import group8.eda397.chalmers.se.pairprogramming.backlog.model.BacklogItem;

/**
 * Created by m_cal on 2016-04-14.
 */
public class BacklogPresenter implements BacklogContract.Presenter {

    private final BacklogContract.View mBacklogView;

    private final Map<BacklogItem.Status, List<BacklogItem>> mBacklog = new HashMap<>();


    public BacklogPresenter(BacklogContract.View backlogView) {
        this.mBacklogView = backlogView;
        mBacklogView.setPresenter(this);
    }

    @Override
    public void start() {
        mBacklog.clear();

        for (BacklogItem.Status status : BacklogItem.Status.values()) {
            List<BacklogItem> items = new ArrayList<>();
            for (int i = 0; i < 20; i++) {
                items.add(new BacklogItem("Item " + (i + 1) + " " + status, "Content", status));
            }
            mBacklog.put(status, items);
        }
    }

    @Override
    public void onAddClicked() {
        mBacklogView.showAddBacklogItemView();
    }

    @Override
    public void onSwipeFragmentResume(BacklogItem.Status status) {
        mBacklogView.showBacklogForStatus(status, mBacklog.get(status));
    }

    @Override
    public void onBacklogItemClicked(BacklogItem backlogItem) {

    }

}
