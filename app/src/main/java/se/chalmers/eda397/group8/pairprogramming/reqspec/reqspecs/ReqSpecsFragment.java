package se.chalmers.eda397.group8.pairprogramming.reqspec.reqspecs;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import se.chalmers.eda397.group8.pairprogramming.R;
import se.chalmers.eda397.group8.pairprogramming.reqspec.RequirementSpecification;
import se.chalmers.eda397.group8.pairprogramming.reqspec.detail.ReqSpecDetailActivity;

/**
 * Created by mysko1 on 2016-04-21.
 */
public class ReqSpecsFragment extends Fragment implements ReqSpecsContract.View {

    private ReqSpecsContract.Presenter mPresenter;
    private ReqSpecsAdapter mAdapter;

    public static ReqSpecsFragment newInstance() {
        return new ReqSpecsFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAdapter = new ReqSpecsAdapter(new ArrayList<RequirementSpecification>(), mOnRequirementClickListener);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_req_specs, container, false);

        // Setup the recycler view
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.requirements_list);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(mAdapter);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    public void showRequirements(List<RequirementSpecification> requirementSpecifications) {
        mAdapter.replaceData(requirementSpecifications);
    }

    @Override
    public void displayRequirement(String reqSpecId) {
        Intent intent = ReqSpecDetailActivity.getCallingIntent(getContext(), reqSpecId);
        startActivity(intent);
    }

    @Override
    public void setPresenter(@NonNull ReqSpecsContract.Presenter presenter) {
        this.mPresenter = presenter;
    }

    private final ReqSpecsAdapter.RequirementItemClickListener mOnRequirementClickListener =
            new ReqSpecsAdapter.RequirementItemClickListener() {
                @Override
                public void onRequirementClick(RequirementSpecification requirementSpecification) {
                    if (ReqSpecsFragment.this.mPresenter != null && requirementSpecification != null) {
                        ReqSpecsFragment.this.mPresenter.onRequirementClicked(requirementSpecification);
                    }
                }

            };

}
