package se.chalmers.eda397.group8.pairprogramming.reqspec.reqspecbacklog;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.joanzapata.pdfview.PDFView;

import java.io.File;

import se.chalmers.eda397.group8.pairprogramming.R;
import se.chalmers.eda397.group8.pairprogramming.reqspec.detail.ReqSpecDetailFragment;

public class ReqSpecBacklogFragment extends Fragment implements ReqSpecBacklogContract.View {

    private ReqSpecBacklogContract.Presenter mPresenter;
    private PDFView mPdfView;

    @Override
    public void setPresenter(@NonNull ReqSpecBacklogContract.Presenter presenter) {
        this.mPresenter = presenter;
    }

    public static ReqSpecBacklogFragment newInstance() {
        return new ReqSpecBacklogFragment();
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



    @Override
    public void showPDF(String file, String page) {
        mPdfView.fromAsset(file)
                .defaultPage(Integer.parseInt(page))
                .enableSwipe(true)
                .showMinimap(true)
                .load();
    }

}
