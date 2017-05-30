package scujcc.com.farm_demo.viewPager.home;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.view.KeyEvent;
import android.widget.Toast;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import butterknife.Bind;
import butterknife.ButterKnife;
import scujcc.com.farm_demo.R;
import scujcc.com.farm_demo.base.BaseFragment;

/**
 * Created by hello-brothers on 2017/5/8.
 */

public class HomeStateFragment extends BaseFragment {
    private static final String TAG = HomeControlFragment.class.getSimpleName();//"CommonFrameFragment"

    private static HomeStateListAdapter adapter;
    private static ListView rv;
    ArrayList<HashMap<String, Object>> runRank;
    
    static private String[] value = {"数据获取中...","数据获取中...","数据获取中...","数据获取中...","数据获取中...","数据获取中..."};

    private String host = "tcp://118.114.120.151:1883";

    private Handler handler;


    private MqttConnectOptions options;

    private ScheduledExecutorService scheduler;
    private String myTopic = "温度";
    private String myTopic2 = "空气湿度";
    private String myTopic3 = "CO2";
    private String myTopic4 = "土壤湿度";
    private String myTopic5 = "电导率";
    private String myTopic6 = "盐分";

    public static HomeStateFragment newInstance() {

        Bundle args = new Bundle();

        HomeStateFragment fragment = new HomeStateFragment();

        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e(TAG,"=====HomeStateFragments  ======");

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_state_fragment,container,false);

        ButterKnife.bind(this, view);
        // 实例化控件
        rv = (ListView) view.findViewById(R.id.home_states_ls);

        refreshAdapter(rv);
        runRank = new ArrayList<HashMap<String, Object>>();


        for (int i = 0; i < 6; i++) {
            HashMap<String, Object> map = new HashMap<String, Object>();

            map.put("ItemTitle", "Level " + i);
            map.put("ItemText", "Finished in 1 Min 54 Secs, 70 Moves! ");
            runRank.add(map);

        }



        init();

        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if(msg.what == 1) {

                    Log.e("TGA","======msg========"+msg.toString());
                    String title = msg.obj.toString().split(":")[0];
                    switch (title){
                        case "温度":value[0] = msg.obj.toString().split(":")[1];break;
                        case "空气湿度":value[1] = msg.obj.toString().split(":")[1];break;
                        case "CO2":value[2] = msg.obj.toString().split(":")[1];break;
                        case "土壤湿度":value[3] = msg.obj.toString().split(":")[1];break;
                        case "电导率":value[4] = msg.obj.toString().split(":")[1];break;
                        case "盐分":value[5] = msg.obj.toString().split(":")[1];break;
                    }
                    refreshAdapter(rv);



                    System.out.println("-----------------------------");
                } else if(msg.what == 2) {
                    Toast.makeText(getContext(), "连接成功", Toast.LENGTH_SHORT).show();

//                    resultTv.setTextSize(20);
//                    resultTv.setText("连接成功!");
                    try {
                        client.subscribe(myTopic, 1);
                        client.subscribe(myTopic2, 1);
                        client.subscribe(myTopic3, 1);
                        client.subscribe(myTopic4, 1);
                        client.subscribe(myTopic5, 1);
                        client.subscribe(myTopic6, 1);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else if(msg.what == 3) {
//                    resultTv.setTextSize(20);
//                    resultTv.setText("连接失败，系统正在重连...");
                }
            }

        };

        startReconnect();


        return view;
    }

    private void refreshAdapter(ListView rv) {
        Log.e("TGA","========refreshAdapter==============");

        // 实例化数据适配器并绑定在控件上
        if (adapter == null){
            adapter = new HomeStateListAdapter(this,value);
            rv.setAdapter(adapter);
        }
        else {adapter.notifyDataSetChanged();}

    }


    @Override
    protected View initView() {
        return null;
    }

    private void startReconnect() {
        scheduler = Executors.newSingleThreadScheduledExecutor();
        scheduler.scheduleAtFixedRate(new Runnable() {

            @Override
            public void run() {

                if(!client.isConnected()) {

                    connect();


                }
            }
        }, 0 * 1000, 1 * 1000, TimeUnit.MILLISECONDS);
    }

    private void init() {
        try {
            //host为主机名，test为clientid即连接MQTT的客户端id，id为内存保存形式
            client = new MqttClient(host, "test", new MemoryPersistence());
            //MQTT的连接设置
            options = new MqttConnectOptions();
            //设置是否清空session
            options.setCleanSession(true);
            // 设置超时时间
            options.setConnectionTimeout(10);
            // 设置会话心跳时间
            options.setKeepAliveInterval(20);
            client.setCallback(new MqttCallback() {

                @Override
                public void connectionLost(Throwable cause) {
                    //连接丢失后，重连
                    System.out.println("connectionLost----------");
                }

                @Override
                public void deliveryComplete(IMqttDeliveryToken token) {
                    //publish
                    System.out.println("deliveryComplete---------"
                            + token.isComplete());
                }

                @Override
                public void messageArrived(String topicName, MqttMessage message)
                        throws Exception {
                    //subscribe
                    System.out.println("messageArrived----------");
                    Message msg = new Message();
                    msg.what = 1;
                    msg.obj = topicName+":"+message.toString();
                    handler.sendMessage(msg);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void connect() {
        new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    client.connect(options);
                    Message msg = new Message();
                    msg.what = 2;
                    handler.sendMessage(msg);
                } catch (Exception e) {
                    e.printStackTrace();
                    Message msg = new Message();
                    msg.what = 3;
                    handler.sendMessage(msg);
                }
            }
        }).start();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        Log.e("TGA","========onkeyDown========");
        if(client != null && keyCode == KeyEvent.KEYCODE_BACK) {
            try {
                client.disconnect();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        try {
            scheduler.shutdown();
            client.disconnect();
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }




}
