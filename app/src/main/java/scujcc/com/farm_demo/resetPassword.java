package scujcc.com.farm_demo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by yang on 17-5-19.
 */

public class resetPassword extends AppCompatActivity{

    private EditText input_once;
    private EditText input_agin;
    //        private TextView username;
//        private TextView phonenumber;
    private Button sure_find;
    int counter = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reset_password);
        input_agin = (EditText) findViewById(R.id.input_phoneNumber);
        input_once = (EditText) findViewById(R.id.input_username);
//            username=(TextView) findViewById(R.id.username);
//            phonenumber=(TextView) findViewById(R.id.phoneNumber);
        sure_find = (Button) findViewById(R.id.suer_find);


        sure_find.setOnClickListener(new Button.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                Intent intent=new Intent(resetPassword.this,LauncherActivity.class);
                startActivity(intent);

            }

        });

    }



}
