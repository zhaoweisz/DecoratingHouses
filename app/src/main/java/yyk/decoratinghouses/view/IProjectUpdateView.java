package yyk.decoratinghouses.view;

import java.util.List;

import yyk.decoratinghouses.bean.Opition;
import yyk.decoratinghouses.bean.ProjectDetail;

/**
 * Created by YYK on 2017/7/25.
 */

public interface IProjectUpdateView {

    void showLoading();
    void hideLoading();
    void showList(List<ProjectDetail> projectDetails);
}
