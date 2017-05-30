package scujcc.com.farm_demo.viewPager.control;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import scujcc.com.farm_demo.R;

/**
 * Created by hello-brothers on 2017/5/22.
 */

public class ControlLightFragment extends Fragment{
    private String title;
    private TextView tv_title;

    public static ControlLightFragment newInstance(String title) {

        Bundle args = new Bundle();
        args.putString("title", title);
        ControlLightFragment fragment = new ControlLightFragment();
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
