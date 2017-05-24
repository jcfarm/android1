package scujcc.com.farm_demo.viewPager.home;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import scujcc.com.farm_demo.R;
import scujcc.com.farm_demo.base.BaseFragment;


/**
 * Created by hello-brothers on 2017/5/8.
 */

public class HomeDetailsPagerAdapter extends PagerAdapter {
    private static final String TAG = HomeDetailsPagerAdapter.class.getSimpleName();
    public final int COUNT = 5;
    private FragmentManager fragmentManager;
    private String[] titles =null;
    private Context context;
    ArrayList<BaseFragment> mFragments;

    public HomeDetailsPagerAdapter(FragmentManager fm, Context context, ArrayList<BaseFragment> fragments) {

//        super(fm);
        titles = context.getResources().getStringArray(R.array.home_details);
        this.fragmentManager = fm;
        this.context = context;
        this.mFragments = fragments;
        Log.e(TAG,"Fragment页面被初始化了..."+fragments);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(mFragments.get(position).getView());
        Log.e(TAG,"removeView"+mFragments.get(position));
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Fragment fg = mFragments.get(position);
        Log.e(TAG,"getposition====="+mFragments.get(position));
        if(!fg.isAdded()){ // 如果fragment还没有added
            FragmentTransaction ft = fragmentManager.beginTransaction();
            ft.add(fg, fg.getClass().getSimpleName());
            ft.commit();
            /**
             * 在用FragmentTransaction.commit()方法提交FragmentTransaction对象后
             * 会在进程的主线程中，用异步的方式来执行。
             * 如果想要立即执行这个等待中的操作，就要调用这个方法（只能在主线程中调用）。
             * 要注意的是，所有的回调和相关的行为都会在这个调用中被执行完成，因此要仔细确认这个方法的调用位置。
             */
            fragmentManager.executePendingTransactions();
        }

        if(fg.getView().getParent() == null){
            container.addView(fg.getView()); // 为viewpager增加布局
        }

        return fg.getView();
    }



    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
