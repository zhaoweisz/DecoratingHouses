package yyk.decoratinghouses.components;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.View;

import yyk.decoratinghouses.BaseActivity;
import yyk.decoratinghouses.R;

/**
 * Created by YYK on 2017/7/25.
 */

public class MyToolBar extends Toolbar {
    public MyToolBar(Context context) {
        super(context);
        initView();
    }

    public MyToolBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public MyToolBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        setBackgroundResource(R.color.colorPrimary);
        setNavigationIcon(R.mipmap.ic_arrow_back_white_24dp);
        setOverflowIcon(getResources().getDrawable(R.mipmap.ic_more_vert_white_24dp));
        setNavigationOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                ((BaseActivity)(getContext())).finish();
            }
        });
    }
}
