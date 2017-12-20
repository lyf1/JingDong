package demo.bw.com.jingdong.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zhy.autolayout.AutoLayoutActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import demo.bw.com.jingdong.R;

public class SettingActivity extends AutoLayoutActivity {

    @BindView(R.id.as_back)
    ImageView asBack;
    @BindView(R.id.as_mangeraddress)
    RelativeLayout asMangeraddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.as_back, R.id.as_mangeraddress})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.as_back:
                SettingActivity.this.finish();
                break;
            case R.id.as_mangeraddress:
               Intent intent=new Intent(this, AddressActivity.class);
                startActivity(intent);
                break;
        }
    }
}
