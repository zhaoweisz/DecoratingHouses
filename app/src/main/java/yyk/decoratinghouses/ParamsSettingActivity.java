package yyk.decoratinghouses;

import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import yyk.decoratinghouses.adapter.ParamsSettingAdapter;
import yyk.decoratinghouses.bean.ZcOpitions;
import yyk.decoratinghouses.bean.ZcOpitionsDao;
import yyk.decoratinghouses.components.ParameterSettingsToolbar;
import yyk.decoratinghouses.util.DensityUtil;

public class ParamsSettingActivity extends AppCompatActivity {

    @BindView(R.id.tool_bar)
    ParameterSettingsToolbar mToolBar;
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout mSwipeRefreshLayout;

    private static final int UPDATE = 0;
    private static final int INSERT = 1;

    private AlertDialog.Builder builder;
    private AlertDialog dialog;
    private View dialogView;
    private Spinner spinner;
    private TextView cancleTextView;
    private TextView confirmTextView;
    private TextInputEditText nameTextView;
    private TextInputEditText numTextView;
    private String name;
    private float num;
    private String unit;

    private PopupWindow mPopupWindow;
    private View popView;
    private TextView updateTextView;
    private TextView deleteTextView;

    private RecyclerView.LayoutManager mLayoutManager;
    private List<ZcOpitions> mZcOpitionses;
    private ParamsSettingAdapter mAdapter;

    private ZcOpitionsDao mZcOpitionsDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_params_setting);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        builder = new AlertDialog.Builder(this);
        mZcOpitionsDao = BaseApplication.getDaoInstant().getZcOpitionsDao();
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mToolBar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.add_param:
                        showMyDialog(INSERT,null);
                        return true;
                }
                return false;
            }
        });
        initData();
    }

    private void initData() {
        mZcOpitionses = mZcOpitionsDao.loadAll();

        mAdapter = new ParamsSettingAdapter(mZcOpitionses);
        mAdapter.setOnItemLongClickListener(new ParamsSettingAdapter.onItemLongClickListener() {
            @Override
            public void onItemLongClick(View view, int position) {
                showPopWindow(view, position);
            }
        });
        mRecyclerView.setAdapter(mAdapter);
    }

    private void showMyDialog(final int type, ZcOpitions zcOpitions) {
        dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_param_alert_dialog,null);
        dialog = builder.create();
        dialog.setView(dialogView);
        dialog.show();
        spinner = dialogView.findViewById(R.id.spinner);
        cancleTextView = dialogView.findViewById(R.id.cancel);
        confirmTextView = dialogView.findViewById(R.id.confirm);
        nameTextView = dialogView.findViewById(R.id.name_textView);
        numTextView = dialogView.findViewById(R.id.num_textView);
        if(type == UPDATE) {
            nameTextView.setText(zcOpitions.getName());
            numTextView.setText(zcOpitions.getNumber() + "");
            spinner.setSelection(getUnitType(zcOpitions.getUnit()));
        }
        cancleTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String[] units = getResources().getStringArray(R.array.unit);
                unit = units[i];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Log.d("unint","00");
            }
        });

        confirmTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name = nameTextView.getText().toString();
                num = Float.valueOf(numTextView.getText().toString());
                ZcOpitions zcOpitions = new ZcOpitions(null, name, 2l ,num , unit);
                mZcOpitionsDao.insertOrReplace(zcOpitions);
                initData();
                dialog.dismiss();
            }
        });
    }

    private void showPopWindow(final View view, final int position) {
        popView = LayoutInflater.from(this).inflate(R.layout.list_popup,null);
        updateTextView = popView.findViewById(R.id.update_textView);
        deleteTextView = popView.findViewById(R.id.delete_textView);
        mPopupWindow = new PopupWindow(popView, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        mPopupWindow.setOutsideTouchable(true);
        if(Build.VERSION.SDK_INT >= 21) {
            mPopupWindow.setElevation(DensityUtil.dip2px(this,24));
        }
        popView.measure(0,0);
        mPopupWindow.showAsDropDown(view, (view.getWidth() - popView.getMeasuredWidth()) / 2, -view.getHeight() / 2);
        if(mPopupWindow.isShowing()) {
            view.setBackgroundColor(getResources().getColor(R.color.list_press));
        }
        mPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                view.setBackgroundResource(R.drawable.dialog_button_bg_ripples);
            }
        });
        updateTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPopupWindow.dismiss();
                showMyDialog(UPDATE, mZcOpitionses.get(position));
            }
        });
        deleteTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mZcOpitionsDao.delete(mZcOpitionses.get(position));
                mPopupWindow.dismiss();
                initData();
            }
        });
    }

    private int getUnitType(String unit) {
        if("m".equals(unit)) {
            return 0;
        } else {
            return 1;
        }
    }
}
