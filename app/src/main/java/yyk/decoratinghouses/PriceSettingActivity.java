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
import yyk.decoratinghouses.adapter.PriceSettingAdapter;
import yyk.decoratinghouses.bean.Opition;
import yyk.decoratinghouses.bean.Price;
import yyk.decoratinghouses.components.ParameterSettingsToolbar;
import yyk.decoratinghouses.presenter.ParamsSettingPresent;
import yyk.decoratinghouses.presenter.PriceSettingPresent;
import yyk.decoratinghouses.util.DensityUtil;
import yyk.decoratinghouses.view.IParamsSettingView;
import yyk.decoratinghouses.view.IPriceSettingView;

public class PriceSettingActivity extends BaseActivity implements IPriceSettingView {

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
    private TextView cancleTextView;
    private TextView confirmTextView;
    private TextInputEditText nameTextView;
    private TextInputEditText priceTextView;
    private TextInputLayout nameInputLayout;
    private TextInputLayout priceInputLayout;
    private String name;
    private float t_price;
    private float total;
    private String unit;

    private PopupWindow mPopupWindow;
    private View popView;
    private TextView updateTextView;
    private TextView deleteTextView;

    private RecyclerView.LayoutManager mLayoutManager;
    private List<Price> mPrices;
    private PriceSettingAdapter mAdapter;
    private Bundle mBundle;
    private Long o_id;
    private String o_name;
    private Opition mOpition;

    private PriceSettingPresent mPriceSettingPresent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_price_setting);
        ButterKnife.bind(this);
        mBundle = getIntent().getExtras();
        mOpition = mBundle.getParcelable("opition");
        o_name = mOpition.getName();
        o_id = mOpition.getId();
        mToolBar.setTitle(o_name + "品牌");
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        mPriceSettingPresent = new PriceSettingPresent(this, o_id);
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
        mPriceSettingPresent.selectDatabase();
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
        }, 500);
    }

    @Override
    public void showList(List<Price> prices) {
        mPrices = prices;
        mAdapter = new PriceSettingAdapter(prices);
        mAdapter.setOnItemLongClickListener(new PriceSettingAdapter.onItemLongClickListener() {
            @Override
            public void onItemLongClick(View view, int position) {
                showPopWindow(view, position);
            }
        });
        mAdapter.setOnItemClickListener(new PriceSettingAdapter.onItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

            }
        });
        mRecyclerView.setAdapter(mAdapter);
    }

    private void showMyDialog(final int type, final Price price) {
        dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_price,null);
        dialog = builder.create();
        dialog.setView(dialogView);
        dialog.show();
        cancleTextView = dialogView.findViewById(R.id.cancel);
        confirmTextView = dialogView.findViewById(R.id.confirm);
        nameTextView = dialogView.findViewById(R.id.name_textView);
        priceTextView = dialogView.findViewById(R.id.price_textView);
        nameInputLayout = dialogView.findViewById(R.id.name_textInputLaoyout);
        priceInputLayout = dialogView.findViewById(R.id.price_TextInputLayout);

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
        priceTextView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                priceInputLayout.setErrorEnabled(false);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        if(type == UPDATE) {
            nameTextView.setText(price.getName());
            priceTextView.setText(price.getNumber() + "");
        }
        cancleTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        confirmTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name = nameInputLayout.getEditText().getText().toString().trim();
                String priceText = priceTextView.getText().toString();
                if(validateName(name) && validateNum(priceText)) {
                    t_price = Float.valueOf(priceText);
                    total = t_price * mOpition.getNumber();
                    Price price = new Price(null, name, t_price, mOpition.getId(), mOpition.getName(),
                            mOpition.getD_id(),mOpition.getD_name(),mOpition.getNumber(),total,mOpition.getUnit());
                    mPriceSettingPresent.insertOrUpdateDatabase(price);
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
                showMyDialog(UPDATE, mPrices.get(position));
            }
        });
        deleteTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPriceSettingPresent.deleteDatabase(mPrices.get(position));
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
        Pattern pattern = Pattern.compile("^(([0-9]|([1-9][0-9]{0,9}))((\\.[0-9]{1,2})?))$");
        Matcher matcher = pattern.matcher(numText);
        if(!matcher.matches()) {
            showError(priceInputLayout, "价格格式错误");
            return false;
        }
        return true;
    }

    private void showError(TextInputLayout textInputLayout, String error) {
        textInputLayout.setError(error);
    }
}
