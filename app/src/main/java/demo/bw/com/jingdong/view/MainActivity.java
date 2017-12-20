package demo.bw.com.jingdong.view;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.hjm.bottomtabbar.BottomTabBar;
import com.zhy.autolayout.AutoLayoutActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import demo.bw.com.jingdong.R;
import demo.bw.com.jingdong.view.fragment.ClassFragment;
import demo.bw.com.jingdong.view.fragment.FindFragment;
import demo.bw.com.jingdong.view.fragment.FirstFragment;
import demo.bw.com.jingdong.view.fragment.MineFragment;
import demo.bw.com.jingdong.view.fragment.ShopFragment;

public class MainActivity extends AutoLayoutActivity{

    @BindView(R.id.bottom_tab_bar)
    BottomTabBar bottomTabBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        bottomTabBar.init(getSupportFragmentManager())
                .setImgSize(100,100)
                .setFontSize(0)
                .setTabPadding(1,3,2)
                .setChangeColor(Color.RED,Color.DKGRAY)
                .addTabItem(null,R.mipmap.ac0, FirstFragment.class)
                .addTabItem(null,R.mipmap.abw, ClassFragment.class)
                .addTabItem(null,R.mipmap.aby, FindFragment.class)
                .addTabItem(null,R.mipmap.abu, ShopFragment.class)
                .addTabItem(null,R.mipmap.ac2, MineFragment.class)
                .isShowDivider(false)
                .setOnTabChangeListener(new BottomTabBar.OnTabChangeListener() {
                    @Override
                    public void onTabChange(int position, String name) {

                    }
                });
        Intent intent = getIntent();
        int flag = intent.getIntExtra("flag", -2);
        if(flag>0){
            Toast.makeText(this,""+flag,Toast.LENGTH_SHORT).show();
        }
    }
}
