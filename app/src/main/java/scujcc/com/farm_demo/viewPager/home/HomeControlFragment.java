package scujcc.com.farm_demo.viewPager.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import scujcc.com.farm_demo.R;
import scujcc.com.farm_demo.base.BaseFragment;


/**
 * Created by hello-brothers on 2017/5/8.
 */

public class HomeControlFragment extends BaseFragment {
    private static final String TAG = HomeControlFragment.class.getSimpleName();//"CommonFrameFragment"
    private Switch Switch0;
    private Switch Switch1;
    private Switch Switch2;
    private Switch Switch3;
    private Switch Switch4;
    private Switch Switch5;

    private TextView mText;
    private TextView text0;
    private TextView text1;
    private TextView text2;
    private TextView text3;
    private TextView text4;
    private TextView text5;



    public static HomeControlFragment newInstance() {

        Bundle args = new Bundle();

        HomeControlFragment fragment = new HomeControlFragment();

        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e(TAG,"=====HomeControlFragment======");

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.control_fragment,container,false);
//        TextView textView = (TextView) view.findViewById(R.id.home_control_text);
//        textView.setText("控制");


        Switch0=(Switch)view.findViewById(R.id.switch0);
        Switch1=(Switch)view.findViewById(R.id.switch1);
        Switch2=(Switch)view.findViewById(R.id.switch2);
        Switch3=(Switch)view.findViewById(R.id.switch3);
        Switch4=(Switch)view.findViewById(R.id.switch4);
        Switch5=(Switch)view.findViewById(R.id.switch5);

        mText=(TextView)view.findViewById(R.id.mText);

        text0=(TextView)view.findViewById(R.id.textView1);
        text1=(TextView)view.findViewById(R.id.textView2);
        text2=(TextView)view.findViewById(R.id.textView3);
        text3=(TextView)view.findViewById(R.id.textView4);
        text4=(TextView)view.findViewById(R.id.textView5);
        text5=(TextView)view.findViewById(R.id.textView6);

        setClick(Switch0);
        setClick(Switch1);
        setClick(Switch2);
        setClick(Switch3);
        setClick(Switch4);
        setClick(Switch5);





        return view;
    }

    public void setClick(Switch a){
        a.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    mText.setText("open success!");
                }else {
                    mText.setText("open false!");
                }
            }
        });
    }


    @Override
    protected View initView() {
        return null;
    }
}
