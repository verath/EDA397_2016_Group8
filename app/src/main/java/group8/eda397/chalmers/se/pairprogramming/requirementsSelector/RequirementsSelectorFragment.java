package group8.eda397.chalmers.se.pairprogramming.requirementsSelector;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;

/**
 * Created by mysko1 on 2016-04-21.
 */
public class RequirementsSelectorFragment extends Fragment implements RequirementsSelectorContract.View {

    RequirementsSelectorContract.Presenter presenter;

    public static RequirementsSelectorFragment newInstance() {
        return new RequirementsSelectorFragment();
    }

    @Override
    public void setPresenter(@NonNull RequirementsSelectorContract.Presenter presenter) {
        this.presenter = presenter;
    }

}
