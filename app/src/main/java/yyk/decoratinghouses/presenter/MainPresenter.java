package yyk.decoratinghouses.presenter;

import yyk.decoratinghouses.model.MainModel;
import yyk.decoratinghouses.view.IMainView;

/**
 * Created by YYK on 2017/7/25.
 */

public class MainPresenter {

    private IMainView mainView;
    private MainModel mMainModel;

    public MainPresenter(IMainView mainView) {
        this.mainView = mainView;
        mMainModel = new MainModel();
    }

    public void getFragments() {
        mainView.getFragments(mMainModel.getFragments());
    }
}
