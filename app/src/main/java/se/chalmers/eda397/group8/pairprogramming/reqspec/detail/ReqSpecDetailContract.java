package se.chalmers.eda397.group8.pairprogramming.reqspec.detail;

import se.chalmers.eda397.group8.pairprogramming.BasePresenter;
import se.chalmers.eda397.group8.pairprogramming.BaseView;

public interface ReqSpecDetailContract {

    interface View extends BaseView<Presenter> {
        void showPDF(String fileName);
    }

    interface Presenter extends BasePresenter {

    }

}
