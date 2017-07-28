package yyk.decoratinghouses.components;

import android.content.Context;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.View;

import yyk.decoratinghouses.BaseActivity;
import yyk.decoratinghouses.R;
import yyk.decoratinghouses.util.DensityUtil;

/**
 * Created by YYK on 2017/7/25.
 */

public class FragmentToolBar extends Toolbar {
    public FragmentToolBar(Context context) {
        super(context);
        initView(context);
    }

    public FragmentToolBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public FragmentToolBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        if(Build.VERSION.SDK_INT >= 21) {
            setElevation(DensityUtil.dip2px(context,4));
        }
        setBackgroundResource(R.color.colorPrimary);
        inflateMenu(R.menu.fragment_tool_bar);
        setOverflowIcon(getResources().getDrawable(R.mipmap.ic_more_vert_white_24dp));
        setTitle("装修计算器");
        setTitleTextColor(getResources().getColor(R.color.colorToolTitle));
    }
}
