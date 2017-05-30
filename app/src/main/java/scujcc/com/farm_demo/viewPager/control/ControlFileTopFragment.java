package scujcc.com.farm_demo.viewPager.control;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import scujcc.com.farm_demo.R;

/**
 * Created by hello-brothers on 2017/5/22.
 */

public class ControlFileTopFragment extends Fragment{
    private String title;
    private TextView tv_title;

    public static ControlFileTopFragment newInstance(String title) {

        Bundle args = new Bundle();
        args.putString("title", title);
        ControlFileTopFragment fragment = new ControlFileTopFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            title = getArguments().getString("title");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.control_pump_fragment, container, false);


        return v;
    }
}
