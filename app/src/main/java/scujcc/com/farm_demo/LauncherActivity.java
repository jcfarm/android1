package scujcc.com.farm_demo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 启动界面
 */
public class LauncherActivity extends AppCompatActivity {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_launcher);
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                //在主线程中执行
//                startMainActivity();
//            }
//        }, 2000);
//    }
//    //lihao shi 臭嗨
//
//    /**
//     * 启动主页面
//     */
//    private void startMainActivity() {
//        Intent intent = new Intent(this,MainActivity.class);
//        startActivity(intent);
//        //关闭当前页面
//        finish();
//
//    }


    private Button loginButton;
    private TextView findPassword;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                //在主线程中执行
//                startMainActivity();
//            }
//        }, 2000);

        loginButton=(Button) findViewById(R.id.login_button);
        loginButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Toast.makeText(LauncherActivity.this,"login success!",Toast.LENGTH_SHORT).show();
                startMainActivity();

            }
        });

        findPassword=(TextView)findViewById(R.id.findPassword);
        findPassword.setClickable(true);
        findPassword.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){

                intent=new Intent(LauncherActivity.this,resetPassword.class);
                startActivity(intent);
            }
        });

    }
    //try

    /**
     * 启动主页面
     */
    private void startMainActivity() {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
        //关闭当前页面
        finish();

    }

}
