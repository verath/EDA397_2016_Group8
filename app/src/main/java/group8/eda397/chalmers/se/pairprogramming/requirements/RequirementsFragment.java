package group8.eda397.chalmers.se.pairprogramming.requirements;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import group8.eda397.chalmers.se.pairprogramming.R;

public class RequirementsFragment extends Fragment implements RequirementsContract.View, View.OnClickListener {

    RequirementsContract.Presenter presenter;

    ImageView imageView;
    Button prevBtn;
    Button nextBtn;

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
        View view = inflater.inflate(R.layout.fragment_requiremnts, container, false);

        imageView = (ImageView) view.findViewById(R.id.pdfImage);
        prevBtn = (Button) view.findViewById(R.id.previous);
        nextBtn = (Button) view.findViewById(R.id.next);

        prevBtn.setOnClickListener(this);
        nextBtn.setOnClickListener(this);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.start();
    }

    @Override
    public void showPage(int index) {

    }

    /**
     * Returns the AssetManager for the context to retrieve the PDF file stored in assets folder.
     *
     * @return
     */
    @Override
    public AssetManager getAssetManager() {
        return getContext().getAssets();
    }


    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.previous: {
                // Move to the previous page
                break;
            }
            case R.id.next: {
                // Move to the next page
                break;
            }
        }

    }
}
