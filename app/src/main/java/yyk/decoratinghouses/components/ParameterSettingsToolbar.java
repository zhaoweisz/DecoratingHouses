package yyk.decoratinghouses.components;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.jaredrummler.materialspinner.MaterialSpinner;

import yyk.decoratinghouses.R;
import yyk.decoratinghouses.util.DensityUtil;

/**
 * Created by YYK on 2017/7/27.
 */

public class ParameterSettingsToolbar extends Toolbar {

    private AlertDialog.Builder builder;

    public ParameterSettingsToolbar(Context context) {
        super(context);
        initView(context);
    }

    public ParameterSettingsToolbar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public ParameterSettingsToolbar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(final Context context) {
//        builder = new AlertDialog.Builder(context);
        if(Build.VERSION.SDK_INT >= 21) {
            setElevation(DensityUtil.dip2px(context,4));
        }
        setBackgroundResource(R.color.colorPrimary);
        inflateMenu(R.menu.param_setting_tool_bar);
        setOverflowIcon(getResources().getDrawable(R.mipmap.ic_more_vert_white_24dp));
        setTitleTextColor(getResources().getColor(R.color.colorToolTitle));
        setNavigationIcon(R.mipmap.ic_arrow_back_white_24dp);
        setNavigationOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                ((Activity)context).onBackPressed();
            }
        });

//        setOnMenuItemClickListener(new OnMenuItemClickListener() {
//            @Override
//            public boolean onMenuItemClick(MenuItem item) {
//                switch (item.getItemId()) {
//                    case R.id.add_param:
//                        final View dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_param_alert_dialog,null);
//                        Spinner spinner = dialogView.findViewById(R.id.spinner);
//                        TextView cancleTextView = dialogView.findViewById(R.id.cancel);
//                        TextView confirmTextView = dialogView.findViewById(R.id.confirm);
//                        final AlertDialog dialog = builder.create();
//                        dialog.setView(dialogView);
//                        dialog.show();
//                        cancleTextView.setOnClickListener(new OnClickListener() {
//                            @Override
//                            public void onClick(View view) {
//                                dialog.dismiss();
//                            }
//                        });
//                        return true;
//                }
//                return false;
//            }
//        });
    }
}
