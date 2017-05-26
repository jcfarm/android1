package scujcc.com.farm_demo.viewPager.control;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import scujcc.com.farm_demo.R;

/**
 * Created by hello-brothers on 2017/5/22.
 * 电磁阀Fragment
 */

public class ControlPumpFragment extends Fragment {
    private String title;
    private TextView tv_title;

    public ControlPumpFragment newInstance(String title) {
        
        Bundle args = new Bundle();
        args.putString("title", title);
        ControlPumpFragment fragment = new ControlPumpFragment();
        fragment.setArguments(args);

        return fragment;
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        if (getArguments() != null) {
            title = getArguments().getString("title");
        }
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.control_pump_fragment, container, false);

        setHasOptionsMenu(true);
        tv_title = (TextView) v.findViewById(R.id.home_farm_id);
        tv_title.setText(title);

        return v;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_control,menu);
        Log.e("TGA","==onCreateOptionsMenu====");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}
