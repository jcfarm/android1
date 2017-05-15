package scujcc.com.farm_demo.viewPager.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import scujcc.com.farm_demo.R;
import scujcc.com.farm_demo.base.BaseFragment;


/**
 * Created by hello-brothers on 2017/5/8.
 */

public class HomeControlFragment extends BaseFragment {
    private static final String TAG = HomeControlFragment.class.getSimpleName();//"CommonFrameFragment"

    public static HomeControlFragment newInstance() {

        Bundle args = new Bundle();

        HomeControlFragment fragment = new HomeControlFragment();

        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e(TAG,"=====HomeControlFragment======");

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_control_fragment,container,false);
        TextView textView = (TextView) view.findViewById(R.id.home_control_text);
        textView.setText("控制");
        return view;
    }

    @Override
    protected View initView() {
        return null;
    }
}
