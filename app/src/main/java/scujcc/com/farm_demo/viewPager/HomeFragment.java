package scujcc.com.farm_demo.viewPager;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.LoaderManager;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Request;
import scujcc.com.farm_demo.R;
import scujcc.com.farm_demo.adapter.HomeRecyclerViewAdapter;
import scujcc.com.farm_demo.adapter.HomeRecyclerViewAdapter;
import scujcc.com.farm_demo.base.BaseFragment;
import scujcc.com.farm_demo.bean.FiledsBean;
import scujcc.com.farm_demo.uitls.CacheUtils;

import static com.zhy.http.okhttp.log.LoggerInterceptor.TAG;

/**
 * Created by hello-brothers on 2017/5/3.
 * 主页的fragment
 */

public class HomeFragment extends BaseFragment {

    public static final int TYPE_LINEAR_LAYOUT = 1;
    public static final int TYPE_GRID_LAYOUT = 2;
    public static final int TYPE_STAGGERED_GRID_LAYOUT = 3;
    @Bind(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @Bind(R.id.progressBar)
    ProgressBar progressBar;
    @Bind(R.id.tv_nodata)
    TextView tv_nodata;

    private int type = TYPE_LINEAR_LAYOUT;

    private ArrayList<BaseFragment> mBaseFragment;
    private TabLayout tabLayout;
    /**
     * 网络链接
     */
    private String url;
    private static List<FiledsBean.ItemFieldsBean> datas;

    public static HomeFragment newInstance(int type) {

        Bundle args = new Bundle();

        HomeFragment fragment = new HomeFragment();
        args.putInt("type", type);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            type = getArguments().getInt("type", TYPE_LINEAR_LAYOUT);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_fragment,container,false);
        TextView textView = (TextView) view.findViewById(R.id.home_textView);
        textView.setText(R.string.home);
        ButterKnife.bind(this, view);
        getDataFromNet();

        return view;
    }

    @Override
    protected View initView() {
        return null;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (type == TYPE_GRID_LAYOUT) {
            mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));//这里用线性宫格显示 类似于grid view
        } else if (type == TYPE_STAGGERED_GRID_LAYOUT) {
            mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, OrientationHelper.VERTICAL));//这里用线性宫格显示 类似于瀑布流
        } else {
            mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));//这里用线性显示 类似于list view
        }
        Log.e("TGA","===onViewCreated.datas==="+datas);
        /*if(datas != null && datas.size() >0){
            //有数据
            tv_nodata.setVisibility(View.GONE);
            //显示适配器
            Log.e(TAG,"datas"+datas);
            mRecyclerView.setAdapter(new HomeRecyclerViewAdapter(getActivity(),datas));
            mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        }else{
            //没有数据
            tv_nodata.setVisibility(View.VISIBLE);
        }*/
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    /**
     * -------------------------------------------
     *                  网络请求
     */

    private void getDataFromNet() {
        Log.e(TAG,"getDataFromNet=============");
        url = "http://10.8.105.30:8090/FileUpload/upload/fields.txt";
        //得到缓存的数据
        String saveJson = CacheUtils.getString(getActivity(),url);
        Log.e(TAG,"=====saveJson====");
        if(!TextUtils.isEmpty(saveJson)){
            processData(saveJson);

        }


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

            Toast.makeText(getActivity(), "loading", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onAfter(int id) {

            Toast.makeText(getActivity(), "finish", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onError(Call call, Exception e, int id) {
            e.printStackTrace();
//            tv_nodata.setVisibility(View.VISIBLE);
        }

        @Override
        public void onResponse(String response, int id) {
            Log.e(TAG,"onResponse=======");
            Log.e(TAG, "onResponse：complete");
            tv_nodata.setVisibility(View.GONE);

            switch (id) {
                case 100:
                    Toast.makeText(getActivity(), "http", Toast.LENGTH_SHORT).show();
                    break;
                case 101:
                    Toast.makeText(getActivity(), "https", Toast.LENGTH_SHORT).show();
                    break;
            }
            //解析数据和显示数据
            if(response != null){
                //缓存数据
                CacheUtils.putString(getActivity(),url,response);
                processData(response);
                Log.e(TAG,"respomse"+response);

            }
        }



        @Override
        public void inProgress(float progress, long total, int id) {
            Log.e(TAG, "inProgress:" + progress);
        }
    }



    /**
     * 解析和显示数据
     * @param json
     */
    private void processData(String json) {

        //解析数据
        FiledsBean FiledsBean = parsedJson(json);
        datas =  FiledsBean.getFields();
        Log.e("TGA","===processData.datas==="+datas);
        progressBar.setVisibility(View.GONE);
        if(datas != null && datas.size() >0){
            //有数据
            tv_nodata.setVisibility(View.GONE);
            //显示适配器
            Log.e(TAG,"datas"+datas);
            mRecyclerView.setAdapter(new HomeRecyclerViewAdapter(getActivity(),datas));
            mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        }else{
            //没有数据
            tv_nodata.setVisibility(View.VISIBLE);
        }
    }


    /**
     * 解析json数据
     *
     * @param response
     * @return
     */
    private FiledsBean parsedJson(String response) {
        FiledsBean FiledsBean = new FiledsBean();

        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray jsonArray = jsonObject.optJSONArray("fields");
            if (jsonArray != null && jsonArray.length() > 0) {
                List<FiledsBean.ItemFieldsBean> fields = new ArrayList<>();
                FiledsBean.setFields(fields);
                Log.e(TAG,"=======jsonArray.length()"+jsonArray.length());
                for (int i = 0; i < jsonArray.length(); i++) {

                    JSONObject jsonObjectItem = (JSONObject) jsonArray.get(i);

                    if (jsonObjectItem != null) {

                        FiledsBean.ItemFieldsBean FiledItem = new FiledsBean.ItemFieldsBean();

                        int id = jsonObjectItem.optInt("id");//name
                        FiledItem.setId(id);

                        String url = jsonObjectItem.optString("url");//desc
                        FiledItem.setUrl(url);

                        int temp = jsonObjectItem.optInt("temp");//imageUrl
                        FiledItem.setTemp(temp);



                        //把数据添加到集合
                        fields.add(FiledItem);
                    }
                }
            }



        } catch (JSONException e) {
            e.printStackTrace();
        }
        return FiledsBean;
    }
}
