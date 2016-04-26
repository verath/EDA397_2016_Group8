package group8.eda397.chalmers.se.pairprogramming.backlog.details;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import group8.eda397.chalmers.se.pairprogramming.R;
import group8.eda397.chalmers.se.pairprogramming.backlog.BacklogContract;
import group8.eda397.chalmers.se.pairprogramming.backlog.model.BacklogItem;

/**
 * A simple {@link Fragment} subclass.
 */
public class BacklogDetailFragment extends Fragment implements BacklogDetailContract.View {

    private BacklogDetailContract.Presenter mPresenter;

    public BacklogDetailFragment() {
        // Required empty public constructor
    }

    public static BacklogDetailFragment newInstance() {
        BacklogDetailFragment fragment = new BacklogDetailFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_backlog_detail, container, false);
    }

    @Override
    public void setPresenter(@NonNull BacklogDetailContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Override
    public void showEditView(String backlogItemId) {

    }

    @Override
    public void showBacklog() {

    }

    @Override
    public void showBacklogItem(BacklogItem backlogItem) {
        TextView title = (TextView) getView().findViewById(R.id.backlog_detail_title);
        if (title != null) {
            title.setText(backlogItem.getTitle());
        }
        TextView status = (TextView) getView().findViewById(R.id.backlog_detail_status);
        if (status != null) {
            status.setText(backlogItem.getStatus().getName(getContext()));
        }
        TextView text = (TextView) getView().findViewById(R.id.backlog_detail_text);
        if (text != null) {
            text.setText(backlogItem.getContent());
        }
    }
}
