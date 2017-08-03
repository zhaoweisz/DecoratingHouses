package yyk.decoratinghouses.view;

import java.util.List;
import java.util.Map;

import yyk.decoratinghouses.bean.Price;
import yyk.decoratinghouses.bean.ProjectDetail;

/**
 * Created by wangyao on 2017/7/29.
 */

public interface IMainFragmentView {

    void showLoading();
    void hideLoading();
    void showList(List<List<ProjectDetail>> mProjects);
}
