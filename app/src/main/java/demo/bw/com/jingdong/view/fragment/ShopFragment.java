package demo.bw.com.jingdong.view.fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
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
import demo.bw.com.jingdong.view.MainActivity;
import demo.bw.com.jingdong.view.activity.AplayActivity;
import demo.bw.com.jingdong.view.activity.LoginActivity;

import static android.content.Context.MODE_PRIVATE;

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
    private GetCartsPersenter getCartsPersenter;
    private double price;
    private boolean istrue;


    @Override
    public void onResume() {
        super.onResume();
        getCartsPersenter = new GetCartsPersenter(this);
        getCartsPersenter.getCarts();

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shop, container, false);

        unbinder = ButterKnife.bind(this, view);
        SharedPreferences message = getActivity().getSharedPreferences("Message", MODE_PRIVATE);
        istrue = message.getBoolean("istrue", false);
        if(istrue==false) {
            Intent intent = new Intent(getActivity(), LoginActivity.class);
            startActivity(intent);
        }



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
    //删除成功或失败返回数据
    @Override
    public void showData(String str) {
        Toast.makeText(getActivity(),str, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    //返回价钱和数量
    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void onMessage(EventCount eventCount) {
        price = eventCount.getPrice();
        fsPrice.setText(this.price + "");
        fsNum.setText("去结算(" + eventCount.getNum() + ")");
    }
    //反选如果子类全选就选中
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
                //点击删除 判断 非空 如果非就弹框进行删除
                final List<String> pidlist = adapter.getPid();
                final DeleteCartPresenter deleteCartPresenter = new DeleteCartPresenter(this);
                if(pidlist.size()==0||pidlist.isEmpty()){
                     Toast.makeText(getActivity(),"请选中,在删除",Toast.LENGTH_SHORT).show();
                }else{
                    final AlertDialog.Builder normalDialog = new AlertDialog.Builder(getActivity());
                    normalDialog.setTitle("删除");
                    normalDialog.setMessage("确定要删除吗？");
                    normalDialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            adapter.delSelect();

                            for(int i=0;i<pidlist.size();i++){
                                String s = pidlist.get(i);
                                deleteCartPresenter.getDelete(s);
                            }
                        }
                    });
                    normalDialog.setNegativeButton("取消",null);
                    normalDialog.show();
                }
                break;
            case R.id.fs_num:
                Intent intent1=new Intent(getActivity(),AplayActivity.class);
                intent1.putExtra("price",price);
                startActivity(intent1);
                break;
        }
    }
}
