package group8.eda397.chalmers.se.pairprogramming.requirements;

public class RequirementsPresenter implements RequirementsContract.Presenter {

    RequirementsContract.View fragment;

    public RequirementsPresenter(RequirementsContract.View fragment){
        this.fragment = fragment;
        fragment.setPresenter(this);
    }

    @Override
    public void start() {

    }
}
