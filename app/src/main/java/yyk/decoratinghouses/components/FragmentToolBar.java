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

public class FragmentToolBar extends Toolbar {
    public FragmentToolBar(Context context) {
        super(context);
        initView();
    }

    public FragmentToolBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public FragmentToolBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        setBackgroundResource(R.color.colorPrimary);
        inflateMenu(R.menu.fragment_tool_bar);
        setOverflowIcon(getResources().getDrawable(R.mipmap.ic_more_vert_white_24dp));
        setTitle("装修计算器");
        setTitleTextColor(getResources().getColor(R.color.colorToolTitle));
    }
}
