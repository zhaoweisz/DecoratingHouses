package yyk.decoratinghouses.model;

import java.util.ArrayList;
import java.util.List;

import yyk.decoratinghouses.BaseApplication;
import yyk.decoratinghouses.bean.Opition;
import yyk.decoratinghouses.bean.OpitionDao;
import yyk.decoratinghouses.bean.Price;
import yyk.decoratinghouses.bean.PriceDao;
import yyk.decoratinghouses.bean.ProjectCategory;
import yyk.decoratinghouses.bean.ProjectCategoryDao;
import yyk.decoratinghouses.bean.ProjectDetail;
import yyk.decoratinghouses.bean.ProjectDetailDao;

/**
 * Created by wangyao on 2017/7/29.
 */

public class ProjectSettingModel {

    private ProjectCategoryDao mProjectCategoryDao;
    private ProjectDetailDao mProjectDetailDao;
    private PriceDao mPriceDao;
    private OpitionDao mOpitionDao;
    private List<List<ProjectDetail>> mProjects;
    private List<ProjectCategory> mCategories;
    private List<ProjectDetail> mDetails;
    private List<Opition> mOpitions;
    private List<List<Price>> mPrices;
    private String categoryName;
    private Long d_id;

    public ProjectSettingModel(Long d_id) {
        mProjectCategoryDao = BaseApplication.getDaoInstant().getProjectCategoryDao();
        mProjectDetailDao = BaseApplication.getDaoInstant().getProjectDetailDao();
        mPriceDao = BaseApplication.getDaoInstant().getPriceDao();
        mOpitionDao = BaseApplication.getDaoInstant().getOpitionDao();
        this.d_id = d_id;
        mProjects = new ArrayList<>();
        mPrices = new ArrayList<>();
    }

    public void selectDatabase(OnSelectDatabaseListener selectDatabaseListener) {
        mOpitions = mOpitionDao.queryBuilder().where(OpitionDao.Properties.D_id.eq(d_id)).list();

        selectDatabaseListener.selectDatabaseSuccess(mOpitions);
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
        void selectDatabaseSuccess(List<Opition> opitions);
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
