package demo.bw.com.jingdong.view.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.zhy.autolayout.AutoLayoutActivity;

import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import demo.bw.com.jingdong.Api.DefaultAddrApi;
import demo.bw.com.jingdong.R;
import demo.bw.com.jingdong.bean.GetDefaultBean;
import demo.bw.com.jingdong.bean.getAddrsBean;
import demo.bw.com.jingdong.presenter.getDefaultAddrPresenter;
import demo.bw.com.jingdong.utils.OrderUtils;
import demo.bw.com.jingdong.utils.PayResult;
import demo.bw.com.jingdong.view.MainActivity;

/**
 * 支付
 */
public class AplayActivity extends AutoLayoutActivity implements DefaultAddrApi {

    @BindView(R.id.ap_back)
    ImageView apBack;
    @BindView(R.id.ap_name)
    TextView apName;
    @BindView(R.id.ap_mob)
    TextView apMob;
    @BindView(R.id.ap_addr)
    TextView apAddr;
    @BindView(R.id.ap_selcetaddr)
    ImageView apSelcetaddr;
    @BindView(R.id.ap_add)
    ImageView apAdd;
    @BindView(R.id.ap_num)
    TextView apNum;
    @BindView(R.id.ap_lose)
    ImageView apLose;
    @BindView(R.id.ap_price)
    TextView apPrice;
    @BindView(R.id.ap_order)
    TextView apOrder;

    private int count=0;
    private double price;
    private Runnable payRunnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aplay);
        ButterKnife.bind(this);
        //点击放弃支付
        apBack.setOnClickListener(new View.OnClickListener() {

            private AlertDialog show;

            @Override
            public void onClick(View v) {
                AlertDialog.Builder alert=new AlertDialog.Builder(AplayActivity.this);
                alert.setTitle("确定要放弃付款吗?");
                alert.setMessage("未支付订单将在不久后自动关闭,请尽快支付");
                alert.setPositiveButton("暂时放弃", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        AplayActivity.this.finish();
                    }
                });
                alert.setNegativeButton("继续支付", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                         show.dismiss();
                    }
                });
                show = alert.show();

            }
        });
        Intent intent = getIntent();
        price = intent.getDoubleExtra("price", 20);
        String num = apNum.getText().toString().trim();
        count+= price *Integer.parseInt(num);
        apPrice.setText(count+"￥");
        getDefaultAddrPresenter gp = new getDefaultAddrPresenter(this);
        gp.getDefaultAddr();
        //支付宝支付
        final String orderInfo = getorderInfo();   // 订单信息
        payRunnable = new Runnable() {

             @Override
             public void run() {
                 PayTask alipay = new PayTask( AplayActivity.this);
                 Map<String, String> result = alipay.payV2(orderInfo, true);
                 Message msg = new Message();
                 msg.obj = result;
                 handler.sendMessage(msg);
             }
         };
    }



    @OnClick({R.id.ap_selcetaddr, R.id.ap_add, R.id.ap_lose, R.id.ap_order})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ap_selcetaddr:
                startActivityForResult(new Intent(this,AddressActivity.class),1);
                break;
            case R.id.ap_add:
                String num = apNum.getText().toString().trim();
                int i = Integer.parseInt(num);
                 ++i;
                 apNum.setText(i+"");
                count+=price;
                apPrice.setText(count+"￥");
                break;
            case R.id.ap_lose:
                String num1 = apNum.getText().toString().trim();
                int i1 = Integer.parseInt(num1);
                 i1--;
                 if(i1==0){
                     return;
                 }
                apNum.setText(i1+"");
                count-=price;
                apPrice.setText(count+"￥");
                break;
            case R.id.ap_order:
                Thread thread=new Thread(payRunnable);
                thread.start();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==3){
            Bundle bundle = data.getExtras();
            String name = bundle.getString("name");
            String mob = bundle.getString("mob");
            String addr = bundle.getString("addr");
            apName.setText("收货人:"+name);
            apMob.setText("电话"+mob);
            apAddr.setText("地址:"+addr);
        }

    }

    @Override
    public void getDefaultAddr(GetDefaultBean bean) {
        GetDefaultBean.DataBean data = bean.getData();
        apName.setText("收货人:"+data.getName());
        apMob.setText("电话"+data.getMobile());
        apAddr.setText("地址:"+data.getAddr());
    }
    public Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            PayResult payResult = new PayResult((Map<String, String>) msg.obj);

            /**
             对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
             */
            String resultInfo = payResult.getResult();// 同步返回需要验证的信息
            String resultStatus = payResult.getResultStatus();
            // 判断resultStatus 为9000则代表支付成功
            if (TextUtils.equals(resultStatus, "9000")) {
                // TODO 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
                Toast.makeText( AplayActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
            } else {
                //TODO  该笔订单真实的支付结果，需要依赖服务端的异步通知。
                Toast.makeText( AplayActivity.this, "支付失败", Toast.LENGTH_SHORT).show();
            }
        }
    };


    /**
     * 记得要看
     * 这里只是为了方便直接向商户展示支付宝的整个支付流程；所以Demo中加签过程直接放在客户端完成；
     * 真实App里，privateKey等数据严禁放在客户端，加签过程务必要放在服务端完成；
     * 防止商户私密数据泄露，造成不必要的资金损失，及面临各种安全风险；
     * <p>
     * orderInfo的获取必须来自服务端；
     */
    public String getorderInfo() {
        Map<String, String> params = OrderUtils.buildOrderParamMap(OrderUtils.APPID);
        String orderParam = OrderUtils.buildOrderParam(params);
        String sign = OrderUtils.getSign(params, OrderUtils.RSA_PRIVATE, false);
        final String orderInfo = orderParam + "&" + sign;
        return orderInfo;
    }

}
