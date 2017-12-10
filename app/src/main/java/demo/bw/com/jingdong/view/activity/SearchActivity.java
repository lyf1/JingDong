package demo.bw.com.jingdong.view.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import demo.bw.com.jingdong.Api.OnListenerClickApi;
import demo.bw.com.jingdong.Api.SearchActivityapi;
import demo.bw.com.jingdong.R;
import demo.bw.com.jingdong.adapter.SearchAdapater;
import demo.bw.com.jingdong.bean.SearchBean;
import demo.bw.com.jingdong.presenter.SearchPersenter;

public class SearchActivity extends Activity implements SearchActivityapi,OnListenerClickApi {

    @BindView(R.id.s_back)
    ImageView sBack;
    @BindView(R.id.s_ss)
    EditText sSs;
    @BindView(R.id.s_bss)
    TextView sBss;
    @BindView(R.id.s_rv)
    RecyclerView sRv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);
        sRv.setLayoutManager(new GridLayoutManager(this,2));
    }

    @OnClick({R.id.s_back, R.id.s_bss})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.s_back:
                finish();
                break;
            case R.id.s_bss:
                SearchPersenter searchPersenter = new SearchPersenter((SearchActivityapi) this);
                searchPersenter.getSearch(sSs.getText().toString().trim(), "1");
                break;
        }
    }

    @Override
    public void showData(List<SearchBean.DataBean> list) {
        SearchAdapater searchAdapater=new SearchAdapater(this,list, (OnListenerClickApi) this);
        sRv.setAdapter(searchAdapater);
    }

    @Override
    public void setOnClieck(String id) {
        Intent intent=new Intent(SearchActivity.this,SearchDetailActivity.class);
        intent.putExtra("pid",id);
        startActivity(intent);
    }
}
