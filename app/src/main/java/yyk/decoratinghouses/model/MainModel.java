package yyk.decoratinghouses.model;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import yyk.decoratinghouses.fragment.DqFragment;
import yyk.decoratinghouses.fragment.JjFragment;
import yyk.decoratinghouses.fragment.MyFragment;
import yyk.decoratinghouses.fragment.MainFragment;
import yyk.decoratinghouses.fragment.ZcFragment;

/**
 * Created by YYK on 2017/7/25.
 */

public class MainModel {

    private Fragment[] fragments;
    private Bundle mBundle1;
    private Bundle mBundle2;
    private Bundle mBundle3;
    private Bundle mBundle4;

    public Fragment[] getFragments() {
        mBundle1 = new Bundle();
        mBundle2 = new Bundle();
        mBundle3 = new Bundle();
        mBundle4 = new Bundle();

        MainFragment qgflFragment = new MainFragment();
        mBundle1.putLong("d_id",1);
        mBundle1.putString("d_name","轻工辅料");
        qgflFragment.setArguments(mBundle1);
        MainFragment zcFragment = new MainFragment();
        mBundle2.putLong("d_id",2);
        mBundle2.putString("d_name","主材");
        zcFragment.setArguments(mBundle2);
        MainFragment jjFragment = new MainFragment();
        mBundle3.putLong("d_id",3);
        mBundle3.putString("d_name","家具");
        jjFragment.setArguments(mBundle3);
        MainFragment dqFragment = new MainFragment();
        mBundle4.putLong("d_id",4);
        mBundle4.putString("d_name","电器");
        dqFragment.setArguments(mBundle4);
        MyFragment myFragment = new MyFragment();
        fragments = new Fragment[]{qgflFragment, zcFragment, jjFragment, dqFragment, myFragment};
        return fragments;
    }
}
