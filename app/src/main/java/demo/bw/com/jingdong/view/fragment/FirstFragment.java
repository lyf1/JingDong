package demo.bw.com.jingdong.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import demo.bw.com.jingdong.Api.fristFragmentApi;
import demo.bw.com.jingdong.R;
import demo.bw.com.jingdong.bean.fristBean;
import demo.bw.com.jingdong.presenter.fristPresenter;
import demo.bw.com.jingdong.utils.GlideImageLoader;
import demo.bw.com.jingdong.view.activity.SearchActivity;

/**
 * Created by 李岳峰 on 2017/12/1.
 */

public class FirstFragment extends Fragment implements fristFragmentApi {

    @BindView(R.id.fa_viewpage)
    ViewPager view;
    private ViewPager pager;
    @BindView(R.id.dimension)
    ImageView dimension;
    @BindView(R.id.sc)
    EditText sc;
    @BindView(R.id.msg)
    ImageView msg;
    Unbinder unbinder;
    @BindView(R.id.fa_banner)
    Banner faBanner;
    private List<String> ilist = new ArrayList<>();
    private List<Fragment> flist = new ArrayList<>();
    private F_fragment01 fragment01 = new F_fragment01();
    private F_fragment02 fragment02 = new F_fragment02();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_first, container, false);
        unbinder = ButterKnife.bind(this, view);
        fristPresenter fp = new fristPresenter(this);
        fp.getFristP();
        flist.add(fragment01);
        flist.add(fragment02);

        return view;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.dimension, R.id.sc, R.id.msg})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.dimension:
                break;
            case R.id.sc:
                Intent intent = new Intent(getContext(), SearchActivity.class);
                startActivity(intent);
                break;
            case R.id.msg:
                break;
        }
    }

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
    }

}
