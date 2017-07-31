package yyk.decoratinghouses;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import yyk.decoratinghouses.presenter.MainPresenter;
import yyk.decoratinghouses.util.BottomNavigationViewHelper;
import yyk.decoratinghouses.view.IMainView;

public class MainActivity extends BaseActivity implements IMainView {

    @BindView(R.id.navigation)
    BottomNavigationView mNavigation;
    @BindView(R.id.fragment_container)
    FrameLayout mFragmentContainer;

    private MainPresenter mMainPresenter;
    private int lastShowFragment = 0;
    private Fragment[] mFragments;

    @Override
    void initView() {
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mMainPresenter = new MainPresenter(this);
        mMainPresenter.getFragments();

        BottomNavigationViewHelper.disableShiftMode(mNavigation);
    }

    @Override
    void initData() {

    }

    @Override
    public void getFragments(final Fragment[] fragments) {
        mFragments = fragments;
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragment_container, fragments[0])
                .show(fragments[0])
                .commit();
        mNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_qgfl:
                        showFragment(0);
                        return true;
                    case R.id.navigation_zc:
                        showFragment(1);
                        return true;
                    case R.id.navigation_jj:
                        showFragment(2);
                        return true;
                    case R.id.navigation_dq:
                        showFragment(3);
                        return true;
                    case R.id.navigation_my:
                        showFragment(4);
                        return true;
                }
                return false;
            }
        });
    }

    private void showFragment(int index) {
        if(lastShowFragment != index) {
            switchFragment(lastShowFragment, index);
            lastShowFragment = index;
        } else {
            Toast.makeText(this, "刷新fragment" + index, Toast.LENGTH_SHORT).show();
        }
    }

    private void switchFragment(int lastIndex, int index) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.hide(mFragments[lastIndex]);
        if (!mFragments[index].isAdded()) {
            transaction.add(R.id.fragment_container, mFragments[index]);
        }
        transaction.show(mFragments[index]).commitAllowingStateLoss();
    }
}
