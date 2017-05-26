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
import android.widget.ListView;
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
 * Created by hello-brothers on 2017/5/22.
 */

public class ControlFileTopFragment extends Fragment{
    private String title;
    private TextView tv_title;
    private static final String TAG = ControlFileTopFragment.class.getSimpleName();;
    View v=null;
    private static final String NAME_TITLE = null;
    private ListView lv;
    private MyAdapter2 mAdapter;
    private ArrayList<String> list;
    ArrayList<HashMap<String, Object>> ls;

    public int checkNum; // 记录选中的条目数量
    private TextView tv_show;// 用于显示选中的条目数量

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
        setHasOptionsMenu(true);
        if (getArguments() != null) {
            title = getArguments().getString("title");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v= inflater.inflate(R.layout.control_pump_fragment, container, false);


        tv_title = (TextView) v.findViewById(R.id.home_farm_id);
        tv_title.setText(title);

        lv = (ListView) v.findViewById(R.id.lv);
        tv_show = (TextView) v.findViewById(R.id.tv);


        list = new ArrayList<String>();
        // 为Adapter准备数据
        ls = new ArrayList<HashMap<String, Object>>();
        initDate();

        // 实例化自定义的MyAdapter
        mAdapter = new MyAdapter2(list, getContext());
        // 绑定Adapter
        lv.setAdapter(mAdapter);
//        // 全选按钮的回调接口
//        bt_selectall.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // 遍历list的长度，将MyAdapter中的map值全部设为true
//                for (int i = 0; i < list.size(); i++) {
//                    MyAdapter.getIsSelected().put(i, true);
//                }
//                // 数量设为list的长度
//                checkNum = list.size();
//                // 刷新listview和TextView的显示
//                dataChanged();
//            }
//        });
//        // 反选按钮的回调接口
//        bt_cancel.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // 遍历list的长度，将已选的设为未选，未选的设为已选
//                for (int i = 0; i < list.size(); i++) {
//                    if (MyAdapter.getIsSelected().get(i)) {
//                        MyAdapter.getIsSelected().put(i, false);
//                        checkNum--;
//                    } else {
//                        MyAdapter.getIsSelected().put(i, true);
//                        checkNum++;
//                    }
//                }
//                // 刷新listview和TextView的显示
//                dataChanged();
//            }
//        });
//        // 取消按钮的回调接口
//        bt_deselectall.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // 遍历list的长度，将已选的按钮设为未选
//                for (int i = 0; i < list.size(); i++) {
//                    if (MyAdapter.getIsSelected().get(i)) {
//                        MyAdapter.getIsSelected().put(i, false);
//                        checkNum--;// 数量减1
//                    }
//                }
//                // 刷新listview和TextView的显示
//                dataChanged();
//            }
//        });
        // 绑定listView的监听器
       /*lv.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                // 取得ViewHolder对象，这样就省去了通过层层的findViewById去实例化我们需要的cb实例的步骤
                ViewHolder holder = (ViewHolder) arg1.getTag();
                // 改变CheckBox的状态
                holder.cb.toggle();
                // 将CheckBox的选中状况记录下来
                MyAdapter.getIsSelected().put(arg2, holder.cb.isChecked());
                Toast.makeText(getContext(), "arg2="+arg2+"isChecked="+holder.cb.isChecked(), Toast.LENGTH_SHORT).show();
                // 调整选定条目
                if (holder.cb.isChecked() == true) {

                    checkNum++;
                } else {
                    checkNum--;
                }
                // 用TextView显示
                tv_show.setText("已选中" + checkNum + "项");
            }
        });*/


//        bt_put.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(v.getContext(), MyAdapter.isSelected.toString(), Toast.LENGTH_SHORT).show();
//                Log.e("TGA",MyAdapter.isSelected.toString()+"");
//                getDataPostByOkhttpUtils(MyAdapter.isSelected);
//            }
//        });
//
        return v;
    }


    // 初始化数据
    private void initDate() {
        for (int i = 0; i < 15; i++) {
            list.add("data" + " " + i);
        }
    }

    private void dataChanged() {
        // 通知listView刷新
        mAdapter.notifyDataSetChanged();
        // TextView显示最新的选中数目
        tv_show.setText("已选中" + checkNum + "项");

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        Log.e("TGA","====onCreateOptionsMenu=====");
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_control, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.all_checked:
                // 遍历list的长度，将MyAdapter中的map值全部设为true
                for (int i = 0; i < list.size(); i++) {
                    MyAdapter2.getIsSelected().put(i, true);
                }
                // 数量设为list的长度
                checkNum = list.size();
                // 刷新listview和TextView的显示
                dataChanged();
                return true;
            case R.id.back_checked:
                // 遍历list的长度，将已选的设为未选，未选的设为已选
                for (int i = 0; i < list.size(); i++) {
                    if (MyAdapter2.getIsSelected().get(i)) {
                        MyAdapter2.getIsSelected().put(i, false);
                        checkNum--;
                    } else {
                        MyAdapter2.getIsSelected().put(i, true);
                        checkNum++;
                    }
                }
                // 刷新listview和TextView的显示
                dataChanged();
                return true;
            case R.id.cancel_checked:
                // 遍历list的长度，将已选的按钮设为未选
                for (int i = 0; i < list.size(); i++) {
                    if (MyAdapter2.getIsSelected().get(i)) {
                        MyAdapter2.getIsSelected().put(i, false);
                        checkNum--;// 数量减1
                    }
                }
                // 刷新listview和TextView的显示
                dataChanged();
                return true;
            case R.id.sure_checked:
                Toast.makeText(v.getContext(), MyAdapter2.isSelected.toString(), Toast.LENGTH_SHORT).show();
                Log.e("TGA",MyAdapter2.isSelected.toString()+"");
                getDataPostByOkhttpUtils(MyAdapter2.isSelected);
                dataChanged();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /**
     * 使用okhttp-utils的post请求网络文本数据
     * @param controlStr
     */
    public void getDataPostByOkhttpUtils(HashMap<Integer, Boolean> controlStr) {
        String url = "http://www.zhiyun-tech.com/App/Rider-M/changelog-zh.txt";
//        url="http://www.391k.com/api/xapi.ashx/info.json?key=bd_hyrzjjfb4modhj&size=10&page=1";
        Log.e(TAG,"====controlStr====="+controlStr);
        url = "http://171.212.149.251:8080/controller?str="+controlStr;
        OkHttpUtils
                .post()
                .url(url)
                .id(100)
                .build()
                .execute(new ControlFileTopFragment.MyStringCallback());
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
            Toast.makeText(getContext(), "https"+response, Toast.LENGTH_SHORT).show();

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
