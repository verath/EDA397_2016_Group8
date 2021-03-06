package se.chalmers.eda397.group8.pairprogramming.backlog.addedit;

import android.support.annotation.NonNull;

import java.util.List;

import se.chalmers.eda397.group8.pairprogramming.BasePresenter;
import se.chalmers.eda397.group8.pairprogramming.BaseView;
import se.chalmers.eda397.group8.pairprogramming.backlog.model.BacklogStatus;
import se.chalmers.eda397.group8.pairprogramming.reqspec.data.RequirementSpecification;

public interface AddEditBacklogContract {

    interface View extends BaseView<Presenter> {

        void showBacklog();

        void showTitleEmptyError();

        void showTitle(String title);

        void showContent(String content);

        void showStatuses(List<BacklogStatus> statuses);

        void showSelectedStatus(BacklogStatus status);

        void showReqSpecs(List<RequirementSpecification> requirements);

        void showSelectedRequirement(RequirementSpecification requirementSpecification);

        void showMissingBacklogItem();

        void showPage(String page);
    }

    interface Presenter extends BasePresenter {

        void onSaveItem(@NonNull String title, @NonNull String content, @NonNull String statusId,
                        @NonNull String reqSpecId, @NonNull String pageNumber);
    }
}
