package scujcc.com.farm_demo.viewPager.home;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

import scujcc.com.farm_demo.R;

/**
 * Created by hello-brothers on 2017/5/8.
 */

public class HomeStateListAdapter extends BaseAdapter{
    ArrayList<HashMap<String, Object>> ls;

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


    public HomeStateListAdapter(HomeStateFragment context, ArrayList<HashMap<String, Object>> list) {
        mContext = context;
        ls = list;



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
                    convertView.setTag(holder1);

                    break;
                case TYPE_2:
                    convertView = inflater.inflate(R.layout.item_runrank2,
                            parent, false);
                    holder2 = new ViewHolder2();
                    holder2.textView21 = (TextView) convertView
                            .findViewById(R.id.text_view21);
                    holder2.textView22 = (TextView) convertView
                            .findViewById(R.id.text_view22);

                    convertView.setTag(holder2);
                    break;
                case TYPE_3:
                    convertView = inflater.inflate(R.layout.item_runrank3,
                            parent, false);
                    holder3 = new ViewHolder3();
                    holder3.textView31 = (TextView) convertView
                            .findViewById(R.id.text_view31);
                    holder3.textView32 = (TextView) convertView
                            .findViewById(R.id.text_view32);
                    convertView.setTag(holder3);
                    break;
                case TYPE_4:
                    convertView = inflater.inflate(R.layout.item_runrank4,
                            parent, false);
                    holder4 = new ViewHolder4();
                    holder4.textView41 = (TextView) convertView
                            .findViewById(R.id.text_view41);
                    holder4.textView42 = (TextView) convertView
                            .findViewById(R.id.text_view42);
                    convertView.setTag(holder4);
                    break;
                case TYPE_5:
                    convertView = inflater.inflate(R.layout.item_runrank5,
                            parent, false);
                    holder5 = new ViewHolder5();
                    holder5.textView51 = (TextView) convertView
                            .findViewById(R.id.text_view51);
                    holder5.textView52 = (TextView) convertView
                            .findViewById(R.id.text_view52);
                    convertView.setTag(holder5);
                    break;
                case TYPE_6:
                    convertView = inflater.inflate(R.layout.item_runrank6,
                            parent, false);
                    holder6 = new ViewHolder6();
                    holder6.textView61 = (TextView) convertView
                            .findViewById(R.id.text_view61);
                    holder6.textView62 = (TextView) convertView
                            .findViewById(R.id.text_view62);
                    convertView.setTag(holder6);
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
                holder1.textView12.setText("18");
                break;
            case TYPE_2:
                holder2.textView21.setText("空气湿度");
                holder2.textView22.setText("17%");
//                holder2.totaldistance2.setText(ls.get(position)
                break;
            case TYPE_3:
                holder3.textView31.setText("CO2");
                holder3.textView32.setText("2000pph");
                break;
            case TYPE_4:
                holder4.textView41.setText("土壤湿度");
                holder4.textView42.setText("18%");
                break;
            case TYPE_5:
                holder5.textView51.setText("电导率");
                holder5.textView52.setText("2000uS/cm²");
//                holder2.totaldistance2.setText(ls.get(position)
                break;
            case TYPE_6:
                holder6.textView61.setText("盐分");
                holder6.textView62.setText("1000mg/L");
                break;
        }


        return convertView;
    }





    public class ViewHolder1 {
        TextView textView11;
        TextView textView12;



    }

    public class ViewHolder2 {
        TextView textView21;
        TextView textView22;

    }

    public class ViewHolder3 {
        TextView textView31;
        TextView textView32;
    }
    public class ViewHolder4 {
        TextView textView41;
        TextView textView42;



    }

    public class ViewHolder5 {
        TextView textView51;
        TextView textView52;

    }

    public class ViewHolder6 {
        TextView textView61;
        TextView textView62;
    }
}