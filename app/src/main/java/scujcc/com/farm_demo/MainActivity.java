package scujcc.com.farm_demo;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

import scujcc.com.farm_demo.drawer.FragmentDrawer;
import scujcc.com.farm_demo.drawer.fragment.MessageFragment;
import scujcc.com.farm_demo.drawer.fragment.SettingFragment;
import scujcc.com.farm_demo.base.BaseFragment;
import scujcc.com.farm_demo.viewPager.MyFragmentPagerAdapter;
import scujcc.com.farm_demo.viewPager.RootHomeFragment;

/**
 * 主界面
 * 包含viewpager与drawer
 */
public class MainActivity extends AppCompatActivity implements FragmentDrawer.FragmentDrawerListener {

    private MyFragmentPagerAdapter pagerAdapter;
    private ArrayList<BaseFragment> mBaseFragment;
    private ViewPager viewPager;

    private TabLayout tabLayout;

    private Toolbar mToolbar;
    private FragmentDrawer drawerFragment;
    private static String TAG = MainActivity.class.getSimpleName();
    private Fragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab_view);

        initViewDraw();
        /**
         * 默认显示首页
         */
        displayView(0);

    }

    /**
     *侧边栏
     */
    private void initViewDraw() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        drawerFragment = (FragmentDrawer)
                getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
        drawerFragment.setUp(R.id.fragment_navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout), mToolbar);
        drawerFragment.setDrawerListener(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    /**
     * 右上方菜单
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_normal_question) {
            Toast.makeText(getApplicationContext(), "setting action is selected!", Toast.LENGTH_SHORT).show();
            return true;
        }
        if (id == R.id.action_about) {
            Toast.makeText(getApplicationContext(), "about action is selected!", Toast.LENGTH_SHORT).show();
            return true;
        }


        if (id == R.id.action_search) {
            Toast.makeText(getApplicationContext(), "Search action is selected!", Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDrawerItemSelected(View view, int position) {
        Log.e(TAG,"onDrawerItemSelected"+position);
        displayView(position);
    }

    private void displayView(int position) {

        String title = getString(R.string.app_name);
        switch (position) {
            case 0:
                fragment = new RootHomeFragment();
                title = getString(R.string.title_home);
                break;
            case 1:
                fragment = new MessageFragment();
                title = getString(R.string.title_message);
                break;
            case 2:
                fragment = new SettingFragment();
                title = getString(R.string.title_setting);
                break;
            default:
                break;
        }
        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container_body, fragment);
            fragmentTransaction.commit();

            // set the toolbar title
            getSupportActionBar().setTitle(title);
        }

    }
}