package yyk.decoratinghouses.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import yyk.decoratinghouses.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class QgflFragment extends Fragment {


    @BindView(R.id.text)
    TextView mText;
    Unbinder unbinder;
    @BindView(R.id.card_view)
    CardView mCardView;

    public QgflFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_qgfl, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.card_view)
    public void onClick() {
    }
}
