package yyk.decoratinghouses.presenter;

import java.util.List;

import yyk.decoratinghouses.bean.ProjectCategory;
import yyk.decoratinghouses.bean.ProjectDetail;
import yyk.decoratinghouses.model.MainFragmentModel;
import yyk.decoratinghouses.view.IMainFragmentView;

/**
 * Created by wangyao on 2017/7/29.
 */

public class MainFragmentPresent {

    private MainFragmentModel mMainFragmentModel;
    private IMainFragmentView mIMainFragmentView;
    private Long d_id;

    public MainFragmentPresent(IMainFragmentView iMainFragmentView, Long d_id) {
        this.d_id = d_id;
        mMainFragmentModel = new MainFragmentModel(d_id);
        mIMainFragmentView = iMainFragmentView;
    }

    public void selectDatabase() {
        mIMainFragmentView.showLoading();
        mMainFragmentModel.selectDatabase(new MainFragmentModel.OnSelectDatabaseListener() {
            @Override
            public void selectDatabaseSuccess(List<ProjectCategory> categories, List<List<ProjectDetail>> mProjects) {
                mIMainFragmentView.showList(categories, mProjects);
                mIMainFragmentView.hideLoading();
            }

            @Override
            public void selectDatabaseFaild() {
                mIMainFragmentView.hideLoading();
            }
        });
    }

    public void insertOrUpdateDatabase(ProjectCategory projectCatogary, List<ProjectDetail> projectDetails) {
        mMainFragmentModel.insertOrUpdataDatabase(projectCatogary, projectDetails, new MainFragmentModel.OnInsertOrUpdataDatabaseListener() {
            @Override
            public void insertOrUpdataDatabaseSuccess() {
                selectDatabase();
            }

            @Override
            public void insertOrUpdatabaseFaild() {
                mIMainFragmentView.hideLoading();
            }
        });
    }

    public void deleteDatabase(ProjectCategory projectCatogary, List<ProjectDetail> projectDetails) {
        mMainFragmentModel.deleteDatabase(projectCatogary, projectDetails, new MainFragmentModel.OnDeleteDatabaseListener() {
            @Override
            public void deleteDatabaseSuccess() {
                selectDatabase();
            }

            @Override
            public void deleteDatabaseFaild() {
                mIMainFragmentView.hideLoading();
            }
        });
    }
}
