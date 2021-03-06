package se.chalmers.eda397.group8.pairprogramming.backlog;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import se.chalmers.eda397.group8.pairprogramming.R;
import se.chalmers.eda397.group8.pairprogramming.backlog.model.BacklogItem;

/**
 * Created by m_cal on 2016-04-21.
 */
public class BacklogItemAdapter extends RecyclerView.Adapter<BacklogItemAdapter.ViewHolder> {

    private List<BacklogItem> mBacklogItems;
    private final BacklogItemListener mBacklogItemListener;

    public interface BacklogItemListener {
        void onBacklogItemClick(BacklogItem item);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView title;
        public final TextView text;

        public ViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            text = (TextView) view.findViewById(R.id.text);
        }
    }

    public BacklogItemAdapter(@NonNull List<BacklogItem> backlogItems, BacklogItemListener backlogItemListener) {
        this.mBacklogItems = backlogItems;
        this.mBacklogItemListener = backlogItemListener;
    }

    public void replaceData(@NonNull List<BacklogItem> backlogItems) {
        this.mBacklogItems = backlogItems;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_backlog, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final BacklogItem backlogItem = mBacklogItems.get(position);
        holder.title.setText(backlogItem.getTitle());
        holder.text.setText(backlogItem.getContent());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mBacklogItemListener != null) {
                    mBacklogItemListener.onBacklogItemClick(backlogItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mBacklogItems.size();
    }
}
