package se.chalmers.eda397.group8.pairprogramming.backlog.addedit;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import se.chalmers.eda397.group8.pairprogramming.R;
import se.chalmers.eda397.group8.pairprogramming.backlog.model.BacklogStatus;
import se.chalmers.eda397.group8.pairprogramming.reqspec.RequirementSpecification;

public class AddEditBacklogItemFragment extends Fragment implements AddEditBacklogContract.View {

    private AddEditBacklogContract.Presenter mPresenter;
    private EditText mTitleEt;
    private EditText mDescEt;
    private Spinner mStatusSp;
    private Spinner mRequirementSp;
    private EditText mPageEt;
    private String mFileName;
    private ArrayAdapter<BacklogStatus> mStatusAdapter;
    private ArrayAdapter<RequirementSpecification> mRequirementAdapter;

    public AddEditBacklogItemFragment() {
        // Required empty public constructor
    }

    public static AddEditBacklogItemFragment newInstance() {
        AddEditBacklogItemFragment fragment = new AddEditBacklogItemFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        mStatusAdapter = new StatusArrayAdapter(getContext(), new ArrayList<BacklogStatus>());
        mRequirementAdapter = new RequirementArrayAdapter(getContext(), new ArrayList<RequirementSpecification>());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_backlog_item, container, false);

        mStatusSp = (Spinner) view.findViewById(R.id.backlog_status_spinner);
        mStatusAdapter.setDropDownViewResource(R.layout.backlog_status_spinner_item);
        mStatusSp.setAdapter(mStatusAdapter);

        mRequirementSp = (Spinner) view.findViewById(R.id.backlog_requirements_spinner);
        mRequirementAdapter.setDropDownViewResource(R.layout.backlog_requirement_spinner_item);
        mRequirementSp.setAdapter(mRequirementAdapter);

        mTitleEt = (EditText) view.findViewById(R.id.backlog_title_textfield);
        mDescEt = (EditText) view.findViewById(R.id.backlog_description_text_field);
        mPageEt = (EditText) view.findViewById(R.id.page_number_textfield);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Override
    public void setPresenter(@NonNull AddEditBacklogContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_add_backlog_item, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_backlog_item_add:
                return addBacklogItem();
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void showBacklog() {
        getActivity().setResult(Activity.RESULT_OK);
        getActivity().finish();
    }

    @Override
    public void showTitleEmptyError() {
        mTitleEt.setError(getResources().getString(R.string.missing_title));
    }

    @Override
    public void showTitle(String title) {
        mTitleEt.setText(title);
    }

    @Override
    public void showContent(String content) {
        mDescEt.setText(content);
    }

    @Override
    public void showStatuses(List<BacklogStatus> statuses) {
        mStatusAdapter.clear();
        mStatusAdapter.addAll(statuses);
    }

    @Override
    public void showSelectedStatus(BacklogStatus status) {
        mStatusSp.setSelection(mStatusAdapter.getPosition(status));
    }

    @Override
    public void showRequirements(List<RequirementSpecification> requirements) {
        mRequirementAdapter.clear();
        mRequirementAdapter.addAll(requirements);
    }

    @Override
    public void showSelectedRequirement(RequirementSpecification requirementSpecification) {
        mRequirementSp.setSelection(mRequirementAdapter.getPosition(requirementSpecification));
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
    public void showMissingBacklogItem() {
        // TODO: Show some view here instead?
        Toast.makeText(getContext(), R.string.backlog_item_does_not_exist, Toast.LENGTH_LONG).show();
        getActivity().finish();
    }

    @Override
    public void showPage(String page) {
        mPageEt.setText(page);
    }

    private boolean addBacklogItem() {
        String title = mTitleEt.getText().toString();
        String desc = mDescEt.getText().toString();
        String page = mPageEt.getText().toString();
        String PDFName = mFileName;
        Log.d("PDF", PDFName);
        String statusId = ((BacklogStatus) mStatusSp.getSelectedItem()).getId();
        mPresenter.onSaveItem(title, desc, statusId, page, PDFName);
        return true;
    }

    private class StatusArrayAdapter extends ArrayAdapter<BacklogStatus> {

        private final LayoutInflater mInflater;

        public StatusArrayAdapter(Context context, List<BacklogStatus> statuses) {
            super(context, 0, statuses);
            mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public View getDropDownView(int position, View convertView, ViewGroup parent) {
            return getView(position, convertView, parent);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            TextView tw;
            if (convertView == null || !(convertView instanceof TextView)) {
                tw = (TextView) mInflater.inflate(R.layout.backlog_status_spinner_item, parent, false);
            } else {
                tw = (TextView) convertView;
            }
            tw.setText(getItem(position).getName());
            return tw;
        }
    }

    private class RequirementArrayAdapter extends ArrayAdapter<RequirementSpecification> {

        private final LayoutInflater mInflater;

        public RequirementArrayAdapter(Context context, List<RequirementSpecification> requirements) {
            super(context, 0, requirements);
            mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public View getDropDownView(int position, View convertView, ViewGroup parent) {
            return getView(position, convertView, parent);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            TextView tw;
            if (convertView == null || !(convertView instanceof TextView)) {
                tw = (TextView) mInflater.inflate(R.layout.backlog_requirement_spinner_item, parent, false);
            } else {
                tw = (TextView) convertView;
            }
            tw.setText(getItem(position).getFilePath());
            String name = getItem(position).getFilePath();
            mFileName = name;
            return tw;
        }
    }
}
