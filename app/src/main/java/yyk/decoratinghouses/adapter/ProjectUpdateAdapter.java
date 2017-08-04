package yyk.decoratinghouses.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import yyk.decoratinghouses.R;
import yyk.decoratinghouses.bean.Opition;
import yyk.decoratinghouses.bean.ProjectDetail;

/**
 * Created by YYK on 2017/7/28.
 */

public class ProjectUpdateAdapter extends RecyclerView.Adapter<ProjectUpdateAdapter.ViewHolder> {

    private List<ProjectDetail> mProjectDetails;
    private onItemClickListener mOnItemClickListener;
    private onItemLongClickListener mOnItemLongClickListener;

    public ProjectUpdateAdapter(List<ProjectDetail> projectDetails) {
        mProjectDetails = projectDetails;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_project_setting, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.nameText.setText(mProjectDetails.get(position).getO_name());
        holder.priceNameText.setText(mProjectDetails.get(position).getP_name());
        if(mOnItemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickListener.onItemClick(holder.itemView,position);
                }
            });
        }

        if(mOnItemLongClickListener != null) {
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    mOnItemLongClickListener.onItemLongClick(holder.itemView,position);
                    return true;
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mProjectDetails.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView nameText;
        TextView priceNameText;

        public ViewHolder(View itemView) {
            super(itemView);
            nameText = itemView.findViewById(R.id.name_text);
            priceNameText = itemView.findViewById(R.id.price_name_text);
        }
    }

    public void setOnItemClickListener(onItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    public void setOnItemLongClickListener(onItemLongClickListener onItemLongClickListener) {
        mOnItemLongClickListener = onItemLongClickListener;
    }

    public interface onItemClickListener {
        void onItemClick(View view, int position);
    }

    public interface onItemLongClickListener {
        void onItemLongClick(View view, int position);
    }
}
