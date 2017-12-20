package demo.bw.com.jingdong.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.zhy.autolayout.AutoLayoutActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import demo.bw.com.jingdong.Api.AddAddrActivityApi;
import demo.bw.com.jingdong.Api.AddSelectClickApi;
import demo.bw.com.jingdong.R;
import demo.bw.com.jingdong.adapter.AddressAdapter;
import demo.bw.com.jingdong.bean.getAddrsBean;
import demo.bw.com.jingdong.presenter.AddAddrPresenter;
import demo.bw.com.jingdong.presenter.getAddrsPresenter;

/**
 * 获取地址 管理地址
 */

public class AddressActivity extends AutoLayoutActivity implements AddAddrActivityApi,AddSelectClickApi   {


    @BindView(R.id.aa_back)
    ImageView aaBack;
    @BindView(R.id.aa_rv)
    RecyclerView aaRv;
    @BindView(R.id.aa_add)
    TextView aaAdd;
    private AddAddrPresenter ap;
    private AddressAdapter aa;
    private AlertDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);
        ButterKnife.bind(this);
        aaRv.setLayoutManager(new LinearLayoutManager(this));
        getAddrsPresenter gp=new getAddrsPresenter(this);
        gp.getAddrs();
    }


    @OnClick({R.id.aa_back, R.id.aa_add})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.aa_back:
                this.finish();
                break;
            case R.id.aa_add:
                ap = new AddAddrPresenter((AddAddrActivityApi) this);
                View view1= LayoutInflater.from(this).inflate(R.layout.alertdialog,null);
                TextView title=view1.findViewById(R.id.alert_title);
                final TextView addr=view1.findViewById(R.id.alert_addr);
                final TextView mob=view1.findViewById(R.id.alert_mob);
                final TextView name=view1.findViewById(R.id.alert_name);
                Button qd=view1.findViewById(R.id.alert_sure);
                Button qx=view1.findViewById(R.id.alert_qx);
                title.setText("添加地址");
                final AlertDialog.Builder alert=new AlertDialog.Builder(this);
                alert.setView(view1);
                qd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ap.getAddAddr(addr.getText().toString().trim(),mob.getText().toString().trim(),name.getText().toString().trim());
                         dialog.dismiss();
                    }
                });
                qx.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                dialog = alert.show();
                aa.notifyDataSetChanged();
                break;
        }
    }

    @Override
    public void showData(List<getAddrsBean.DataBean> list) {
        aa = new AddressAdapter(list,this,this);
        aaRv.setAdapter(aa);
}
    //提示添加失败或成功
    @Override
    public void addData(String str) {

    }

    @Override
    public void setAddr(String str) {
        Toast.makeText(getApplicationContext(),str,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void updateAddr(String str) {
        Toast.makeText(getApplicationContext(),str,Toast.LENGTH_SHORT).show();
    }


    @Override
    public void setOnClick(String name, String mob, String addr) {
        Intent intent2=new Intent(this,AplayActivity.class);
        Bundle bundle=new Bundle();
        bundle.putString("name",name);
        bundle.putString("mob",mob);
        bundle.putString("addr",addr);
        intent2.putExtras(bundle);
        AddressActivity.this.setResult(3,intent2);
        finish();
    }
}
