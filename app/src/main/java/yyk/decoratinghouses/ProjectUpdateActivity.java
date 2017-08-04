package yyk.decoratinghouses;

import android.content.Intent;
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
import yyk.decoratinghouses.adapter.ProjectUpdateAdapter;
import yyk.decoratinghouses.bean.Opition;
import yyk.decoratinghouses.bean.Price;
import yyk.decoratinghouses.bean.ProjectCategory;
import yyk.decoratinghouses.bean.ProjectDetail;
import yyk.decoratinghouses.components.BottomDialogProject;
import yyk.decoratinghouses.presenter.ProjectSettingPresent;
import yyk.decoratinghouses.presenter.ProjectUpdatePresent;
import yyk.decoratinghouses.view.IProjectSettingView;
import yyk.decoratinghouses.view.IProjectUpdateView;

public class ProjectUpdateActivity extends BaseActivity implements IProjectUpdateView {

    @BindView(R.id.tool_bar)
    Toolbar mToolBar;
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.save_text)
    TextView mSaveText;

    private RecyclerView.LayoutManager mLayoutManager;
    private List<ProjectDetail> mProjectDetails;
    private ProjectUpdateAdapter mAdapter;
    private Intent mIntent;
    private String pc_name;

    private ProjectUpdatePresent mProjectUpdatePresent;

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
        mIntent = getIntent();
        pc_name = mIntent.getStringExtra("pc_name");
        mToolBar.setTitle(pc_name);
        initData();
    }

    @Override
    void initData() {
        mProjectUpdatePresent = new ProjectUpdatePresent(this, pc_name);
        mProjectUpdatePresent.selectDatabase();
    }

    @OnClick(R.id.save_text)
    public void onClick() {
        mProjectUpdatePresent.insertOrUpdateDatabase(mProjectDetails);
        setResult(1001);
        onBackPressed();
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
    public void showList(List<ProjectDetail> projectDetails) {
        mProjectDetails = projectDetails;
        mAdapter = new ProjectUpdateAdapter(projectDetails);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new ProjectUpdateAdapter.onItemClickListener() {
            @Override
            public void onItemClick(final View view, final int position) {
                BottomDialogProject bottomSheet = new BottomDialogProject(ProjectUpdateActivity.this, mProjectDetails.get(position).getO_id());
                bottomSheet.setOnItemClickListener(new BottomDialogProject.onItemClickListener() {
                    @Override
                    public void onItemClick(Price mPrice) {
                        ProjectDetail projectDetail = mProjectDetails.get(position);
                        projectDetail.setP_id(mPrice.getId());
                        projectDetail.setP_name(mPrice.getName());
                        projectDetail.setP_total(mPrice.getTotal());
                        mAdapter.notifyDataSetChanged();
                    }
                });
                bottomSheet.show();
            }
        });
    }
}
