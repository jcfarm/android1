package scujcc.com.farm_demo.viewPager.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.util.ArrayList;

import scujcc.com.farm_demo.R;
import scujcc.com.farm_demo.base.BaseFragment;

/**
 * Created by hello-brothers on 2017/5/8.
 */

public class HomeDetails extends AppCompatActivity{
    private CustomViewPager home_Details_ViewPager;
    private HomeDetailsPagerAdapter pagerAdapter;
    private TabLayout home_Details_TabLayout;
    private ArrayList<BaseFragment> mBaseFragment;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_details);
        Intent intent = getIntent();
        int home_Farm_Id = (int) intent.getSerializableExtra("home_Farm_Id");
        TextView home_farm_id = (TextView) findViewById(R.id.home_farm_id);
        home_farm_id.setText(home_Farm_Id+"号田");

        initFragment();
        pagerAdapter = new HomeDetailsPagerAdapter(getSupportFragmentManager(), this,mBaseFragment);
        home_Details_ViewPager = (CustomViewPager) findViewById(R.id.home_details_viewPager);
        home_Details_ViewPager.setAdapter(pagerAdapter);
        home_Details_ViewPager.setPagingEnabled(false);
        home_Details_TabLayout = (TabLayout) findViewById(R.id.home_details_tabLayout);
        home_Details_TabLayout.setupWithViewPager(home_Details_ViewPager);
        home_Details_TabLayout.setTabMode(TabLayout.MODE_FIXED);


    }

    private void initFragment() {
        mBaseFragment = new ArrayList<>();
        mBaseFragment.add(HomeStateFragment.newInstance());//常用框架Fragment
        mBaseFragment.add(HomeControlFragment.newInstance());//第三方Fragment

    }
}
