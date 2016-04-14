package group8.eda397.chalmers.se.pairprogramming.backlog;

import java.util.ArrayList;
import java.util.List;

import group8.eda397.chalmers.se.pairprogramming.backlog.model.BacklogItem;

/**
 * Created by m_cal on 2016-04-14.
 */
public class BacklogPresenter implements BacklogContract.Presenter {

    private final BacklogContract.View mBacklogView;
    private final List<BacklogItem> mBacklog = new ArrayList<BacklogItem>();

    public BacklogPresenter(BacklogContract.View backlogView) {
        this.mBacklogView = backlogView;
        mBacklogView.setPresenter(this);
    }

    @Override
    public void start() {
        mBacklog.clear();
        for (int i = 0; i < 20; i++) {
            mBacklog.add(new BacklogItem("Item " + (i + 1), "Backlog content " + (i + 1),
                    BacklogItem.Status.BACKLOG));
        }
        loadBacklog();
    }

    @Override
    public void loadBacklog() {
        mBacklogView.showBacklog(mBacklog);
    }

}
