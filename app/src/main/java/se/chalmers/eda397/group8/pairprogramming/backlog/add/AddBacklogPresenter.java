package se.chalmers.eda397.group8.pairprogramming.backlog.add;

import java.util.ArrayList;
import java.util.List;

import se.chalmers.eda397.group8.pairprogramming.backlog.model.BacklogItem;

public class AddBacklogPresenter implements AddBacklogContract.Presenter {

    private final AddBacklogContract.View mBacklogView;
    private final List<BacklogItem> mBacklog = new ArrayList<>();

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
    public void onAddBacklogItem(BacklogItem item) {

    }
}
