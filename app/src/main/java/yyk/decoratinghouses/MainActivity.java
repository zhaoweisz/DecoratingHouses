package yyk.decoratinghouses;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import yyk.decoratinghouses.components.TitleBar;
import yyk.decoratinghouses.presenter.MainPrensenter;
import yyk.decoratinghouses.view.IMainView;

public class MainActivity extends BaseActivity implements IMainView {

    @BindView(R.id.title_bar)
    TitleBar mTitleBar;
    @BindView(R.id.navigation)
    BottomNavigationView mNavigation;
    @BindView(R.id.fragment)
    FrameLayout mFragment;

    private MainPrensenter mMainPrensenter;
    private FragmentManager mFragmentManager;
    private FragmentTransaction mTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mTitleBar.setLeftIconVisible(false);
        mTitleBar.setRightIconVisible(true);

        Toolbar toolbar = (Toolbar) findViewById(R.id.tool_bar);
        toolbar.inflateMenu(R.menu.tool_bar);
        toolbar.setOverflowIcon(getResources().getDrawable(R.mipmap.ic_more_vert_white_24dp));

        mMainPrensenter = new MainPrensenter(this);
        mMainPrensenter.getFragments();

        mFragmentManager = getSupportFragmentManager();

        BottomNavigationViewHelper.disableShiftMode(mNavigation);
        mNavigation.setSelectedItemId(R.id.navigation_qgfl);
    }

    @Override
    public void getFragments(final Fragment[] fragments) {
        mNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_qgfl:
                        replaceFragment(fragments[0]);
                        return true;
                    case R.id.navigation_zc:
                        replaceFragment(fragments[1]);
                        return true;
                    case R.id.navigation_jj:
                        replaceFragment(fragments[2]);
                        return true;
                    case R.id.navigation_dq:
                        replaceFragment(fragments[3]);
                        return true;
                    case R.id.navigation_my:
                        replaceFragment(fragments[4]);
                        return true;
                }
                return false;
            }
        });
    }

    private void replaceFragment(Fragment fragment) {
        mTransaction = mFragmentManager.beginTransaction();
        if (!fragment.isAdded()) {
            mTransaction.replace(R.id.fragment,fragment);
            mTransaction.commit();
        }
    }
}
