package scujcc.com.farm_demo.viewPager.control;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import scujcc.com.farm_demo.R;

/**
 * Created by hello-brothers on 2017/5/21.
 */

public class BaseControlActivity extends AppCompatActivity {
    private List<Fragment> mBaseFragment;
    private static int content_id;
    private static String[] contents =null;
    private TextView tv;
    private Fragment fragment;
    private String t;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base_control_activity);


        Intent intent = getIntent();
        content_id = (int) intent.getSerializableExtra("Position");
        contents = getResources().getStringArray(R.array.home_control_content);


        t = contents[content_id];
        FragmentManager fm = getSupportFragmentManager();
        fragment = fm.findFragmentById(R.id.control_fragment_container);
        initFragment();
        fragment = mBaseFragment.get(content_id);
        Log.e("TGA","===fragment==="+fragment);
        fm.beginTransaction().add(R.id.control_fragment_container, fragment)
                    .commit();




        }


    private void initFragment() {
        mBaseFragment = new ArrayList<>();
        mBaseFragment.add(new ControlPumpFragment().newInstance(contents[content_id]));//电磁阀Fragment
        mBaseFragment.add(new ControlDraughtFragment().newInstance(contents[content_id]));//环流风机Fragment
        mBaseFragment.add(new ControlLightFragment().newInstance(contents[content_id]));//照明灯Fragment
        mBaseFragment.add(ControlWaningFargment.newInstance(contents[content_id]));//遮阳网Fragment
        mBaseFragment.add(new ControlFilmSideFragment().newInstance(contents[content_id]));//侧卷膜Fragment
        mBaseFragment.add(new ControlFileTopFragment().newInstance(contents[content_id]));//顶卷膜Fragment
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

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

}
