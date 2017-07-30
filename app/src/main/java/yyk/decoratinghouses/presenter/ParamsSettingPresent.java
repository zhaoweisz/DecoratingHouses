package yyk.decoratinghouses.presenter;

import java.util.List;

import yyk.decoratinghouses.bean.Opition;
import yyk.decoratinghouses.model.ParamsSettingModel;
import yyk.decoratinghouses.view.IParamsSettingView;

/**
 * Created by wangyao on 2017/7/29.
 */

public class ParamsSettingPresent {

    private ParamsSettingModel mParamsSettingModel;
    private IParamsSettingView mIParamsSettingView;
    private Long d_id;

    public ParamsSettingPresent(IParamsSettingView iParamsSettingView, Long d_id) {
        this.d_id = d_id;
        mParamsSettingModel = new ParamsSettingModel(d_id);
        mIParamsSettingView = iParamsSettingView;
    }

    public void selectDatabase() {
        mIParamsSettingView.showLoading();
        mParamsSettingModel.selectDatabase(new ParamsSettingModel.OnSelectDatabaseListener() {
            @Override
            public void selectDatabaseSuccess(List<Opition> opitions) {
                mIParamsSettingView.showList(opitions);
                mIParamsSettingView.hideLoading();
            }

            @Override
            public void selectDatabaseFaild() {
                mIParamsSettingView.hideLoading();
            }
        });
    }

    public void insertOrUpdateDatabase(Opition opition) {
        mParamsSettingModel.insertOrUpdataDatabase(opition, new ParamsSettingModel.OnInsertOrUpdataDatabaseListener() {
            @Override
            public void insertOrUpdataDatabaseSuccess() {
                selectDatabase();
            }

            @Override
            public void insertOrUpdatabaseFaild() {
                mIParamsSettingView.hideLoading();
            }
        });
    }

    public void deleteDatabase(Opition opition) {
        mParamsSettingModel.deleteDatabase(opition, new ParamsSettingModel.OnDeleteDatabaseListener() {
            @Override
            public void deleteDatabaseSuccess() {
                selectDatabase();
            }

            @Override
            public void deleteDatabaseFaild() {
                mIParamsSettingView.hideLoading();
            }
        });
    }
}
