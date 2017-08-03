package yyk.decoratinghouses.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import yyk.decoratinghouses.BaseActivity;
import yyk.decoratinghouses.ParamsSettingActivity;
import yyk.decoratinghouses.ProjectSettingActivity;
import yyk.decoratinghouses.R;
import yyk.decoratinghouses.adapter.FragmentAdapter;
import yyk.decoratinghouses.bean.ProjectDetail;
import yyk.decoratinghouses.components.FragmentToolBar;
import yyk.decoratinghouses.presenter.MainFragmentPresent;
import yyk.decoratinghouses.view.IMainFragmentView;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment implements IMainFragmentView {

    Unbinder unbinder;
    @BindView(R.id.tool_bar)
    FragmentToolBar mToolBar;
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    private Long d_id;
    private String d_name;
    private Bundle mBundle;

    private MainFragmentPresent mMainFragmentPresent;

    private RecyclerView.LayoutManager mLayoutManager;
    private FragmentAdapter mAdapter;
    private List<List<ProjectDetail>> mProjects;


    public MainFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        unbinder = ButterKnife.bind(this, view);
        initView();
        return view;
    }

    private void initView() {
        mBundle = getArguments();
        d_id = mBundle.getLong("d_id");
        d_name = mBundle.getString("d_name");
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mMainFragmentPresent = new MainFragmentPresent(this, d_id);
        mMainFragmentPresent.selectDatabase();
        mToolBar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.add:
                        ((BaseActivity)getContext()).startActivityBase(ProjectSettingActivity.class,mBundle,null,null);
                        return true;
                    case R.id.update:
                        ((BaseActivity)getContext()).startActivityBase(ParamsSettingActivity.class,mBundle,null,null);
                        return true;
                }
                return false;
            }
        });
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
    public void showList(List<List<ProjectDetail>> projects) {
        mProjects = projects;
        mAdapter = new FragmentAdapter(mProjects);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
