package group8.eda397.chalmers.se.pairprogramming.requirements;

import android.content.res.AssetManager;

import group8.eda397.chalmers.se.pairprogramming.BasePresenter;
import group8.eda397.chalmers.se.pairprogramming.BaseView;

public interface RequirementsContract {

    interface View extends BaseView<Presenter> {
        void showPage(int index);

        AssetManager getAssetManager();
    }

    interface Presenter extends BasePresenter {

    }

}
