package yyk.decoratinghouses.presenter;

import java.util.List;

import yyk.decoratinghouses.bean.Opition;
import yyk.decoratinghouses.bean.ProjectCategory;
import yyk.decoratinghouses.bean.ProjectDetail;
import yyk.decoratinghouses.model.ProjectSettingModel;
import yyk.decoratinghouses.view.IProjectSettingView;

/**
 * Created by wangyao on 2017/7/29.
 */

public class ProjectSettingPresent {

    private ProjectSettingModel mProjectSettingModel;
    private IProjectSettingView mIProjectSettingView;
    private Long d_id;

    public ProjectSettingPresent(IProjectSettingView iProjectSettingView, Long d_id) {
        this.d_id = d_id;
        mProjectSettingModel = new ProjectSettingModel(d_id);
        mIProjectSettingView = iProjectSettingView;
    }

    public void selectDatabase() {
        mIProjectSettingView.showLoading();
        mProjectSettingModel.selectDatabase(new ProjectSettingModel.OnSelectDatabaseListener() {
            @Override
            public void selectDatabaseSuccess(List<Opition> opitions) {
                mIProjectSettingView.showList(opitions);
                mIProjectSettingView.hideLoading();
            }

            @Override
            public void selectDatabaseFaild() {
                mIProjectSettingView.hideLoading();
            }
        });
    }

    public void insertOrUpdateDatabase(ProjectCategory projectCatogary, List<ProjectDetail> projectDetails) {
        mProjectSettingModel.insertOrUpdataDatabase(projectCatogary, projectDetails, new ProjectSettingModel.OnInsertOrUpdataDatabaseListener() {
            @Override
            public void insertOrUpdataDatabaseSuccess() {
                selectDatabase();
            }

            @Override
            public void insertOrUpdatabaseFaild() {
                mIProjectSettingView.hideLoading();
            }
        });
    }

    public void deleteDatabase(ProjectCategory projectCatogary, List<ProjectDetail> projectDetails) {
        mProjectSettingModel.deleteDatabase(projectCatogary, projectDetails, new ProjectSettingModel.OnDeleteDatabaseListener() {
            @Override
            public void deleteDatabaseSuccess() {
                selectDatabase();
            }

            @Override
            public void deleteDatabaseFaild() {
                mIProjectSettingView.hideLoading();
            }
        });
    }
}
