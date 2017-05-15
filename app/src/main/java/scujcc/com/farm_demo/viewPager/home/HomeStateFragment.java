package scujcc.com.farm_demo.viewPager.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.Bind;
import butterknife.ButterKnife;
import scujcc.com.farm_demo.R;
import scujcc.com.farm_demo.base.BaseFragment;

/**
 * Created by hello-brothers on 2017/5/8.
 */

public class HomeStateFragment extends BaseFragment {
    private static final String TAG = HomeControlFragment.class.getSimpleName();//"CommonFrameFragment"

    ArrayList<HashMap<String, Object>> runRank;
    public static HomeStateFragment newInstance() {

        Bundle args = new Bundle();

        HomeStateFragment fragment = new HomeStateFragment();

        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e(TAG,"=====HomeStateFragments  ======");

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_state_fragment,container,false);

        ButterKnife.bind(this, view);
        // 实例化控件
        final ListView rv = (ListView) view.findViewById(R.id.home_states_ls);


        runRank = new ArrayList<HashMap<String, Object>>();


        for (int i = 0; i < 6; i++) {
            HashMap<String, Object> map = new HashMap<String, Object>();

            map.put("ItemTitle", "Level " + i);
            map.put("ItemText", "Finished in 1 Min 54 Secs, 70 Moves! ");
            runRank.add(map);
        }
        // 实例化数据适配器并绑定在控件上
        final HomeStateListAdapter adapter = new HomeStateListAdapter(this,runRank);
        rv.setAdapter(adapter);

        return view;
    }



    @Override
    protected View initView() {
        return null;
    }
}
