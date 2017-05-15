package scujcc.com.farm_demo.viewPager;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import scujcc.com.farm_demo.R;
import scujcc.com.farm_demo.base.BaseFragment;

/**
 * Created by hello-brothers on 2017/5/4.
 * viewpager中主页的fragment 其中包含有主页 监控 控制 日志四个fragment
 */

public class RootHomeFragment extends BaseFragment {
    private static final String TAG = RootHomeFragment.class.getSimpleName();//"CommonFrameFragment"
    private ArrayList<BaseFragment> mBaseFragment;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_home_root,container,false);


        //Fragment+ViewPager+FragmentViewPager组合的使用
        ViewPager viewPager = (ViewPager) view.findViewById(R.id.viewPager);
        initFragment();
        MyFragmentPagerAdapter adapter = new MyFragmentPagerAdapter(getActivity().getSupportFragmentManager(), getActivity(), mBaseFragment);
        viewPager.setAdapter(adapter);

        //TabLayout
        TabLayout tabLayout = (TabLayout) view.findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        return view;
    }
    @Override
    protected View initView() {
        return null;
    }
    private void initFragment() {
        mBaseFragment = new ArrayList<>();
        mBaseFragment.add(HomeFragment.newInstance(1));//常用框架Fragment
        mBaseFragment.add(MonitoringFragment.newInstance());//第三方Fragment
        mBaseFragment.add(new ControlFragment());//自定义控件Fragment
        mBaseFragment.add(new LogFragment());//其他Fragment
    }
}
