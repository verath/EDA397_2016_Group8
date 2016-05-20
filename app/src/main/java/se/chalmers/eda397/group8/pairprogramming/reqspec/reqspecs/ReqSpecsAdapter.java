package se.chalmers.eda397.group8.pairprogramming.reqspec.reqspecs;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import se.chalmers.eda397.group8.pairprogramming.R;
import se.chalmers.eda397.group8.pairprogramming.reqspec.data.RequirementSpecification;

/**
 * A RecyclerView adapter for requirements, showing each note as a custom
 * view.
 */
public class ReqSpecsAdapter extends RecyclerView.Adapter<ReqSpecsAdapter.ViewHolder> {

    private List<RequirementSpecification> mRequirementSpecifications;
    private final RequirementItemClickListener mRequirementItemClickListener;

    public interface RequirementItemClickListener {
        void onRequirementClick(RequirementSpecification requirementSpecification);
    }

    /**
     * Creates a new ReqSpecsAdapter displaying pdf files in a the assets folder.
     *
     * @param requirementSpecifications    The initial list of requirement files to display (empty when called).
     * @param requirementItemClickListener The listener for when a requirement is clicked.
     */
    public ReqSpecsAdapter(@NonNull List<RequirementSpecification> requirementSpecifications, RequirementItemClickListener requirementItemClickListener) {
        mRequirementSpecifications = requirementSpecifications;
        mRequirementItemClickListener = requirementItemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_req_spec, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final RequirementSpecification requirementSpecification = mRequirementSpecifications.get(position);
        holder.fileName.setText(requirementSpecification.getFilePath());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ReqSpecsAdapter.this.mRequirementItemClickListener != null) {
                    ReqSpecsAdapter.this.mRequirementItemClickListener.onRequirementClick(requirementSpecification);
                }
            }
        });
    }

    /**
     * Replaces the list of requirementSpecifications displayed in the list by a new
     * list of requirementSpecifications.
     *
     * @param requirementSpecifications The new list of requirementSpecifications to be displayed.
     */
    public void replaceData(@NonNull List<RequirementSpecification> requirementSpecifications) {
        mRequirementSpecifications = requirementSpecifications;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mRequirementSpecifications.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView fileName;

        public ViewHolder(View view) {
            super(view);
            fileName = (TextView) view.findViewById(R.id.fileName);
        }
    }

}
