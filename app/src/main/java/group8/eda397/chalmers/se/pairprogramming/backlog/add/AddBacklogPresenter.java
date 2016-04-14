package group8.eda397.chalmers.se.pairprogramming.backlog.add;

import java.util.ArrayList;
import java.util.List;

import group8.eda397.chalmers.se.pairprogramming.backlog.model.BacklogItem;

/**
 * Created by m_cal on 2016-04-14.
 */
public class AddBacklogPresenter implements AddBacklogContract.Presenter {

    private final AddBacklogContract.View mBacklogView;
    private final List<BacklogItem> mBacklog = new ArrayList<BacklogItem>();

    public AddBacklogPresenter(AddBacklogContract.View backlogView) {
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
    }

    @Override
    public void addBacklogItem(BacklogItem item) {

    }
}
