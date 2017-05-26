package scujcc.com.farm_demo.viewPager.monitor;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import butterknife.Bind;
import scujcc.com.farm_demo.R;

/**
 * Created by hello-brothers on 2017/5/8.
 */

public class MonitorDetails extends AppCompatActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.monitor_details);
        Intent intent = getIntent();
        int position= (int) intent.getSerializableExtra("Position");
        TextView textView = (TextView) findViewById(R.id.monitor_id);
        textView.setText("摄像头"+position+"号");

    }
}
