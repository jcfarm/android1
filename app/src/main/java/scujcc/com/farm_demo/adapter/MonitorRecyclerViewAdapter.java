package scujcc.com.farm_demo.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import scujcc.com.farm_demo.R;
import scujcc.com.farm_demo.viewPager.monitor.MonitorDetails;

/**
 * Created by hello-brothers on 2017/5/4.
 * 监控recylerview的适配器
 */

public class MonitorRecyclerViewAdapter extends RecyclerView.Adapter<MonitorRecyclerViewAdapter.MonitorTextViewHolder> {
    private final LayoutInflater mLayoutInflater;
    private final Context mContext;
    private String[] mTitles;

    public MonitorRecyclerViewAdapter(Context context) {
        mTitles = context.getResources().getStringArray(R.array.titles);
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public MonitorTextViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MonitorTextViewHolder(mLayoutInflater.inflate(R.layout.monitor_item, parent, false));
    }

    @Override
    public void onBindViewHolder(MonitorTextViewHolder holder, int position) {
        holder.mTextView.setText("编号："+(position+1)+"号");
    }

    @Override
    public int getItemCount() {
        return mTitles == null ? 0 : mTitles.length;
    }

    public class MonitorTextViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.monitor_farm_id)
        TextView mTextView;

        MonitorTextViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        @OnClick(R.id.mo_item)
        void onItemClick() {
            Log.d("MonitorTextViewHolder", "onClick--> position = " + getPosition());
            Intent intent = new Intent(mContext, MonitorDetails.class);
            intent.putExtra("Position", getPosition()+1);
            mContext.startActivity(intent);
        }
    }
}
