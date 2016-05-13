package se.chalmers.eda397.group8.pairprogramming;

import android.support.annotation.NonNull;

public interface BaseView<T extends BasePresenter> {

    void setPresenter(@NonNull T presenter);
}
