package group8.eda397.chalmers.se.pairprogramming.requirementsSelector;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import group8.eda397.chalmers.se.pairprogramming.R;
import group8.eda397.chalmers.se.pairprogramming.requirements.Requirement;

public class RequirementsSelectorAdapter extends RecyclerView.Adapter<RequirementsSelectorAdapter.ViewHolder> {

    private List<Requirement> requirements;
    private RequirementItemClickListener requirementItemClickListener;

    public interface RequirementItemClickListener {
        void onRequirementClick(Requirement requirement);
    }

    public RequirementsSelectorAdapter(@NonNull List<Requirement> requirements, RequirementItemClickListener requirementItemClickListener) {
        this.requirements = requirements;
        this.requirementItemClickListener = requirementItemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_requirement, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Requirement requirement = requirements.get(position);
        holder.fileName.setText(requirement.getFilePath());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (RequirementsSelectorAdapter.this.requirementItemClickListener != null) {
                    RequirementsSelectorAdapter.this.requirementItemClickListener.onRequirementClick(requirement);
                }
            }
        });
    }

    public void replaceData(@NonNull List<Requirement> requirements) {
        this.requirements = requirements;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return requirements.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView fileName;

        public ViewHolder(View view) {
            super(view);
            fileName = (TextView) view.findViewById(R.id.fileName);
        }
    }

}
