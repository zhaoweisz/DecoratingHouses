package yyk.decoratinghouses;

import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import yyk.decoratinghouses.adapter.ParamsSettingAdapter;
import yyk.decoratinghouses.bean.Opition;
import yyk.decoratinghouses.components.ParameterSettingsToolbar;
import yyk.decoratinghouses.presenter.ParamsSettingPresent;
import yyk.decoratinghouses.util.DensityUtil;
import yyk.decoratinghouses.view.IParamsSettingView;

public class ParamsSettingActivity extends BaseActivity implements IParamsSettingView {

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
    private TextInputLayout nameInputLayout;
    private TextInputLayout numInputLayout;
    private String name;
    private float num;
    private String unit;

    private PopupWindow mPopupWindow;
    private View popView;
    private TextView updateTextView;
    private TextView deleteTextView;

    private RecyclerView.LayoutManager mLayoutManager;
    private List<Opition> mOpitions;
    private ParamsSettingAdapter mAdapter;
    private Bundle mBundle;
    private Long d_id;
    private String d_name;

    private ParamsSettingPresent mParamsSettingPresent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_params_setting);
        ButterKnife.bind(this);
        mBundle = getIntent().getExtras();
        d_name = mBundle.getString("d_name");
        d_id = mBundle.getLong("d_id");
        mToolBar.setTitle(d_name + "参数");
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        mParamsSettingPresent = new ParamsSettingPresent(this, d_id);
        builder = new AlertDialog.Builder(this);
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
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                initData();
            }
        });
        initData();
    }

    @Override
    protected void initData() {
        mParamsSettingPresent.selectDatabase();
    }

    private int getUnitType(String unit) {
        switch (unit) {
            case "m":
                return 0;
            case "㎡":
                return 1;
            case "个":
                return 2;
            case "套":
                return 3;
        }
        return 0;
    }

    @Override
    public void showLoading() {
        mSwipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                mSwipeRefreshLayout.setRefreshing(true);
            }
        });
    }

    @Override
    public void hideLoading() {
        mSwipeRefreshLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                mSwipeRefreshLayout.setRefreshing(false);
            }
        }, 1000);
    }

    @Override
    public void showList(final List<Opition> opitions) {
        mOpitions = opitions;
        mAdapter = new ParamsSettingAdapter(opitions);
        mAdapter.setOnItemLongClickListener(new ParamsSettingAdapter.onItemLongClickListener() {
            @Override
            public void onItemLongClick(View view, int position) {
                showPopWindow(view, position);
            }
        });
        mAdapter.setOnItemClickListener(new ParamsSettingAdapter.onItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                mBundle.putParcelable("opition",opitions.get(position));
                startActivityBase(PriceSettingActivity.class, mBundle, null, null);
            }
        });
        mRecyclerView.setAdapter(mAdapter);
    }

    private void showMyDialog(final int type, Opition opition) {
        dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_param,null);
        dialog = builder.create();
        dialog.setView(dialogView);
        dialog.show();
        spinner = dialogView.findViewById(R.id.spinner);
        cancleTextView = dialogView.findViewById(R.id.cancel);
        confirmTextView = dialogView.findViewById(R.id.confirm);
        nameTextView = dialogView.findViewById(R.id.name_textView);
        numTextView = dialogView.findViewById(R.id.num_textView);
        nameInputLayout = dialogView.findViewById(R.id.name_textInputLaoyout);
        numInputLayout = dialogView.findViewById(R.id.num_TextInputLayout);

        nameTextView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                nameInputLayout.setErrorEnabled(false);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        numTextView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                numInputLayout.setErrorEnabled(false);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        if(type == UPDATE) {
            nameTextView.setText(opition.getName());
            numTextView.setText(opition.getNumber() + "");
            spinner.setSelection(getUnitType(opition.getUnit()),true);
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
                name = nameInputLayout.getEditText().getText().toString().trim();
                String numText = numTextView.getText().toString();
                if(validateName(name) && validateNum(numText)) {
                    num = Float.valueOf(numText);
                    Opition opition = new Opition(null, name, d_id, d_name ,num , unit);
                    mParamsSettingPresent.insertOrUpdateDatabase(opition);
                    initData();
                    dialog.dismiss();
                }
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
                showMyDialog(UPDATE, mOpitions.get(position));
            }
        });
        deleteTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mParamsSettingPresent.deleteDatabase(mOpitions.get(position));
                mPopupWindow.dismiss();
                initData();
            }
        });
    }

    private boolean validateName(String nameText) {
        if(nameText.isEmpty()) {
            showError(nameInputLayout, "选项不能为空");
            return false;
        }
        return true;
    }

    private boolean validateNum(String numText) {
        Pattern pattern = Pattern.compile("^(([0-9]|([1-9][0-9]{0,9}))((\\.[0-9]{1,3})?))$");
        Matcher matcher = pattern.matcher(numText);
        if(!matcher.matches()) {
            showError(numInputLayout, "数量格式错误");
            return false;
        }
        return true;
    }

    private void showError(TextInputLayout textInputLayout, String error) {
        textInputLayout.setError(error);
    }
}
