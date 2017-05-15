package scujcc.com.farm_demo.viewPager;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import scujcc.com.farm_demo.R;
import scujcc.com.farm_demo.adapter.HomeRecyclerViewAdapter;
import scujcc.com.farm_demo.adapter.MonitorRecyclerViewAdapter;
import scujcc.com.farm_demo.base.BaseFragment;

/**
 * Created by hello-brothers on 2017/5/3.
 * 监控的fragment
 */

public class MonitoringFragment extends BaseFragment {

    private static final String TAG = MonitoringFragment.class.getSimpleName();//"CommonFrameFragment"
    public static final int TYPE_LINEAR_LAYOUT = 1;
    public static final int TYPE_GRID_LAYOUT = 2;
    public static final int TYPE_STAGGERED_GRID_LAYOUT = 3;
    @Bind(R.id.recycler_view)
    RecyclerView mRecyclerView;



    private ArrayList<BaseFragment> mBaseFragment;
    private TabLayout tabLayout;

    public static MonitoringFragment newInstance() {

        Bundle args = new Bundle();

        MonitoringFragment fragment = new MonitoringFragment();

        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_fragment,container,false);
        TextView textView = (TextView) view.findViewById(R.id.home_textView);
        textView.setText(R.string.monitor);
        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    protected View initView() {
        return null;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

            mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));//这里用线性显示 类似于list view

        mRecyclerView.setAdapter(new MonitorRecyclerViewAdapter(getActivity()));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
