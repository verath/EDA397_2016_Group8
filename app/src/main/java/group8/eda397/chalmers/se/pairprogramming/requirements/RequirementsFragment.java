package group8.eda397.chalmers.se.pairprogramming.requirements;

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

    private RequirementsContract.Presenter presenter;
    private PDFView pdfView;

    @Override
    public void setPresenter(@NonNull RequirementsContract.Presenter presenter) {
        this.presenter = presenter;
    }

    public static RequirementsFragment newInstance() {
        return new RequirementsFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_requirements, container, false);

        pdfView = (PDFView) view.findViewById(R.id.pdfView);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.start();
    }

    /**
     * Display the PDF given an asset name (i.e. PDF file name in the assets folder).
     *
     * @param assetName
     */
    @Override
    public void showPDF(String assetName) {
        pdfView.fromAsset(assetName)
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
        pdfView.fromFile(file)
                .defaultPage(1)
                .enableSwipe(true)
                .showMinimap(true)
                .load();
    }

}
