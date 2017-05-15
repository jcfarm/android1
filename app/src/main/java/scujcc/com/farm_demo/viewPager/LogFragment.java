package scujcc.com.farm_demo.viewPager;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import scujcc.com.farm_demo.R;
import scujcc.com.farm_demo.base.BaseFragment;

/**
 * Created by hello-brothers on 2017/5/3.
 * 日志的fragment
 */

 public class LogFragment extends BaseFragment {
    private static final String TAG = LogFragment.class.getSimpleName();//"CommonFrameFragment"
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.log_fragment,container,false);
        TextView textView = (TextView) view.findViewById(R.id.log_textView);
        textView.setText(R.string.log);
        return view;
    }

    @Override
    protected View initView() {
        return null;
    }
}
