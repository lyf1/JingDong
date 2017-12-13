package demo.bw.com.jingdong.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import demo.bw.com.jingdong.R;

/**
 * Created by 李岳峰 on 2017/12/13.
 */

public class F_fragment01 extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.f_fragment1,container,false);
        return view;
    }
}
