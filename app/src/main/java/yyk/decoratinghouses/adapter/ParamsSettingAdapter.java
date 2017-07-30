package yyk.decoratinghouses.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import yyk.decoratinghouses.R;
import yyk.decoratinghouses.bean.Opition;

/**
 * Created by YYK on 2017/7/28.
 */

public class ParamsSettingAdapter extends RecyclerView.Adapter<ParamsSettingAdapter.ViewHolder> {

    private List<Opition> mOpitionses;
    private onItemClickListener mOnItemClickListener;
    private onItemLongClickListener mOnItemLongClickListener;

    public ParamsSettingAdapter(List<Opition> opitionses) {
        mOpitionses = opitionses;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_params_setting, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        Opition opition = mOpitionses.get(position);
        holder.name.setText(opition.getName());
        holder.num.setText(opition.getNumber() + opition.getUnit());
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
                    return false;
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mOpitionses.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView name;
        TextView num;

        public ViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            num = itemView.findViewById(R.id.num);
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
