package demo.bw.com.jingdong.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import demo.bw.com.jingdong.R;
import demo.bw.com.jingdong.view.activity.LoginActivity;
import demo.bw.com.jingdong.view.activity.SettingActivity;

/**
 * Created by 李岳峰 on 2017/12/1.
 */

public class MineFragment extends Fragment {
    @BindView(R.id.fm_lor)
    TextView fmLor;
    @BindView(R.id.fm_set)
    ImageView fmSet;
    Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mine, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.fm_lor, R.id.fm_set})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fm_lor:
                startActivityForResult(new Intent(getActivity(),
                        LoginActivity.class), 1);
                break;
            case R.id.fm_set:
                Intent intent=new Intent(getActivity(), SettingActivity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1){
            Bundle bundle = data.getExtras();
            String account = bundle.getString("account");
            fmLor.setText(account);
        }else if(resultCode==2){
            Bundle bundle = data.getExtras();
            String account = bundle.getString("account");
            fmLor.setText(account);
        }

    }
}
