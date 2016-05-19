package se.chalmers.eda397.group8.pairprogramming.reqspec.reqspecbacklog;


public class ReqSpecBacklogPresenter implements ReqSpecBacklogContract.Presenter {

    private final ReqSpecBacklogContract.View mView;
    private final String mPage;
    private final String mName;

    public ReqSpecBacklogPresenter(ReqSpecBacklogContract.View view, String page, String name) {
        mView = view;
        mPage = page;
        mName = name;
        view.setPresenter(this);
    }

    @Override
    public void start() {
        // Only testing for PDF files in assets folder for now.
        mView.showPDF(mName, mPage);
    }

}
