package demo.bw.com.jingdong.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ExpandableListView;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import demo.bw.com.jingdong.Api.ShopFragmentApi;
import demo.bw.com.jingdong.R;
import demo.bw.com.jingdong.bean.CartBean;

/**
 * Created by 李岳峰 on 2017/12/1.
 */

public class ShopFragment extends Fragment implements ShopFragmentApi {


    @BindView(R.id.fs_expand)
    ExpandableListView fsExpand;
    @BindView(R.id.fs_qx)
    CheckBox fsQx;
    @BindView(R.id.fs_price)
    TextView fsPrice;
    @BindView(R.id.fs_num)
    TextView fsNum;
    Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shop, container, false);

        unbinder = ButterKnife.bind(this, view);
        return view;
    }


    @Override
    public void getShop(List<CartBean.DataBean> grouplist, List<List<CartBean.DataBean.ListBean>> childlist) {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.fs_num)
    public void onViewClicked() {

    }
}
