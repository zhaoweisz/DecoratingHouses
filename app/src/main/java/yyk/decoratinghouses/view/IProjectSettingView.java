package yyk.decoratinghouses.view;

import java.util.List;

import yyk.decoratinghouses.bean.Opition;

/**
 * Created by YYK on 2017/7/25.
 */

public interface IProjectSettingView {

    void showLoading();
    void hideLoading();
    void showList(List<Opition> opitions);
}
