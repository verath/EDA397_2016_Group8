package se.chalmers.eda397.group8.pairprogramming.reqspec.reqspecbacklog;


import se.chalmers.eda397.group8.pairprogramming.BasePresenter;
import se.chalmers.eda397.group8.pairprogramming.BaseView;

public interface ReqSpecBacklogContract {

    interface View extends BaseView<Presenter> {
        void showPDF(String fileName, String page);
    }

    interface Presenter extends BasePresenter {

    }

}
