package group8.eda397.chalmers.se.pairprogramming.requirements;

import java.io.File;

import group8.eda397.chalmers.se.pairprogramming.BasePresenter;
import group8.eda397.chalmers.se.pairprogramming.BaseView;

public interface RequirementsContract {

    interface View extends BaseView<Presenter> {
        void showPDF(String assetName);
        void showPDF(File file);
    }

    interface Presenter extends BasePresenter {

    }

}
