package demo.bw.com.jingdong.view.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.facebook.drawee.view.SimpleDraweeView;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import demo.bw.com.jingdong.Api.fristFragmentApi;
import demo.bw.com.jingdong.R;
import demo.bw.com.jingdong.adapter.TjAdapater;
import demo.bw.com.jingdong.bean.fristBean;
import demo.bw.com.jingdong.presenter.fristPresenter;
import demo.bw.com.jingdong.utils.AdDialog;
import demo.bw.com.jingdong.utils.GlideImageLoader;
import demo.bw.com.jingdong.view.activity.SearchActivity;
import demo.bw.com.jingdong.view.activity.SeckillActivity;
import demo.bw.com.jingdong.view.activity.erweima.MipcaActivityCapture;
import demo.bw.com.jingdong.view.activity.erweima.WebViewActivity;

import static android.app.Activity.RESULT_OK;

/**
 * Created by 李岳峰 on 2017/12/1.
 */

public class FirstFragment extends Fragment implements fristFragmentApi {

    @BindView(R.id.fa_viewpage)
    ViewPager vp;
    @BindView(R.id.dimension)
    ImageView dimension;
    @BindView(R.id.sc)
    EditText sc;
    @BindView(R.id.msg)
    ImageView msg;
    Unbinder unbinder;
    @BindView(R.id.fa_banner)
    Banner faBanner;

    @BindView(R.id.fa_ms_img1)
    SimpleDraweeView faMsImg1;
    @BindView(R.id.fa_ms_img2)
    SimpleDraweeView faMsImg2;
    @BindView(R.id.fa_ms_img3)
    SimpleDraweeView faMsImg3;
    @BindView(R.id.fa_ms_img4)
    SimpleDraweeView faMsImg4;
    @BindView(R.id.fa_ms_img5)
    SimpleDraweeView faMsImg5;
    @BindView(R.id.tv_hour)
    TextView tvHour;
    @BindView(R.id.tv_minute)
    TextView tvMinute;
    @BindView(R.id.tv_second)
    TextView tvSecond;
    @BindView(R.id.fa_ms)
    LinearLayout faMs;
    @BindView(R.id.fa_ms_img6)
    SimpleDraweeView faMsImg6;
    @BindView(R.id.fa_ms_img7)
    SimpleDraweeView faMsImg7;
    @BindView(R.id.fa_ms_img8)
    SimpleDraweeView faMsImg8;
    @BindView(R.id.fa_ms_img9)
    SimpleDraweeView faMsImg9;
    @BindView(R.id.fa_ms_logo)
    LinearLayout faMsLogo;
    @BindView(R.id.fa_tj_rv)
    RecyclerView faTjRv;
    @BindView(R.id.flipper)
    ViewFlipper flipper;
    //倒计时
    private long mHour = 02;
    private long mMin = 15;
    private long mSecond = 36;
    private boolean isRun = true;
    private final static int SCANNIN_GREQUEST_CODE = 1;
    private Handler timeHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
                computeTime();
                if (mHour < 10) {
                    tvHour.setText("0" + mHour + "");
                } else {
                    tvHour.setText("0" + mHour + "");
                }
                if (mMin < 10) {
                    tvMinute.setText("0" + mMin + "");
                } else {
                    tvMinute.setText(mMin + "");
                }
                if (mSecond < 10) {
                    tvSecond.setText("0" + mSecond + "");
                } else {
                    tvSecond.setText(mSecond + "");
                }
            }
        }
    };
    //声明
    private List<String> ilist = new ArrayList<>();
    private List<Fragment> flist = new ArrayList<>();
    private F_fragment01 fragment01 = new F_fragment01();
    private F_fragment02 fragment02 = new F_fragment02();
    private List testList;
    private int count;
    private AdDialog dialog2;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_first, container, false);
        unbinder = ButterKnife.bind(this, view);
        faTjRv.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        startRun();
        //秒杀底部图片
        faMsImg1.setImageURI(Uri.parse("http://img3.imgtn.bdimg.com/it/u=2582268618,2692242955&fm=27&gp=0.jpg"));
        faMsImg2.setImageURI(Uri.parse("http://e.hiphotos.baidu.com/image/pic/item/d1160924ab18972b98e0bf1aeccd7b899f510a03.jpg"));
        faMsImg3.setImageURI(Uri.parse("http://img3.yxlady.com/fs/UploadFiles_2682/2015089/20150809125956894.jpg"));
        faMsImg4.setImageURI(Uri.parse("https://img.alicdn.com/bao/uploaded/i2/3040235139/TB1rtZ5hYYI8KJjy0FaXXbAiVXa_!!0-item_pic.jpg_430x430q90.jpg"));
        faMsImg5.setImageURI(Uri.parse("http://img1.imgtn.bdimg.com/it/u=822673230,919626979&fm=27&gp=0.jpg"));
        faMsImg9.setImageURI(Uri.parse("https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=3947195320,4137666202&fm=27&gp=0.jpg"));
        faMsImg6.setImageURI(Uri.parse("https://img.adidas.com.cn/resources/2017/12/6/1512553395625681.jpg?version=000000"));
        faMsImg7.setImageURI(Uri.parse("https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=2001531924,3829790088&fm=27&gp=0.jpg"));
        faMsImg8.setImageURI(Uri.parse("https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=2025475237,3116931282&fm=27&gp=0.jpg"));
        fristPresenter fp = new fristPresenter(this);
        fp.getFristP();
        //viewpager分页滑动
        flist.add(fragment01);
        flist.add(fragment02);
        vp.setAdapter(new FragmentPagerAdapter(getActivity().getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return flist.get(position);
            }

            @Override
            public int getCount() {
                return flist.size();
            }
        });
        vp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
        //京东头条

         BannerHead();
         dialog2 = new AdDialog(getActivity());
        dialog2.show();

        return view;
    }




    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
   //页面的点击的事件
    @OnClick({R.id.dimension, R.id.sc, R.id.msg, R.id.fa_ms, R.id.fa_ms_logo})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.dimension:
                //扫描二维码
                Intent intent3 = new Intent(getActivity(), MipcaActivityCapture.class);
                intent3.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivityForResult(intent3,4);
                break;
            case R.id.sc:
                Intent intent = new Intent(getContext(), SearchActivity.class);
                startActivity(intent);
                break;
            case R.id.msg:
                break;
            case R.id.fa_ms:
                Intent intent1 = new Intent(getContext(), SeckillActivity.class);
                startActivity(intent1);
                break;
            //点击进入秒杀
            case R.id.fa_ms_logo:
                Intent intent2 = new Intent(getContext(), SeckillActivity.class);
                startActivity(intent2);
                break;
        }
    }

    //接受处理后的数据
    @Override
    public void ShowData(fristBean bean) {
        List<fristBean.DataBean> data = bean.getData();
        for (int i = 0; i < data.size(); i++) {
            fristBean.DataBean dataBean = data.get(i);
            ilist.add(dataBean.getIcon());
        }
        faBanner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        faBanner.setImages(ilist);
        //banner设置方法全部调用完毕时最后调用
        faBanner.start();
        //底部推荐
        List<fristBean.TuijianBean.ListBean> list = bean.getTuijian().getList();
        TjAdapater tjAdapater = new TjAdapater(getActivity(), list);
        faTjRv.setAdapter(tjAdapater);
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
    //京东头条
    private void BannerHead() {
        testList = new ArrayList();
        testList.add(0, "爸妈爱的“白”娃娃，真是孕期吃出来的吗？");
        testList.add(1, "如果徒步真的需要理由，十四个够不够？");
        testList.add(2, "享受清爽啤酒的同时，这些常识你真的了解吗？");
        testList.add(3, "三星Galaxy S8定型图无悬念");
        testList.add(4, "家猫为何如此高冷？");
        count = testList.size();
        for (int i = 0; i < count; i++) {
            final View ll_content = View.inflate(getContext(), R.layout.item_flipper, null);
            TextView tv_content = (TextView) ll_content.findViewById(R.id.tv_content);
            ImageView iv_closebreak = (ImageView) ll_content.findViewById(R.id.iv_closebreak);
            tv_content.setText(testList.get(i).toString());
            iv_closebreak.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //对当前显示的视图进行移除
                    flipper.removeView(ll_content);
                    count--;
                    //当删除后仅剩 一条 新闻时，则取消滚动
                    if (count == 1) {
                        flipper.stopFlipping();
                    }
                }
            });
            flipper.addView(ll_content);
        }
    }
    //二维码返回结果


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == SCANNIN_GREQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                String result = data.getStringExtra("result");
                Log.i("xxx", result.toString());
                Intent intent = new Intent(getActivity(), WebViewActivity.class);
                intent.putExtra("url", result);
                startActivity(intent);
            }
        }
    }
}


