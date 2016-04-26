package se.chalmers.eda397.group8.pairprogramming.requirementsSelector;

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

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import group8.eda397.chalmers.se.pairprogramming.R;
import se.chalmers.eda397.group8.pairprogramming.requirements.Requirement;
import se.chalmers.eda397.group8.pairprogramming.requirements.RequirementsActivity;

/**
 * Created by mysko1 on 2016-04-21.
 */
public class RequirementsSelectorFragment extends Fragment implements RequirementsSelectorContract.View {

    private RequirementsSelectorContract.Presenter mPresenter;
    private RequirementsSelectorAdapter mAdapter;

    public static RequirementsSelectorFragment newInstance() {
        return new RequirementsSelectorFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAdapter = new RequirementsSelectorAdapter(new ArrayList<Requirement>(), mOnRequirementClickListener);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_reqselector, container, false);

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

    public void showRequirements(List<Requirement> requirements) {
        mAdapter.replaceData(requirements);
    }

    @Override
    public void displayRequirement(Requirement requirement) {
        Intent intent = RequirementsActivity.getCallingIntent(getContext(), requirement);
        startActivity(intent);
    }

    //Get all the files from the assets folder.
    @Override
    public String[] getFileNames() {
        try {
            return getContext().getAssets().list("");
        } catch (IOException e) {
            e.printStackTrace();
            return new String[]{};
        }
    }

    @Override
    public void setPresenter(@NonNull RequirementsSelectorContract.Presenter presenter) {
        this.mPresenter = presenter;
    }

    private final RequirementsSelectorAdapter.RequirementItemClickListener mOnRequirementClickListener =
            new RequirementsSelectorAdapter.RequirementItemClickListener() {
                @Override
                public void onRequirementClick(Requirement requirement) {
                    if (RequirementsSelectorFragment.this.mPresenter != null && requirement != null) {
                        RequirementsSelectorFragment.this.mPresenter.onRequirementClicked(requirement);
                    }
                }

            };

}
