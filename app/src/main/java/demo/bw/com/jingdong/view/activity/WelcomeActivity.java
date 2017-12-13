package demo.bw.com.jingdong.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;

import java.util.Timer;
import java.util.TimerTask;

import demo.bw.com.jingdong.R;
import demo.bw.com.jingdong.view.MainActivity;

public class WelcomeActivity extends AppCompatActivity {

    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_welcome);
        intent = new Intent(this,MainActivity.class);
        Timer timer=new Timer();
        TimerTask task=new TimerTask() {
            @Override
            public void run() {
                startActivity(intent);
            }
        };
        timer.schedule(task,3000);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        finish();
    }
}
