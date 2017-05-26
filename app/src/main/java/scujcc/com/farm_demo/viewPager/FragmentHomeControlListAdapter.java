package scujcc.com.farm_demo.viewPager;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

import scujcc.com.farm_demo.R;
import scujcc.com.farm_demo.viewPager.control.BaseControlActivity;


/**
 * Created by hello-brothers on 2017/5/21.
 */

public class FragmentHomeControlListAdapter extends BaseAdapter {
    private final Context mContext;
    LayoutInflater inflater;
    ArrayList<HashMap<String, Object>> ls;
    private String[] imgs =null;
    private String[] contents =null;
    ImageView img;
    TextView content;

    public FragmentHomeControlListAdapter(Context context, ArrayList<HashMap<String, Object>> runRank) {
        Log.e("TGA","FragmentHomeControlListAdapter");
        mContext = context;
        ls = runRank;
        imgs = context.getResources().getStringArray(R.array.home_control_img);
        contents = context.getResources().getStringArray(R.array.home_control_content);
    }

    @Override
    public int getCount() {
        return ls.size();
    }

    @Override
    public Object getItem(int position) {
        return ls.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        if (convertView == null)
        {
            inflater = LayoutInflater.from(parent.getContext());
            convertView = inflater.inflate(R.layout.fragment_home_control_item,
                    parent, false);

            img = (ImageView) convertView.findViewById(R.id.fragment_home_control_img);
            content = (TextView) convertView.findViewById(R.id.fragment_home_control_content);




            img.setImageResource(R.drawable.jkx);
            content.setText(contents[position]);

            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(v.getContext(), "position"+position, Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(mContext, BaseControlActivity.class);
                    intent.putExtra("Position", position);
                    mContext.startActivity(intent);

                }
            });
        }
        return convertView;
    }

}
