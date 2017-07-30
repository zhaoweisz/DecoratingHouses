package yyk.decoratinghouses.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import yyk.decoratinghouses.BaseActivity;
import yyk.decoratinghouses.ParamsSettingActivity;
import yyk.decoratinghouses.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyFragment extends Fragment {

    @BindView(R.id.qgfl_setting)
    RelativeLayout mQgflSetting;
    @BindView(R.id.zc_setting)
    RelativeLayout mZcSetting;
    @BindView(R.id.jj_setting)
    RelativeLayout mJjSetting;
    @BindView(R.id.dq_setting)
    RelativeLayout mDqSetting;

    Unbinder unbinder;
    private Context context;
    private BaseActivity baseActivity;

    private Bundle mBundle;

    public MyFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my, container, false);
        unbinder = ButterKnife.bind(this, view);
        context = getActivity();
        baseActivity = (BaseActivity) context;
        mBundle = new Bundle();

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.qgfl_setting, R.id.zc_setting, R.id.jj_setting, R.id.dq_setting})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.qgfl_setting:
                mBundle.putLong("d_id",1);
                mBundle.putString("d_name", "轻工辅料");
                baseActivity.startActivityBase(ParamsSettingActivity.class,mBundle,null,null);
                break;
            case R.id.zc_setting:
                mBundle.putLong("d_id",2);
                mBundle.putString("d_name", "主材");
                baseActivity.startActivityBase(ParamsSettingActivity.class,mBundle,null,null);
                break;
            case R.id.jj_setting:
                mBundle.putLong("d_id",3);
                mBundle.putString("d_name", "家居");
                baseActivity.startActivityBase(ParamsSettingActivity.class,mBundle,null,null);
                break;
            case R.id.dq_setting:
                mBundle.putLong("d_id",4);
                mBundle.putString("d_name", "电器");
                baseActivity.startActivityBase(ParamsSettingActivity.class,mBundle,null,null);
                break;
        }
    }
}
