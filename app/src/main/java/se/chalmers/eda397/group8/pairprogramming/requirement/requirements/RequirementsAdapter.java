package se.chalmers.eda397.group8.pairprogramming.requirement.requirements;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import group8.eda397.chalmers.se.pairprogramming.R;
import se.chalmers.eda397.group8.pairprogramming.requirement.Requirement;

/**
 * A RecyclerView adapter for requirements, showing each note as a custom
 * view.
 */
public class RequirementsAdapter extends RecyclerView.Adapter<RequirementsAdapter.ViewHolder> {

    private List<Requirement> mRequirements;
    private final RequirementItemClickListener mRequirementItemClickListener;

    public interface RequirementItemClickListener {
        void onRequirementClick(Requirement requirement);
    }

    /**
     * Creates a new RequirementsAdapter displaying pdf files in a the assets folder.
     *
     * @param requirements                 The initial list of requirement files to display (empty when called).
     * @param requirementItemClickListener The listener for when a requirement is clicked.
     */
    public RequirementsAdapter(@NonNull List<Requirement> requirements, RequirementItemClickListener requirementItemClickListener) {
        mRequirements = requirements;
        mRequirementItemClickListener = requirementItemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_requirement, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Requirement requirement = mRequirements.get(position);
        holder.fileName.setText(requirement.getFilePath());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (RequirementsAdapter.this.mRequirementItemClickListener != null) {
                    RequirementsAdapter.this.mRequirementItemClickListener.onRequirementClick(requirement);
                }
            }
        });
    }

    /**
     * Replaces the list of requirements displayed in the list by a new
     * list of requirements.
     *
     * @param requirements The new list of requirements to be displayed.
     */
    public void replaceData(@NonNull List<Requirement> requirements) {
        mRequirements = requirements;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mRequirements.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView fileName;

        public ViewHolder(View view) {
            super(view);
            fileName = (TextView) view.findViewById(R.id.fileName);
        }
    }

}
