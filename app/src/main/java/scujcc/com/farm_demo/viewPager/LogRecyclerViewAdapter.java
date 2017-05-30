package scujcc.com.farm_demo.viewPager;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import scujcc.com.farm_demo.R;
import scujcc.com.farm_demo.bean.FiledsBean;
import scujcc.com.farm_demo.bean.LogBean;


/**
 * Created by hello-brothers on 2017/5/29.
 */

public class LogRecyclerViewAdapter extends RecyclerView.Adapter<LogRecyclerViewAdapter.LogTextViewHolder> {
    private final LayoutInflater mLayoutInflater;
    private final Context mContext;
    private String[] mTitles;
    private final List<LogBean.ItemLogBean> mdatas;

    public LogRecyclerViewAdapter(Context context, List<LogBean.ItemLogBean> datas) {
        mTitles = context.getResources().getStringArray(R.array.titles);
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
        mdatas = datas;
    }

    @Override
    public LogRecyclerViewAdapter.LogTextViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new LogRecyclerViewAdapter.LogTextViewHolder(mLayoutInflater.inflate(R.layout.log_item, parent, false));
    }

    @Override
    public void onBindViewHolder(LogRecyclerViewAdapter.LogTextViewHolder holder, int position) {
//        holder.mTextView_Log_rough.setText("编号："+mdatas.get(position).getId()+"号"+"\n"+"\n"+"温度："+mdatas.get(position).getTemp()+"摄氏度");
//        holder.mTextView_id.setText((position+1)+"号田");
        holder.log_Target.setText("对象："+mdatas.get(position).getTarget());
        holder.log_Type.setText("类型："+mdatas.get(position).getAction().getType());
        holder.log_commond.setText("命令："+mdatas.get(position).getAction().getCommond());
        holder.log_time.setText("时间："+mdatas.get(position).getTime());
        holder.log_sender.setText("发布者："+mdatas.get(position).getSender());
    }

    @Override
    public int getItemCount() {
        return mTitles == null ? 0 : mdatas.size();
    }

    public class LogTextViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.log_target)
        TextView log_Target;
        @Bind(R.id.log_type)
        TextView log_Type;
        @Bind(R.id.log_commond)
        TextView log_commond;
        @Bind(R.id.log_time)
        TextView log_time;
        @Bind(R.id.log_sender)
        TextView log_sender;

        LogTextViewHolder(View view) {
            super(view);
            ButterKnife.bind(this,view);
        }

        @OnClick(R.id.log_card)
        void onItemClick() {
            Log.d("LogTextViewHolder", "onClick--> position = " + getPosition());
//            Intent intent = new Intent(mContext, LogDetails.class);
//            intent.putExtra("Log_Farm_Id", getPosition()+1);
//            mContext.startActivity(intent);
        }
    }
}
