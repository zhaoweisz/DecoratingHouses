package yyk.decoratinghouses.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

import yyk.decoratinghouses.R;
import yyk.decoratinghouses.bean.Price;
import yyk.decoratinghouses.bean.ProjectDetail;

/**
 * Created by YYK on 2017/7/28.
 */

public class FragmentAdapter extends RecyclerView.Adapter<FragmentAdapter.ViewHolder> {

    private List<List<ProjectDetail>> mProjects;
    private onItemClickListener mOnItemClickListener;
    private onItemLongClickListener mOnItemLongClickListener;

    public FragmentAdapter(List<List<ProjectDetail>> projects) {
        mProjects = projects;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_project_fragment, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        List<ProjectDetail> projectDetails = mProjects.get(position);
        holder.primaryText.setText(projectDetails.get(0).getPc_name());
        float sum = 0;
        StringBuilder content = new StringBuilder();
        for (int i = 0; i < projectDetails.size(); i++) {
            ProjectDetail projectDetail = projectDetails.get(i);
            sum = sum + projectDetail.getP_total();
            content.append(projectDetail.getO_name()).append("：").append(projectDetail.getP_name()).append("\n");
        }
        holder.subText.setText(sum + "");
        holder.contentText.setText(content.toString());
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
        return mProjects.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView primaryText;
        TextView subText;
        TextView contentText;

        public ViewHolder(View itemView) {
            super(itemView);
            primaryText = itemView.findViewById(R.id.primary_text);
            subText = itemView.findViewById(R.id.sub_text);
            contentText = itemView.findViewById(R.id.content_text);
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
