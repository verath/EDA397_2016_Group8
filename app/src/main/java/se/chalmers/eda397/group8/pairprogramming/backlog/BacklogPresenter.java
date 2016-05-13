package se.chalmers.eda397.group8.pairprogramming.backlog;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import se.chalmers.eda397.group8.pairprogramming.backlog.model.BacklogStatus;
import se.chalmers.eda397.group8.pairprogramming.backlog.model.BacklogStatusDataSource;

public class BacklogPresenter implements BacklogContract.Presenter {

    private final BacklogContract.View mBacklogView;
    private final BacklogStatusDataSource mStatusDataSource;

    public BacklogPresenter(BacklogContract.View backlogView, BacklogStatusDataSource statusDataSource) {
        mBacklogView = backlogView;
        mStatusDataSource = statusDataSource;
        mBacklogView.setPresenter(this);
    }

    @Override
    public void start() {
        List<BacklogStatus> statuses = mStatusDataSource.getAll();
        // Sort by id
        // TODO: better sorting
        Collections.sort(statuses, new Comparator<BacklogStatus>() {
            @Override
            public int compare(BacklogStatus lhs, BacklogStatus rhs) {
                return lhs.getId().compareTo(rhs.getId());
            }
        });
        mBacklogView.showBacklogStatuses(statuses);
    }

    @Override
    public void onAddClicked(BacklogStatus selectedStatus) {
        mBacklogView.showAddBacklogItemView(selectedStatus.getId());
    }

}
