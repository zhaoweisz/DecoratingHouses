package yyk.decoratinghouses.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import yyk.decoratinghouses.BaseApplication;
import yyk.decoratinghouses.bean.Price;
import yyk.decoratinghouses.bean.ProjectCategory;
import yyk.decoratinghouses.bean.ProjectCategoryDao;
import yyk.decoratinghouses.bean.ProjectDetail;
import yyk.decoratinghouses.bean.ProjectDetailDao;

/**
 * Created by wangyao on 2017/7/29.
 */

public class MainFragmentModel {

    private ProjectCategoryDao mProjectCategoryDao;
    private ProjectDetailDao mProjectDetailDao;
    private List<List<ProjectDetail>> mProjects;
    private List<ProjectCategory> mCategories;
    private List<ProjectDetail> mDetails;
    private String categoryName;
    private Long d_id;

    public MainFragmentModel(Long d_id) {
        mProjectCategoryDao = BaseApplication.getDaoInstant().getProjectCategoryDao();
        mProjectDetailDao = BaseApplication.getDaoInstant().getProjectDetailDao();
        this.d_id = d_id;
        mProjects = new ArrayList<>();
    }

    public void selectDatabase(OnSelectDatabaseListener selectDatabaseListener) {

        mCategories = mProjectCategoryDao.queryBuilder().where(ProjectCategoryDao.Properties.D_id.eq(d_id)).list();
        for (int i = 0; i < mCategories.size(); i++) {
            mDetails = mProjectDetailDao.queryBuilder().where(ProjectDetailDao.Properties.Pc_name.eq(mCategories.get(i).getName())).list();
            mProjects.add(mDetails);
        }
        selectDatabaseListener.selectDatabaseSuccess(mProjects);
    }

    public void insertOrUpdataDatabase(ProjectCategory projectCatogary, List<ProjectDetail> projectDetails, OnInsertOrUpdataDatabaseListener insertOrUpdataDatabaseListener) {
        mProjectCategoryDao.insertOrReplace(projectCatogary);
        mProjectDetailDao.insertOrReplaceInTx(projectDetails);
    }

    public void deleteDatabase(ProjectCategory projectCatogary, List<ProjectDetail> projectDetails, OnDeleteDatabaseListener deleteDatabaseListener) {
        mProjectCategoryDao.delete(projectCatogary);
        mProjectDetailDao.deleteInTx(projectDetails);
    }

    public interface OnSelectDatabaseListener {
        void selectDatabaseSuccess(List<List<ProjectDetail>> mProjects);
        void selectDatabaseFaild();
    }

    public interface OnInsertOrUpdataDatabaseListener {
        void insertOrUpdataDatabaseSuccess();
        void insertOrUpdatabaseFaild();
    }

    public interface OnDeleteDatabaseListener {
        void deleteDatabaseSuccess();
        void deleteDatabaseFaild();
    }
}
