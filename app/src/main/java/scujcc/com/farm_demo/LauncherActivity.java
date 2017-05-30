package scujcc.com.farm_demo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

/**
 * 启动界面
 */
public class LauncherActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //在主线程中执行
                startMainActivity();
            }
        }, 2000);
    }
    //lihao shi 臭嗨

    /**
     * 启动主页面
     */
    private void startMainActivity() {
        Intent intent = new Intent(this,Login.class);
        startActivity(intent);
        //关闭当前页面
        finish();

    }
}
