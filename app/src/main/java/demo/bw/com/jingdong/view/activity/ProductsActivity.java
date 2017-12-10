package demo.bw.com.jingdong.view.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
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

public class ProductsActivity extends AppCompatActivity implements SearchDetailActivityApi {

    @BindView(R.id.cp_back)
    ImageView cpBack;
    @BindView(R.id.cp_img)
    SimpleDraweeView cpImg;
    @BindView(R.id.cp_title)
    TextView cpTitle;
    @BindView(R.id.cp_price)
    TextView cpPrice;
    @BindView(R.id.cp_cart)
    TextView cpCart;
    @BindView(R.id.cp_addcart)
    TextView cpAddcart;
   private String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        String pid = intent.getStringExtra("pid");
        id=pid;
        SearchDetailPresenter sp = new SearchDetailPresenter(this);
        sp.getSearchDetail(pid);
    }

    @Override
    public void showData(SearchDetailBean.DataBean list) {
        String[] split = list.getImages().split("\\|");
        String s = split[0];
        Uri uri = Uri.parse(s);
        cpImg.setImageURI(uri);
        cpPrice.setText("￥:"+list.getPrice());
        cpTitle.setText(list.getTitle());
    }

    @Override
    public void btnData(String str) {
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
    }

    @OnClick({R.id.cp_back, R.id.cp_cart, R.id.cp_addcart})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.cp_back:
                finish();
                break;
            case R.id.cp_cart:
                Intent intent=new Intent(this, MainActivity.class);
                intent.putExtra("flag",1);
                startActivity(intent);
                finish();
                break;
            case R.id.cp_addcart:
                AddCartPresenter addCartPresenter = new AddCartPresenter(this);
                addCartPresenter.getAddCart(id);
                break;
        }
    }
}
