package yyk.decoratinghouses.view;

import java.util.List;

import yyk.decoratinghouses.bean.ProjectCategory;
import yyk.decoratinghouses.bean.ProjectDetail;

/**
 * Created by wangyao on 2017/7/29.
 */

public interface IMainFragmentView {

    void showLoading();
    void hideLoading();
    void showList(List<ProjectCategory> categories, List<List<ProjectDetail>> mProjects);
}
