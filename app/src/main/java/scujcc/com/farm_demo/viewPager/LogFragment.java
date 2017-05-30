package scujcc.com.farm_demo.viewPager;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
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
import scujcc.com.farm_demo.base.BaseFragment;
import scujcc.com.farm_demo.bean.FiledsBean;
import scujcc.com.farm_demo.bean.LogBean;
import scujcc.com.farm_demo.uitls.CacheUtils;

import static com.zhy.http.okhttp.log.LoggerInterceptor.TAG;

/**
 * Created by hello-brothers on 2017/5/3.
 * 日志的fragment
 */

 public class LogFragment extends BaseFragment {
    private static final String TAG = LogFragment.class.getSimpleName();//"CommonFrameFragment"
    /**
     * 网络链接
     */
    private String url;
    private static List<LogBean.ItemLogBean> datas;
    @Bind(R.id.log_recycler_view)
    RecyclerView mLogRecyclerView;
    @Bind(R.id.log_ProgressBar)
    ProgressBar progressBar;
    @Bind(R.id.log_data)
    TextView tv_nodata;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.log_fragment,container,false);

        ButterKnife.bind(this, view);
        getDataFromNet();
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mLogRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    @Override
    protected View initView() {
        return null;
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
        url = "http://118.114.120.151:8090/FileUpload/upload/log.txt";



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
        LogBean FiledsBean = parsedJson(json);
        datas =  FiledsBean.getLog();
        Log.e("TGA","===processData.datas==="+datas);
        progressBar.setVisibility(View.GONE);
        if(datas != null && datas.size() >0){
            //有数据
            tv_nodata.setVisibility(View.GONE);
            //显示适配器
            Log.e(TAG,"datas"+datas);
            mLogRecyclerView.setAdapter(new LogRecyclerViewAdapter(getActivity(),datas));
            mLogRecyclerView.setItemAnimator(new DefaultItemAnimator());
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
    private LogBean parsedJson(String response) {
        LogBean LogBean = new LogBean();
        Gson gson = new Gson();
        LogBean = gson.fromJson(response,LogBean.class);
        Log.e(TAG,"=======LogBean======"+LogBean.toString());
        return LogBean;
    }
}
