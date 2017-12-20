package demo.bw.com.jingdong.view.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import demo.bw.com.jingdong.Api.fristFragmentApi;
import demo.bw.com.jingdong.R;
import demo.bw.com.jingdong.adapter.SeckllAdapter;
import demo.bw.com.jingdong.bean.fristBean;
import demo.bw.com.jingdong.presenter.fristPresenter;

/**
 * 秒杀 展示秒杀数据
 */
public class SeckillActivity extends AppCompatActivity implements fristFragmentApi {

    @BindView(R.id.s_back)
    ImageView sBack;
    @BindView(R.id.s_hour)
    TextView sHour;
    @BindView(R.id.s_minute)
    TextView sMinute;
    @BindView(R.id.s_second)
    TextView sSecond;
    @BindView(R.id.s_rv)
    RecyclerView sRv;

    private long mHour = 02;
    private long mMin = 15;
    private long mSecond = 36;
    private boolean isRun = true;

    private Handler timeHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what==1) {
                computeTime();
                if (mHour<10){
                    sHour.setText("0"+mHour+"");
                }else {
                    sHour.setText("0"+mHour+"");
                }
                if (mMin<10){
                    sMinute.setText("0"+mMin+"");
                }else {
                    sMinute.setText(mMin+"");
                }
                if (mSecond<10){
                    sSecond.setText("0"+mSecond+"");
                }else {
                    sSecond.setText(mSecond+"");
                }
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seckill);
        startRun();
        ButterKnife.bind(this);
        sRv.setLayoutManager(new LinearLayoutManager(this));
        fristPresenter fp = new fristPresenter(this);
        fp.getFristP();
    }

    @Override
    public void ShowData(fristBean bean) {
        List<fristBean.MiaoshaBean.ListBeanX> list = bean.getMiaosha().getList();
        SeckllAdapter seckllAdapter=new SeckllAdapter(this,list);
        sRv.setAdapter(seckllAdapter);
    }

    @OnClick(R.id.s_back)
    public void onViewClicked() {
        SeckillActivity.this.finish();
    }
    private void startRun() {
        new Thread(new Runnable() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                while (isRun) {
                    try {
                        Thread.sleep(1000); // sleep 1000ms
                        Message message = Message.obtain();
                        message.what = 1;
                        timeHandler.sendMessage(message);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    /**
     * 倒计时计算
     */
    private void computeTime() {
        mSecond--;
        if (mSecond < 0) {
            mMin--;
            mSecond = 59;
            if (mMin < 0) {
                mMin = 59;
                mHour--;
            }
        }
    }
}
