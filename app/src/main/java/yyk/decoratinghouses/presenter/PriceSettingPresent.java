package yyk.decoratinghouses.presenter;

import java.util.List;

import yyk.decoratinghouses.bean.Opition;
import yyk.decoratinghouses.bean.Price;
import yyk.decoratinghouses.model.ParamsSettingModel;
import yyk.decoratinghouses.model.PriceSettingModel;
import yyk.decoratinghouses.view.IParamsSettingView;
import yyk.decoratinghouses.view.IPriceSettingView;

/**
 * Created by wangyao on 2017/7/29.
 */

public class PriceSettingPresent {

    private PriceSettingModel mPriceSettingModel;
    private IPriceSettingView mIPriceSettingView;
    private Long o_id;

    public PriceSettingPresent(IPriceSettingView iPriceSettingView, Long o_id) {
        this.o_id = o_id;
        mPriceSettingModel = new PriceSettingModel(o_id);
        mIPriceSettingView = iPriceSettingView;
    }

    public void selectDatabase() {
        mIPriceSettingView.showLoading();
        mPriceSettingModel.selectDatabase(new PriceSettingModel.OnSelectDatabaseListener() {
            @Override
            public void selectDatabaseSuccess(List<Price> prices) {
                mIPriceSettingView.showList(prices);
                mIPriceSettingView.hideLoading();
            }

            @Override
            public void selectDatabaseFaild() {
                mIPriceSettingView.hideLoading();
            }
        });
    }

    public void insertOrUpdateDatabase(Price price) {
        mPriceSettingModel.insertOrUpdataDatabase(price, new PriceSettingModel.OnInsertOrUpdataDatabaseListener() {
            @Override
            public void insertOrUpdataDatabaseSuccess() {
                selectDatabase();
            }

            @Override
            public void insertOrUpdatabaseFaild() {
                mIPriceSettingView.hideLoading();
            }
        });
    }

    public void deleteDatabase(Price price) {
        mPriceSettingModel.deleteDatabase(price, new PriceSettingModel.OnDeleteDatabaseListener() {
            @Override
            public void deleteDatabaseSuccess() {
                selectDatabase();
            }

            @Override
            public void deleteDatabaseFaild() {
                mIPriceSettingView.hideLoading();
            }
        });
    }
}
