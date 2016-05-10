package se.chalmers.eda397.group8.pairprogramming.reqspec.detail;

import java.io.File;

import se.chalmers.eda397.group8.pairprogramming.BasePresenter;
import se.chalmers.eda397.group8.pairprogramming.BaseView;

public interface ReqSpecDetailContract {

    interface View extends BaseView<Presenter> {
        void showPDF(String assetName);
        void showPDF(String name, int nr);
        void showPDF(File file);
    }

    interface Presenter extends BasePresenter {

    }

}
