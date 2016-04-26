package se.chalmers.eda397.group8.pairprogramming.requirements;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.joanzapata.pdfview.PDFView;

import java.io.File;

import group8.eda397.chalmers.se.pairprogramming.R;

public class RequirementsFragment extends Fragment implements RequirementsContract.View {

    private RequirementsContract.Presenter mPresenter;
    private PDFView mPdfView;

    @Override
    public void setPresenter(@NonNull RequirementsContract.Presenter presenter) {
        this.mPresenter = presenter;
    }

    public static RequirementsFragment newInstance() {
        return new RequirementsFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_requirements, container, false);

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
     * @param assetName
     */
    @Override
    public void showPDF(String assetName) {
        mPdfView.fromAsset(assetName)
                .defaultPage(1)
                .enableSwipe(true)
                .showMinimap(true)
                .load();
    }

    /**
     * Display the PDF given a file.
     *
     * @param file
     */
    @Override
    public void showPDF(File file) {
        mPdfView.fromFile(file)
                .defaultPage(1)
                .enableSwipe(true)
                .showMinimap(true)
                .load();
    }

}
