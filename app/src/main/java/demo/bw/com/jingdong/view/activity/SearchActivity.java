package demo.bw.com.jingdong.view.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import demo.bw.com.jingdong.Api.OnListenerClickApi;
import demo.bw.com.jingdong.Api.SearchActivityapi;
import demo.bw.com.jingdong.R;
import demo.bw.com.jingdong.adapter.SearchAdapater;
import demo.bw.com.jingdong.bean.SearchBean;
import demo.bw.com.jingdong.presenter.SearchPersenter;

/**
 * 首页 搜索 展示数据
 */
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
                String name= sSs.getText().toString().trim();
                if(TextUtils.isEmpty(name)){
                    Toast.makeText(this,"输入框不能为空",Toast.LENGTH_SHORT).show();
                }
                else{
                    SearchPersenter searchPersenter = new SearchPersenter((SearchActivityapi) this);
                    searchPersenter.getSearch(name, "1");
                }

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
