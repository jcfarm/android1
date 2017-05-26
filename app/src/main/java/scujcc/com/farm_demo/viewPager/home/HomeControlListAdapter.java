package scujcc.com.farm_demo.viewPager.home;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.HashMap;

import okhttp3.Call;
import okhttp3.Request;
import scujcc.com.farm_demo.R;

/**
 * Created by hello-brothers on 2017/5/21.
 */

public class HomeControlListAdapter extends BaseAdapter {
    private static final String TAG = HomeControlListAdapter.class.getSimpleName();;
    private final HomeControlFragment mContext;
    ArrayList<HashMap<String, Object>> ls;
    LayoutInflater inflater;
    final int TYPE_1 = 0;
    final int TYPE_2 = 1;
    final int TYPE_3 = 2;
    final int TYPE_4 = 3;
    int Sign = 0;

    private String[] img =null;
    private String[] content =null;
    private int Farm_Id;
    private String[] sign =null;
//    private ViewHolder1 holder1;
//    private ViewHolder2 holder2;



    public HomeControlListAdapter(HomeControlFragment context, ArrayList<HashMap<String, Object>> runRank, int farm_Id) {
        mContext = context;
        ls = runRank;
        this.Farm_Id = farm_Id;
        img = context.getResources().getStringArray(R.array.home_control_img);
        content = context.getResources().getStringArray(R.array.home_control_content);
        sign = context.getResources().getStringArray(R.array.home_control_sign);

    }

    @Override
    public int getCount() {
        return ls.size();
    }

    @Override
    public Object getItem(int position) {
        return ls.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    // 每个convert view都会调用此方法，获得当前所需要的view样式
    @Override
    public int getItemViewType(int position) {
        int p = position;
        if (p <3 )
            return TYPE_1;
        else if (p == 3)
            return TYPE_2;
        else if (p == 4)
            return TYPE_3;
        else
            return TYPE_4;

    }
    @Override
    public int getViewTypeCount() {
        return 4;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder1 holder1 = null;
        ViewHolder2 holder2 = null;
        ViewHolder3 holder3 = null;
        ViewHolder4 holder4 = null;
        int type = getItemViewType(position);
        if (convertView == null) {
            inflater = LayoutInflater.from(parent.getContext());
            // 按当前所需的样式，确定new的布局
            switch (type) {
                case TYPE_1:

                    convertView = inflater.inflate(R.layout.home_control_item_type1,
                            parent, false);
//                    holder1.item1 = (TextView)convertView.findViewById(R.id.text_view);
//                    holder1.item2 = (RelativeLayout)convertView.findViewById(R.id.pull_layout);

                    holder1 = new ViewHolder1();
                    holder1.img_type1 = (ImageView) convertView
                            .findViewById(R.id.home_control1_img);

                    holder1.content_type1 = (TextView) convertView
                            .findViewById(R.id.home_control1_content);
                    holder1.sw = (Switch) convertView.findViewById(R.id.home_control1_sw);
                    convertView.setTag(holder1);
                    final View finalConvertView = convertView;
                    holder1.sw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                            if (isChecked){
                                getDataPostByOkhttpUtils(Farm_Id,isChecked,position);
                                Toast.makeText(finalConvertView.getContext(), "开启"+position, Toast.LENGTH_SHORT).show();
                            }else {
                                Toast.makeText(finalConvertView.getContext(), "关闭"+position, Toast.LENGTH_SHORT).show();
                                getDataPostByOkhttpUtils(Farm_Id,isChecked,position);
                            }
                        }
                    });


                    break;
                case TYPE_2:
                    convertView = inflater.inflate(R.layout.home_control_item_type2,
                            parent, false);
                    holder2 = new ViewHolder2();
                    holder2.img_type2 = (ImageView) convertView
                            .findViewById(R.id.home_control2_img);
                    holder2.content_type2 = (TextView) convertView
                            .findViewById(R.id.home_control2_content);
                    holder2.sign_img_type2 = (ImageView) convertView.findViewById(R.id.home_control2_sign);
                    holder2.sBar2 = (SeekBar) convertView.findViewById(R.id.home_control2_seekBar);
                    holder2.ll2 = (LinearLayout) convertView.findViewById(R.id.pull2_action);
                    holder2.ll2.setVisibility(View.GONE);
                    holder2.barValue22 = (TextView) convertView.findViewById(R.id.TextValue22);
                    holder2.t21 = (TextView) convertView.findViewById(R.id.TextView21);
                    holder2.t23 = (TextView) convertView.findViewById(R.id.TextView23);

                    convertView.setTag(holder2);

//                    holder2.sBar.setOnSeekBarChangeListener(this);

                    convertView.setOnClickListener(new View.OnClickListener() {
                        public int Sign2 = 0;

                        @Override
                        public void onClick(View v) {
                            LinearLayout l = (LinearLayout) v.findViewById(R.id.pull2_action);
                            ImageView sign_img_type2 = (ImageView) v.findViewById(R.id.home_control2_sign);
                            if (Sign2 == 1){

                                l.setVisibility(View.GONE);
                                sign_img_type2.setImageResource(R.drawable.ic_pull_down);
                                Sign2 = 0;
                            }
                            else {
                                sign_img_type2.setImageResource(R.drawable.ic_pull_up);
                                l.setVisibility(View.VISIBLE);
                                Sign2 = 1;
                            }
                        }
                    });
                    final ViewHolder2 finalHolder = holder2;
                    holder2.sBar2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                        @Override
                        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                            finalHolder.barValue22.setText("progress"+progress+"seekBar"+seekBar.getId()+"fromUser"+fromUser);
//                            getDataPostByOkhttpUtils(Farm_Id,progress,);
                        }

                        @Override
                        public void onStartTrackingTouch(SeekBar seekBar) {

                        }

                        @Override
                        public void onStopTrackingTouch(SeekBar seekBar) {

                        }
                    });


                    break;
                case TYPE_3:
                    convertView = inflater.inflate(R.layout.home_control_item_type3,
                            parent, false);
                    holder3 = new ViewHolder3();
                    holder3.img_type3 = (ImageView) convertView
                            .findViewById(R.id.home_control3_img);
                    holder3.content_type3 = (TextView) convertView
                            .findViewById(R.id.home_control3_content);
                    holder3.sign_img_type3 = (ImageView) convertView.findViewById(R.id.home_control3_sign);
                    holder3.sBar3 = (SeekBar) convertView.findViewById(R.id.home_control3_seekBar);
                    holder3.ll3 = (LinearLayout) convertView.findViewById(R.id.pull3_action);
                    holder3.ll3.setVisibility(View.GONE);
                    holder3.barValue32 = (TextView) convertView.findViewById(R.id.TextValue32);
                    holder3.t31 = (TextView) convertView.findViewById(R.id.TextView31);
                    holder3.t33 = (TextView) convertView.findViewById(R.id.TextView33);

                    convertView.setTag(holder3);

//                    holder2.sBar.setOnSeekBarChangeListener(this);

                    convertView.setOnClickListener(new View.OnClickListener() {
                        public int Sign3 = 0;

                        @Override
                        public void onClick(View v) {
                            LinearLayout l = (LinearLayout) v.findViewById(R.id.pull3_action);
                            ImageView sign_img_type3 = (ImageView) v.findViewById(R.id.home_control3_sign);
                            if (Sign3 == 1){

                                l.setVisibility(View.GONE);
                                sign_img_type3.setImageResource(R.drawable.ic_pull_down);
                                Sign3 = 0;
                            }
                            else {
                                sign_img_type3.setImageResource(R.drawable.ic_pull_up);
                                l.setVisibility(View.VISIBLE);
                                Sign3 = 1;
                            }
                        }
                    });
                    final ViewHolder3 finalHolder3 = holder3;
                    holder3.sBar3.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                        @Override
                        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                            finalHolder3.barValue32.setText("progress"+progress+"seekBar"+seekBar.getId()+"fromUser"+fromUser);
                        }

                        @Override
                        public void onStartTrackingTouch(SeekBar seekBar) {

                        }

                        @Override
                        public void onStopTrackingTouch(SeekBar seekBar) {

                        }
                    });

                    break;
                case TYPE_4:
                    convertView = inflater.inflate(R.layout.home_control_item_type4,
                            parent, false);
                    holder4 = new ViewHolder4();
                    holder4.img_type4 = (ImageView) convertView
                            .findViewById(R.id.home_control4_img);
                    holder4.content_type4 = (TextView) convertView
                            .findViewById(R.id.home_control4_content);
                    holder4.sign_img_type4 = (ImageView) convertView.findViewById(R.id.home_control4_sign);
                    holder4.sBar4 = (SeekBar) convertView.findViewById(R.id.home_control4_seekBar);
                    holder4.ll4 = (LinearLayout) convertView.findViewById(R.id.pull4_action);
                    holder4.ll4.setVisibility(View.GONE);
                    holder4.barValue42 = (TextView) convertView.findViewById(R.id.TextValue42);
                    holder4.t41 = (TextView) convertView.findViewById(R.id.TextView41);
                    holder4.t43 = (TextView) convertView.findViewById(R.id.TextView43);

                    convertView.setTag(holder4);

//                    holder2.sBar.setOnSeekBarChangeListener(this);

                    convertView.setOnClickListener(new View.OnClickListener() {
                        public int Sign4 = 0;

                        @Override
                        public void onClick(View v) {
                            LinearLayout l = (LinearLayout) v.findViewById(R.id.pull4_action);
                            ImageView sign_img_type4 = (ImageView) v.findViewById(R.id.home_control4_sign);
                            if (Sign4 == 1){

                                l.setVisibility(View.GONE);
                                sign_img_type4.setImageResource(R.drawable.ic_pull_down);
                                Sign4 = 0;
                            }
                            else {
                                sign_img_type4.setImageResource(R.drawable.ic_pull_up);
                                l.setVisibility(View.VISIBLE);
                                Sign4 = 1;
                            }
                        }
                    });
                    final ViewHolder4 finalHolder4 = holder4;
                    holder4.sBar4.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                        @Override
                        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
//                            finalHolder4.barValue42.setText(""+progress);
                            seekBar.getId();
                            finalHolder4.barValue42.setText("progress"+progress+"seekBar"+seekBar.getId()+"fromUser"+fromUser);
                        }

                        @Override
                        public void onStartTrackingTouch(SeekBar seekBar) {

                        }

                        @Override
                        public void onStopTrackingTouch(SeekBar seekBar) {

                        }
                    });

                    break;
                default:
                    break;
            }

        } else {
            switch (type) {

                case TYPE_1:
                    holder1 = (ViewHolder1) convertView.getTag();
                    break;
                case TYPE_2:
                    holder2 = (ViewHolder2) convertView.getTag();
                    break;
                case TYPE_3:
                    holder3 = (ViewHolder3) convertView.getTag();
                    break;
                case TYPE_4:
                    holder4 = (ViewHolder4) convertView.getTag();

                    break;


            }
        }

        switch (type) {
            case TYPE_1:
                holder1.img_type1.setImageResource(R.drawable.jkx);
                holder1.content_type1.setText(content[position]);

                break;
            case TYPE_2:
                holder2.img_type2.setImageResource(R.drawable.jkx);
                holder2.content_type2.setText(content[position]);
//                holder2.sBar2.setOnSeekBarChangeListener(this);
                break;
            case TYPE_3:
                holder3.img_type3.setImageResource(R.drawable.jkx);
                holder3.content_type3.setText(content[position]);
                break;
            case TYPE_4:
                holder4.img_type4.setImageResource(R.drawable.jkx);
                holder4.content_type4.setText(content[position]);




//                holder2.totaldistance2.setText(ls.get(position)
                break;
        }

        return convertView;
    }

//    @Override
//    public void onClick(View v) {
//        LinearLayout l = (LinearLayout) v.findViewById(R.id.pull2_action);
//        ImageView sign_img_type2 = (ImageView) v.findViewById(R.id.home_control2_sign);
//        if (Sign == 1){
//
//            l.setVisibility(View.GONE);
//            sign_img_type2.setImageResource(R.drawable.ic_pull_down);
//            Sign = 0;
//        }
//        else {
//            sign_img_type2.setImageResource(R.drawable.ic_pull_up);
//            l.setVisibility(View.VISIBLE);
//            Sign = 1;
//        }
//
//        Log.e(TAG,"getposition====="+v.findViewById(R.id.home_control2_content).toString());
//    }



    public class ViewHolder1 {
        ImageView img_type1;
        TextView content_type1;
        Switch sw;


    }

    public class ViewHolder2 {
        ImageView img_type2;
        TextView content_type2;
        ImageView sign_img_type2;
        SeekBar sBar2;
        TextView t21;
        TextView t23;
        TextView barValue22;
        LinearLayout ll2;

    }
    public class ViewHolder3 {
        ImageView img_type3;
        TextView content_type3;
        ImageView sign_img_type3;
        SeekBar sBar3;
        TextView t31;
        TextView t33;
        TextView barValue32;
        LinearLayout ll3;

    }
    public class ViewHolder4 {
        ImageView img_type4;
        TextView content_type4;
        ImageView sign_img_type4;
        SeekBar sBar4;
        TextView t41;
        TextView t43;
        TextView barValue42;
        LinearLayout ll4;

    }
    /**
     * 使用okhttp-utils的post请求网络文本数据
     * @param farm_Id
     * @param isChecked
     * @param pos
     */
    public void getDataPostByOkhttpUtils(int farm_Id, boolean isChecked, int pos) {
        String url = "http://www.zhiyun-tech.com/App/Rider-M/changelog-zh.txt";
//        url="http://www.391k.com/api/xapi.ashx/info.json?key=bd_hyrzjjfb4modhj&size=10&page=1";
        url = "http://10.8.105.221:8080/greeting?farm_Id="+farm_Id+"&position="+pos+"&isChecked="+isChecked;
        OkHttpUtils
                .post()
                .url(url)
                .id(100)
                .build()
                .execute(new MyStringCallback());
    }
    public class MyStringCallback extends StringCallback {
        @Override
        public void onBefore(Request request, int id) {

        }

        @Override
        public void onAfter(int id) {

        }

        @Override
        public void onError(Call call, Exception e, int id) {
            e.printStackTrace();

        }

        @Override
        public void onResponse(String response, int id) {
            Log.e(TAG, "onResponse：complete");
            Toast.makeText(mContext.getContext(), "https"+response, Toast.LENGTH_SHORT).show();

            switch (id) {
                case 100:
//                    Toast.makeText(mContext, "http", Toast.LENGTH_SHORT).show();
                    break;
                case 101:
//                    Toast.makeText(OKHttpActivity.this, "https", Toast.LENGTH_SHORT).show();
                    break;
            }
        }

        @Override
        public void inProgress(float progress, long total, int id) {
            Log.e(TAG, "inProgress:" + progress);

        }
    }
}
