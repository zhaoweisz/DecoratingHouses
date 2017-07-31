package yyk.decoratinghouses.model;

import java.util.List;

import yyk.decoratinghouses.BaseApplication;
import yyk.decoratinghouses.bean.Opition;
import yyk.decoratinghouses.bean.OpitionDao;
import yyk.decoratinghouses.bean.PriceDao;

/**
 * Created by wangyao on 2017/7/29.
 */

public class ParamsSettingModel {

    private OpitionDao mOpitionDao;
    private List<Opition> mOpitions;
    private PriceDao mPriceDao;
    private Long d_id;

    public ParamsSettingModel(Long d_id) {
        mOpitionDao = BaseApplication.getDaoInstant().getOpitionDao();
        mPriceDao = BaseApplication.getDaoInstant().getPriceDao();
        this.d_id = d_id;
    }

    public void selectDatabase(OnSelectDatabaseListener selectDatabaseListener) {

        mOpitions = mOpitionDao.queryBuilder().where(OpitionDao.Properties.D_id.eq(d_id)).list();
        selectDatabaseListener.selectDatabaseSuccess(mOpitions);
    }

    public void insertOrUpdataDatabase(Opition opition, OnInsertOrUpdataDatabaseListener insertOrUpdataDatabaseListener) {
        mOpitionDao.insertOrReplace(opition);
    }

    public void deleteDatabase(Opition opition, OnDeleteDatabaseListener deleteDatabaseListener) {
        mOpitionDao.delete(opition);
        mPriceDao.deleteInTx(mPriceDao.queryBuilder().where(PriceDao.Properties.O_id.eq(opition.getId())).list());
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
