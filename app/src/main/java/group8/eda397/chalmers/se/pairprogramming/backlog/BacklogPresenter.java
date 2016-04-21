package group8.eda397.chalmers.se.pairprogramming.backlog;

import android.content.Intent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import group8.eda397.chalmers.se.pairprogramming.backlog.add.AddBacklogActivity;
import group8.eda397.chalmers.se.pairprogramming.backlog.model.BacklogItem;

/**
 * Created by m_cal on 2016-04-14.
 */
public class BacklogPresenter implements BacklogContract.Presenter {

    private final BacklogContract.View mBacklogView;

    public BacklogPresenter(BacklogContract.View backlogView) {
        this.mBacklogView = backlogView;
        mBacklogView.setPresenter(this);
    }

    @Override
    public void start() {
        mBacklogView.getFab().setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                mBacklogView.showAddBacklogItemView();
            }
        });
    }

}
