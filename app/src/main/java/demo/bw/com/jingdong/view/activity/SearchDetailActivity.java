package demo.bw.com.jingdong.view.activity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import demo.bw.com.jingdong.Api.SearchDetailActivityApi;
import demo.bw.com.jingdong.R;
import demo.bw.com.jingdong.bean.SearchDetailBean;
import demo.bw.com.jingdong.presenter.AddCartPresenter;
import demo.bw.com.jingdong.presenter.SearchDetailPresenter;
import demo.bw.com.jingdong.view.MainActivity;

public class SearchDetailActivity extends Activity implements SearchDetailActivityApi {

    @BindView(R.id.sd_img)
    SimpleDraweeView sdImg;
    @BindView(R.id.sd_title)
    TextView sdTitle;
    @BindView(R.id.sd_price)
    TextView sdPrice;
    @BindView(R.id.sd_addcart)
    TextView sdAddcart;
    @BindView(R.id.jump_cart)
    TextView j_Cart;

    private String pid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_detail);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        String id = intent.getStringExtra("pid");
        pid = id;

        SearchDetailPresenter sp = new SearchDetailPresenter(this);
        sp.getSearchDetail(id);
    }

    @Override
    public void showData(SearchDetailBean.DataBean list) {
        String[] split = list.getImages().split("\\|");
        String s = split[0];
        Uri uri = Uri.parse(s);
        sdImg.setImageURI(uri);
        sdTitle.setText(list.getTitle());
        sdPrice.setText("￥" + list.getPrice());

    }

    @Override
    public void btnData(String str) {
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
    }


    @OnClick({R.id.jump_cart, R.id.sd_addcart})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.jump_cart:

                Intent intent=new Intent(SearchDetailActivity.this, MainActivity.class);
                intent.putExtra("flag",1);
                startActivity(intent);
                finish();
                break;
            case R.id.sd_addcart:

                AddCartPresenter addCartPresenter = new AddCartPresenter(this);
                addCartPresenter.getAddCart(pid);
                break;
        }
    }
}
