package scujcc.com.farm_demo.viewPager.monitor;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import android.widget.VideoView;

import butterknife.Bind;
import scujcc.com.farm_demo.R;

/**
 * Created by hello-brothers on 2017/5/8.
 */

public class MonitorDetails extends AppCompatActivity{

    VideoView videoView;
    private static String url;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.monitor_details);
        Intent intent = getIntent();
        int position= (int) intent.getSerializableExtra("Position");
        url = intent.getSerializableExtra("url").toString();
        TextView streamplayer_text= (TextView)findViewById(R.id.streamplayer);
        streamplayer_text.setText("第"+position+"号田");
        videoView = (VideoView)this.findViewById(R.id.rtsp_player);
        PlayRtspStream(url.toString());
    }

    private void PlayRtspStream(String rtspUrl){
        Log.e("TAG","----PlayRtspStream----"+rtspUrl);
        videoView.setVideoURI(Uri.parse(rtspUrl));
        videoView.requestFocus();
        videoView.start();
    }
}
