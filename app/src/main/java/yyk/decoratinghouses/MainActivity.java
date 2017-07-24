package yyk.decoratinghouses;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.view.MenuItem;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import yyk.decoratinghouses.components.TitleBar;

public class MainActivity extends BaseActivity {

    @BindView(R.id.title_bar)
    TitleBar mTitleBar;
    @BindView(R.id.navigation)
    BottomNavigationView mNavigation;
    @BindView(R.id.message)
    TextView mMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mTitleBar.setLeftIconVisible(false);
        setNavigationItemSelectedListener();
        BottomNavigationViewHelper.disableShiftMode(mNavigation);
    }

    private void setNavigationItemSelectedListener() {
        mNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_qgfl:
                        mMessage.setText("轻工辅料");
                        return true;
                    case R.id.navigation_zc:
                        mMessage.setText("主材");
                        return true;
                    case R.id.navigation_jj:
                        mMessage.setText("家居");
                        return true;
                    case R.id.navigation_dq:
                        mMessage.setText("电器");
                        return true;
                    case R.id.navigation_my:
                        mMessage.setText("我的");
                        return true;
                }
                return false;
            }
        });
    }

}
