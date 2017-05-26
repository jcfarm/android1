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

public class ControlLightFragment extends Fragment{
    private String title;
    private TextView tv_title;

    private static final String TAG = ControlLightFragment.class.getSimpleName();;
    View v=null;
    private static final String NAME_TITLE = null;
    private ListView lv;
    private MyAdapter mAdapter;
    private ArrayList<String> list;
    ArrayList<HashMap<String, Object>> ls;

    public int checkNum; // 记录选中的条目数量
    private TextView tv_show;// 用于显示选中的条目数量


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
        setHasOptionsMenu(true);
        if (getArguments() != null) {
            title = getArguments().getString("title");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.control_pump_fragment, container, false);

        tv_title = (TextView) v.findViewById(R.id.home_farm_id);
        tv_title.setText(title);
        lv = (ListView) v.findViewById(R.id.lv);
        tv_show = (TextView) v.findViewById(R.id.tv);

        list = new ArrayList<String>();
        // 为Adapter准备数据
        ls = new ArrayList<HashMap<String, Object>>();
        initDate();

        // 实例化自定义的MyAdapter
        mAdapter = new MyAdapter(list, getContext());
        // 绑定Adapter
        lv.setAdapter(mAdapter);
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
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_control,menu);
        Log.e("TGA","==onCreateOptionsMenu====");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.all_checked:
                // 遍历list的长度，将MyAdapter中的map值全部设为true
                for (int i = 0; i < list.size(); i++) {
                    MyAdapter.getIsSelected().put(i, true);
                }
                // 数量设为list的长度
                checkNum = list.size();
                // 刷新listview和TextView的显示
                dataChanged();
                return true;
            case R.id.back_checked:
                // 遍历list的长度，将已选的设为未选，未选的设为已选
                for (int i = 0; i < list.size(); i++) {
                    if (MyAdapter.getIsSelected().get(i)) {
                        MyAdapter.getIsSelected().put(i, false);
                        checkNum--;
                    } else {
                        MyAdapter.getIsSelected().put(i, true);
                        checkNum++;
                    }
                }
                // 刷新listview和TextView的显示
                dataChanged();
                return true;
            case R.id.cancel_checked:
                // 遍历list的长度，将已选的按钮设为未选
                for (int i = 0; i < list.size(); i++) {
                    if (MyAdapter.getIsSelected().get(i)) {
                        MyAdapter.getIsSelected().put(i, false);
                        checkNum--;// 数量减1
                    }
                }
                // 刷新listview和TextView的显示
                dataChanged();
                return true;
            case R.id.sure_checked:
                Toast.makeText(v.getContext(), MyAdapter.isSelected.toString(), Toast.LENGTH_SHORT).show();
                Log.e("TGA",MyAdapter.isSelected.toString()+"");
                getDataPostByOkhttpUtils(MyAdapter.isSelected);
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
                .execute(new ControlLightFragment.MyStringCallback());
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
