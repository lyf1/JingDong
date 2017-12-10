package demo.bw.com.jingdong.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import demo.bw.com.jingdong.Api.ClassFragmentApi;
import demo.bw.com.jingdong.Api.OnClickApi;
import demo.bw.com.jingdong.Api.fristFragmentApi;
import demo.bw.com.jingdong.R;
import demo.bw.com.jingdong.adapter.C_Right_Adapter;
import demo.bw.com.jingdong.adapter.C_catagory_Adapter;
import demo.bw.com.jingdong.bean.CatagoryBean;
import demo.bw.com.jingdong.bean.ProductCatagoryBean;
import demo.bw.com.jingdong.bean.fristBean;
import demo.bw.com.jingdong.presenter.CotagortyP;
import demo.bw.com.jingdong.presenter.ProductCatagoryP;
import demo.bw.com.jingdong.utils.GlideImageLoader;
import demo.bw.com.jingdong.view.activity.C_childActivity;

/**
 * Created by 李岳峰 on 2017/12/1.
 */

public class ClassFragment extends Fragment implements ClassFragmentApi,OnClickApi {
    @BindView(R.id.c_rv)
    RecyclerView cRv;
    @BindView(R.id.c_banner)
    Banner cBanner;
    @BindView(R.id.c_expand)
    ExpandableListView cExpand;
    Unbinder unbinder;
    private List<String> ilist=new ArrayList<>();
    private String cid="1";
    private ProductCatagoryP catagoryPp;
    private C_catagory_Adapter catagory_adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_class, container, false);
        unbinder = ButterKnife.bind(this, view);
        cRv.setLayoutManager(new LinearLayoutManager(getActivity()));
        CotagortyP cotagortyP = new CotagortyP(this);
        cotagortyP.getCotagory();
        ilist.add("https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=807055029,3341205176&fm=27&gp=0.jpg");
        ilist.add("https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=466531044,4070525749&fm=27&gp=0.jpg");
        ilist.add("http://img1.imgtn.bdimg.com/it/u=980240160,1262175739&fm=27&gp=0.jpg");
        ilist.add("http://img4.imgtn.bdimg.com/it/u=2144844902,1660255202&fm=200&gp=0.jpg");
        cBanner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        cBanner.setImages(ilist);
        //banner设置方法全部调用完毕时最后调用
        cBanner.start();
        catagoryPp = new ProductCatagoryP(this);
        catagoryPp.getProduct(cid);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void ctagoryData(List<CatagoryBean.DataBean> list) {
        catagory_adapter = new C_catagory_Adapter(list,getActivity(),this);
        cRv.setAdapter(catagory_adapter);
    }

    @Override
    public void productCatagoryData(List<ProductCatagoryBean.DataBean> grouplist, List<List<ProductCatagoryBean.DataBean.ListBean>> childlist) {
        C_Right_Adapter adapter=new C_Right_Adapter(grouplist,childlist,getActivity());
        cExpand.setAdapter(adapter);
        cExpand.setGroupIndicator(null);
        for(int i=0;i<grouplist.size();i++){
            cExpand.expandGroup(i);
        }
    }


    @Override
    public void setOnClickListener(String id) {
        catagoryPp.getProduct(id);
        catagory_adapter.notifyDataSetChanged();
    }


}
