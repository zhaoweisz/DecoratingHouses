package yyk.decoratinghouses;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import yyk.decoratinghouses.bean.DaoMaster;
import yyk.decoratinghouses.bean.DaoSession;

/**
 * Created by YYK on 2017/7/28.
 */

public class BaseApplication extends Application {

    private static DaoSession daoSession;

    @Override
    public void onCreate() {
        super.onCreate();
        //配置数据库
        setupDatabase();
    }

    private void setupDatabase() {
        //创建数据库test.db
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this,"test.db");
        //获取可写数据库
        SQLiteDatabase db = helper.getWritableDatabase();
        //获取数据库对象
        DaoMaster daoMaster = new DaoMaster(db);
        //获取dao对象管理者
        daoSession = daoMaster.newSession();
    }

    public static DaoSession getDaoInstant() {
        return daoSession;
    }
}
