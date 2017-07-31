package yyk.decoratinghouses.model;

import java.util.List;

import yyk.decoratinghouses.BaseApplication;
import yyk.decoratinghouses.bean.Opition;
import yyk.decoratinghouses.bean.OpitionDao;
import yyk.decoratinghouses.bean.Price;
import yyk.decoratinghouses.bean.PriceDao;

/**
 * Created by wangyao on 2017/7/29.
 */

public class PriceSettingModel {

    private PriceDao mPriceDao;
    private List<Price> mPrices;
    private Long o_id;

    public PriceSettingModel(Long o_id) {
        mPriceDao = BaseApplication.getDaoInstant().getPriceDao();
        this.o_id = o_id;
    }

    public void selectDatabase(OnSelectDatabaseListener selectDatabaseListener) {

        mPrices = mPriceDao.queryBuilder().where(PriceDao.Properties.O_id.eq(o_id)).list();
        selectDatabaseListener.selectDatabaseSuccess(mPrices);
    }

    public void insertOrUpdataDatabase(Price price, OnInsertOrUpdataDatabaseListener insertOrUpdataDatabaseListener) {
        mPriceDao.insertOrReplace(price);
    }

    public void deleteDatabase(Price price, OnDeleteDatabaseListener deleteDatabaseListener) {
        mPriceDao.delete(price);
    }

    public interface OnSelectDatabaseListener {
        void selectDatabaseSuccess(List<Price> prices);
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
