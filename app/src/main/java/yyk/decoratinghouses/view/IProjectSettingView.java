package yyk.decoratinghouses.view;

import android.support.v4.app.Fragment;

import java.util.List;

import yyk.decoratinghouses.bean.Price;
import yyk.decoratinghouses.bean.ProjectDetail;

/**
 * Created by YYK on 2017/7/25.
 */

public interface IProjectSettingView {

    void showLoading();
    void hideLoading();
    void showList(List<List<Price>> mPrices);
}
