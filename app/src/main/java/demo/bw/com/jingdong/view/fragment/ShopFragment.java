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
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import demo.bw.com.jingdong.Api.ShopFragmentApi;
import demo.bw.com.jingdong.R;
import demo.bw.com.jingdong.adapter.ShopAdapter;
import demo.bw.com.jingdong.bean.CartBean;
import demo.bw.com.jingdong.presenter.DeleteCartPresenter;
import demo.bw.com.jingdong.presenter.GetCartsPersenter;
import demo.bw.com.jingdong.utils.EventCheck;
import demo.bw.com.jingdong.utils.EventCount;

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
    @BindView(R.id.fs_del)
    TextView fsDel;
    private ShopAdapter adapter;
    private int pid;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shop, container, false);

        unbinder = ButterKnife.bind(this, view);
        GetCartsPersenter getCartsPersenter = new GetCartsPersenter(this);
        getCartsPersenter.getCarts();
        fsQx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.changeAll(fsQx.isChecked());

            }
        });
        return view;
    }


    @Override
    public void getShop(List<CartBean.DataBean> grouplist, List<List<CartBean.DataBean.ListBean>> childlist) {
        adapter = new ShopAdapter(grouplist, childlist, getActivity());
        fsExpand.setAdapter(adapter);
        for (int i = 0; i < grouplist.size(); i++) {
            fsExpand.expandGroup(i);
        }
    }

    @Override
    public void showData(String str) {
        Toast.makeText(getActivity(),str, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void onMessage(EventCount eventCount) {
        fsPrice.setText(eventCount.getPrice() + "");
        fsNum.setText("去结算(" + eventCount.getNum() + ")");
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void onMessage(EventCheck even) {
        fsQx.setChecked(even.isChecked());
    }



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(getActivity());
    }


    @OnClick({R.id.fs_del, R.id.fs_num})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fs_del:
                List<String>  list = adapter.delSelect();
                DeleteCartPresenter deleteCartPresenter = new DeleteCartPresenter(this);
                for(int i=0;i<list.size();i++){
                    String s = list.get(i);
                    deleteCartPresenter.getDelete(s);
                }
                break;
            case R.id.fs_num:
                break;
        }
    }
}
