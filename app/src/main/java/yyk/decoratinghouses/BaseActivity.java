package yyk.decoratinghouses;

import android.app.Activity;
import android.app.ActivityOptions;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.transition.Fade;
import android.util.Log;
import android.view.View;
import android.view.Window;

/**
 * Created by admin on 2016/5/19.
 */
public abstract class BaseActivity extends AppCompatActivity {

    protected Context mBaseContext = null;
    protected Context mApplicationContext = null;
    protected Intent mBaseIntent;
    protected SharedPreferences preferences;
    protected NotificationManager notificationManager;
    protected boolean isOnCreate = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window window = getWindow();
        window.requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        if(Build.VERSION.SDK_INT >= 21) {
            window.setEnterTransition(new Fade());
            window.setExitTransition(new Fade());
        }
        Log.i("BaseActivity", getClass().getSimpleName());
        mApplicationContext = getApplicationContext();
        mBaseContext = this;
        mBaseIntent = new Intent();
        isOnCreate = false;
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    public Context getContext() {
        return mBaseContext;
    }


//    public abstract void initView();
//
//    public abstract void initParameter();
//
//    public abstract void initEvent();

    /**
     * 判断是否有网络连接,没有返回false
     */
    public boolean hasInternetConnected() {
        ConnectivityManager manager = (ConnectivityManager) mBaseContext
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (manager != null) {
            NetworkInfo network = manager.getActiveNetworkInfo();
            if (network != null && network.isConnectedOrConnecting()) {
                return true;
            }
        }
        return false;
    }

    public void startActivityBase(Activity activity, View view, String viewName) {
        mBaseIntent.setClass(mBaseContext, activity.getClass());
        startNextActivity(mBaseIntent, view, viewName);
    }

    public void startActivityBase(Class<?> activityClass, View view, String viewName) {
        mBaseIntent.setClass(mBaseContext, activityClass);
        startNextActivity(mBaseIntent, view, viewName);
    }
    public void startActivityBase(Class<?> activityClass,Bundle bundle, View view, String viewName) {
        mBaseIntent.putExtras(bundle);
        mBaseIntent.setClass(mBaseContext, activityClass);
        startNextActivity(mBaseIntent, view, viewName);
    }

    public void startActivityBase(Class<?> activityClass, int flag, View view, String viewName) {
        mBaseIntent.putExtra("flag", flag);
        mBaseIntent.setClass(mBaseContext, activityClass);
        startNextActivity(mBaseIntent, view, viewName);
    }

    public void startActivityBase(Class<?> activityClass, String source, View view, String viewName) {
        mBaseIntent.putExtra("flag", source);
        mBaseIntent.setClass(mBaseContext, activityClass);
        startNextActivity(mBaseIntent, view, viewName);
    }

    public void startActivityBaseBySharedView(Class<?> activityClass, View view, String viewName) {
        startNextActivity(mBaseIntent, view, viewName);
    }

    private void startNextActivity(Intent intent, View view, String viewName) {
        if(Build.VERSION.SDK_INT >= 21) {
            if(view != null && viewName != null) {
                startActivity(intent, ActivityOptions.
                        makeSceneTransitionAnimation(this, view, viewName).toBundle());
            } else {
                startActivity(intent, ActivityOptions.
                        makeSceneTransitionAnimation(this).toBundle());
            }
        } else {
            startActivity(intent);
        }
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
