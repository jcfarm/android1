package scujcc.com.farm_demo.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import scujcc.com.farm_demo.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import scujcc.com.farm_demo.viewPager.home.HomeDetails;
import scujcc.com.farm_demo.viewPager.monitor.MonitorDetails;

/**
 * 主页recylerview的适配器
 **/

public class HomeRecyclerViewAdapter extends RecyclerView.Adapter<HomeRecyclerViewAdapter.HomeTextViewHolder> {
    private final LayoutInflater mLayoutInflater;
    private final Context mContext;
    private String[] mTitles;

    public HomeRecyclerViewAdapter(Context context) {
        mTitles = context.getResources().getStringArray(R.array.titles);
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public HomeTextViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new HomeTextViewHolder(mLayoutInflater.inflate(R.layout.item_image, parent, false));
    }

    @Override
    public void onBindViewHolder(HomeTextViewHolder holder, int position) {
        holder.mTextView_home_rough.setText("编号："+(position+1)+"号"+"\n"+"温度："+mTitles[position]+"\n"+"湿度："+mTitles[position]);
        holder.mTextView_id.setText((position+1)+"号田");
        holder.mTextView_time.setText((position+1)+"分钟前操作");
    }

    @Override
    public int getItemCount() {
        return mTitles == null ? 0 : mTitles.length;
    }

    public class HomeTextViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.image_text_view)
        TextView mTextView_home_rough;
        @Bind(R.id.item_image_farm_id)
                TextView mTextView_id;
        @Bind(R.id.item_image_farm_time)
                TextView mTextView_time;

        HomeTextViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        @OnClick(R.id.cv_item)
        void onItemClick() {
            Log.d("HomeTextViewHolder", "onClick--> position = " + getPosition());
            Intent intent = new Intent(mContext, HomeDetails.class);
            intent.putExtra("home_Farm_Id", getPosition()+1);
            mContext.startActivity(intent);
        }
    }
}
