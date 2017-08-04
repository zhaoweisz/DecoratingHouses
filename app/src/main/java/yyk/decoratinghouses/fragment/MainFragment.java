package yyk.decoratinghouses.fragment;


import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
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
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import yyk.decoratinghouses.BaseActivity;
import yyk.decoratinghouses.ParamsSettingActivity;
import yyk.decoratinghouses.ProjectSettingActivity;
import yyk.decoratinghouses.ProjectUpdateActivity;
import yyk.decoratinghouses.R;
import yyk.decoratinghouses.adapter.FragmentAdapter;
import yyk.decoratinghouses.bean.ProjectCategory;
import yyk.decoratinghouses.bean.ProjectDetail;
import yyk.decoratinghouses.components.FragmentToolBar;
import yyk.decoratinghouses.presenter.MainFragmentPresent;
import yyk.decoratinghouses.util.DensityUtil;
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
    private List<ProjectCategory> mCategories;

    private PopupWindow mPopupWindow;
    private View popView;
    private TextView updateTextView;
    private TextView deleteTextView;


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
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                initData();
            }
        });
        mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mToolBar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.add:
                        Intent intent = new Intent();
                        intent.putExtras(mBundle);
                        startMyActivityForResult(intent, ProjectSettingActivity.class);
                        return true;
                    case R.id.update:
                        ((BaseActivity)getContext()).startActivityBase(ParamsSettingActivity.class,mBundle,null,null);
                        return true;
                }
                return false;
            }
        });
        initData();
    }

    private void initData() {
        mBundle = getArguments();
        d_id = mBundle.getLong("d_id");
        d_name = mBundle.getString("d_name");
        mMainFragmentPresent = new MainFragmentPresent(this, d_id);
        mMainFragmentPresent.selectDatabase();
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
    public void showList(List<ProjectCategory> categories, List<List<ProjectDetail>> projects) {
        mProjects = projects;
        mCategories = categories;
        mAdapter = new FragmentAdapter(mCategories, mProjects);
        mAdapter.setOnItemLongClickListener(new FragmentAdapter.onItemLongClickListener() {
            @Override
            public void onItemLongClick(View view, int position) {
                showPopWindow(view, position);
            }
        });
        mRecyclerView.setAdapter(mAdapter);
    }

    private void showPopWindow(final View view, final int position) {
        popView = LayoutInflater.from(getContext()).inflate(R.layout.list_popup,null);
        updateTextView = popView.findViewById(R.id.update_textView);
        deleteTextView = popView.findViewById(R.id.delete_textView);
        mPopupWindow = new PopupWindow(popView, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        mPopupWindow.setOutsideTouchable(true);
        if(Build.VERSION.SDK_INT >= 21) {
            mPopupWindow.setElevation(DensityUtil.dip2px(getContext(),24));
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
                Intent intent = new Intent();
                intent.putExtra("pc_name",mCategories.get(position).getName());
                startMyActivityForResult(intent, ProjectUpdateActivity.class);
            }
        });
        deleteTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mMainFragmentPresent.deleteDatabase(mCategories.get(position), mProjects.get(position));
                mPopupWindow.dismiss();
                initData();
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private void startMyActivityForResult(Intent intent, Class<?> activityClass) {
        intent.setClass(getContext(),activityClass);
        if(Build.VERSION.SDK_INT >= 21) {
            startActivityForResult(intent,1000, ActivityOptions.
                    makeSceneTransitionAnimation(getActivity()).toBundle());
        } else {
            startActivityForResult(intent,1000);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1000 && resultCode == 1001) {
            initData();
        }
    }
}
