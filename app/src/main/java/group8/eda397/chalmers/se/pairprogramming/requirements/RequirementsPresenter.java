package group8.eda397.chalmers.se.pairprogramming.requirements;

import android.graphics.pdf.PdfRenderer;
import android.os.ParcelFileDescriptor;

public class RequirementsPresenter implements RequirementsContract.Presenter {

    RequirementsContract.View fragment;

    private ParcelFileDescriptor mFileDescriptor;
    private PdfRenderer mPdfRenderer;
    private PdfRenderer.Page mCurrentPage;

    public RequirementsPresenter(RequirementsContract.View fragment) {
        this.fragment = fragment;
        fragment.setPresenter(this);
    }

    @Override
    public void start() {
        //show first pdf page
    }

}
