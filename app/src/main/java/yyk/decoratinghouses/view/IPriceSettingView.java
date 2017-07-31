package yyk.decoratinghouses.view;

import java.util.List;

import yyk.decoratinghouses.bean.Opition;
import yyk.decoratinghouses.bean.Price;

/**
 * Created by wangyao on 2017/7/29.
 */

public interface IPriceSettingView {

    void showLoading();
    void hideLoading();
    void showList(List<Price> prices);
}
