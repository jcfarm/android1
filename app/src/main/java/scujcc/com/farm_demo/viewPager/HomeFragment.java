package scujcc.com.farm_demo.viewPager;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.LoaderManager;
import android.support.v4.view.ViewPager;
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
import scujcc.com.farm_demo.adapter.HomeRecyclerViewAdapter;
import scujcc.com.farm_demo.base.BaseFragment;

/**
 * Created by hello-brothers on 2017/5/3.
 * 主页的fragment
 */

public class HomeFragment extends BaseFragment {

    public static final int TYPE_LINEAR_LAYOUT = 1;
    public static final int TYPE_GRID_LAYOUT = 2;
    public static final int TYPE_STAGGERED_GRID_LAYOUT = 3;
    @Bind(R.id.recycler_view)
    RecyclerView mRecyclerView;

    private int type = TYPE_LINEAR_LAYOUT;

    private ArrayList<BaseFragment> mBaseFragment;
    private TabLayout tabLayout;

    public static HomeFragment newInstance(int type) {

        Bundle args = new Bundle();

        HomeFragment fragment = new HomeFragment();
        args.putInt("type", type);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            type = getArguments().getInt("type", TYPE_LINEAR_LAYOUT);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_fragment,container,false);
        TextView textView = (TextView) view.findViewById(R.id.home_textView);
        textView.setText(R.string.home);
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
        if (type == TYPE_GRID_LAYOUT) {
            mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));//这里用线性宫格显示 类似于grid view
        } else if (type == TYPE_STAGGERED_GRID_LAYOUT) {
            mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, OrientationHelper.VERTICAL));//这里用线性宫格显示 类似于瀑布流
        } else {
            mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));//这里用线性显示 类似于list view
        }
        mRecyclerView.setAdapter(new HomeRecyclerViewAdapter(getActivity()));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
