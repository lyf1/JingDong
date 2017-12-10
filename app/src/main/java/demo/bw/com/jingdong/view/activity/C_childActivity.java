package demo.bw.com.jingdong.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import demo.bw.com.jingdong.Api.OnListenerClickApi;
import demo.bw.com.jingdong.Api.childActivityApi;
import demo.bw.com.jingdong.R;
import demo.bw.com.jingdong.adapter.ProducrtsAdapter;
import demo.bw.com.jingdong.bean.c_childBean;
import demo.bw.com.jingdong.presenter.ProductsPresenter;

public class C_childActivity extends AppCompatActivity implements childActivityApi, OnListenerClickApi {

    @BindView(R.id.p_rv)
    RecyclerView pRv;
    @BindView(R.id.p_back)
    ImageView pBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_c_child2);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        String pscid = intent.getStringExtra("pscid");
        pRv.setLayoutManager(new GridLayoutManager(this, 2));
        ProductsPresenter productsPresenter = new ProductsPresenter((childActivityApi) this);
        productsPresenter.getProducts(pscid, "1");
         pBack.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 finish();
             }
         });
    }

    @Override
    public void onProducts(List<c_childBean.DataBean> list) {
        ProducrtsAdapter producrtsAdapter = new ProducrtsAdapter(this, list, this);
        pRv.setAdapter(producrtsAdapter);
    }

    @Override
    public void setOnClieck(String id) {
        Intent intent = new Intent(this, ProductsActivity.class);
        intent.putExtra("pid", id);
        startActivity(intent);
    }
}
