package scujcc.com.farm_demo.viewPager.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.ButterKnife;
import scujcc.com.farm_demo.R;
import scujcc.com.farm_demo.base.BaseFragment;


/**
 * Created by hello-brothers on 2017/5/8.
 */

public class HomeControlFragment extends BaseFragment {
    private static final String TAG = HomeControlFragment.class.getSimpleName();//"CommonFrameFragment"
    private static int Farm_Id;
    ArrayList<HashMap<String, Object>> runRank;

    private String[] img =null;
    private String[] content =null;
    private String[] sign =null;

    public static HomeControlFragment newInstance(int home_Farm_Id) {

        Bundle args = new Bundle();
        Farm_Id = home_Farm_Id;
        HomeControlFragment fragment = new HomeControlFragment();

        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e(TAG,"=====HomeControlFragment======");
        img = getResources().getStringArray(R.array.home_control_img);
        content = getResources().getStringArray(R.array.home_control_content);
        sign = getResources().getStringArray(R.array.home_control_sign);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_control_fragment,container,false);
        ButterKnife.bind(this, view);
        // 实例化控件
        final ListView rv = (ListView) view.findViewById(R.id.home_control_ls);
        runRank = new ArrayList<HashMap<String, Object>>();




        for (int i = 0; i < 6; i++) {
            HashMap<String, Object> map = new HashMap<String, Object>();

            map.put("img", img[i]);
            map.put("content", content[i]);
            map.put("sign", sign[i]);
            runRank.add(map);
        }
        // 实例化数据适配器并绑定在控件上
        final HomeControlListAdapter adapter = new HomeControlListAdapter(this,runRank,Farm_Id);
        rv.setAdapter(adapter);
        return view;
    }

    @Override
    protected View initView() {
        return null;
    }
}
