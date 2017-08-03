package yyk.decoratinghouses;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import yyk.decoratinghouses.adapter.ParamsSettingAdapter;
import yyk.decoratinghouses.adapter.ProjectSettingAdapter;
import yyk.decoratinghouses.bean.Opition;
import yyk.decoratinghouses.bean.OpitionDao;
import yyk.decoratinghouses.bean.Price;
import yyk.decoratinghouses.bean.PriceDao;
import yyk.decoratinghouses.bean.ProjectDetail;
import yyk.decoratinghouses.components.BottomDialogProject;
import yyk.decoratinghouses.presenter.MainFragmentPresent;
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
    private List<List<Price>> mPrices;
    private List<String> mPriceNames;
    private ProjectSettingAdapter mAdapter;
    private Bundle mBundle;
    private Long d_id;
    private String d_name;

    private MainFragmentPresent mMainFragmentPresent;
    private PriceDao mPriceDao;
    private OpitionDao mOpitionDao;

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
        mToolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        initData();
    }

    @Override
    void initData() {
        mBundle = getIntent().getExtras();
        d_id = mBundle.getLong("d_id");
        d_name = mBundle.getString("d_name");
        mPriceDao = BaseApplication.getDaoInstant().getPriceDao();
        mOpitionDao = BaseApplication.getDaoInstant().getOpitionDao();
        mPrices = new ArrayList<>();
        mOpitions = mOpitionDao.queryBuilder().where(OpitionDao.Properties.D_id.eq(d_id)).list();
        mPriceNames = new ArrayList<>();
        for (int i = 0; i < mOpitions.size(); i++) {
            mPrices.add(mPriceDao.queryBuilder().where(PriceDao.Properties.O_id.eq(mOpitions.get(i).getId())).list());
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
                        mAdapter.notifyDataSetChanged();
                    }
                });
                bottomSheet.show();
            }
        });
    }

    @OnClick(R.id.save_text)
    public void onClick() {
    }

    @Override
    public void showLoading() {
    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showList(List<List<Price>> mPrices) {

    }
}
