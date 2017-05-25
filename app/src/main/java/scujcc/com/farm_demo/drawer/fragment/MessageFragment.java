package scujcc.com.farm_demo.drawer.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import scujcc.com.farm_demo.MainActivity;
import scujcc.com.farm_demo.Msg;
import scujcc.com.farm_demo.MsgAdapter;
import scujcc.com.farm_demo.R;

/**
 * Created by hello-brothers on 2017/5/3.
 * drawer侧边栏消息的fragment
 */

public class MessageFragment extends Fragment {
    private ListView msgListView;
    private EditText inputText;
    private Button send;
    private MsgAdapter adapter;
    private List<Msg> msgList=new ArrayList<Msg>();



    public MessageFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_message_from, container, false);



        initMsgs(); //初始化消息数据
//        adapter=new MsgAdapter(this,R.layout.msg_item,msgList);
        inputText=(EditText)rootView.findViewById(R.id.input_text);
        send=(Button)rootView.findViewById(R.id.send);
        msgListView=(ListView)rootView.findViewById(R.id.msg_list_view);
        msgListView.setAdapter(adapter);
        send.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                String content=inputText.getText().toString();
                if(!"".equals(content))
                {
                    Msg msg=new Msg(content,Msg.TYPE_SENT);
                    msgList.add(msg);
                    adapter.notifyDataSetChanged();  //当有新消息时，刷新ListView中的显示
                    msgListView.setSelection(msgList.size()); //将ListView定位到最后一行
                    inputText.setText(""); //清空输入框的内容


                }
            }
        });
        // Inflate the layout for this fragment
        return rootView;
    }


//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
////        getMenuInflater().inflate(R.menu.main, menu);
//        return true;
//    }
    private void initMsgs(){
        Msg msg1=new Msg("Hello guy",Msg.TYPE_RECEIVED);
        msgList.add(msg1);
        Msg msg2=new Msg("Hello ,who is that",Msg.TYPE_SENT);
        msgList.add(msg2);
        Msg msg3=new Msg("This is Tom,Nice talking to you",Msg.TYPE_RECEIVED);
        msgList.add(msg3);

    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


}
