package yyk.decoratinghouses.model;

import android.support.v4.app.Fragment;
import yyk.decoratinghouses.fragment.DqFragment;
import yyk.decoratinghouses.fragment.JjFragment;
import yyk.decoratinghouses.fragment.MyFragment;
import yyk.decoratinghouses.fragment.QgflFragment;
import yyk.decoratinghouses.fragment.ZcFragment;

/**
 * Created by YYK on 2017/7/25.
 */

public class MainModel {

    private Fragment[] fragments;

    public Fragment[] getFragments() {

        QgflFragment qgflFragment = new QgflFragment();
        ZcFragment zcFragment = new ZcFragment();
        JjFragment jjFragment = new JjFragment();
        DqFragment dqFragment = new DqFragment();
        MyFragment myFragment = new MyFragment();
        fragments = new Fragment[]{qgflFragment, zcFragment, jjFragment, dqFragment, myFragment};
        return fragments;
    }
}
