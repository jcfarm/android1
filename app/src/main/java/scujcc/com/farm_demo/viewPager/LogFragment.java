package scujcc.com.farm_demo.viewPager;

import android.app.Fragment;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import scujcc.com.farm_demo.R;
import scujcc.com.farm_demo.base.BaseFragment;


public class LogFragment extends BaseFragment {


    @RequiresApi(api = Build.VERSION_CODES.GINGERBREAD)
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.log_fragment,container,false);

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.log_recycler_view);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),1));
        recyclerView.setAdapter(new LogMyAdapter());
        return view;
    }

    @Override
    protected View initView() {
        return null;
    }


    public class LogMyAdapter extends RecyclerView.Adapter<LogMyAdapter.LogHoder> {
        @Override
        public LogHoder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(getActivity());
            View view =  inflater.inflate(R.layout.item,parent,false);
            return new LogHoder(view);
        }

        @Override
        public void onBindViewHolder(LogHoder holder, int position) {

        }

        @Override
        public int getItemCount() {
            return 10;
        }

        class LogHoder extends RecyclerView.ViewHolder{
            public LogHoder(View itemView) {
                super(itemView);
            }
        }
    }

}
