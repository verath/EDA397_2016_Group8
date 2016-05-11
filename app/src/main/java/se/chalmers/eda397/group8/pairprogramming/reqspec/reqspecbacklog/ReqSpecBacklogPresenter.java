package se.chalmers.eda397.group8.pairprogramming.reqspec.reqspecbacklog;

import se.chalmers.eda397.group8.pairprogramming.reqspec.RequirementSpecification;

public class ReqSpecBacklogPresenter implements ReqSpecBacklogContract.Presenter {

    private final ReqSpecBacklogContract.View mView;
    private final String mPage;

    public ReqSpecBacklogPresenter(ReqSpecBacklogContract.View view, String page) {
        mView = view;
        mPage = page;
        view.setPresenter(this);
    }

    @Override
    public void start() {
        // Only testing for PDF files in assets folder for now.
        mView.showPDF("sample.pdf",mPage);
    }

}
