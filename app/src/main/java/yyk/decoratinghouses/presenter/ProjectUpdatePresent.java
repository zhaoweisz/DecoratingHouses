package yyk.decoratinghouses.presenter;

import java.util.List;

import yyk.decoratinghouses.bean.Opition;
import yyk.decoratinghouses.bean.ProjectCategory;
import yyk.decoratinghouses.bean.ProjectDetail;
import yyk.decoratinghouses.model.ProjectSettingModel;
import yyk.decoratinghouses.model.ProjectUpdateModel;
import yyk.decoratinghouses.view.IProjectSettingView;
import yyk.decoratinghouses.view.IProjectUpdateView;

/**
 * Created by wangyao on 2017/7/29.
 */

public class ProjectUpdatePresent {

    private ProjectUpdateModel mProjectUpdateModel;
    private IProjectUpdateView mIProjectUpdateView;
    private String pc_name;

    public ProjectUpdatePresent(IProjectUpdateView iProjectUpdateView, String pc_name) {
        this.pc_name = pc_name;
        mProjectUpdateModel = new ProjectUpdateModel(pc_name);
        mIProjectUpdateView = iProjectUpdateView;
    }

    public void selectDatabase() {
        mIProjectUpdateView.showLoading();
        mProjectUpdateModel.selectDatabase(new ProjectUpdateModel.OnSelectDatabaseListener() {
            @Override
            public void selectDatabaseSuccess(List<ProjectDetail> projectDetails) {
                mIProjectUpdateView.showList(projectDetails);
                mIProjectUpdateView.hideLoading();
            }

            @Override
            public void selectDatabaseFaild() {
                mIProjectUpdateView.hideLoading();
            }
        });
    }

    public void insertOrUpdateDatabase(List<ProjectDetail> projectDetails) {
        mProjectUpdateModel.insertOrUpdataDatabase(projectDetails, new ProjectUpdateModel.OnInsertOrUpdataDatabaseListener() {
            @Override
            public void insertOrUpdataDatabaseSuccess() {

            }

            @Override
            public void insertOrUpdatabaseFaild() {
                mIProjectUpdateView.hideLoading();
            }
        });
    }
}
