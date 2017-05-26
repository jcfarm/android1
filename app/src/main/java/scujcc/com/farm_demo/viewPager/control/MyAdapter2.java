package scujcc.com.farm_demo.viewPager.control;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

import scujcc.com.farm_demo.R;

/**
 * Created by yang on 17-5-26.
 */

public class MyAdapter2 extends BaseAdapter {
    static int checkNum = 0;
    // 填充数据的list
    private ArrayList<String> list;
    // 用来控制CheckBox的选中状况
    public static HashMap<Integer, Boolean> isSelected;
    // 上下文
    private Context context;
    // 用来导入布局
    private LayoutInflater inflater = null;
    // 构造器
    public MyAdapter2(ArrayList<String> list, Context context) {
        this.context = context;
        this.list = list;
        inflater = LayoutInflater.from(context);

        isSelected = new HashMap<Integer, Boolean>();
        // 初始化数据
        initDate();
    }
    // 初始化isSelected的数据
    private void initDate() {
        for (int i = 0; i < list.size(); i++) {
            getIsSelected().put(i, false);
        }
    }
    @Override
    public int getCount() {
        return list.size();
    }
    @Override
    public Object getItem(int position) {
        return list.get(position);
    }
    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        MyAdapter2.ViewHolder holder = null;


        if (convertView == null) {
            // 获得ViewHolder对象
            holder = new MyAdapter2.ViewHolder();
            // 导入布局并赋值给convertview
            convertView = inflater.inflate(R.layout.listviewitem, null);

            //************seekBar*****/
            holder.seekBar=(SeekBar)convertView.findViewById(R.id.seekBar);
            holder.seekBar.setVisibility(View.GONE);//yinchang

            holder.tv = (TextView) convertView.findViewById(R.id.item_tv);
            holder.cb = (CheckBox) convertView.findViewById(R.id.item_cb);
            holder.tv_show = (TextView) convertView.findViewById(R.id.tv);
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MyAdapter2.ViewHolder holder = (MyAdapter2.ViewHolder) v.getTag();
                    Log.e("TGA","===onClick===");
                    // 改变CheckBox的状态
                    holder.cb.toggle();
                    // 将CheckBox的选中状况记录下来
                    MyAdapter2.getIsSelected().put(position, holder.cb.isChecked());
                    Toast.makeText(context, "arg2="+position+"isChecked="+holder.cb.isChecked(), Toast.LENGTH_SHORT).show();
                    // 调整选定条目

                    if (holder.cb.isChecked() == true) {
                        //*********xianshiyuyingchang************//
                        holder.seekBar.setVisibility(View.VISIBLE);//xianshi

                        checkNum++;
                    } else {
                        holder.seekBar.setVisibility(View.GONE);
                        checkNum--;
                    }
                    // 用TextView显示
                    Log.e("TGA",checkNum+"");
//                    holder.tv_show.setText("已选中"  + "项");



                }
            });


            /***seekBar Listenner***/
            holder.seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){
                @Override
                public void onStopTrackingTouch(SeekBar seekBar){
                    Toast.makeText(context, "stop seekBar", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar){
                    Toast.makeText(context, "start seekBar", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onProgressChanged(SeekBar seekBar,int progress,boolean fromUser){
                    Toast.makeText(context, "now seekBar"+progress+"%", Toast.LENGTH_SHORT).show();
                }

            });



            // 为view设置标签
            convertView.setTag(holder);
        } else {
            // 取出holder
            holder = (MyAdapter2.ViewHolder) convertView.getTag();
        }
        // 设置list中TextView的显示
        holder.tv.setText(list.get(position));
        // 根据isSelected来设置checkbox的选中状况
        holder.cb.setChecked(getIsSelected().get(position));
        return convertView;
    }
    public static HashMap<Integer, Boolean> getIsSelected() {
        return isSelected;
    }
    public static void setIsSelected(HashMap<Integer, Boolean> isSelected) {
        MyAdapter.isSelected = isSelected;
    }
    public static class ViewHolder {
        TextView tv;
        SeekBar seekBar;
        CheckBox cb;
        TextView tv_show;
    }
}
