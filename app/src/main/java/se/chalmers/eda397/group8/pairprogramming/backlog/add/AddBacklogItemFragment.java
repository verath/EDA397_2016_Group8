package se.chalmers.eda397.group8.pairprogramming.backlog.add;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import group8.eda397.chalmers.se.pairprogramming.R;
import se.chalmers.eda397.group8.pairprogramming.backlog.model.BacklogItem;

public class AddBacklogItemFragment extends Fragment implements AddBacklogContract.View {

    private AddBacklogContract.Presenter mPresenter;
    private EditText mTitleEt;
    private EditText mDescEt;
    private Spinner mStatusSp;

    public AddBacklogItemFragment() {
        // Required empty public constructor
    }

    public static AddBacklogItemFragment newInstance() {
        AddBacklogItemFragment fragment = new AddBacklogItemFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_backlog_item, container, false);

        mStatusSp = (Spinner) view.findViewById(R.id.backlog_status_spinner);
        ArrayAdapter<BacklogItem.Status> adapter = new CustomArrayAdapter(getActivity().getApplicationContext(),
                R.layout.backlog_status_spinner_item, BacklogItem.Status.values());
        adapter.setDropDownViewResource(R.layout.backlog_status_spinner_item);
        mStatusSp.setAdapter(adapter);

        mTitleEt = (EditText) view.findViewById(R.id.backlog_title_textfield);
        mDescEt = (EditText) view.findViewById(R.id.backlog_description_textfield);

        return view;
    }

    @Override
    public void setPresenter(@NonNull AddBacklogContract.Presenter presenter) {
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

    private boolean addBacklogItem() {
        String title = mTitleEt.getText().toString();
        if (title == null || title.length() == 0) {
            mTitleEt.setError(getResources().getString(R.string.missing_title));
            return true;
        }
        String desc = mDescEt.getText().toString();
        if (desc == null || desc.length() == 0) {
            mDescEt.setError(getResources().getString(R.string.missing_description));
            return true;
        }
        BacklogItem newItem = new BacklogItem(title, desc, (BacklogItem.Status) mStatusSp.getSelectedItem());
        mPresenter.onAddBacklogItem(newItem);
        getActivity().setResult(Activity.RESULT_OK);
        getActivity().finish();
        return true;
    }

    private class CustomArrayAdapter extends ArrayAdapter<BacklogItem.Status> {

        public CustomArrayAdapter(Context context, int resource, BacklogItem.Status[] objects) {
            super(context, resource, objects);
        }

        @Override
        public View getDropDownView(int position, View convertView, ViewGroup parent) {
            TextView tw = (TextView) ((LayoutInflater) getContext()
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.backlog_status_spinner_item, parent, false);
            tw.setText(getItem(position).getName(getContext()));
            return tw;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            TextView tw = (TextView) ((LayoutInflater) getContext()
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.backlog_status_spinner_item, parent, false);
            tw.setText(getItem(position).getName(getContext()));
            return tw;
        }
    }
}
