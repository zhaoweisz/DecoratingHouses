package yyk.decoratinghouses.components;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import java.util.List;

import yyk.decoratinghouses.BaseApplication;
import yyk.decoratinghouses.R;
import yyk.decoratinghouses.adapter.ProjectBottomDialogAdapter;
import yyk.decoratinghouses.bean.Price;
import yyk.decoratinghouses.bean.PriceDao;

/**
 * Created by YYK on 2017/8/2.
 */

public class BottomDialogProject extends BottomSheetDialog {

    private Long o_id;
    private Context context;
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private View view;
    private ProjectBottomDialogAdapter mAdapter;
    private List<Price> mPrices;
    private PriceDao mPriceDao;
    private Price mPrice;
    private onItemClickListener mOnItemClickListener;

    public BottomDialogProject(@NonNull Context context, Long o_id) {
        super(context);
        this.o_id = o_id;
        this.context = context;
        initView();
    }

    public void setOnItemClickListener(onItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    private void initView() {
        view = LayoutInflater.from(context).inflate(R.layout.dialog_bottom_project_setting,null);
        setContentView(view);
        mRecyclerView = view.findViewById(R.id.recycler_view);
        initData();
    }

    private void initData() {
        mPriceDao = BaseApplication.getDaoInstant().getPriceDao();
        mPrices = mPriceDao.queryBuilder().where(PriceDao.Properties.O_id.eq(o_id)).list();
        mAdapter = new ProjectBottomDialogAdapter(mPrices);
        mLayoutManager = new LinearLayoutManager(context);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new ProjectBottomDialogAdapter.onItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                mPrice = mPrices.get(position);
                mOnItemClickListener.onItemClick(mPrice);
                dismiss();
            }
        });
    }

    public interface onItemClickListener{
        void onItemClick(Price mPrice);
    }
}
