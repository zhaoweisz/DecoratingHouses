package yyk.decoratinghouses.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import yyk.decoratinghouses.BaseActivity;
import yyk.decoratinghouses.QgflActivity;
import yyk.decoratinghouses.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyFragment extends Fragment {

    BaseActivity mBaseActivity;

    @BindView(R.id.text)
    TextView mText;
    Unbinder unbinder;

    public MyFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.text)
    public void onClick() {
        mText.setText("已点击");
        ((BaseActivity) getActivity()).startActivityBase(QgflActivity.class,null,null);
    }
}
