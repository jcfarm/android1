package scujcc.com.farm_demo.viewPager.home;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

import scujcc.com.farm_demo.R;

/**
 * Created by hello-brothers on 2017/5/8.
 */

public class HomeStateListAdapter extends BaseAdapter{
    String[] ls;

    private HomeStateFragment mContext;



    LayoutInflater inflater;
    TextView tex;
    final int VIEW_TYPE = 3;
    final int TYPE_1 = 0;
    final int TYPE_2 = 1;
    final int TYPE_3 = 2;
    final int TYPE_4 = 3;
    final int TYPE_5 = 4;
    final int TYPE_6 = 5;
    String[] values=null;


    public HomeStateListAdapter(HomeStateFragment context, String[] list) {
        mContext = context;
        ls = list;



    }

    @Override
    public int getCount() {
        return ls.length;
    }

    @Override
    public Object getItem(int position) {
        return ls[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    // 每个convert view都会调用此方法，获得当前所需要的view样式
    @Override
    public int getItemViewType(int position) {
        int p = position;
        if (p == 0)
            return TYPE_1;
        else if (p == 1)
            return TYPE_2;
        else if(p == 2)
            return TYPE_3;
        else if(p == 3)
            return TYPE_4;
        else if(p == 4)
            return TYPE_5;
        else
            return TYPE_6;
    }

    @Override
    public int getViewTypeCount() {
        return 6;
    }

    @Override
    public View getView (int position, View convertView, ViewGroup parent) {
        values = new String[]{"18", "17%","2000pph","18%","sdf","asdf"};
        Log.e("TGA","======getview===="+position);
        ViewHolder1 holder1 = null;
        ViewHolder2 holder2 = null;
        ViewHolder3 holder3 = null;
        ViewHolder4 holder4 = null;
        ViewHolder5 holder5 = null;
        ViewHolder6 holder6 = null;
        // 列表展开标识
        int opened = -1;
        int type = getItemViewType(position);
        if (convertView == null) {
            inflater = LayoutInflater.from(parent.getContext());
            // 按当前所需的样式，确定new的布局
            switch (type) {
                case TYPE_1:

                    convertView = inflater.inflate(R.layout.item_runrank1,
                            parent, false);
//                    holder1.item1 = (TextView)convertView.findViewById(R.id.text_view);
//                    holder1.item2 = (RelativeLayout)convertView.findViewById(R.id.pull_layout);

                    holder1 = new ViewHolder1();
                    holder1.textView11 = (TextView) convertView
                            .findViewById(R.id.text_view11);

                    holder1.textView12 = (TextView) convertView
                            .findViewById(R.id.text_view12);
                    holder1.l1 = (LinearLayout) convertView.findViewById(R.id.pull_layout1);

                    convertView.setTag(holder1);
                    convertView.setOnClickListener(new View.OnClickListener() {
                        public int Sign1 = 0;

                        @Override
                        public void onClick(View v) {
                            LinearLayout ll1 = (LinearLayout) v.findViewById(R.id.pull_layout1);
                            Toast.makeText(v.getContext(), "温度!", Toast.LENGTH_SHORT).show();
                            if (Sign1 == 1){

                                ll1.setVisibility(View.GONE);
//                                sign_img_type2.setImageResourcegeResource(R.drawable.ic_pull_down);
                                Sign1 = 0;
                            }
                            else {
//                                sign_img_type2g_type2.setImageResource(R.drawable.ic_pull_up);
                                ll1.setVisibility(View.VISIBLE);
                                Sign1 = 1;
                            }
                        }
                    });

                    break;
                case TYPE_2:
                    convertView = inflater.inflate(R.layout.item_runrank2,
                            parent, false);
                    holder2 = new ViewHolder2();
                    holder2.textView21 = (TextView) convertView
                            .findViewById(R.id.text_view21);
                    holder2.textView22 = (TextView) convertView
                            .findViewById(R.id.text_view22);
                    holder2.l2 = (LinearLayout) convertView.findViewById(R.id.pull_layout2);

                    convertView.setTag(holder2);
                    convertView.setOnClickListener(new View.OnClickListener() {
                        public int Sign2 = 0;

                        @Override
                        public void onClick(View v) {
                            LinearLayout ll2 = (LinearLayout) v.findViewById(R.id.pull_layout2);
                            Toast.makeText(v.getContext(), "温度!", Toast.LENGTH_SHORT).show();
                            if (Sign2 == 1){

                                ll2.setVisibility(View.GONE);
//                                sign_img_type2.setImageResourcegeResource(R.drawable.ic_pull_down);
                                Sign2 = 0;
                            }
                            else {
//                                sign_img_type2g_type2.setImageResource(R.drawable.ic_pull_up);
                                ll2.setVisibility(View.VISIBLE);
                                Sign2 = 1;
                            }
                        }
                    });
                    break;
                case TYPE_3:
                    convertView = inflater.inflate(R.layout.item_runrank3,
                            parent, false);
                    holder3 = new ViewHolder3();
                    holder3.textView31 = (TextView) convertView
                            .findViewById(R.id.text_view31);
                    holder3.textView32 = (TextView) convertView
                            .findViewById(R.id.text_view32);
                    holder3.l3 = (LinearLayout) convertView.findViewById(R.id.pull_layout3);
                    convertView.setTag(holder3);
                    convertView.setOnClickListener(new View.OnClickListener() {
                        public int Sign3 = 0;

                        @Override
                        public void onClick(View v) {
                            LinearLayout ll1 = (LinearLayout) v.findViewById(R.id.pull_layout3);
                            Toast.makeText(v.getContext(), "温度!", Toast.LENGTH_SHORT).show();
                            if (Sign3 == 1){

                                ll1.setVisibility(View.GONE);
//                                sign_img_type2.setImageResourcegeResource(R.drawable.ic_pull_down);
                                Sign3 = 0;
                            }
                            else {
//                                sign_img_type2g_type2.setImageResource(R.drawable.ic_pull_up);
                                ll1.setVisibility(View.VISIBLE);
                                Sign3 = 1;
                            }
                        }
                    });
                    break;
                case TYPE_4:
                    convertView = inflater.inflate(R.layout.item_runrank4,
                            parent, false);
                    holder4 = new ViewHolder4();
                    holder4.textView41 = (TextView) convertView
                            .findViewById(R.id.text_view41);
                    holder4.textView42 = (TextView) convertView
                            .findViewById(R.id.text_view42);
                    holder4.l4 = (LinearLayout) convertView.findViewById(R.id.pull_layout4);
                    convertView.setTag(holder4);

                    convertView.setOnClickListener(new View.OnClickListener() {
                        public int Sign4 = 0;

                        @Override
                        public void onClick(View v) {
                            LinearLayout ll1 = (LinearLayout) v.findViewById(R.id.pull_layout4);
                            Toast.makeText(v.getContext(), "温度!", Toast.LENGTH_SHORT).show();
                            if (Sign4 == 1){

                                ll1.setVisibility(View.GONE);
//                                sign_img_type2.setImageResourcegeResource(R.drawable.ic_pull_down);
                                Sign4 = 0;
                            }
                            else {
//                                sign_img_type2g_type2.setImageResource(R.drawable.ic_pull_up);
                                ll1.setVisibility(View.VISIBLE);
                                Sign4 = 1;
                            }
                        }
                    });
                    break;
                case TYPE_5:
                    convertView = inflater.inflate(R.layout.item_runrank5,
                            parent, false);
                    holder5 = new ViewHolder5();
                    holder5.textView51 = (TextView) convertView
                            .findViewById(R.id.text_view51);
                    holder5.textView52 = (TextView) convertView
                            .findViewById(R.id.text_view52);
                    holder5.l5 = (LinearLayout) convertView.findViewById(R.id.pull_layout5);
                    convertView.setTag(holder5);

                    convertView.setOnClickListener(new View.OnClickListener() {
                        public int Sign5 = 0;

                        @Override
                        public void onClick(View v) {
                            LinearLayout ll1 = (LinearLayout) v.findViewById(R.id.pull_layout5);
                            Toast.makeText(v.getContext(), "温度!", Toast.LENGTH_SHORT).show();
                            if (Sign5 == 1){

                                ll1.setVisibility(View.GONE);
//                                sign_img_type2.setImageResourcegeResource(R.drawable.ic_pull_down);
                                Sign5 = 0;
                            }
                            else {
//                                sign_img_type2g_type2.setImageResource(R.drawable.ic_pull_up);
                                ll1.setVisibility(View.VISIBLE);
                                Sign5 = 1;
                            }
                        }
                    });
                    break;
                case TYPE_6:
                    convertView = inflater.inflate(R.layout.item_runrank6,
                            parent, false);
                    holder6 = new ViewHolder6();
                    holder6.textView61 = (TextView) convertView
                            .findViewById(R.id.text_view61);
                    holder6.textView62 = (TextView) convertView
                            .findViewById(R.id.text_view62);
                    holder6.l6 = (LinearLayout) convertView.findViewById(R.id.pull_layout6);
                    convertView.setTag(holder6);

                    convertView.setOnClickListener(new View.OnClickListener() {
                        public int Sign6 = 0;

                        @Override
                        public void onClick(View v) {
                            LinearLayout ll1 = (LinearLayout) v.findViewById(R.id.pull_layout6);
                            Toast.makeText(v.getContext(), "温度!", Toast.LENGTH_SHORT).show();
                            if (Sign6 == 1){

                                ll1.setVisibility(View.GONE);
//                                sign_img_type2.setImageResourcegeResource(R.drawable.ic_pull_down);
                                Sign6 = 0;
                            }
                            else {
//                                sign_img_type2g_type2.setImageResource(R.drawable.ic_pull_up);
                                ll1.setVisibility(View.VISIBLE);
                                Sign6 = 1;
                            }
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
                case TYPE_5:
                    holder5 = (ViewHolder5) convertView.getTag();
                    break;
                case TYPE_6:
                    holder6 = (ViewHolder6) convertView.getTag();
                    break;

            }
        }

        // 设置资源
        switch (type) {
            case TYPE_1:
                holder1.textView11.setText("温度");
                holder1.textView12.setText(ls[0]);
                holder1.l1.setVisibility(View.GONE);
                break;
            case TYPE_2:
                holder2.textView21.setText("空气湿度");
                holder2.textView22.setText(ls[1]);
                holder2.l2.setVisibility(View.GONE);
//                holder2.totaldistance2.setText(ls.get(position)
                break;
            case TYPE_3:
                holder3.textView31.setText("CO2");
                holder3.textView32.setText(ls[2]);
                holder3.l3.setVisibility(View.GONE);
                break;
            case TYPE_4:
                holder4.textView41.setText("土壤湿度");
                holder4.textView42.setText(ls[3]);
                holder4.l4.setVisibility(View.GONE);
                break;
            case TYPE_5:
                holder5.textView51.setText("电导率");
                holder5.textView52.setText(ls[4]);
                holder5.l5.setVisibility(View.GONE);
//                holder2.totaldistance2.setText(ls.get(position)
                break;
            case TYPE_6:
                holder6.textView61.setText("盐分");
                holder6.textView62.setText(ls[5]);
                holder6.l6.setVisibility(View.GONE);
                break;
        }


        return convertView;
    }





    public class ViewHolder1 {
        TextView textView11;
        TextView textView12;
        LinearLayout l1;



    }

    public class ViewHolder2 {
        TextView textView21;
        TextView textView22;
        LinearLayout l2;

    }

    public class ViewHolder3 {
        TextView textView31;
        TextView textView32;
        LinearLayout l3;
    }
    public class ViewHolder4 {
        TextView textView41;
        TextView textView42;
        LinearLayout l4;



    }

    public class ViewHolder5 {
        TextView textView51;
        TextView textView52;
        LinearLayout l5;

    }

    public class ViewHolder6 {
        TextView textView61;
        TextView textView62;
        LinearLayout l6;
    }
}