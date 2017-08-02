package yyk.decoratinghouses.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import yyk.decoratinghouses.R;
import yyk.decoratinghouses.bean.Opition;
import yyk.decoratinghouses.bean.Price;

/**
 * Created by YYK on 2017/7/28.
 */

public class ProjectBottomDialogAdapter extends RecyclerView.Adapter<ProjectBottomDialogAdapter.ViewHolder> {

    private List<Price> mPrices;
    private onItemClickListener mOnItemClickListener;
    private onItemLongClickListener mOnItemLongClickListener;

    public ProjectBottomDialogAdapter(List<Price> prices) {
        mPrices = prices;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_dialog_bottom_project, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        Price price = mPrices.get(position);
        holder.name.setText(price.getName());
        holder.total.setText(price.getTotal() + "");
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
        return mPrices.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView name;
        TextView total;

        public ViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            total = itemView.findViewById(R.id.total);
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
