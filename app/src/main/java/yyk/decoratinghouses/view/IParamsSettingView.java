package yyk.decoratinghouses.view;

import java.util.List;

import yyk.decoratinghouses.bean.Opition;

/**
 * Created by wangyao on 2017/7/29.
 */

public interface IParamsSettingView {

    void showLoading();
    void hideLoading();
    void showList(List<Opition> opitions);
}
