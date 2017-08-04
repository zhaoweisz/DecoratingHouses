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

public class ProjectUpdateModel {

    private ProjectDetailDao mProjectDetailDao;
    private List<ProjectDetail> mDetails;
    private String pc_name;

    public ProjectUpdateModel(String pc_name) {
        mProjectDetailDao = BaseApplication.getDaoInstant().getProjectDetailDao();
        this.pc_name = pc_name;
    }

    public void selectDatabase(OnSelectDatabaseListener selectDatabaseListener) {
        mDetails = mProjectDetailDao.queryBuilder().where(ProjectDetailDao.Properties.Pc_name.eq(pc_name)).list();
        selectDatabaseListener.selectDatabaseSuccess(mDetails);
    }

    public void insertOrUpdataDatabase(List<ProjectDetail> projectDetails, OnInsertOrUpdataDatabaseListener insertOrUpdataDatabaseListener) {
        mProjectDetailDao.insertOrReplaceInTx(projectDetails);
    }

    public void deleteDatabase(ProjectDetail projectDetail, OnDeleteDatabaseListener deleteDatabaseListener) {
        mProjectDetailDao.delete(projectDetail);
    }

    public interface OnSelectDatabaseListener {
        void selectDatabaseSuccess(List<ProjectDetail> projectDetails);
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
