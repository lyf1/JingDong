package demo.bw.com.jingdong.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import demo.bw.com.jingdong.Api.ClassFragmentApi;
import demo.bw.com.jingdong.Api.OnClickApi;
import demo.bw.com.jingdong.R;
import demo.bw.com.jingdong.adapter.fa_view_Adapter;
import demo.bw.com.jingdong.bean.CatagoryBean;
import demo.bw.com.jingdong.bean.ProductCatagoryBean;
import demo.bw.com.jingdong.presenter.CotagortyP;

/**
 * Created by 李岳峰 on 2017/12/13.
 */

public class F_fragment01 extends Fragment implements ClassFragmentApi{
    @BindView(R.id.fa_view_rv1)
    RecyclerView faViewRv1;
    Unbinder unbinder;
    private List<CatagoryBean.DataBean> datalist=new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.f_fragment1, container, false);
        unbinder = ButterKnife.bind(this, view);
        faViewRv1.setLayoutManager(new GridLayoutManager(getActivity(),5));
        CotagortyP cotagortyP = new CotagortyP((ClassFragmentApi) this);
        cotagortyP.getCotagory();

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void ctagoryData(List<CatagoryBean.DataBean> list) {
     for(int i=0;i<10;i++){
           datalist.add(list.get(i));
        }
        fa_view_Adapter fa_view_adapter = new fa_view_Adapter(datalist, getActivity());
         faViewRv1.setAdapter(fa_view_adapter);
    }


    @Override
    public void productCatagoryData(List<ProductCatagoryBean.DataBean> grouplist, List<List<ProductCatagoryBean.DataBean.ListBean>> childlist) {

    }
}
