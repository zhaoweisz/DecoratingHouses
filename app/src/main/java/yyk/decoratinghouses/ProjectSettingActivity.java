package yyk.decoratinghouses;

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
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import yyk.decoratinghouses.adapter.ProjectSettingAdapter;
import yyk.decoratinghouses.bean.Opition;
import yyk.decoratinghouses.bean.Price;
import yyk.decoratinghouses.bean.ProjectCategory;
import yyk.decoratinghouses.bean.ProjectDetail;
import yyk.decoratinghouses.components.BottomDialogProject;
import yyk.decoratinghouses.presenter.ProjectSettingPresent;
import yyk.decoratinghouses.view.IProjectSettingView;

public class ProjectSettingActivity extends BaseActivity implements IProjectSettingView {

    @BindView(R.id.tool_bar)
    Toolbar mToolBar;
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.save_text)
    TextView mSaveText;

    private RecyclerView.LayoutManager mLayoutManager;
    private List<Opition> mOpitions;
    private List<String> mPriceNames;
    private List<Price> mPrices;
    private ProjectSettingAdapter mAdapter;
    private Bundle mBundle;
    private Long d_id;
    private String d_name;

    private AlertDialog.Builder builder;
    private AlertDialog dialog;
    private View dialogView;
    private String project_title;
    private TextView cancleTextView;
    private TextView confirmTextView;
    private TextInputLayout nameInputLayout;
    private TextInputEditText nameTextView;

    private ProjectSettingPresent mProjectSettingPresent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    void initView() {
        setContentView(R.layout.activity_project_setting);
        ButterKnife.bind(this);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                initData();
            }
        });
        mToolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        showMyDialog();
    }

    @Override
    void initData() {
        mBundle = getIntent().getExtras();
        d_id = mBundle.getLong("d_id");
        d_name = mBundle.getString("d_name");
        mProjectSettingPresent = new ProjectSettingPresent(this,d_id);
        mProjectSettingPresent.selectDatabase();
    }

    @OnClick(R.id.save_text)
    public void onClick() {
        if(canSave()) {
            ProjectCategory projectCategory = new ProjectCategory(null, d_name + project_title, d_id, d_name);
            List<ProjectDetail> projectDetails = new ArrayList<>();
            for (int i = 0; i < mPrices.size(); i++) {
                Price price = mPrices.get(i);
                ProjectDetail projectDetail = new ProjectDetail(null,d_name + project_title + price.getName(),
                        d_name + project_title,price.getId(),price.getName(),
                        price.getTotal(),price.getO_id(),price.getO_name(),price.getD_id(),price.getD_name());
                projectDetails.add(projectDetail);
            }
            mProjectSettingPresent.insertOrUpdateDatabase(projectCategory, projectDetails);
            setResult(1001);
            onBackPressed();
        } else {
            Toast.makeText(this,"还有未选择的选项",Toast.LENGTH_SHORT).show();
        }

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
    public void showList(List<Opition> opitions) {
        mOpitions = opitions;
        mPriceNames = new ArrayList<>();
        mPrices = new ArrayList<>();
        for (int i = 0; i < mOpitions.size(); i++) {
            mPriceNames.add(i,"");
        }
        mAdapter = new ProjectSettingAdapter(mOpitions, mPriceNames);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new ProjectSettingAdapter.onItemClickListener() {
            @Override
            public void onItemClick(final View view, final int position) {
                BottomDialogProject bottomSheet = new BottomDialogProject(ProjectSettingActivity.this,mOpitions.get(position).getId());
                bottomSheet.setOnItemClickListener(new BottomDialogProject.onItemClickListener() {
                    @Override
                    public void onItemClick(Price mPrice) {
                        mPriceNames.remove(position);
                        mPriceNames.add(position,mPrice.getName());
                        if(mPrices.size() > position && mPrices.get(position) != null) {
                            mPrices.remove(position);
                        }
                        mPrices.add(position, mPrice);
                        mAdapter.notifyDataSetChanged();
                    }
                });
                bottomSheet.show();
            }
        });
    }

    private void showMyDialog() {
        builder = new AlertDialog.Builder(this);
        dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_project_title,null);
        dialog = builder.create();
        dialog.setView(dialogView);
        dialog.setCancelable(false);
        dialog.show();
        cancleTextView = dialogView.findViewById(R.id.cancel);
        confirmTextView = dialogView.findViewById(R.id.confirm);
        nameTextView = dialogView.findViewById(R.id.name_textView);
        nameInputLayout = dialogView.findViewById(R.id.name_textInputLaoyout);
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
        cancleTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                onBackPressed();
            }
        });

        confirmTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                project_title = nameInputLayout.getEditText().getText().toString().trim();
                if(validateName(project_title)) {
                    dialog.dismiss();
                    mToolBar.setTitle(project_title);
                    initData();
                }
            }
        });
    }

    private boolean validateName(String nameText) {
        if(nameText.isEmpty()) {
            showError(nameInputLayout, "方案名称不能为空");
            return false;
        }
        return true;
    }

    private void showError(TextInputLayout textInputLayout, String error) {
        textInputLayout.setError(error);
    }

    private boolean canSave() {
        if(mOpitions.size() == mPrices.size()) {
            return true;
        }
        return false;
    }
}
