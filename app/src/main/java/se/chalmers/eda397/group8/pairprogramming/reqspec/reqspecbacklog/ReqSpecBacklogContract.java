package se.chalmers.eda397.group8.pairprogramming.reqspec.reqspecbacklog;

import java.io.File;

import se.chalmers.eda397.group8.pairprogramming.BasePresenter;
import se.chalmers.eda397.group8.pairprogramming.BaseView;

public interface ReqSpecBacklogContract {

    interface View extends BaseView<Presenter> {
        void showPDF(String name, String page);
    }

    interface Presenter extends BasePresenter {

    }

}
