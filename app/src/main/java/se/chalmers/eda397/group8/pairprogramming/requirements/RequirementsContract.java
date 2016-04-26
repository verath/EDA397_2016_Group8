package se.chalmers.eda397.group8.pairprogramming.requirements;

import java.io.File;

import se.chalmers.eda397.group8.pairprogramming.BasePresenter;
import se.chalmers.eda397.group8.pairprogramming.BaseView;

public interface RequirementsContract {

    interface View extends BaseView<Presenter> {
        void showPDF(String assetName);

        void showPDF(File file);
    }

    interface Presenter extends BasePresenter {

    }

}
