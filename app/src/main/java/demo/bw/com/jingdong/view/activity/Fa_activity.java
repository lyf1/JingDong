package demo.bw.com.jingdong.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import demo.bw.com.jingdong.R;

public class Fa_activity extends AppCompatActivity {

    @BindView(R.id.fa_c_back)
    ImageView faCBack;
    @BindView(R.id.fa_c_title)
    TextView faCTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fa_activity);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        faCTitle.setText(name);
    }

    @OnClick(R.id.fa_c_back)
    public void onViewClicked() {
        finish();
    }
}
