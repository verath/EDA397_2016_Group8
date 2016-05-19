package se.chalmers.eda397.group8.pairprogramming.reqspec.detail;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.joanzapata.pdfview.PDFView;

import se.chalmers.eda397.group8.pairprogramming.R;

public class ReqSpecDetailFragment extends Fragment implements ReqSpecDetailContract.View {

    private ReqSpecDetailContract.Presenter mPresenter;
    private PDFView mPdfView;

    @Override
    public void setPresenter(@NonNull ReqSpecDetailContract.Presenter presenter) {
        this.mPresenter = presenter;
    }

    public static ReqSpecDetailFragment newInstance() {
        return new ReqSpecDetailFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_req_spec_detail, container, false);

        mPdfView = (PDFView) view.findViewById(R.id.pdfView);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    /**
     * Display the PDF given an asset name (i.e. PDF file name in the assets folder).
     *
     * @param fileName
     */
    @Override
    public void showPDF(String fileName) {
        mPdfView.fromAsset(fileName)
                .defaultPage(1)
                .enableSwipe(true)
                .showMinimap(true)
                .load();
    }

}
