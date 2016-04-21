package group8.eda397.chalmers.se.pairprogramming.backlog.details;

import group8.eda397.chalmers.se.pairprogramming.backlog.BacklogContract;
import group8.eda397.chalmers.se.pairprogramming.backlog.add.AddBacklogContract;

/**
 * Created by m_cal on 2016-04-21.
 */
public class BacklogDetailPresenter implements BacklogDetailContract.Presenter {

    private final BacklogDetailContract.View mDetailView;

    public BacklogDetailPresenter(BacklogDetailContract.View mDetailView) {
        this.mDetailView = mDetailView;
        mDetailView.setPresenter(this);
    }

    @Override
    public void start() {

    }
}
