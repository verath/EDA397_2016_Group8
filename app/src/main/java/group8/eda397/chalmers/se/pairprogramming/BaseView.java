package group8.eda397.chalmers.se.pairprogramming;

import android.support.annotation.NonNull;

public interface BaseView<T> {

    void setPresenter(@NonNull T presenter);
}
